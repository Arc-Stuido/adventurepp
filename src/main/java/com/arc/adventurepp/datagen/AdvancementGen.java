package com.arc.adventurepp.datagen;

import com.arc.adventurepp.AdventurePP;
import com.arc.adventurepp.registry.APPAdvancements;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.checkerframework.checker.units.qual.C;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class AdvancementGen extends AdvancementProvider {
    public AdvancementGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new Generator()));
    }

    public static final class Generator implements AdvancementProvider.AdvancementGenerator {
        Function<ResourceLocation, Criterion<PlayerTrigger.TriggerInstance>> CRITERION_IN_STRUCTURE = resourceLocation -> PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(DeferredHolder.create(ResourceKey.create(Registries.STRUCTURE,resourceLocation))));
        Supplier<Criterion<PlayerTrigger.TriggerInstance>> CRITERION_TICK = PlayerTrigger.TriggerInstance::tick;

        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            AdvancementGeneratorHelper.of(APPAdvancements.Catacomb.SMALL_ROOT)
                    .icon(Items.ZOMBIE_HEAD)
                    .criterion(CRITERION_IN_STRUCTURE.apply(AdventurePP.of("")))
                    .save(saver,existingFileHelper);
        }
        private void challenger(Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper){
            AdvancementGeneratorHelper.of(APPAdvancements.Challenger.CHALLENGER_ROOT)
                    .icon(Items.NETHER_STAR)
                    .criterion(CRITERION_TICK.get())
                    .save(saver,existingFileHelper);
            AdvancementGeneratorHelper.of(APPAdvancements.Challenger.CHALLENGER_ROOT)
                    .icon(Items.GOLDEN_BOOTS)
                    .criterion(PlayerTrigger.TriggerInstance.tick())
                    .save(saver,existingFileHelper);

            AdvancementGeneratorHelper.of(APPAdvancements.Challenger.SPYGLASS_VILLAGER)
                    .parent("minecraft:adventure/spyglass_at_parrot")
                    .icon(Items.SPYGLASS)
                    .criterion(UsingItemTrigger.TriggerInstance.lookingAt(
                                    EntityPredicate.Builder.entity().subPredicate(PlayerPredicate.Builder.player().setLookingAt(EntityPredicate.Builder.entity().of(EntityType.VILLAGER)).build()),
                                    ItemPredicate.Builder.item().of(Items.SPYGLASS)))
                    .save(saver,existingFileHelper);
        }
    }

}
