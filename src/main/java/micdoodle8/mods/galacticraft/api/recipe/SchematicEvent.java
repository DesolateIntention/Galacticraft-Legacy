/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.api.recipe;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * These events are used internally to perform actions when Galacticraft is
 * installed, without needing to include unnecessary classes. There is no need
 * to subscribe to these events.
 */
public abstract class SchematicEvent extends Event
{

    public ISchematicPage page;

    public SchematicEvent(ISchematicPage page)
    {
        this.page = page;
    }

    public static class Unlock extends SchematicEvent
    {

        public EntityPlayerMP player;

        public Unlock(EntityPlayerMP player, ISchematicPage page)
        {
            super(page);
            this.player = player;
        }
    }

    public static class FlipPage extends SchematicEvent
    {

        public int index;
        public int direction;
        public GuiScreen currentGui;

        public FlipPage(GuiScreen cs, ISchematicPage page, int index, int direction)
        {
            super(page);
            this.currentGui = cs;
            this.index = index;
            this.direction = direction;
        }
    }
}
