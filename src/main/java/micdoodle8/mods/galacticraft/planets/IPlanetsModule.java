package micdoodle8.mods.galacticraft.planets;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

public interface IPlanetsModule
{

    public void preInit(FMLPreInitializationEvent event);

    public void init(FMLInitializationEvent event);

    public void postInit(FMLPostInitializationEvent event);

    public void serverInit(FMLServerStartedEvent event);

    public void serverStarting(FMLServerStartingEvent event);

    public void getGuiIDs(List<Integer> idList);

    public Object getGuiElement(Side side, int ID, EntityPlayer player, World world, int x, int y, int z);

    public Configuration getConfiguration();

    public void syncConfig();
}
