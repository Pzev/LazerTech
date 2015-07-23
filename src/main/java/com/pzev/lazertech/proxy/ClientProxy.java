package com.pzev.lazertech.proxy;

import com.pzev.lazertech.client.render.RenderLazerFurnace;
import com.pzev.lazertech.tileentity.TileEntityLaserFurnace;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy{

    public void registerRenderThings() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaserFurnace.class, new RenderLazerFurnace());
    }

    public void preInit() {

    }

    public void init() {
        registerRenderThings();
    }

    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    public World getClientWorld() {
        return Minecraft.getMinecraft().theWorld;
    }
}
