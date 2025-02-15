/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.core.tile;

import com.mojang.authlib.GameProfile;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import micdoodle8.mods.galacticraft.api.entity.ITelemetry;
import micdoodle8.mods.galacticraft.api.prefab.entity.EntitySpaceshipBase;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3Dim;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityTelemetry extends TileEntity implements ITickable
{

    public Class<?> clientClass;
    public int[] clientData =
    {-1};
    public String clientName;
    public GameProfile clientGameProfile = null;

    public static HashSet<BlockVec3Dim> loadedList = new HashSet<BlockVec3Dim>();
    public Entity linkedEntity;
    private UUID toUpdate = null;
    private int pulseRate = 400;
    private int lastHurttime = 0;
    private int ticks = 0;

    @Override
    public void onLoad()
    {
        if (this.world.isRemote)
        {
            loadedList.add(new BlockVec3Dim(this));
        }
    }

    @Override
    public void invalidate()
    {
        super.invalidate();
        if (this.world.isRemote)
        {
            loadedList.remove(new BlockVec3Dim(this));
        }
    }

    @Override
    public void update()
    {
        if (!this.world.isRemote && ++this.ticks % 2 == 0)
        {
            if (this.toUpdate != null)
            {
                this.addTrackedEntity(this.toUpdate);
                this.toUpdate = null;
            }

            String name = null;
            int[] data =
            {-1, -1, -1, -1, -1};
            String strUUID = "";

            if (linkedEntity != null)
            {
                // Help the Garbage Collector
                if (linkedEntity.isDead)
                {
                    linkedEntity = null;
                    name = "";
                    // TODO: track players after death and respawn? or not?
                } else
                {
                    if (linkedEntity instanceof EntityPlayerMP)
                    {
                        name = "$" + linkedEntity.getName();
                    } else
                    {
                        EntityEntry entityEntry = EntityRegistry.getEntry(linkedEntity.getClass());
                        if (entityEntry != null && entityEntry.getRegistryName() != null)
                        {
                            name = entityEntry.getRegistryName().toString();
                        }
                    }

                    if (name == null)
                    {
                        GalacticraftCore.logger.info("Telemetry Unit: Error finding name for " + linkedEntity.getClass().getSimpleName());
                        name = "";
                    }

                    double xmotion = linkedEntity.motionX;
                    double ymotion = linkedEntity instanceof EntityLivingBase ? linkedEntity.motionY + 0.078D : linkedEntity.motionY;
                    double zmotion = linkedEntity.motionZ;
                    data[2] = (int) (MathHelper.sqrt(xmotion * xmotion + ymotion * ymotion + zmotion * zmotion) * 2000D);

                    if (linkedEntity instanceof ITelemetry)
                    {
                        ((ITelemetry) linkedEntity).transmitData(data);
                    } else if (linkedEntity instanceof EntityLivingBase)
                    {
                        EntityLivingBase eLiving = (EntityLivingBase) linkedEntity;
                        data[0] = eLiving.hurtTime;

                        // Calculate a "pulse rate" based on motion and taking
                        // damage
                        this.pulseRate--;
                        if (eLiving.hurtTime > this.lastHurttime)
                        {
                            this.pulseRate += 100;
                        }
                        this.lastHurttime = eLiving.hurtTime;
                        if (eLiving.getRidingEntity() != null)
                        {
                            data[2] /= 4; // reduced pulse effect if riding a
                                          // vehicle
                        } else if (data[2] > 1)
                        {
                            this.pulseRate += 2;
                        }
                        this.pulseRate += Math.max(data[2] - pulseRate, 0) / 4;
                        if (this.pulseRate > 2000)
                        {
                            this.pulseRate = 2000;
                        }
                        if (this.pulseRate < 400)
                        {
                            this.pulseRate = 400;
                        }
                        data[2] = this.pulseRate / 10;

                        data[1] = (int) (eLiving.getHealth() * 100 / eLiving.getMaxHealth());
                        if (eLiving instanceof EntityPlayerMP)
                        {
                            data[3] = ((EntityPlayerMP) eLiving).getFoodStats().getFoodLevel() * 5;
                            GCPlayerStats stats = GCPlayerStats.get(eLiving);
                            data[4] = stats.getAirRemaining() * 4096 + stats.getAirRemaining2();
                            UUID uuid = ((EntityPlayerMP) eLiving).getUniqueID();
                            if (uuid != null)
                            {
                                strUUID = uuid.toString();
                            }
                        } else if (eLiving instanceof EntityHorse)
                        {
//                            data[3] = ((EntityHorse) eLiving).getType().ordinal();
                            data[4] = ((EntityHorse) eLiving).getHorseVariant();
                        } else if (eLiving instanceof EntityVillager)
                        {
                            data[3] = ((EntityVillager) eLiving).getProfession();
                            data[4] = ((EntityVillager) eLiving).getGrowingAge();
                        } else if (eLiving instanceof EntityWolf)
                        {
                            data[3] = ((EntityWolf) eLiving).getCollarColor().getDyeDamage();
                            data[4] = ((EntityWolf) eLiving).isBegging() ? 1 : 0;
                        } else if (eLiving instanceof EntitySheep)
                        {
                            data[3] = ((EntitySheep) eLiving).getFleeceColor().getDyeDamage();
                            data[4] = ((EntitySheep) eLiving).getSheared() ? 1 : 0;
                        } else if (eLiving instanceof EntityOcelot)
                        {
                            data[3] = ((EntityOcelot) eLiving).getTameSkin();
                        } else if (eLiving instanceof EntitySkeleton)
                        {
//                            data[3] = ((EntitySkeleton) eLiving).getSkeletonType().ordinal();
                        } else if (eLiving instanceof EntityZombie)
                        {
//                            data[3] = ((EntityZombie) eLiving).isVillager() ? 1 : 0; TODO Fix for MC 1.10
                            data[4] = ((EntityZombie) eLiving).isChild() ? 1 : 0;
                        }
                    }
                }
            } else
            {
                name = "";
            }
            GalacticraftCore.packetPipeline.sendToAllAround(new PacketSimple(EnumSimplePacket.C_UPDATE_TELEMETRY, this.world.provider.getDimension(), new Object[]
            {this.getPos(), name, data[0], data[1], data[2], data[3], data[4], strUUID}),
                new TargetPoint(this.world.provider.getDimension(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 320D));
        }
    }

    @SideOnly(Side.CLIENT)
    public void receiveUpdate(List<Object> data, int dimID)
    {
        String name = (String) data.get(1);
        if (name.startsWith("$"))
        {
            // It's a player name
            this.clientClass = EntityPlayerMP.class;
            String strName = name.substring(1);
            this.clientName = strName;
            this.clientGameProfile = PlayerUtil.getSkinForName(strName, (String) data.get(7), dimID);
        } else
        {
            EntityEntry entityEntry = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(name));
            this.clientClass = entityEntry == null ? null : entityEntry.getEntityClass();
        }
        this.clientData = new int[5];
        for (int i = 2; i < 7; i++)
        {
            this.clientData[i - 2] = (Integer) data.get(i);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        Long msb = nbt.getLong("entityUUIDMost");
        Long lsb = nbt.getLong("entityUUIDLeast");
        this.toUpdate = new UUID(msb, lsb);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        if (this.linkedEntity != null && !this.linkedEntity.isDead)
        {
            nbt.setLong("entityUUIDMost", this.linkedEntity.getUniqueID().getMostSignificantBits());
            nbt.setLong("entityUUIDLeast", this.linkedEntity.getUniqueID().getLeastSignificantBits());
        }
        return nbt;
    }

    public void addTrackedEntity(UUID uuid)
    {
        this.pulseRate = 400;
        this.lastHurttime = 0;
        List<Entity> eList = this.world.loadedEntityList;
        for (Entity e : eList)
        {
            if (e.getUniqueID().equals(uuid))
            {
                this.linkedEntity = e;
                if (e instanceof EntitySpaceshipBase)
                {
                    ((EntitySpaceshipBase) e).addTelemetry(this);
                }
                return;
            }
        }
        // TODO Add some kind of watcher to add the entity when next loaded
        this.linkedEntity = null;
    }

    public void addTrackedEntity(Entity e)
    {
        this.pulseRate = 400;
        this.lastHurttime = 0;
        this.linkedEntity = e;
        if (e instanceof EntitySpaceshipBase)
        {
            ((EntitySpaceshipBase) e).addTelemetry(this);
        }
    }

    public void removeTrackedEntity()
    {
        this.pulseRate = 400;
        this.linkedEntity = null;
    }

    public static TileEntityTelemetry getNearest(TileEntity te)
    {
        if (te == null)
        {
            return null;
        }
        BlockVec3 target = new BlockVec3(te);

        int distSq = 1025;
        BlockVec3Dim nearest = null;
        int dim = GCCoreUtil.getDimensionID(te.getWorld());
        for (BlockVec3Dim telemeter : loadedList)
        {
            if (telemeter.dim != dim)
            {
                continue;
            }
            int dist = telemeter.distanceSquared(target);
            if (dist < distSq)
            {
                distSq = dist;
                nearest = telemeter;
            }
        }

        if (nearest == null)
        {
            return null;
        }
        TileEntity result = te.getWorld().getTileEntity(new BlockPos(nearest.x, nearest.y, nearest.z));
        if (result instanceof TileEntityTelemetry)
        {
            return (TileEntityTelemetry) result;
        }
        return null;
    }

    /**
     * Call this when a player wears a frequency module to check whether it has
     * been linked with a Telemetry Unit.
     *
     * @param held The frequency module
     * @param player
     */
    public static void frequencyModulePlayer(ItemStack held, EntityPlayerMP player, boolean remove)
    {
        if (held == null)
        {
            return;
        }
        NBTTagCompound fmData = held.getTagCompound();
        if (fmData != null && fmData.hasKey("teDim"))
        {
            int dim = fmData.getInteger("teDim");
            int x = fmData.getInteger("teCoordX");
            int y = fmData.getInteger("teCoordY");
            int z = fmData.getInteger("teCoordZ");
            WorldProvider wp = WorldUtil.getProviderForDimensionServer(dim);
            // TODO
            if (wp == null || wp.world == null)
            {
                GalacticraftCore.logger.debug("Frequency module worn: world provider is null.  This is a bug. " + dim);
            } else
            {
                TileEntity te = wp.world.getTileEntity(new BlockPos(x, y, z));
                if (te instanceof TileEntityTelemetry)
                {
                    if (remove)
                    {
                        if (((TileEntityTelemetry) te).linkedEntity == player)
                            ((TileEntityTelemetry) te).removeTrackedEntity();
                    } else
                    {
                        ((TileEntityTelemetry) te).addTrackedEntity(player.getUniqueID());
                    }
                }
            }
        }
    }

    public static void updateLinkedPlayer(EntityPlayerMP playerOld, EntityPlayerMP playerNew)
    {
        for (BlockVec3Dim telemeter : loadedList)
        {
            TileEntity te = telemeter.getTileEntityNoLoad();
            if (te instanceof TileEntityTelemetry)
            {
                if (((TileEntityTelemetry) te).linkedEntity == playerOld)
                {
                    ((TileEntityTelemetry) te).linkedEntity = playerNew;
                }
            }
        }
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
    {
        return oldState.getBlock() != newSate.getBlock();
    }
}
