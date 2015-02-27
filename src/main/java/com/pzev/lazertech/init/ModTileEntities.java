package com.pzev.lazertech.init;

import com.pzev.lazertech.reference.Reference;
import com.pzev.lazertech.tileentity.TileEntityBattery;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityBattery.class, Reference.BATTERY);
    }
}
