package com.pzev.lazertech.proxy;

import com.pzev.lazertech.tileentity.TileEntityBattery;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityBattery.class, "battery");
    }

}
