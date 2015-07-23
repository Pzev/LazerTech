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

    private void adjustRotatePivotMeta(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        GL11.glPushMatrix();
        GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) + 0.5F);
        ResourceLocation off = new ResourceLocation(Reference.MOD_ID + ":textures/blocks/laserFurnace.png");
        ResourceLocation on = new ResourceLocation(Reference.MOD_ID + ":textures/blocks/laserFurnace1.png");

        if(tile instanceof TileEntityLaserFurnace) {
            TileEntityLaserFurnace furnace = (TileEntityLaserFurnace) tile;

            if(furnace.isSmelting()) {
                Minecraft.getMinecraft().renderEngine.bindTexture(on);
            } else {
                Minecraft.getMinecraft().renderEngine.bindTexture(off);
            }

            GL11.glPushMatrix();
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    private void adjustLightFixture(World world, int x, int y, int z, Block block) {
        Tessellator tess = Tessellator.instance;
        float brightness = block.getLightValue(world, x, y, z);
        int skyLight = world.getLightBrightnessForSkyBlocks(x, y, z, 0);
        int modulousModifier = skyLight % 65536;
        int divModifier = skyLight / 65535;
        tess.setColorOpaque_F(brightness, brightness, brightness);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier);
    }
}
