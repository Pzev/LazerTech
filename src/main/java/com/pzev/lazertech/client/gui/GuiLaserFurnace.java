package com.pzev.lazertech.client.gui;

import com.pzev.lazertech.inventory.ContainerLaserFurnace;
import com.pzev.lazertech.reference.Reference;
import com.pzev.lazertech.tileentity.TileEntityLaserFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GuiLaserFurnace extends GuiContainer {

    private static final ResourceLocation GUI_LASER_FURNACE = new ResourceLocation(Reference.MOD_ID + ":textures/gui/furnace.png");
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("textures/gui/container/furnace.png");
    private TileEntityLaserFurnace furnace;
    boolean devMode = true;

    public GuiLaserFurnace(InventoryPlayer player, TileEntityLaserFurnace furnace) {
        super(new ContainerLaserFurnace(player, furnace));
        this.furnace = furnace;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(devMode ? furnaceGuiTextures : GUI_LASER_FURNACE);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        int amountRemaining;

        if(furnace.isSmelting()) {
            amountRemaining = furnace.getBurnTimeRemainingScaled(14);
            drawTexturedModalRect(x + 58, y + 51 - amountRemaining, 177, 14 - amountRemaining, 14, amountRemaining);
        }

        amountRemaining = furnace.getCookProgressScaled(24);
        drawTexturedModalRect(x + 79, y + 35, 176, 14, amountRemaining + 1, 16);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int color = Color.WHITE.getRGB();
        fontRendererObj.drawString(StatCollector.translateToLocal(furnace.getInventoryName()), xSize / 2 - fontRendererObj.getStringWidth(StatCollector.translateToLocal(furnace.getInventoryName())) / 2, 6, color);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), xSize - 60, ySize - 96 + 2, color, true);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
        super.drawScreen(mouseX, mouseY, f);
        //I assume you will need this in the future
    }
}
