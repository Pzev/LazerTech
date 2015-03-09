package com.pzev.lazertech.inventory;

import com.pzev.lazertech.tileentity.TileEntityBattery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBattery extends Container
{

    public TileEntityBattery battery;
    public EntityPlayer player;

    public ContainerBattery(InventoryPlayer player, TileEntityBattery tileEntityBattery) {

        this.player = player.player;
        battery = tileEntityBattery;

        addSlot(tileEntityBattery, 0, 53, 15);
        addSlot(tileEntityBattery, 1, 78, 52);
        addSlot(tileEntityBattery, 2, 103, 15);
        addSlot(tileEntityBattery, 3, 8, 62);
        addSlot(tileEntityBattery, 4, 151, 11);
        addSlot(tileEntityBattery, 5, 151, 31);
        addSlot(tileEntityBattery, 6, 151, 51);

        bindPlayerInventory(player);
    }

    private void addSlot(IInventory inv, final int id, int x, int y) {
        addSlotToContainer(new Slot(inv, id, x, y) {

            @Override
            public boolean isItemValid(ItemStack arg0) {
                return inventory.isItemValidForSlot(id, arg0);
            }
        });
    }

    protected void bindPlayerInventory(InventoryPlayer invPlayer) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlotToContainer(new Slot(invPlayer, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }

        for (int slotNumber = 0; slotNumber < 9; slotNumber++) {
            addSlotToContainer(new Slot(invPlayer, slotNumber, 8 + slotNumber * 18, 142));
        }
    }


    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return battery.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            //merges the item into player inventory since its in the tileEntity
            if (slot < 9) {
                if (!this.mergeItemStack(stackInSlot, 0, 35, true)) {
                    return null;
                }
            }
            //places it into the tileEntity is possible since its in the player inventory
            else if (!this.mergeItemStack(stackInSlot, 0, 9, false)) {
                return null;
            }

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            } else {
                slotObject.onSlotChanged();
            }

            if (stackInSlot.stackSize == stack.stackSize) {
                return null;
            }
            slotObject.onPickupFromSlot(player, stackInSlot);
        }
        return stack;
    }



}
