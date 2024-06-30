package com.black_dog20.morphingequipment.common.datagen;

import com.black_dog20.bml.datagen.BaseRecipeProvider;
import com.black_dog20.morphingequipment.MorphingEquipment;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static com.black_dog20.morphingequipment.common.items.ModItems.MORPHING_TOOL;

public class GeneratorRecipes extends BaseRecipeProvider {

    public GeneratorRecipes(DataGenerator generator) {
        super(generator.getPackOutput(), MorphingEquipment.MOD_ID);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MORPHING_TOOL.get())
                .define('s', Items.IRON_SWORD)
                .define('a', Items.IRON_AXE)
                .define('h', Items.IRON_SHOVEL)
                .define('p', Items.IRON_PICKAXE)
                .define('d', Tags.Items.GEMS_DIAMOND)
                .define('r', Tags.Items.DUSTS_REDSTONE)
                .pattern("rsr")
                .pattern("adp")
                .pattern("rhr")
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .save(consumer);
    }
}
