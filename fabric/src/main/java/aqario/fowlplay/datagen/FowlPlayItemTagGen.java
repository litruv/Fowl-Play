package aqario.fowlplay.datagen;

import aqario.fowlplay.core.tags.FowlPlayItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("deprecation")
public class FowlPlayItemTagGen extends FabricTagProvider.ItemTagProvider {
    private static final Identifier WORM = Identifier.fromNamespaceAndPath("angling", "worm");

    public FowlPlayItemTagGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        this.valueLookupBuilder(FowlPlayItemTags.BLUE_JAY_FOOD)
            .add(Items.BEETROOT_SEEDS)
            .add(Items.GLOW_BERRIES)
            .add(Items.MELON_SEEDS)
            .add(Items.PUMPKIN_SEEDS)
            .add(Items.SWEET_BERRIES)
            .add(Items.TORCHFLOWER_SEEDS)
            .add(Items.WHEAT_SEEDS);
        this.valueLookupBuilder(FowlPlayItemTags.CARDINAL_FOOD)
            .add(Items.BEETROOT_SEEDS)
            .add(Items.GLOW_BERRIES)
            .add(Items.MELON_SEEDS)
            .add(Items.PUMPKIN_SEEDS)
            .add(Items.SWEET_BERRIES)
            .add(Items.TORCHFLOWER_SEEDS)
            .add(Items.WHEAT_SEEDS);
        this.valueLookupBuilder(FowlPlayItemTags.CHICKADEE_FOOD)
            .add(Items.BEETROOT_SEEDS)
            .add(Items.GLOW_BERRIES)
            .add(Items.MELON_SEEDS)
            .add(Items.PUMPKIN_SEEDS)
            .add(Items.SWEET_BERRIES)
            .add(Items.TORCHFLOWER_SEEDS)
            .add(Items.WHEAT_SEEDS);
        this.valueLookupBuilder(FowlPlayItemTags.CROW_FOOD)
            .addOptionalTag(ConventionalItemTags.FOODS)
            .add(Items.BEETROOT_SEEDS)
            .add(Items.EGG)
            .add(Items.MELON_SEEDS)
            .add(Items.PUMPKIN_SEEDS)
            .add(Items.TORCHFLOWER_SEEDS)
            .add(Items.TURTLE_EGG)
            .add(Items.WHEAT_SEEDS);
        this.valueLookupBuilder(FowlPlayItemTags.DUCK_FOOD)
            .add(Items.BEETROOT_SEEDS)
            .add(Items.BREAD)
            .add(Items.COD)
            .add(Items.MELON_SEEDS)
            .add(Items.PUMPKIN_SEEDS)
            .add(Items.SALMON)
            .add(Items.SEAGRASS)
            .add(Items.TORCHFLOWER_SEEDS)
            .add(Items.TROPICAL_FISH)
            .add(Items.WHEAT_SEEDS);
        // TagAppender takes values only, so external entries go through the raw builder.
        this.getOrCreateRawBuilder(FowlPlayItemTags.DUCK_FOOD).addOptionalElement(WORM);
        this.valueLookupBuilder(FowlPlayItemTags.GOOSE_FOOD)
            .add(Items.BEETROOT_SEEDS)
            .add(Items.BREAD)
            .add(Items.COD)
            .add(Items.MELON_SEEDS)
            .add(Items.PUMPKIN_SEEDS)
            .add(Items.SALMON)
            .add(Items.SEAGRASS)
            .add(Items.TORCHFLOWER_SEEDS)
            .add(Items.TROPICAL_FISH)
            .add(Items.WHEAT_SEEDS);
        this.getOrCreateRawBuilder(FowlPlayItemTags.GOOSE_FOOD).addOptionalElement(WORM);
        this.valueLookupBuilder(FowlPlayItemTags.GULL_FOOD)
            .addOptionalTag(ConventionalItemTags.FOODS)
            .add(Items.BEETROOT_SEEDS)
            .add(Items.EGG)
            .add(Items.MELON_SEEDS)
            .add(Items.PUMPKIN_SEEDS)
            .add(Items.TORCHFLOWER_SEEDS)
            .add(Items.TURTLE_EGG)
            .add(Items.WHEAT_SEEDS);
        this.valueLookupBuilder(FowlPlayItemTags.HAWK_FOOD)
            .add(Items.EGG)
            .add(Items.TURTLE_EGG)
            .addOptionalTag(ConventionalItemTags.RAW_MEAT_FOODS)
            .addOptionalTag(ConventionalItemTags.RAW_MEATS_FOODS);
        this.valueLookupBuilder(FowlPlayItemTags.PENGUIN_FOOD)
            .add(Items.COD)
            .add(Items.SALMON)
            .add(Items.TROPICAL_FISH);
        this.valueLookupBuilder(FowlPlayItemTags.PIGEON_FOOD)
            .addOptionalTag(ConventionalItemTags.FOODS)
            .add(Items.BEETROOT_SEEDS)
            .add(Items.MELON_SEEDS)
            .add(Items.PUMPKIN_SEEDS)
            .add(Items.TORCHFLOWER_SEEDS)
            .add(Items.WHEAT_SEEDS);
        this.valueLookupBuilder(FowlPlayItemTags.RAVEN_FOOD)
            .addOptionalTag(ConventionalItemTags.FOODS)
            .add(Items.BEETROOT_SEEDS)
            .add(Items.EGG)
            .add(Items.MELON_SEEDS)
            .add(Items.PUMPKIN_SEEDS)
            .add(Items.TORCHFLOWER_SEEDS)
            .add(Items.TURTLE_EGG)
            .add(Items.WHEAT_SEEDS);
        this.valueLookupBuilder(FowlPlayItemTags.ROBIN_FOOD)
            .add(Items.BEETROOT_SEEDS)
            .add(Items.GLOW_BERRIES)
            .add(Items.MELON_SEEDS)
            .add(Items.PUMPKIN_SEEDS)
            .add(Items.SWEET_BERRIES)
            .add(Items.TORCHFLOWER_SEEDS)
            .add(Items.WHEAT_SEEDS);
        this.getOrCreateRawBuilder(FowlPlayItemTags.ROBIN_FOOD).addOptionalElement(WORM);
        this.valueLookupBuilder(FowlPlayItemTags.SPARROW_FOOD)
            .add(Items.BEETROOT_SEEDS)
            .add(Items.MELON_SEEDS)
            .add(Items.PUMPKIN_SEEDS)
            .add(Items.TORCHFLOWER_SEEDS)
            .add(Items.WHEAT_SEEDS);
    }
}
