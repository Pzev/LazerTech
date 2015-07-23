package com.pzev.lazertech.tileentity;

import com.pzev.lazertech.block.BlockLaserFurnace;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLaserFurnace extends TileEntity implements ISidedInventory{
    private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {2, 1};
    private static final int[] slotsSides = new int[] {1};
    private ItemStack[] inventory = new ItemStack[3];
    public int burnTime;
    public int currentBurnTime;
    public int cookTime;
    private String customName;

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return false;
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return false;
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
    public ItemStack decrStackSize(int slot, int amount) {
        if(this.inventory[slot] != null) {
            ItemStack stack;

            if(this.inventory[slot].stackSize <= amount) {
                stack = this.inventory[slot];
                this.inventory[slot] = null;
                return stack;
            } else {
                stack = this.inventory[slot].splitStack(amount);

                if(this.inventory[slot].stackSize == 0) {
                    this.inventory[slot] = null;
                }

                return stack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if(this.inventory[slot] != null) {
            ItemStack stack = this.inventory[slot];
            this.inventory[slot] = null;
            return stack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.inventory[slot] = stack;

        if(stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : "container.laserfurnace";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return this.customName != null && this.customName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
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
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList tagList = nbt.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];

        for(int c = 0; c < tagList.tagCount(); c++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(c);
            byte slot = tag.getByte("Slot");

            if(slot >= 0 && slot < this.inventory.length) {
                this.inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }

        this.burnTime = nbt.getShort("BurnTime");
        this.cookTime = nbt.getShort("CookTime");
        this.currentBurnTime = getItemBurnTime(this.inventory[1]);

        if(nbt.hasKey("CustomName", 8)) {
            setCustomName(nbt.getString("CustomName"));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setShort("BurnTime", (short) this.burnTime);
        nbt.setShort("CookTime", (short) this.cookTime);
        NBTTagList tagList = new NBTTagList();

        for(int slot = 0; slot < this.inventory.length; slot++) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setByte("Slot", (byte) slot);
            this.inventory[slot].writeToNBT(tag);
            tagList.appendTag(tag);
        }

        nbt.setTag("Items", tagList);

        if(this.hasCustomInventoryName()) {
            nbt.setString("CustomName", this.customName);
        }
    }

    public void setCustomName(String name) {
        this.customName = name;
    }

    public static int getItemBurnTime(ItemStack stack) {
        if(stack == null) {
            return 0;
        } else {
            Item item = stack.getItem();

            if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);

                if(block == Blocks.wooden_slab) {
                    return 150;
                }

                if(block.getMaterial() == Material.wood) {
                    return 300;
                }

                if(block == Blocks.coal_block) {
                    return 16000;
                }
            }
            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return GameRegistry.getFuelValue(stack);
        }
    }

    public static boolean isItemValidFuel(ItemStack stack) {
        return getItemBurnTime(stack) > 0;
    }

    private boolean canSmelt() {
        if(this.inventory[0] == null) {
            return false;
        } else {
            ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
            if(stack == null) return false;
            if(this.inventory[2] == null) return true;
            if(!this.inventory[2].isItemEqual(stack)) return false; //If the output slot isnt the smelting result the don't
            int result = inventory[2].stackSize + stack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.inventory[2].getMaxStackSize();
        }
    }

    public void smeltItem() {
        if(this.canSmelt()) {
            ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);

            if(this.inventory[2] == null) {
                this.inventory[2] = stack.copy();
            } else if(this.inventory[2].getItem() == stack.getItem()) {
                this.inventory[2].stackSize += stack.stackSize;
            }

            --this.inventory[0].stackSize;

            if(this.inventory[0].stackSize <= 0) {
                this.inventory[0] = null;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int scale) {
        return this.cookTime * scale / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int scale) {
        if(this.currentBurnTime == 0) {
            this.currentBurnTime = 200;
        }

        return this.burnTime * scale / this.currentBurnTime;
    }

    public boolean isSmelting() {
        return this.burnTime > 0;
    }

    public void updateEntity() {
        boolean flag = this.isSmelting();
        boolean flag1 = false;

        if(flag) {
            --this.burnTime;
        }

        if(!this.worldObj.isRemote) {
            if(this.burnTime != 0 || this.inventory[1] != null && this.inventory[0] != null) {
                if(this.burnTime == 0 && this.canSmelt()) {
                    this.currentBurnTime = this.burnTime = getItemBurnTime(this.inventory[1]);

                    if(flag) {
                        flag1 = true;

                        if(this.inventory[1] != null) {
                            --this.inventory[1].stackSize;

                            if(this.inventory[1].stackSize == 0) {
                                this.inventory[1] = this.inventory[1].getItem().getContainerItem(this.inventory[1]);
                            }
                        }
                    }
                }

                if(flag && this.canSmelt()) {
                    ++this.cookTime;

                    if(this.cookTime == 200) {
                        this.cookTime = 0;
                        this.smeltItem();
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            }

            if(flag != this.burnTime > 0) {
                flag1 = true;
                BlockLaserFurnace.updateBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if(flag1) {
            this.markDirty();
        }
    }
}
