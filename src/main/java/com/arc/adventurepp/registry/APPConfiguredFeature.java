package com.arc.adventurepp.registry;

import com.arc.adventurepp.AdventurePP;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class APPConfiguredFeature {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON_TREE = createKey("nightmare_tree");

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(final String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, AdventurePP.of(name));
    }
}
