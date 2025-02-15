/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.api.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Implement into Armor items
 */
public interface IBreathableArmor
{

    enum EnumGearType
    {
        HELMET, GEAR, TANK1, TANK2
    }

    /**
     * @param gearType The gear type to be handled
     * @return true if the passed gear type should be handled by this armor item
     */
    boolean handleGearType(EnumGearType gearType);

    /**
     * Determines if armor item is currently valid for breathing
     *
     * @param helmetInSlot The armor itemstack
     * @param playerWearing The player wearing the armor
     * @param type The oxygen gear type @see EnumGearType
     * @return true if this armor item is valid for the provided oxygen gear
     *         type
     */
    boolean canBreathe(ItemStack helmetInSlot, EntityPlayer playerWearing, EnumGearType type);
}
