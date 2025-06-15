package com.arc.adventurepp.registry;

import com.arc.adventurepp.AdventurePP;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class APPPlacedFeature {
    public static final ResourceKey<PlacedFeature> NIGHTMARE_TREE = createKey("nightmare_tree_checked");

    public static void boostrap(final BootstrapContext<PlacedFeature> context) {
        register(context, NIGHTMARE_TREE, APPConfiguredFeature.LEMON_TREE,List.of());
    }

    private static ResourceKey<PlacedFeature> createKey(final String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, AdventurePP.of(name));
    }

    public static Holder.Reference<PlacedFeature> register(final BootstrapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final ResourceKey<ConfiguredFeature<?, ?>> featureKey, final List<PlacementModifier> placement) {
        final Holder<ConfiguredFeature<?, ?>> feature = context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(featureKey);
        return context.register(key, new PlacedFeature(feature, placement));
    }
}
