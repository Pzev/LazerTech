package com.pzev.lazertech.tileentity;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBattery extends TileEntity
{
    private int power = 10000;
    private ItemStack[] inventory = new ItemStack[4];

    public void updateEntity()
    {
       power--;
        if(power % 1000 == 0)
        {
                worldObj.createExplosion(null, xCoord+0.5F, yCoord+1.1F, zCoord+0.5F, 5.0F, true);

        }
    }

    public void writeToPacket(ByteBuf buf)
    {
        for(ItemStack stack : inventory)
            ByteBufUtils.writeItemStack(buf, stack);
    }

    public void readFromPacket(ByteBuf buf)
    {
        for(int i = 0; i < inventory.length; i++)
            inventory[i] = ByteBufUtils.readItemStack(buf);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        power = tag.getInteger("power");
    }
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setInteger("power", power);
    }


}
