package com.arc.adventurepp.datagen;

import net.minecraft.advancements.*;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.function.Consumer;

public class AdvancementGeneratorHelper {
    public static ResourceLocation EMPTY_BACKGROUND = null;
    private final ResourceLocation id;
    Advancement.Builder builder = Advancement.Builder.advancement();
    ItemStack icon = Items.AIR.getDefaultInstance();
    Component title = Component.literal("title");
    Component description = Component.literal("description");
    ResourceLocation background = EMPTY_BACKGROUND;
    AdvancementType type = AdvancementType.GOAL;
    boolean showToast = true;
    boolean announceChat = true;
    boolean hidden = false;
    ArrayList<String> criteria = new ArrayList<>();
    private int count = 0;
    private boolean requireAll = true;

    AdvancementGeneratorHelper(ResourceLocation id) {
        this.id = id;
    }

    public static AdvancementGeneratorHelper of(ResourceLocation id) {
        return new AdvancementGeneratorHelper(id);
    }

    public AdvancementGeneratorHelper parent(AdvancementHolder parent) {
        this.builder.parent(parent);
        return this;
    }
    public AdvancementGeneratorHelper parent(String parent) {
        this.builder.parent(AdvancementSubProvider.createPlaceholder(parent));
        return this;
    }

    public AdvancementGeneratorHelper parent(ResourceLocation parent) {
        this.builder.parent(AdvancementSubProvider.createPlaceholder(parent.toString()));
        return this;
    }

    public AdvancementGeneratorHelper icon(Item icon) {
        this.icon = icon.getDefaultInstance();
        return this;
    }

    public AdvancementGeneratorHelper icon(ItemStack icon) {
        this.icon = icon;
        return this;
    }

    public AdvancementGeneratorHelper title(String title) {
        this.title = Component.literal(title);
        return this;
    }

    public AdvancementGeneratorHelper title(Component title) {
        this.title = title;
        return this;
    }

    public AdvancementGeneratorHelper description(String description) {
        this.description = Component.literal(description);
        return this;
    }

    public AdvancementGeneratorHelper description(Component description) {
        this.description = description;
        return this;
    }

    public AdvancementGeneratorHelper background(ResourceLocation background) {
        this.background = background;
        return this;
    }

    public AdvancementGeneratorHelper type(AdvancementType type) {
        this.type = type;
        return this;
    }

    public AdvancementGeneratorHelper showToast(boolean showToast) {
        this.showToast = showToast;
        return this;
    }

    public AdvancementGeneratorHelper announceChat(boolean announceChat) {
        this.announceChat = announceChat;
        return this;
    }

    public AdvancementGeneratorHelper hidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public AdvancementGeneratorHelper rewards(AdvancementRewards.Builder rewards) {
        this.builder.rewards(rewards);
        return this;
    }

    public AdvancementGeneratorHelper criterion(String name, Criterion<?> criterion) {
        this.builder.addCriterion(name, criterion);
        criteria.add(name);
        return this;
    }

    public AdvancementGeneratorHelper criterion(Criterion<?> criterion) {
        String key = "criterion" + count++;
        this.builder.addCriterion(key, criterion);
        criteria.add(key);
        return this;
    }

    public AdvancementGeneratorHelper requirements(AdvancementRequirements requirements) {
        this.requireAll = false;
        this.builder.requirements(requirements);
        return this;
    }

    public AdvancementGeneratorHelper requireAll() {
        AdvancementRequirements.allOf(criteria);
        return this;
    }

    public Advancement.Builder build() {
        builder.display(
                icon,
                // The advancement title and description. Don't forget to add translations for these!
                title,
                description,
                // The background texture. Use null if you don't want a background texture (for non-root advancements).
                background,
                // The frame type. Valid values are AdvancementType.TASK, CHALLENGE, or GOAL.
                type,
                // Whether to show the advancement toast or not.
                showToast,
                // Whether to announce the advancement into chat or not.
                announceChat,
                // Whether the advancement should be hidden or not.
                hidden
        );
        if (requireAll) {
            AdvancementRequirements.allOf(criteria);
        }
        return builder;
    }

    public void save(Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
        build().save(saver, id, existingFileHelper);
    }

}
