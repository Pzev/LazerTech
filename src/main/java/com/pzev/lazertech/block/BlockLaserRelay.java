package com.pzev.lazertech.block;

import com.pzev.lazertech.tileentity.TileEntityLaserRelay;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLaserRelay extends BlockLTTileEntity
{
    public BlockLaserRelay()
    {
        super();
        this.setBlockName("laserRelay");
        this.setBlockTextureName("laserRelay");
        this.setHardness(10.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypeMetal);
    }
    
    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityLaserRelay();
    }
}
