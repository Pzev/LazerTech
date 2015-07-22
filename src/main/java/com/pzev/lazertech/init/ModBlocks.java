package com.pzev.lazertech.init;


import com.pzev.lazertech.block.BlockBattery;
import com.pzev.lazertech.block.BlockLTTileEntity;
import com.pzev.lazertech.block.BlockLaserFurnace;
import com.pzev.lazertech.block.BlockLaserRelay;
import com.pzev.lazertech.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
    public static final BlockLaserFurnace laserFurnace = new BlockLaserFurnace(false);
    public static final BlockLaserFurnace laserFurnaceLit = new BlockLaserFurnace(true);
    public static final BlockLTTileEntity battery = new BlockBattery();
    public static final BlockLTTileEntity laserRelay = new BlockLaserRelay();


    public static void init() {
        GameRegistry.registerBlock(laserFurnace, "laserFurnace");
        GameRegistry.registerBlock(laserFurnaceLit, "laserFurnaceLit");
        GameRegistry.registerBlock(battery, "battery");
        GameRegistry.registerBlock(laserRelay, "laserRelay");

    }
}
