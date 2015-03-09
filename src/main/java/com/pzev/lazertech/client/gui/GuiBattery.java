package com.pzev.lazertech.client.gui;

import com.pzev.lazertech.inventory.ContainerBattery;
import com.pzev.lazertech.tileentity.TileEntityBattery;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiBattery extends GuiContainer
{
    private static final ResourceLocation gui = new ResourceLocation("lazertech:textures/gui/battery-gui.png");


    public GuiBattery(InventoryPlayer inventoryPlayer, TileEntityBattery tileEntityBattery)
    {
        super(new ContainerBattery(inventoryPlayer, tileEntityBattery));
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {


    }

    private boolean mouseInside(Slot slot, int x, int y)
    {
        return x >= slot.xDisplayPosition && x <= slot.xDisplayPosition + 16 && y >= slot.yDisplayPosition && y <= slot.yDisplayPosition + 16;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(gui);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

    }


}
