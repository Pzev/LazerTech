package com.pzev.lazertech.init;


import com.pzev.lazertech.block.BlockLT;
import com.pzev.lazertech.block.BlockLaserFurnace;
import com.pzev.lazertech.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockLT laserFurnace = new BlockLaserFurnace();

    public static void init()
    {
        GameRegistry.registerBlock(laserFurnace, "laserFurnace");
    }
}
