package com.pzev.lazertech.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes
{
    public static void init()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.electricSpear), " s ", "sls", " s ", 's', "stickWood", 'l', "gemDiamond"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.hammer), "isi", " s ", " s ", 's', "stickWood", 'i', "ingotIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pliers), " i ", "iis", " s ", 's', "stickWood", 'i', "ingotIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.screwdriver), " i ", " is", " is", 's', "stickWood", 'i', "ingotIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.level), "sss", "ibi", "sss", 's', "stickWood", 'i', "ingotIron", 'b', new ItemStack(Items.glass_bottle)));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.basicTools), new ItemStack(ModItems.hammer), new ItemStack(ModItems.pliers), new ItemStack(ModItems.screwdriver), new ItemStack(ModItems.level)));
    }
}
