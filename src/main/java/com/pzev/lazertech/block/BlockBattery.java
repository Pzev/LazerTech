package com.pzev.lazertech.block;

import com.pzev.lazertech.LazerTech;
import com.pzev.lazertech.reference.Reference;
import com.pzev.lazertech.tileentity.TileEntityBattery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
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

    //fix for gui texture
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float x1, float y1, float z1) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (world.isRemote)
            return true;

        if (tile != null && tile instanceof TileEntityBattery && !player.isSneaking()) {
            player.openGui(LazerTech.instance, 1, world, x, y, z);
        }

        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, block, par6);
    }

    // Method that drops item upon being called
    private void dropItems(World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (!(tileEntity instanceof TileEntityBattery)) {
            return;
        }

        TileEntityBattery inventory = (TileEntityBattery) world.getTileEntity(x, y, z);

        for (int c = 0; c < inventory.getSizeInventory(); c++) {
            ItemStack stack = inventory.getStackInSlot(c);
            if (stack != null) {
                dropBlockAsItem(world, x, y, z, stack);
            }
        }
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityBattery();
    }
}
