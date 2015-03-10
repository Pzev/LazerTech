package com.pzev.lazertech.tileentity;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBattery extends TileEntity implements ISidedInventory
{
    private int power = 0;
    private ItemStack[] inventory = new ItemStack[4];
    private String name = "battery";

    public void updateEntity()
    {
       power--;
        if(power % 100 == 0)
        {
            worldObj.createExplosion(null, xCoord+0.5F, yCoord+1.1F, zCoord+0.5F, 1.0F, false);


        }
    }





    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        power = nbt.getInteger("power");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("power", power);
    }





    @Override
    public final boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {
        ;
    }

    @Override
    public void closeInventory() {
        ;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int size) {
        if (inventory[slot] != null) {
            ItemStack is;
            if (inventory[slot].stackSize <= size) {
                is = inventory[slot];
                inventory[slot] = null;
                return is;
            } else {
                is = inventory[slot].splitStack(size);
                if (inventory[slot].stackSize == 0)
                    inventory[slot] = null;
                return is;
            }
        } else
            return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (inventory[slot] != null) {
            ItemStack is = inventory[slot];
            inventory[slot] = null;
            return is;
        } else
            return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;

        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }

    }

    @Override
    public String getInventoryName() {
        return name;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    // Add later for slots for piping in and out
    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[0];
    }

    //These two methods for piping in and out
    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return false;
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return false;
    }
}
