package com.pzev.lazertech.init;


import com.pzev.lazertech.block.*;
import com.pzev.lazertech.tileentity.TileEntityBattery;
import com.pzev.lazertech.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockLT laserFurnace = new BlockLaserFurnace();
    public static final BlockLTTileEntity battery = new BlockBattery();
    public static final BlockLTTileEntity laserRelay = new BlockLaserRelay();


    public static void init()
    {
        GameRegistry.registerBlock(laserFurnace, "laserFurnace");
        GameRegistry.registerBlock(battery, "battery");
        GameRegistry.registerBlock(laserRelay, "laserRelay");

    }
}
