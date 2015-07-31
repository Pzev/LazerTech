package com.pzev.lazertech.client.render;

import com.pzev.lazertech.LazerTech;
import com.pzev.lazertech.client.models.ModelElectricFurnace;
import com.pzev.lazertech.reference.Reference;
import com.pzev.lazertech.tileentity.TileEntityLaserFurnace;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class RenderLazerFurnace extends TileEntitySpecialRenderer {

    private final ModelElectricFurnace model;

    public RenderLazerFurnace() {
        this.model = new ModelElectricFurnace();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
        if(tile instanceof TileEntityLaserFurnace)
            this.renderLazerFurnaceAt((TileEntityLaserFurnace) tile, x, y, z, scale);
    }

    protected void renderLazerFurnaceAt(TileEntityLaserFurnace furnace, double x, double y, double z, float scale) {
        GL11.glPushMatrix();
        ResourceLocation off = new ResourceLocation(Reference.MOD_ID + ":textures/blocks/laserFurnace.png");
        ResourceLocation on = new ResourceLocation(Reference.MOD_ID + ":textures/blocks/laserFurnace1.png");

        if(furnace.isSmelting()) {
            Minecraft.getMinecraft().renderEngine.bindTexture(on);
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(off);
        }

        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F); //Why is this binding to the player
        GL11.glPopMatrix();
    }
}
