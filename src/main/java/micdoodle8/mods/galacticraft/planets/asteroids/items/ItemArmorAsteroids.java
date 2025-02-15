/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.planets.asteroids.items;

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

public class ItemArmorAsteroids extends ItemArmor implements ISortableItem, GCRarity
{

    public ItemArmorAsteroids(EntityEquipmentSlot armorIndex, String assetSuffix)
    {
        super(AsteroidsItems.ARMOR_TITANIUM, 0, armorIndex);
        this.setTranslationKey("titanium_" + assetSuffix);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return GalacticraftCore.galacticraftItemsTab;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        if (this.getArmorMaterial() == AsteroidsItems.ARMOR_TITANIUM)
        {
            if (stack.getItem() == AsteroidsItems.titaniumHelmet)
            {
                return GalacticraftPlanets.TEXTURE_PREFIX + "textures/model/armor/titanium_1.png";
            } else if (stack.getItem() == AsteroidsItems.titaniumChestplate || stack.getItem() == AsteroidsItems.titaniumBoots)
            {
                return GalacticraftPlanets.TEXTURE_PREFIX + "textures/model/armor/titanium_2.png";
            } else if (stack.getItem() == AsteroidsItems.titaniumLeggings)
            {
                return GalacticraftPlanets.TEXTURE_PREFIX + "textures/model/armor/titanium_3.png";
            }
        }

        return null;
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta)
    {
        return EnumSortCategoryItem.ARMOR;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return repair.getItem() == AsteroidsItems.basicItem && repair.getItemDamage() == 6;
    }
}
