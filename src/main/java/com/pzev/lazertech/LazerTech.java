package com.pzev.lazertech;

import com.pzev.lazertech.API.Laser;
import com.pzev.lazertech.handler.ConfigurationHandler;
import com.pzev.lazertech.handler.LazerTechGuiHandler;
import com.pzev.lazertech.init.ModBlocks;
import com.pzev.lazertech.init.ModItems;
import com.pzev.lazertech.init.ModTileEntities;
import com.pzev.lazertech.init.Recipes;
import com.pzev.lazertech.proxy.IProxy;
import com.pzev.lazertech.reference.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory = "com.pzev.lazertech.client.gui.GuiFactory")

public class LazerTech {

    @Mod.Instance(Reference.MOD_ID)
    public static LazerTech instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new LazerTechGuiHandler());
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        ModItems.init();

        ModBlocks.init();
        ModTileEntities.init();


    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        Recipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event)
    {
        Laser.renderArchived();
    }


}
