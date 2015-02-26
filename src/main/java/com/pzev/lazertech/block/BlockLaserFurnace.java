package com.pzev.lazertech.block;

import com.pzev.lazertech.creativetab.CreativeTabLT;
import net.minecraft.creativetab.CreativeTabs;

public class BlockLaserFurnace extends BlockLT
{
    public BlockLaserFurnace()
    {
        super();
        this.setBlockName("laserFurnace");
        this.setBlockTextureName("laserFurnace");
        this.setHardness(7.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypeMetal);
        this.setCreativeTab(CreativeTabLT.LT_TAB);
    }
}
