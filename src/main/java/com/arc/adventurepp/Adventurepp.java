package com.arc.adventurepp;

import com.arc.adventurepp.registry.APPFeatures;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

@Mod(Adventurepp.MODID)
public class Adventurepp {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "adventurepp";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final ResourceBundle LANGUAGE_RESOURCES = ResourceBundle.getBundle("lang", Locale.getDefault());

    public Adventurepp(IEventBus modEventBus, ModContainer modContainer) {
        APPFeatures.FEATURES.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.SERVER, Config.SPEC);
    }
    public static ResourceLocation of(String path){
        return ResourceLocation.fromNamespaceAndPath(MODID,path);
    }
}
