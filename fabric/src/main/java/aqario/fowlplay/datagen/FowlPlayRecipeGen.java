package aqario.fowlplay.datagen;

import aqario.fowlplay.core.FowlPlayItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class FowlPlayRecipeGen extends FabricRecipeProvider {
    public FowlPlayRecipeGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public String getName() {
        return "Fowl Play recipes";
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider lookup, RecipeOutput exporter) {
        return new RecipeProvider(lookup, exporter) {
            @Override
            public void buildRecipes() {
                ShapedRecipeBuilder.shaped(lookup.lookupOrThrow(Registries.ITEM), RecipeCategory.DECORATIONS, FowlPlayItems.SCARECROW.get(), 1)
                    .define('#', Items.HAY_BLOCK)
                    .define('/', Items.STICK)
                    .pattern(" # ")
                    .pattern("/#/")
                    .pattern(" / ")
                    .unlockedBy(getHasName(Items.HAY_BLOCK), has(Items.HAY_BLOCK))
                    .save(exporter);
            }
        };
    }
}
