package com.arc.adventurepp.registry;

import com.arc.adventurepp.AdventurePP;
import net.minecraft.resources.ResourceLocation;

import static com.arc.adventurepp.AdventurePP.of;

public class APPAdvancements {
    public static class Catacomb{
        public static final ResourceLocation SMALL_ROOT = of("enter_small_catacomb");
        public static final ResourceLocation MEDIUM_ROOT = of("enter_medium_catacomb");
        public static final ResourceLocation LARGE_ROOT = of("enter_large_catacomb");
    }
    public static class Challenger{
        public static final ResourceLocation CHALLENGER_ROOT = of("challenger_root");
        public static final ResourceLocation SPYGLASS_VILLAGER = of("spyglass_villager");
    }
}
