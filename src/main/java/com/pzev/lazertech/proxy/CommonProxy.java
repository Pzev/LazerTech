package com.pzev.lazertech.proxy;

import com.pzev.lazertech.client.gui.GuiLaserFurnace;
import com.pzev.lazertech.inventory.ContainerLaserFurnace;
import com.pzev.lazertech.tileentity.TileEntityBattery;
import com.pzev.lazertech.tileentity.TileEntityLaserFurnace;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler{

    public static final int GUI_ID_LASER_FURNACE = 1;
    public static final int GUI_ID_BATTERY = 2;

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityBattery.class, "battery");
        GameRegistry.registerTileEntity(TileEntityLaserFurnace.class, "laserFurnace");
    }

    public void preInit() {

    }

    public void init() {
        registerTileEntities();
    }

    public EntityPlayer getClientPlayer() {
        return null;
    }

    public World getClientWorld() {
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == GUI_ID_LASER_FURNACE) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if(tileEntity instanceof TileEntityLaserFurnace)
                return new ContainerLaserFurnace(player.inventory, (TileEntityLaserFurnace) tileEntity);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == GUI_ID_LASER_FURNACE) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if(tileEntity instanceof TileEntityLaserFurnace)
                return new GuiLaserFurnace(player.inventory, (TileEntityLaserFurnace) tileEntity);
        }

        return null;
    }
}
