package com.pzev.lazertech.block;

import com.pzev.lazertech.LazerTech;
import com.pzev.lazertech.creativetab.CreativeTabLT;
import com.pzev.lazertech.init.ModBlocks;
import com.pzev.lazertech.proxy.CommonProxy;
import com.pzev.lazertech.tileentity.TileEntityLaserFurnace;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockLaserFurnace extends BlockContainer
{
    private final Random random = new Random();
    private final boolean isPowered;
    private static boolean flag;
    @SideOnly(Side.CLIENT)
    private IIcon face;

    public BlockLaserFurnace(boolean isPowered) {
        super(Material.iron);
        this.setBlockName("laserFurnace");
        this.setBlockTextureName("laserFurnace");
        this.setHardness(10.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypeMetal);
        if(!isPowered) setCreativeTab(CreativeTabLT.LT_TAB);
        this.isPowered = isPowered;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? this.blockIcon : (side == 0 ? this.blockIcon : (meta == 2 && side == 2 ? face : (meta == 3 && side == 5 ? face : (meta == 0 && side == 3 ? face : (meta == 1 && side == 4 ? face : this.blockIcon)))));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon("iron_block");
        this.face = icon.registerIcon(this.isPowered ? "furnace_front_on" : "furnace_front_off");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float yaw, float pitch, float partialTicks) {

        if(world.isRemote) {
            return true;
        } else {
            TileEntity furnace = world.getTileEntity(x, y, z);

            if(furnace != null && !player.isSneaking() && furnace instanceof TileEntityLaserFurnace) {
                player.openGui(LazerTech.instance, CommonProxy.GUI_ID_LASER_FURNACE, world, x, y, z);
                FMLLog.info("open GUI");
            }

            return true;
        }
    }

    public static void updateBlockState(boolean isBurning, World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        TileEntity tile = world.getTileEntity(x, y, z);
        flag = true;

        if(isBurning) {
            world.setBlock(x, y, z, ModBlocks.laserFurnaceLit);
        } else {
            world.setBlock(x, y, z, ModBlocks.laserFurnace);
        }

        flag = false;
        world.setBlockMetadataWithNotify(x, y, z, meta, 2);

        if(tile != null) {
            tile.validate();
            world.setTileEntity(x, y, z, tile);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityLaserFurnace();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        if (!flag) {
            TileEntityLaserFurnace furnace = (TileEntityLaserFurnace)world.getTileEntity(x, y, z);

            if (furnace != null) {
                for (int i1 = 0; i1 < furnace.getSizeInventory(); ++i1) {
                    ItemStack itemstack = furnace.getStackInSlot(i1);

                    if (itemstack != null) {
                        float f = this.random.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0) {
                            int j1 = this.random.nextInt(21) + 10;

                            if (j1 > itemstack.stackSize) {
                                j1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= j1;
                            EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound()) {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
                            entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double)((float)this.random.nextGaussian() * f3);
                            world.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                world.func_147453_f(x, y, z, block);
            }
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (this.isPowered) {
            int l = world.getBlockMetadata(x, y, z);
            float f = (float)x + 0.5F;
            float f1 = (float)y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)z + 0.5F;
            float f3 = 0.52F;
            float f4 = random.nextFloat() * 0.6F - 0.3F;

            if (l == 4) {
                world.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 5) {
                world.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 2) {
                world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 3) {
                world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(World world, int x, int y, int z, int meta) {
        return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(ModBlocks.laserFurnace);
    }

    @Override
    public Item getItemDropped(int amount, Random random, int par3) {
        return Item.getItemFromBlock(ModBlocks.laserFurnace);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.checkForNeighbors(world, x, y, z);
    }

    private void checkForNeighbors(World world, int x, int y, int z) {
        if (!world.isRemote) {
            Block block = world.getBlock(x, y, z - 1);
            Block block1 = world.getBlock(x, y, z + 1);
            Block block2 = world.getBlock(x - 1, y, z);
            Block block3 = world.getBlock(x + 1, y, z);
            byte meta = 3;

            if (block.func_149730_j() && !block1.func_149730_j()) {
                meta = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j()) {
                meta = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j()) {
                meta = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j()) {
                meta = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, meta, 2);
        }
    }
}
