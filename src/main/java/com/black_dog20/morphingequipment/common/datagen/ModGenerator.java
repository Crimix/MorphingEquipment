package com.black_dog20.morphingequipment.common.datagen;

import com.black_dog20.morphingequipment.MorphingEquipment;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MorphingEquipment.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        if (event.includeServer())
            registerServerProviders(event.getGenerator());

        if (event.includeClient())
            registerClientProviders(event.getGenerator(), event);
    }

    private static void registerServerProviders(DataGenerator generator) {
        generator.addProvider(true, new GeneratorRecipes(generator));
    }

    private static void registerClientProviders(DataGenerator generator, GatherDataEvent event) {
        ExistingFileHelper helper = event.getExistingFileHelper();

        generator.addProvider(true, new GeneratorItemModels(generator, helper));
        generator.addProvider(true, new GeneratorLanguageEnglish(generator));
    }
}
