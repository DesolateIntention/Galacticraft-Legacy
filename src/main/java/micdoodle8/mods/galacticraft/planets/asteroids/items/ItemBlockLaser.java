/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.planets.asteroids.items;

import java.util.List;
import javax.annotation.Nullable;
import micdoodle8.mods.galacticraft.core.items.ItemBlockDesc;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBlockLaser extends ItemBlockDesc
{

    public ItemBlockLaser(Block block)
    {
        super(block);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> info, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, info, flagIn);

        info.add(TextFormatting.DARK_RED + "BETA Testing Phase");
        info.add(TextFormatting.DARK_RED + "Please Report Bugs");
    }
}
