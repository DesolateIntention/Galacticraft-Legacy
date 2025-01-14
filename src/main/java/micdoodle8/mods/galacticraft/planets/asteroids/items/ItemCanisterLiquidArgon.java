/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.planets.asteroids.items;

import java.util.List;
import javax.annotation.Nullable;
import micdoodle8.mods.galacticraft.core.items.ItemCanisterGeneric;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCanisterLiquidArgon extends ItemCanisterGeneric
{
//    protected IIcon[] icons = new IIcon[7];

    public ItemCanisterLiquidArgon(String assetName)
    {
        super(assetName);
        this.setAllowedFluid("liquidargon");
        // this.setTextureName(GalacticraftPlanets.TEXTURE_PREFIX + assetName);
    }

    /*
     * @Override
     * @SideOnly(Side.CLIENT) public void registerIcons(IIconRegister
     * iconRegister) { for (int i = 0; i < this.icons.length; i++) {
     * this.icons[i] = iconRegister.registerIcon(this.getIconString() + "_" +
     * i); } }
     */

    @Override
    public String getTranslationKey(ItemStack itemStack)
    {
        if (itemStack.getMaxDamage() - itemStack.getItemDamage() == 0)
        {
            return "item.emptyGasCanister";
        }

        if (itemStack.getItemDamage() == 1)
        {
            return "item.canister.liquidArgon.full";
        }

        return "item.canister.liquidArgon.partial";
    }

    /*
     * @Override public IIcon getIconFromDamage(int par1) { final int damage = 6
     * * par1 / this.getMaxDamage(); if (this.icons.length > damage) { return
     * this.icons[this.icons.length - damage - 1]; } return
     * super.getIconFromDamage(damage); }
     */

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage() > 0)
        {
            tooltip.add(GCCoreUtil.translate("item.canister.liquid_argon.name") + ": " + (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage()));
        }
    }
}
