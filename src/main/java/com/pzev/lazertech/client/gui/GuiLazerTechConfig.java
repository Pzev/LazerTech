package com.pzev.lazertech.client.gui;


import com.pzev.lazertech.config.Configurations;
import com.pzev.lazertech.reference.Reference;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class GuiLazerTechConfig extends GuiConfig {

    public GuiLazerTechConfig(GuiScreen parent) {
        super(parent, new ConfigElement<ConfigCategory>(Configurations.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Reference.MOD_ID, false, false, GuiLazerTechConfig
                .getAbridgedConfigPath(Configurations.config.toString()));
    }
}
