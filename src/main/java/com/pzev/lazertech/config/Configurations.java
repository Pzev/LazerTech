package com.pzev.lazertech.config;

import net.minecraftforge.common.config.Configuration;



public class Configurations {

    public static Configuration config;

    public static boolean configExists = true;

    public static boolean refreshConfig()
    {


        if (config.hasChanged()) {
            config.save();
        }
        return true;
    }

}

