/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.core.items;

import java.util.List;
import javax.annotation.Nullable;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemOilCanister extends ItemCanisterGeneric implements ISortableItem
{

    public ItemOilCanister(String assetName)
    {
        super(assetName);
        this.setAllowedFluid(ConfigManagerCore.useOldOilFluidID ? "oilgc" : "oil");
        this.setContainerItem(this);
    }

    @Override
    public String getTranslationKey(ItemStack itemStack)
    {
        if (itemStack.getMaxDamage() - itemStack.getItemDamage() == 0)
        {
            return "item.empty_liquid_canister";
        }

        if (itemStack.getItemDamage() == 1)
        {
            return "item.oil_canister";
        }

        return "item.oil_canister_partial";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage() > 0)
        {
            tooltip.add(GCCoreUtil.translate("gui.message.oil.name") + ": " + (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage()));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list)
    {
        if (tab == GalacticraftCore.galacticraftItemsTab || tab == CreativeTabs.SEARCH)
        {
            list.add(new ItemStack(this, 1, this.getMaxDamage()));
            list.add(new ItemStack(this, 1, 1));
        }
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if (ItemCanisterGeneric.EMPTY == par1ItemStack.getItemDamage())
        {
            par1ItemStack.setTagCompound(null);
        } else if (par1ItemStack.getItemDamage() <= 0)
        {
            par1ItemStack.setItemDamage(1);
        }
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta)
    {
        return EnumSortCategoryItem.CANISTER;
    }
}
