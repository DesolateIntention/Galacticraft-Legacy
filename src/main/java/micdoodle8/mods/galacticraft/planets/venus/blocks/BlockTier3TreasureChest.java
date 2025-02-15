/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.planets.venus.blocks;

import micdoodle8.mods.galacticraft.core.blocks.BlockTier1TreasureChest;
import micdoodle8.mods.galacticraft.planets.venus.tile.TileEntityTreasureChestVenus;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTier3TreasureChest extends BlockTier1TreasureChest
{

    public BlockTier3TreasureChest(String assetName)
    {
        super(assetName);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityTreasureChestVenus();
    }
}
