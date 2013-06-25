package micdoodle8.mods.galacticraft.core.wgen;

import java.util.Random;
import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityLandingPad;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.multiblock.IMultiBlock;

public class GCCoreWorldGenSpaceStation extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, int i, int j, int k)
    {
        world.setBlock(i + 0, j + 0, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 0, j + 0, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 0, j + 0, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 0, j + 0, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 0, j + 0, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 0, j + 0, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 0, j + 0, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 0, j + 1, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 0, j + 1, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 0, j + 1, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 0, j + 1, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 0, j + 1, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 0, j + 1, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 0, j + 1, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 1, j + 0, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 1, j + 0, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 1, j + 0, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 1, j + 0, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 1, j + 0, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 1, j + 0, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 1, j + 0, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 1, j + 1, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 1, j + 1, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 2, j + 0, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 2, j + 0, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 2, j + 0, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 2, j + 0, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 2, j + 0, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 2, j + 0, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 2, j + 0, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 2, j + 1, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 2, j + 1, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 3, j + 0, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 3, j + 0, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 3, j + 0, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 3, j + 0, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 3, j + 0, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 3, j + 0, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 3, j + 0, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 3, j + 1, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);

        world.setBlock(i + 3, j + 1, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 4, j + 0, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 4, j + 0, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 4, j + 0, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 4, j + 0, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 4, j + 0, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 4, j + 0, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 4, j + 0, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 4, j + 1, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 4, j + 1, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 5, j + 0, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 5, j + 0, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 5, j + 0, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 5, j + 0, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 5, j + 0, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 5, j + 0, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 5, j + 0, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 5, j + 1, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 5, j + 1, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 1, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 1, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 1, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 1, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 1, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 1, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 1, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 2, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 2, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 3, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 3, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 4, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 4, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 5, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 5, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 5, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 5, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 5, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 5, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 6, j + 5, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 1, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 1, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 1, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 1, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 1, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 1, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 1, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 2, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 2, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 2, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 2, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 3, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 3, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 3, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 3, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 4, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 4, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 4, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 4, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 5, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 5, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 5, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 5, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 5, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 5, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 5, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 6, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 6, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 6, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 6, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 6, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 6, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 7, j + 6, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 1, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 1, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 1, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 1, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 1, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 1, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 1, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 2, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 2, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 3, k + 0, 102, 0, 3);
        world.setBlock(i + 8, j + 3, k + 6, 102, 0, 3);
        world.setBlock(i + 8, j + 4, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 4, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 5, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 5, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 6, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 6, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 6, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 6, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 6, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 6, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 8, j + 6, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 1, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 1, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 1, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 1, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 1, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 1, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 1, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 5, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 5, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 6, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 6, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 6, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 6, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 6, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 6, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 9, j + 6, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 1, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 1, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 1, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 1, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 1, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 1, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 1, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);

        // world.setBlock(i + 10, j + 2, k + 3, 3, 0, 3);

        world.setBlock(i + 10, j + 5, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 5, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 6, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 6, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 6, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 6, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 6, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 6, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 10, j + 6, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 1, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 1, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 1, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 1, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 1, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 1, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 1, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 5, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 5, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 6, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 6, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 6, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 6, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 6, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 6, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 11, j + 6, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 1, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 1, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 1, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 1, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 1, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 1, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 1, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 2, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 2, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 3, k + 0, 102, 0, 3);
        world.setBlock(i + 12, j + 3, k + 6, 102, 0, 3);
        world.setBlock(i + 12, j + 4, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 4, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 5, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 5, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 6, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 6, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 6, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 6, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 6, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 6, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 12, j + 6, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 1, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 1, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 1, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 1, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 1, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 1, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 1, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 2, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 2, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 2, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 2, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 3, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 3, k + 1, 102, 0, 3);
        world.setBlock(i + 13, j + 3, k + 5, 102, 0, 3);
        world.setBlock(i + 13, j + 3, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 4, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 4, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 4, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 4, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 5, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 5, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 5, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 5, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 5, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 5, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 5, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 6, k + 0, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 6, k + 1, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 6, k + 2, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 6, k + 3, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 6, k + 4, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 6, k + 5, GCCoreBlocks.decorationBlocks.blockID, 4, 3);
        world.setBlock(i + 13, j + 6, k + 6, GCCoreBlocks.decorationBlocks.blockID, 4, 3);

        return true;
    }
}
