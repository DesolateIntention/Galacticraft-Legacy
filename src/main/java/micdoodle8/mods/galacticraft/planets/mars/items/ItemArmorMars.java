/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.planets.mars.items;

import micdoodle8.mods.galacticraft.api.item.GCRarity;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import micdoodle8.mods.galacticraft.planets.GalacticraftPlanets;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorMars extends ItemArmor implements ISortableItem, GCRarity
{

    private final ArmorMaterial material;

    public ItemArmorMars(ArmorMaterial par2EnumArmorMaterial, int par3, EntityEquipmentSlot par4)
    {
        super(par2EnumArmorMaterial, par3, par4);
        this.material = par2EnumArmorMaterial;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        if (this.material == MarsItems.ARMORDESH)
        {
            if (stack.getItem() == MarsItems.deshHelmet)
            {
                return GalacticraftPlanets.TEXTURE_PREFIX + "textures/model/armor/desh_1.png";
            } else if (stack.getItem() == MarsItems.deshChestplate || stack.getItem() == MarsItems.deshBoots)
            {
                return GalacticraftPlanets.TEXTURE_PREFIX + "textures/model/armor/desh_2.png";
            } else if (stack.getItem() == MarsItems.deshLeggings)
            {
                return GalacticraftPlanets.TEXTURE_PREFIX + "textures/model/armor/desh_3.png";
            }
        }

        return null;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public CreativeTabs getCreativeTab()
    {
        return GalacticraftCore.galacticraftItemsTab;
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta)
    {
        return EnumSortCategoryItem.ARMOR;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return repair.getItem() == MarsItems.marsItemBasic && repair.getItemDamage() == 2;
    }
}
