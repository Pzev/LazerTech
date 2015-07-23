package com.pzev.lazertech.tileentity;

import com.pzev.lazertech.API.Laser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLaserRelay extends TileEntity implements ISidedInventory
{

    private int[] blockLocation = new int[3];
    private int connectedBlock = 0;
    private int laserID;
    private int[] connectedBlockLocation = new int[3];


    private  void setConnectedBlock(int i)
    {
        connectedBlock = i;
    }

    private int getConnectedBlock()
    {
        return connectedBlock;
    }

    private void setblockLocation(int x, int y, int z)
    {
        blockLocation[1] = x;
        blockLocation[2] = y;
        blockLocation[3] = z;
    }

    private int[] getblockLocation()
    {
        return blockLocation;
    }

    private void setconnectedBlockLocation(int x, int y, int z)
    {
        connectedBlockLocation[1] = x;
        connectedBlockLocation[2] = y;
        connectedBlockLocation[3] = z;
    }

    private int[] getconnectedBlockLocation()
    {
        return connectedBlockLocation;
    }


    public void updateEntity() {
        for (int x = -10; x <= 10; x++) {
            for (int y = -10; y <= 10; y++){
                for (int z = -10; z <= 10; z++) {
                    TileEntity tempBlock = worldObj.getTileEntity(xCoord + x, yCoord + y, zCoord + z);
                    if (x != 0 && y != 0 && z != 0) {

                    } else if (tempBlock != null && (tempBlock instanceof TileEntityLaserRelay) && this.getConnectedBlock() == 0 && ((TileEntityLaserRelay) tempBlock).getConnectedBlock() == 0) {
                        this.setConnectedBlock(1);
                        ((TileEntityLaserRelay) tempBlock).setConnectedBlock(2);

                        Laser.archiveLazers(true);
                        Laser.newLazer(xCoord, yCoord, zCoord, xCoord + x, yCoord + y, zCoord + z, 0, 1, 8);
                        laserID = Laser.returnLatestID();

                        System.out.println("updated reLay!");
                    }
                }
            }
        }
    }


    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("connectedBlock", connectedBlock);
        nbt.setIntArray("blockLocation", blockLocation);
        nbt.setIntArray("connectedBlockLocation", connectedBlockLocation);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        connectedBlock = nbt.getInteger("connectedBlock");
        nbt.getIntArray("blockLocation");
        nbt.getIntArray("connectedBlockLocation");
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int amount) {
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int amount) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int size) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {

    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }
}
