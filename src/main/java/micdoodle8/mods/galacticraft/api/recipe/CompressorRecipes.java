/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.api.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import micdoodle8.mods.galacticraft.api.GalacticraftConfigAccess;
import micdoodle8.mods.galacticraft.core.GCItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class CompressorRecipes
{

    private static List<IRecipe> recipes = new ArrayList<>();
    private static List<IRecipe> recipesAdventure = new ArrayList<>();
    private static boolean adventureOnly = false;
    //private static Field adventureFlag;
    //private static boolean flagNotCached = true;
    public static boolean steelIngotsPresent = false;
    public static List<ItemStack> steelRecipeGC;

    public static ShapedRecipesGC addRecipe(ItemStack output, Object... inputList)
    {
        StringBuilder s = new StringBuilder();
        int i = 0;
        int j = 0;
        int k = 0;

        if (inputList[i] instanceof String[])
        {
            String[] astring = (String[]) inputList[i++];

            for (String s1 : astring)
            {
                ++k;
                j = s1.length();
                s.append(s1);
            }
        } else
        {
            while (inputList[i] instanceof String)
            {
                String s2 = (String) inputList[i++];
                ++k;
                j = s2.length();
                s.append(s2);
            }
        }

        HashMap<Character, ItemStack> hashmap = new HashMap<>(inputList.length / 2, 1.0F);

        for (; i < inputList.length; i += 2)
        {
            Character character = (Character) inputList[i];
            ItemStack itemstack1 = null;

            if (inputList[i + 1] instanceof Item)
            {
                itemstack1 = new ItemStack((Item) inputList[i + 1]);
            } else if (inputList[i + 1] instanceof Block)
            {
                itemstack1 = new ItemStack((Block) inputList[i + 1], 1, 32767);
            } else if (inputList[i + 1] instanceof ItemStack)
            {
                itemstack1 = (ItemStack) inputList[i + 1];
            }

            hashmap.put(character, itemstack1);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1)
        {
            char c0 = s.charAt(i1);

            if (hashmap.containsKey(c0))
            {
                aitemstack[i1] = hashmap.get(c0).copy();
            } else
            {
                aitemstack[i1] = null;
            }
        }

        ShapedRecipesGC shapedRecipes = new ShapedRecipesGC(j, k, aitemstack, output);
        if (!adventureOnly)
            CompressorRecipes.recipes.add(shapedRecipes);
        CompressorRecipes.recipesAdventure.add(shapedRecipes);
        return shapedRecipes;
    }

    public static void addShapelessRecipe(ItemStack itemStack, Object... objs)
    {
        ArrayList<Object> arraylist = new ArrayList<>();

        for (Object object1 : objs)
        {
            if (object1 instanceof ItemStack)
            {
                arraylist.add(((ItemStack) object1).copy());
            } else if (object1 instanceof Item)
            {
                arraylist.add(new ItemStack((Item) object1));
            } else if (object1 instanceof String)
            {
                arraylist.add(object1);
            } else
            {
                if (!(object1 instanceof Block))
                {
                    throw new RuntimeException("Invalid shapeless compressor recipe!");
                }

                arraylist.add(new ItemStack((Block) object1));
            }
        }

        IRecipe toAdd = new ShapelessOreRecipeGC(itemStack, arraylist.toArray());
        if (!adventureOnly)
            CompressorRecipes.recipes.add(toAdd);
        CompressorRecipes.recipesAdventure.add(toAdd);
    }

    public static ShapedRecipesGC addRecipeAdventure(ItemStack output, Object... inputList)
    {
        adventureOnly = true;
        ShapedRecipesGC returnValue = CompressorRecipes.addRecipe(output, inputList);
        adventureOnly = false;
        return returnValue;
    }

    public static void addShapelessAdventure(ItemStack par1ItemStack, Object... par2ArrayOfObj)
    {
        adventureOnly = true;
        CompressorRecipes.addShapelessRecipe(par1ItemStack, par2ArrayOfObj);
        adventureOnly = false;
    }

    public static ItemStack findMatchingRecipe(InventoryCrafting inventory, World par2World)
    {
        int i = 0;
        ItemStack itemstack = ItemStack.EMPTY;
        ItemStack itemstack1 = ItemStack.EMPTY;
        int j;

        for (j = 0; j < inventory.getSizeInventory(); ++j)
        {
            ItemStack itemstack2 = inventory.getStackInSlot(j);

            if (!itemstack2.isEmpty())
            {
                if (i == 0)
                {
                    itemstack = itemstack2;
                }

                if (i == 1)
                {
                    itemstack1 = itemstack2;
                }

                ++i;
            }
        }

        if (i == 2 && itemstack.getItem() == itemstack1.getItem() && itemstack.getCount() == 1 && itemstack1.getCount() == 1 && itemstack.getItem().isRepairable())
        {
            Item item = itemstack.getItem();
            int k = item.getMaxDamage(itemstack) - itemstack.getItemDamage();
            int l = item.getMaxDamage(itemstack) - itemstack1.getItemDamage();
            int i1 = k + l + item.getMaxDamage(itemstack) * 5 / 100;
            int j1 = item.getMaxDamage(itemstack) - i1;

            if (j1 < 0)
            {
                j1 = 0;
            }

            return new ItemStack(itemstack.getItem(), 1, j1);
        } else
        {
            List<IRecipe> theRecipes = CompressorRecipes.getRecipeList();

            for (j = 0; j < theRecipes.size(); ++j)
            {
                IRecipe irecipe = theRecipes.get(j);

                if (irecipe instanceof ShapedRecipesGC && irecipe.matches(inventory, par2World))
                {
                    return irecipe.getRecipeOutput().copy();
                } else if (irecipe instanceof ShapelessOreRecipeGC && irecipe.matches(inventory, par2World))
                {
                    return irecipe.getRecipeOutput().copy();
                }
            }

            return ItemStack.EMPTY;
        }
    }

    public static List<IRecipe> getRecipeListAll()
    {
        List<IRecipe> result = new ArrayList<>(CompressorRecipes.recipesAdventure);
        List<IRecipe> endList = getRecipeListHidden(true, true);
        result.removeIf(irecipe -> endList.contains(irecipe));
        IRecipe ice = null;
        Item iceItem = new ItemStack(Blocks.ICE).getItem();
        for (IRecipe test : result)
        {
            if (test.getRecipeOutput().getItem() == iceItem)
            {
                ice = test;
                break;
            }
        }
        if (ice != null)
        {
            result.remove(ice);
            result.add(ice);
        }
        result.addAll(endList);
        return result;
    }

    public static List<IRecipe> getRecipeListHidden(boolean hideSteel, boolean hideAdventure)
    {
        if (!hideAdventure)
            return new ArrayList<IRecipe>(0);

        List<IRecipe> result = new ArrayList<>(CompressorRecipes.recipesAdventure);
        result.removeIf(irecipe -> CompressorRecipes.recipes.contains(irecipe));
        if (steelIngotsPresent && hideSteel)
        {
            List<IRecipe> resultSteelless = new ArrayList<>(result.size());
            for (IRecipe recipe : result)
            {
                ItemStack output = recipe.getRecipeOutput();
                if (output == null)
                    continue; // Intentional ItemStack null check
                if (output.getItemDamage() == 9 && output.getItem() == GCItems.basicItem && recipe instanceof ShapelessOreRecipeGC)
                {
                    if (((ShapelessOreRecipeGC) recipe).matches(steelRecipeGC))
                    {
                        continue;
                    }
                }
                resultSteelless.add(recipe);
            }
            return resultSteelless;
        }
        return result;
    }

    public static List<IRecipe> getRecipeList()
    {
        if (GalacticraftConfigAccess.getChallengeRecipes())
            return CompressorRecipes.recipesAdventure;

        // Filter out the GC steel recipe in Hard Mode
        if (steelIngotsPresent && GalacticraftConfigAccess.getHardMode())
        {
            List<IRecipe> resultSteelless = new ArrayList<>(CompressorRecipes.recipes.size());
            for (IRecipe recipe : CompressorRecipes.recipes)
            {
                ItemStack output = recipe.getRecipeOutput();
                if (output == null)
                    continue; // Intentional ItemStack null check
                if (output.getItemDamage() == 9 && output.getItem() == GCItems.basicItem && recipe instanceof ShapelessOreRecipeGC)
                {
                    if (((ShapelessOreRecipeGC) recipe).matches(steelRecipeGC))
                    {
                        continue;
                    }
                }
                resultSteelless.add(recipe);
            }
            return resultSteelless;
        }

        return CompressorRecipes.recipes;
    }

    public static List<IRecipe> getRecipes(ItemStack match)
    {
        List<IRecipe> result = new ArrayList<IRecipe>(CompressorRecipes.getRecipeList());
        result.removeIf(irecipe -> !ItemStack.areItemStacksEqual(match, irecipe.getRecipeOutput()));
        return result;
    }

    /**
     * Caution: call this BEFORE the JEI plugin registers recipes - or else the
     * removed recipe will still be shown in JEI.
     */
    public static void removeRecipe(ItemStack match)
    {
        CompressorRecipes.recipes.removeIf(irecipe -> ItemStack.areItemStacksEqual(match, irecipe.getRecipeOutput()));
        CompressorRecipes.recipesAdventure.removeIf(irecipe -> ItemStack.areItemStacksEqual(match, irecipe.getRecipeOutput()));
    }

    public static void replaceRecipeIngredient(ItemStack ingredient, List<ItemStack> replacement)
    {
        if (ingredient == null)
            return;

        for (IRecipe recipe : CompressorRecipes.recipesAdventure)
        {
            if (recipe instanceof IRecipeUpdatable)
            {
                ((IRecipeUpdatable) recipe).replaceInput(ingredient, replacement);
            }
        }
    }

    public static void replaceRecipeIngredient(ItemStack ingredient)
    {
        if (ingredient == null)
            return;

        for (IRecipe recipe : CompressorRecipes.recipesAdventure)
        {
            if (recipe instanceof IRecipeUpdatable)
            {
                ((IRecipeUpdatable) recipe).replaceInput(ingredient);
            }
        }
    }
}
