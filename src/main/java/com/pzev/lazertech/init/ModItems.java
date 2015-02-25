package com.pzev.lazertech.init;

import com.pzev.lazertech.item.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems
{
    public static final ItemLT pewPewGun = new ItemPewPewGun();
    public static final ItemLT powPowGun = new ItemPowPowGun();
    public static final ItemLT electricSpear = new ItemElectricSpear();
    public static final ItemLT hammer = new ItemHammer();
    public static final ItemLT pliers = new ItemPliers();
    public static final ItemLT screwdriver = new ItemScrewdriver();


    public static void init()
    {
        GameRegistry.registerItem(pewPewGun, "pewPewGun");
        GameRegistry.registerItem(powPowGun, "powPowGun");
        GameRegistry.registerItem(electricSpear, "electricSpear");
        GameRegistry.registerItem(hammer, "hammer");
        GameRegistry.registerItem(pliers, "pliers");
        GameRegistry.registerItem(screwdriver, "screwdriver");


    }
}
