/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.planets.mars.blocks;

import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.blocks.BlockMulti;
import micdoodle8.mods.galacticraft.core.blocks.BlockTileGC;
import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.items.IShiftDescription;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import micdoodle8.mods.galacticraft.core.world.IChunkLoader;
import micdoodle8.mods.galacticraft.planets.GalacticraftPlanets;
import micdoodle8.mods.galacticraft.planets.GuiIdsPlanets;
import micdoodle8.mods.galacticraft.planets.mars.ConfigManagerMars;
import micdoodle8.mods.galacticraft.planets.mars.tile.TileEntityCryogenicChamber;
import micdoodle8.mods.galacticraft.planets.mars.tile.TileEntityLaunchController;
import micdoodle8.mods.galacticraft.planets.mars.tile.TileEntityTerraformer;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Type;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockMachineMars extends BlockTileGC implements IShiftDescription, ISortableBlock, IPartialSealableBlock
{

    public static final int TERRAFORMER_METADATA = 0;
    public static final int CRYOGENIC_CHAMBER_METADATA = 4;
    public static final int LAUNCH_CONTROLLER_METADATA = 8;

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyEnum<EnumMachineType> TYPE = PropertyEnum.create("type", EnumMachineType.class);

    public enum EnumMachineType implements IStringSerializable
    {

        TERRAFORMER(0, "terraformer"), CRYOGENIC_CHAMBER(1, "cryogenic_chamber"), LAUNCH_CONTROLLER(2, "launch_controller");

        private final int meta;
        private final String name;

        EnumMachineType(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
        }

        public int getMeta()
        {
            return this.meta;
        }

        public static EnumMachineType byMetadata(int meta)
        {
            return values()[meta % 3];
        }

        @Override
        public String getName()
        {
            return this.name;
        }
    }

    public BlockMachineMars(String assetName)
    {
        super(GCBlocks.machine);
        this.setSoundType(SoundType.METAL);
        this.setTranslationKey(assetName);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        final TileEntity var9 = worldIn.getTileEntity(pos);

        if (var9 instanceof IMultiBlock)
        {
            ((IMultiBlock) var9).onDestroy(var9);
        }

        super.breakBlock(worldIn, pos, state);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public CreativeTabs getCreativeTab()
    {
        return GalacticraftCore.galacticraftBlocksTab;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        int metadata = getMetaFromState(state);

        final int angle = MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int change = EnumFacing.byHorizontalIndex(angle).getOpposite().getHorizontalIndex();

        worldIn.setBlockState(pos, getStateFromMeta((metadata & 12) + change), 3);

        switch (metadata & 12)
        {

            case BlockMachineMars.CRYOGENIC_CHAMBER_METADATA:
                BlockMulti.onPlacement(worldIn, pos, placer, this);
                break;

            case BlockMachineMars.LAUNCH_CONTROLLER_METADATA:
                WorldUtil.markAdjacentPadForUpdate(worldIn, pos);
                TileEntity var8 = worldIn.getTileEntity(pos);
                if (var8 instanceof IChunkLoader && !worldIn.isRemote && ConfigManagerMars.launchControllerChunkLoad && placer instanceof EntityPlayer)
                {
                    ((IChunkLoader) var8).setOwnerName(placer.getName());
                    ((IChunkLoader) var8).onTicketLoaded(ForgeChunkManager.requestTicket(GalacticraftCore.instance, var8.getWorld(), Type.NORMAL), true);
                } else if (var8 instanceof TileEntityLaunchController && placer instanceof EntityPlayer)
                {
                    ((TileEntityLaunchController) var8).setOwnerName(placer.getName());
                }
                break;
        }
    }

    @Override
    public boolean onMachineActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        EnumMachineType type = state.getValue(TYPE);
        if (type == EnumMachineType.LAUNCH_CONTROLLER)
        {
            playerIn.openGui(GalacticraftPlanets.instance, GuiIdsPlanets.MACHINE_MARS, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        } else if (type == EnumMachineType.CRYOGENIC_CHAMBER)
        {
            ((IMultiBlock) worldIn.getTileEntity(pos)).onActivated(playerIn);
            return true;
        } else
        {
            playerIn.openGui(GalacticraftPlanets.instance, GuiIdsPlanets.MACHINE_MARS, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        EnumMachineType type = state.getValue(TYPE);
        return type == EnumMachineType.CRYOGENIC_CHAMBER ? BlockFaceShape.UNDEFINED : BlockFaceShape.SOLID;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        EnumMachineType type = state.getValue(TYPE);
        if (type == EnumMachineType.LAUNCH_CONTROLLER)
        {
            return new TileEntityLaunchController();
        }
        if (type == EnumMachineType.CRYOGENIC_CHAMBER)
        {
            return new TileEntityCryogenicChamber();
        } else
        {
            return new TileEntityTerraformer();
        }
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        if (getMetaFromState(state) >= BlockMachineMars.LAUNCH_CONTROLLER_METADATA)
        {
            WorldUtil.markAdjacentPadForUpdate(world, pos);
        }

        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    public ItemStack getTerraformer()
    {
        return new ItemStack(this, 1, BlockMachineMars.TERRAFORMER_METADATA);
    }

    public ItemStack getChamber()
    {
        return new ItemStack(this, 1, BlockMachineMars.CRYOGENIC_CHAMBER_METADATA);
    }

    public ItemStack getLaunchController()
    {
        return new ItemStack(this, 1, BlockMachineMars.LAUNCH_CONTROLLER_METADATA);
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list)
    {
        list.add(this.getTerraformer());
        list.add(this.getChamber());
        list.add(this.getLaunchController());
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        EnumMachineType type = state.getValue(TYPE);
        if (type == EnumMachineType.LAUNCH_CONTROLLER)
        {
            return BlockMachineMars.LAUNCH_CONTROLLER_METADATA;
        } else if (type == EnumMachineType.CRYOGENIC_CHAMBER)
        {
            return BlockMachineMars.CRYOGENIC_CHAMBER_METADATA;
        } else
        {
            return BlockMachineMars.TERRAFORMER_METADATA;
        }
    }

    @Override
    public boolean isBed(IBlockState state, IBlockAccess world, BlockPos pos, Entity player)
    {
        return state.getValue(TYPE) == EnumMachineType.CRYOGENIC_CHAMBER;
    }

    @Override
    public BlockPos getBedSpawnPosition(IBlockState state, IBlockAccess world, BlockPos pos, EntityPlayer player)
    {
        return pos.up();
    }

    @Override
    public void setBedOccupied(IBlockAccess world, BlockPos pos, EntityPlayer player, boolean occupied)
    {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityCryogenicChamber)
        {
            ((TileEntityCryogenicChamber) tile).isOccupied = occupied;
        }
    }

    @Override
    public EnumFacing getBedDirection(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return state.getValue(FACING);
    }

    @Override
    public String getShiftDescription(int meta)
    {
        switch (meta)
        {
            case CRYOGENIC_CHAMBER_METADATA:
                return GCCoreUtil.translate("tile.cryo_chamber.description");
            case LAUNCH_CONTROLLER_METADATA:
                return GCCoreUtil.translate("tile.launch_controller.description");
            case TERRAFORMER_METADATA:
                return GCCoreUtil.translate("tile.terraformer.description");
        }
        return "";
    }

    @Override
    public boolean showDescription(int meta)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return true;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.byHorizontalIndex(meta % 4);
        EnumMachineType type = EnumMachineType.byMetadata((int) Math.floor(meta / 4.0));
        return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(TYPE, type);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(FACING)).getHorizontalIndex() + state.getValue(TYPE).getMeta() * 4;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING, TYPE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand)
    {
        if (state.getValue(TYPE) == EnumMachineType.CRYOGENIC_CHAMBER)
        {
            GalacticraftPlanets.spawnParticle("cryoFreeze", new Vector3(pos.getX() + 0.3 + rand.nextDouble() * 0.4, pos.getY(), pos.getZ() + 0.3 + rand.nextDouble() * 0.4),
                new Vector3(0.0, 0.05 + rand.nextDouble() * 0.01, 0.0));
            GalacticraftPlanets.spawnParticle("cryoFreeze", new Vector3(pos.getX() + 0.3 + rand.nextDouble() * 0.4, pos.getY(), pos.getZ() + 0.3 + rand.nextDouble() * 0.4),
                new Vector3(0.0, 0.05 + rand.nextDouble() * 0.01, 0.0));
            GalacticraftPlanets.spawnParticle("cryoFreeze", new Vector3(pos.getX() + 0.3 + rand.nextDouble() * 0.4, pos.getY(), pos.getZ() + 0.3 + rand.nextDouble() * 0.4),
                new Vector3(0.0, 0.05 + rand.nextDouble() * 0.01, 0.0));

            GalacticraftPlanets.spawnParticle("cryoFreeze", new Vector3(pos.getX() + 0.3 + rand.nextDouble() * 0.4, pos.getY() + 2.9F, pos.getZ() + 0.3 + rand.nextDouble() * 0.4),
                new Vector3(0.0, -0.05 - rand.nextDouble() * 0.01, 0.0));
            GalacticraftPlanets.spawnParticle("cryoFreeze", new Vector3(pos.getX() + 0.3 + rand.nextDouble() * 0.4, pos.getY() + 2.9F, pos.getZ() + 0.3 + rand.nextDouble() * 0.4),
                new Vector3(0.0, -0.05 - rand.nextDouble() * 0.01, 0.0));
            GalacticraftPlanets.spawnParticle("cryoFreeze", new Vector3(pos.getX() + 0.3 + rand.nextDouble() * 0.4, pos.getY() + 2.9F, pos.getZ() + 0.3 + rand.nextDouble() * 0.4),
                new Vector3(0.0, -0.05 - rand.nextDouble() * 0.01, 0.0));
        }
    }

    @Override
    public EnumSortCategoryBlock getCategory(int meta)
    {
        return EnumSortCategoryBlock.MACHINE;
    }

    @Override
    public boolean isSealed(World world, BlockPos pos, EnumFacing direction)
    {
        return world.getBlockState(pos).getValue(TYPE) != EnumMachineType.CRYOGENIC_CHAMBER;
    }
}
