package com.arc.adventurepp.registry;

import com.arc.adventurepp.Adventurepp;
import net.minecraft.core.Cloner;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class APPWorldGen {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, APPFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, APPPlacedFeature::boostrap);

    public static RegistrySetBuilder.PatchedRegistries createLookup(final HolderLookup.Provider vanillaProvider) {
        final RegistryAccess.Frozen registryAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
        return BUILDER.buildPatch(registryAccess, vanillaProvider, new Cloner.Factory());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(final String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Adventurepp.of(name));
    }
}
