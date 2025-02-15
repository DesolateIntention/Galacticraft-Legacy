/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.core.client;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.Type;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public abstract class KeyHandler
{

    private final KeyBinding[] keyBindings;
    private KeyBinding[] vKeyBindings;
    private boolean[] keyDown;
    private boolean[] repeatings;
    private boolean[] vRepeatings;
    public boolean isDummy;

    public KeyHandler(KeyBinding[] keyBindings, boolean[] repeatings, KeyBinding[] vanillaKeys, boolean[] vanillaRepeatings)
    {
        assert keyBindings.length == repeatings.length : "You need to pass two arrays of identical length";
        assert vanillaKeys.length == vanillaRepeatings.length : "You need to pass two arrays of identical length";
        this.keyBindings = keyBindings;
        this.repeatings = repeatings;
        this.vKeyBindings = vanillaKeys;
        this.vRepeatings = vanillaRepeatings;
        this.keyDown = new boolean[keyBindings.length + vanillaKeys.length];
    }

    public KeyHandler(KeyBinding[] keyBindings)
    {
        this.keyBindings = keyBindings;
        this.isDummy = true;
    }

    @SubscribeEvent
    public void onTick(ClientTickEvent event)
    {
        if (event.side == Side.CLIENT)
        {
            if (event.phase == Phase.START)
            {
                this.keyTick(event.type, false);
            } else if (event.phase == Phase.END)
            {
                this.keyTick(event.type, true);
            }
        }

    }

    public void keyTick(Type type, boolean tickEnd)
    {
        boolean inChat = FMLClientHandler.instance().getClient().currentScreen instanceof GuiChat;

        for (int i = 0; i < this.keyBindings.length; i++)
        {
            KeyBinding keyBinding = this.keyBindings[i];
            int keyCode = keyBinding.getKeyCode();
            if (keyCode == Keyboard.KEY_NONE)
                continue;
            boolean state = false;

            try
            {
                if (!inChat)
                {
                    if (keyCode < 0)
                    {
                        keyCode += 100;
                        state = Mouse.isButtonDown(keyCode);
                    } else
                    {
                        state = Keyboard.isKeyDown(keyCode);
                    }
                }
            } catch (IndexOutOfBoundsException e)
            {
                GalacticraftCore.logger.error("Invalid keybinding! " + keyBinding.getKeyDescription());
                continue;
            }

            if (state != this.keyDown[i] || state && this.repeatings[i])
            {
                if (state)
                {
                    this.keyDown(type, keyBinding, tickEnd, state != this.keyDown[i]);
                } else
                {
                    this.keyUp(type, keyBinding, tickEnd);
                }
                if (tickEnd)
                {
                    this.keyDown[i] = state;
                }
            }
        }
        for (int i = 0; i < this.vKeyBindings.length; i++)
        {
            KeyBinding keyBinding = this.vKeyBindings[i];
            int keyCode = keyBinding.getKeyCode();
            if (keyCode == Keyboard.KEY_NONE)
                continue;
            boolean state = keyCode < 0 ? Mouse.isButtonDown(keyCode + 100) : Keyboard.isKeyDown(keyCode);
            if (state != this.keyDown[i + this.keyBindings.length] || state && this.vRepeatings[i])
            {
                if (state)
                {
                    this.keyDown(type, keyBinding, tickEnd, state != this.keyDown[i + this.keyBindings.length]);
                } else
                {
                    this.keyUp(type, keyBinding, tickEnd);
                }
                if (tickEnd)
                {
                    this.keyDown[i + this.keyBindings.length] = state;
                }
            }
        }
    }

    public abstract void keyDown(Type types, KeyBinding kb, boolean tickEnd, boolean isRepeat);

    public abstract void keyUp(Type types, KeyBinding kb, boolean tickEnd);

}
