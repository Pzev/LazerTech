package com.pzev.lazertech.block;

import com.pzev.lazertech.reference.Reference;
import com.pzev.lazertech.tileentity.TileEntityBattery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockBattery extends BlockLTTileEntity
{
    public BlockBattery()
    {
        super();
        this.setBlockName("battery");
        this.setBlockTextureName("battery");
        this.setHardness(10.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypeMetal);
    }

    @SideOnly(Side.CLIENT)
    private IIcon top, face;

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? top : (side == 0 ? top : (meta == 2 && side == 2 ? face : (meta == 3 && side == 5 ? face
                : (meta == 0 && side == 3 ? face : (meta == 1 && side == 4 ? face : this.blockIcon)))));
    }

    @Override
    public void registerBlockIcons(IIconRegister icon)
    {
        top = icon.registerIcon(Reference.MOD_ID + ":battery3");
        face = icon.registerIcon(Reference.MOD_ID + ":battery2");
        this.blockIcon = icon.registerIcon(Reference.MOD_ID + ":battery1");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityBattery();
    }
}
