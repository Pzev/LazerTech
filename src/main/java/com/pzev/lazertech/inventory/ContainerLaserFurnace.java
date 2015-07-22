package com.pzev.lazertech.inventory;

import com.pzev.lazertech.tileentity.TileEntityLaserFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerLaserFurnace extends Container {

    private TileEntityLaserFurnace furnace;
    private int lastCookTime, lastBurnTime, lastBurnedTime;

    public ContainerLaserFurnace(InventoryPlayer player, TileEntityLaserFurnace tile) {
        furnace  = tile;
        this.addSlotToContainer(new Slot(tile, 0, 56, 17));
        this.addSlotToContainer(new Slot(tile, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnace(player.player, tile, 2, 116, 35));
        bindPlayerInventory(player);
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.furnace.cookTime);
        crafting.sendProgressBarUpdate(this, 1, this.furnace.burnTime);
        crafting.sendProgressBarUpdate(this, 2, this.furnace.currentBurnTime);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for(int c = 0; c < this.crafters.size(); c++) {
            ICrafting crafting = (ICrafting) this.crafters.get(c);

            if(this.lastCookTime != this.furnace.cookTime) {
                crafting.sendProgressBarUpdate(this, 0, this.furnace.cookTime);
            }

            if(this.lastBurnTime != this.furnace.burnTime) {
                crafting.sendProgressBarUpdate(this, 1, this.furnace.burnTime);
            }

            if(this.lastBurnedTime != this.furnace.currentBurnTime) {
                crafting.sendProgressBarUpdate(this, 2, this.furnace.currentBurnTime);
            }
        }

        this.lastCookTime = this.furnace.cookTime;
        this.lastBurnTime = this.furnace.burnTime;
        this.lastBurnedTime = this.furnace.currentBurnTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int crafter, int updateAmount) {
        if(crafter == 0) {
            this.furnace.cookTime = updateAmount;
        }

        if(crafter == 1) {
            this.furnace.burnTime = updateAmount;
        }

        if(crafter == 2) {
            this.furnace.currentBurnTime = updateAmount;
        }
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 9; y++) {
                addSlotToContainer(new Slot(inventoryPlayer, y + x * 9 + 9, 8 + y * 18, 84 + x * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return furnace.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotID);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotID == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (slotID != 1 && slotID != 0) {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntityLaserFurnace.isItemValidFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (slotID >= 3 && slotID < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return null;
                    }
                } else if (slotID >= 30 && slotID < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}
