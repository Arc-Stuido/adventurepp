package com.arc.adventurepp.events;

import com.arc.adventurepp.datagen.AdvancementGen;
import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModCommonEvent {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        event.addProvider(new AdvancementGen(generator.getPackOutput(),event.getLookupProvider(),event.getExistingFileHelper()));
    }

}
