/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.api.power;

public interface IEnergyHandlerGC
{

    /**
     * Add energy from an external source
     *
     * @param from Energy Source that is providing power
     * @param amount Maximum amount of energy to receive
     * @param simulate If true, the transfer will only be simulated.
     * @return The amount of energy that was successfully received (or would
     *         have been, if simulated).
     */
    float receiveEnergyGC(EnergySource from, float amount, boolean simulate);

    /**
     * Remove energy, transferring it to an external source
     *
     * @param from Energy Source that is extracting power
     * @param amount Maximum amount of energy to extract
     * @param simulate If true, the transfer will only be simulated.
     * @return The amount of energy that was successfully extracted (or would
     *         have been, if simulated).
     */
    float extractEnergyGC(EnergySource from, float amount, boolean simulate);

    /**
     * Returns true if the handler can interface with the provided energy source
     */
    boolean nodeAvailable(EnergySource from);

    /**
     * Returns the amount of energy stored in this handler available to the
     * provided source
     */
    float getEnergyStoredGC(EnergySource from);

    /**
     * Returns the maximum amount of energy stored in this handler available to
     * the provided source
     */
    float getMaxEnergyStoredGC(EnergySource from);
}
