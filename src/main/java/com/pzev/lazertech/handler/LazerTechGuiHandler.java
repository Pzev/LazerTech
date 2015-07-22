package com.pzev.lazertech.handler;

import com.pzev.lazertech.client.gui.GuiBattery;
import com.pzev.lazertech.inventory.ContainerBattery;
import com.pzev.lazertech.tileentity.TileEntityBattery;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LazerTechGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0:
                TileEntity tileentity = world.getTileEntity(x, y, z);
                if (tileentity instanceof TileEntityBattery)
                    return new ContainerBattery(player.inventory, (TileEntityBattery) tileentity);
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0:
                TileEntity tileentity = world.getTileEntity(x, y, z);
                if (tileentity instanceof TileEntityBattery)
                    return new GuiBattery(player.inventory, (TileEntityBattery) tileentity);
            default:
                return null;
        }
    }
}