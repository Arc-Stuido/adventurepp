package com.arc.adventurepp.datagen;

import com.arc.adventurepp.Adventurepp;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementGen extends AdvancementProvider {
    public AdvancementGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of());
    }

    private static final class Generator implements AdvancementProvider.AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            Advancement.Builder builder = Advancement.Builder.advancement();
            builder.parent(AdvancementSubProvider.createPlaceholder("minecraft:story/root"));
            builder.display(
                    new ItemStack(Items.GRASS_BLOCK),
                    // The advancement title and description. Don't forget to add translations for these!
                    Component.translatable("advancements.examplemod.example_advancement.title"),
                    Component.translatable("advancements.examplemod.example_advancement.description"),
                    // The background texture. Use null if you don't want a background texture (for non-root advancements).
                    null,
                    // The frame type. Valid values are AdvancementType.TASK, CHALLENGE, or GOAL.
                    AdvancementType.GOAL,
                    // Whether to show the advancement toast or not.
                    true,
                    // Whether to announce the advancement into chat or not.
                    true,
                    // Whether the advancement should be hidden or not.
                    false
            );

            builder.rewards(
                    // Alternatively, use addExperience() to add to an existing builder.
                    AdvancementRewards.Builder.experience(100)
                            // Alternatively, use loot() to create a new builder.
                            .addLootTable(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath("minecraft", "chests/igloo")))
                            // Alternatively, use recipe() to create a new builder.
                            .addRecipe(ResourceLocation.fromNamespaceAndPath("minecraft", "iron_ingot"))
                            // Alternatively, use function() to create a new builder.
                            .runs(Adventurepp.of("function"))
            );

// Adds a criterion with the given name to the advancement. Use the corresponding trigger instance's static method.
            builder.addCriterion("pickup_dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIRT));

// Adds a requirements handler. Minecraft natively provides allOf() and anyOf(), more complex requirements
// must be implemented manually. Only has an effect with two or more criteria.
            builder.requirements(AdvancementRequirements.allOf(List.of("pickup_dirt")));

// Save the advancement to disk, using the given resource location. This returns an AdvancementHolder,
// which may be stored in a variable and used as a parent by other advancement builders.
            builder.save(saver, Adventurepp.of("name"), existingFileHelper);
        }
    }
}
