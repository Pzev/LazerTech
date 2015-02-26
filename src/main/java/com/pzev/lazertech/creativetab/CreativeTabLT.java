package com.pzev.lazertech.creativetab;

import com.pzev.lazertech.init.ModItems;
import com.pzev.lazertech.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabLT
{
    public static final CreativeTabs LT_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem() {
            return ModItems.electricSpear;
        }
    };
}
