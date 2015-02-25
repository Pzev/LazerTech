package com.pzev.lazertech.init;

import com.pzev.lazertech.item.ItemElectricSpear;
import com.pzev.lazertech.item.ItemLT;
import com.pzev.lazertech.item.ItemPewPewGun;
import com.pzev.lazertech.item.ItemPowPowGun;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems
{
    public static final ItemLT pewPewGun = new ItemPewPewGun();
    public static final ItemLT powPowGun = new ItemPowPowGun();
    public static final ItemLT electricSpear = new ItemElectricSpear();

    public static void init()
    {
        GameRegistry.registerItem(pewPewGun, "pewPewGun");
        GameRegistry.registerItem(powPowGun, "powPowGun");
        GameRegistry.registerItem(electricSpear, "electricSpear");
    }
}
