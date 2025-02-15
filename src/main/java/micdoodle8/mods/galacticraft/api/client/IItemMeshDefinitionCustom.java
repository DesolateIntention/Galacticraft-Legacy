/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.api.client;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

public interface IItemMeshDefinitionCustom extends ItemMeshDefinition
{

    static ItemMeshDefinition create(IItemMeshDefinitionCustom lambda)
    {
        return lambda;
    }

    ModelResourceLocation getLocation(ItemStack stack);

    @Override
    default ModelResourceLocation getModelLocation(ItemStack stack)
    {
        return getLocation(stack);
    }
}
