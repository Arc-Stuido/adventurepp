package com.arc.adventurepp.registry;

import com.arc.adventurepp.Adventurepp;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class APPFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Adventurepp.MODID);

    public static <T extends Feature<?>> DeferredHolder<Feature<?>, T> register(final String name, final Supplier<T> sup) {
        return FEATURES.register(name, sup);
    }

    public static <F extends Feature<NoneFeatureConfiguration>> Holder.Reference<ConfiguredFeature<?, ?>> registerTo(final BootstrapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final DeferredHolder<Feature<?>, F> feature) {
        return context.register(key, new ConfiguredFeature<>(feature.get(), NoneFeatureConfiguration.INSTANCE));
    }

    public static void bootstrap(final BootstrapContext<ConfiguredFeature<?, ?>> context) {
        //registerTo(context, APPConfiguredFeature.LEMON_TREE, NIGHTMARE_TREE);
    }

}
