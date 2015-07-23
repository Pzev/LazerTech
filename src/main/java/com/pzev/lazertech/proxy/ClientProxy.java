package com.pzev.lazertech.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy{

    public void registerTileEntities() {

    }

    public void preInit() {

    }

    public void init() {

    }

    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    public World getClientWorld() {
        return Minecraft.getMinecraft().theWorld;
    }
}
