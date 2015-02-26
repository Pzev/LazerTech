package com.pzev.lazertech.init;

import com.pzev.lazertech.item.*;
import com.pzev.lazertech.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemLT pewPewGun = new ItemPewPewGun();
    public static final ItemLT powPowGun = new ItemPowPowGun();
    public static final ItemLT electricSpear = new ItemElectricSpear();
    public static final ItemLT hammer = new ItemHammer();
    public static final ItemLT pliers = new ItemPliers();
    public static final ItemLT screwdriver = new ItemScrewdriver();
    public static final ItemLT level = new ItemLevel();
    public static final ItemLT basicTools = new ItemBasicTools();




    public static void init()
    {
        GameRegistry.registerItem(pewPewGun, "pewPewGun");
        GameRegistry.registerItem(powPowGun, "powPowGun");
        GameRegistry.registerItem(electricSpear, "electricSpear");
        GameRegistry.registerItem(hammer, "hammer");
        GameRegistry.registerItem(pliers, "pliers");
        GameRegistry.registerItem(screwdriver, "screwdriver");
        GameRegistry.registerItem(level, "level");
        GameRegistry.registerItem(basicTools, "basicTools");




    }
}
