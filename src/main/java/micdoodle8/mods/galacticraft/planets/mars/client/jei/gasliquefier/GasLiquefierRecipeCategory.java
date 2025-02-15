/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.planets.mars.client.jei.gasliquefier;

import java.util.List;
import javax.annotation.Nonnull;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.client.jei.RecipeCategories;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.planets.GalacticraftPlanets;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GasLiquefierRecipeCategory implements IRecipeCategory
{

    private static final ResourceLocation refineryGuiTex = new ResourceLocation(GalacticraftPlanets.ASSET_PREFIX, "textures/gui/gas_liquefier_recipe.png");
    private static final ResourceLocation gasesTex = new ResourceLocation(GalacticraftPlanets.ASSET_PREFIX, "textures/gui/gases_methane_oxygen_nitrogen.png");

    @Nonnull private final IDrawable background;
    @Nonnull private final String localizedName;
    @Nonnull private final IDrawableAnimated methaneBarInput;
    @Nonnull private final IDrawableAnimated oxygenBarInput;
    @Nonnull private final IDrawableAnimated nitrogenBarInput;
    @Nonnull private final IDrawableAnimated fuelBarOutput;
    @Nonnull private final IDrawableAnimated oxygenBarOutput;
    @Nonnull private final IDrawableAnimated nitrogenBarOutput;

    private int inputGas = 2;
    private int outputGas = 2;

    public GasLiquefierRecipeCategory(IGuiHelper guiHelper)
    {
        this.background = guiHelper.createDrawable(refineryGuiTex, 3, 4, 168, 64);
        this.localizedName = GCCoreUtil.translate("tile.mars_machine.4.name");

        IDrawableStatic methaneBar = guiHelper.createDrawable(gasesTex, 1, 0, 16, 38);
        this.methaneBarInput = guiHelper.createAnimatedDrawable(methaneBar, 70, IDrawableAnimated.StartDirection.TOP, true);
        IDrawableStatic oxygenBar = guiHelper.createDrawable(gasesTex, 18, 0, 16, 38);
        this.oxygenBarInput = guiHelper.createAnimatedDrawable(oxygenBar, 70, IDrawableAnimated.StartDirection.TOP, true);
        IDrawableStatic atmosphereBar = guiHelper.createDrawable(gasesTex, 34, 0, 16, 38);
        this.nitrogenBarInput = guiHelper.createAnimatedDrawable(atmosphereBar, 70, IDrawableAnimated.StartDirection.TOP, true);
        IDrawableStatic fuelBar = guiHelper.createDrawable(refineryGuiTex, 176, 0, 16, 38);
        this.fuelBarOutput = guiHelper.createAnimatedDrawable(fuelBar, 70, IDrawableAnimated.StartDirection.BOTTOM, false);
        this.oxygenBarOutput = guiHelper.createAnimatedDrawable(oxygenBar, 70, IDrawableAnimated.StartDirection.BOTTOM, false);
        IDrawableStatic nitrogenBar = guiHelper.createDrawable(gasesTex, 68, 0, 16, 38);
        this.nitrogenBarOutput = guiHelper.createAnimatedDrawable(nitrogenBar, 70, IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return RecipeCategories.GAS_LIQUEFIER_ID;
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return this.localizedName;
    }

    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return this.background;
    }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft)
    {
        IDrawableAnimated input;
        IDrawableAnimated output;

        switch (this.inputGas)
        {
            case 0:
                input = this.methaneBarInput;
                break;
            case 1:
                input = this.oxygenBarInput;
                break;
            default:
                input = this.nitrogenBarInput;
                break;
        }

        switch (this.outputGas)
        {
            case 0:
                output = this.fuelBarOutput;
                break;
            case 3:
                output = this.oxygenBarOutput;
                break;
            default:
                output = this.nitrogenBarOutput;
                break;
        }

        input.draw(minecraft, 40, 24);
        output.draw(minecraft, this.outputGas == 0 || this.outputGas == 3 ? 124 : 105, 24);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients)
    {
        IGuiItemStackGroup itemstacks = recipeLayout.getItemStacks();

        itemstacks.init(0, true, 39, 2);
        itemstacks.init(1, false, 104, 2);
        itemstacks.init(2, false, 123, 2);

        if (recipeWrapper instanceof GasLiquefierRecipeWrapper)
        {
            GasLiquefierRecipeWrapper gasLiquefierRecipeWrapper = (GasLiquefierRecipeWrapper) recipeWrapper;
            List<ItemStack> input = ingredients.getInputs(ItemStack.class).get(0);
            List<ItemStack> output = ingredients.getOutputs(ItemStack.class).get(0);
            itemstacks.set(0, input);

            Item inputItem = input.get(0).getItem();
            if (inputItem == AsteroidsItems.methaneCanister)
            {
                this.inputGas = 0;
            } else if (inputItem == AsteroidsItems.canisterLOX)
            {
                this.inputGas = 1;
            }

            Item outputItem = output.get(0).getItem();
            if (outputItem == GCItems.fuelCanister)
            {
                this.outputGas = 0;
            } else if (outputItem == AsteroidsItems.canisterLOX)
            {
                this.outputGas = 3;
            } else
            {
                this.outputGas = 4;
            }

            itemstacks.set(this.outputGas == 0 || this.outputGas == 3 ? 2 : 1, output);
        }
    }

    @Override
    public String getModName()
    {
        return GalacticraftPlanets.NAME;
    }
}
