/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.core.event;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EventLandingPadRemoval extends Event
{

    public boolean allow = true;
    public final BlockPos pos;
    public final World world;

    public EventLandingPadRemoval(World world, BlockPos pos)
    {
        this.world = world;
        this.pos = pos;
    }
}
