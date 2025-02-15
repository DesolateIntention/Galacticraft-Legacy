/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.api.event.client;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.lwjgl.util.vector.Vector3f;

public abstract class CelestialBodyRenderEvent extends Event
{

    public final CelestialBody celestialBody;

    public CelestialBodyRenderEvent(CelestialBody celestialBody)
    {
        this.celestialBody = celestialBody;
    }

    public static class CelestialRingRenderEvent extends CelestialBodyRenderEvent
    {

        public CelestialRingRenderEvent(CelestialBody celestialBody)
        {
            super(celestialBody);
        }

        @Cancelable
        public static class Pre extends CelestialBodyRenderEvent
        {

            public final Vector3f parentOffset;

            public Pre(CelestialBody celestialBody, Vector3f parentOffset)
            {
                super(celestialBody);
                this.parentOffset = parentOffset;
            }
        }

        public static class Post extends CelestialBodyRenderEvent
        {

            public Post(CelestialBody celestialBody)
            {
                super(celestialBody);
            }
        }
    }

    @Cancelable
    public static class Pre extends CelestialBodyRenderEvent
    {

        public ResourceLocation celestialBodyTexture;
        public int textureSize;

        public Pre(CelestialBody celestialBody, ResourceLocation celestialBodyTexture, int textureSize)
        {
            super(celestialBody);
            this.celestialBodyTexture = celestialBodyTexture;
            this.textureSize = textureSize;
        }
    }

    public static class Post extends CelestialBodyRenderEvent
    {

        public Post(CelestialBody celestialBody)
        {
            super(celestialBody);
        }
    }
}
