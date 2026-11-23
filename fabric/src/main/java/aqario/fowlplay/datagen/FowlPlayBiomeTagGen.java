package aqario.fowlplay.datagen;

import aqario.fowlplay.core.tags.FowlPlayBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.CompletableFuture;

public class FowlPlayBiomeTagGen extends FabricTagProvider<Biome> {
    private static final ResourceKey<Biome> WHITE_CLIFFS = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("natures_spirit", "white_cliffs"));
    private static final TagKey<Biome> IS_ALPINE = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("natures_spirit", "is_alpine"));
    private static final TagKey<Biome> IS_AUTUMN = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("natures_spirit", "is_autumn"));
    private static final TagKey<Biome> IS_COAST = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("natures_spirit", "is_coast"));
    private static final TagKey<Biome> IS_COLD = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("natures_spirit", "is_cold"));
    private static final TagKey<Biome> IS_CYPRESS = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("natures_spirit", "is_cypress"));
    private static final TagKey<Biome> IS_FIELD = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("natures_spirit", "is_field"));
    private static final TagKey<Biome> IS_FRONTIER = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("natures_spirit", "is_frontier"));
    private static final TagKey<Biome> IS_FREEZING = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("natures_spirit", "is_freezing"));
    private static final TagKey<Biome> IS_WETLAND = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("natures_spirit", "is_wetland"));
    private static final TagKey<Biome> BEACH = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("c", "beach"));
    private static final TagKey<Biome> FOREST = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("c", "forest"));
    private static final TagKey<Biome> RIVER = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("c", "river"));
    private static final TagKey<Biome> SWAMP = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("c", "swamp"));
    private static final TagKey<Biome> TREE_CONIFEROUS = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("c", "tree_coniferous"));
    private static final TagKey<Biome> TREE_DECIDUOUS = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("c", "tree_deciduous"));
    private static final TagKey<Biome> VEGETATION_SPARSE = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("c", "vegetation_sparse"));
    private static final TagKey<Biome> IS_TREE_CONIFEROUS = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("c", "is_tree_coniferous"));
    private static final TagKey<Biome> IS_TREE_DECIDUOUS = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("c", "is_tree_deciduous"));

    public FowlPlayBiomeTagGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BIOME, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        this.builder(FowlPlayBiomeTags.SPAWNS_BLUE_JAYS)
            .addOptionalTag(IS_ALPINE)
            .addOptionalTag(IS_AUTUMN)
            .addOptionalTag(IS_COLD)
            .addOptionalTag(IS_CYPRESS)
            .addOptionalTag(IS_FRONTIER)
            .addOptionalTag(IS_FREEZING)
            .addOptionalTag(ConventionalBiomeTags.IS_FOREST)
            .addOptionalTag(FOREST)
            .addOptionalTag(TREE_CONIFEROUS)
            .addOptionalTag(TREE_DECIDUOUS)
            .addOptionalTag(IS_TREE_CONIFEROUS)
            .addOptionalTag(IS_TREE_DECIDUOUS)
            .addOptionalTag(ConventionalBiomeTags.IS_CONIFEROUS_TREE)
            .addOptionalTag(ConventionalBiomeTags.IS_DECIDUOUS_TREE);
        this.builder(FowlPlayBiomeTags.SPAWNS_CARDINALS)
            .addOptionalTag(IS_ALPINE)
            .addOptionalTag(IS_AUTUMN)
            .addOptionalTag(IS_COLD)
            .addOptionalTag(IS_CYPRESS)
            .addOptionalTag(IS_FRONTIER)
            .addOptionalTag(IS_FREEZING)
            .addOptionalTag(ConventionalBiomeTags.IS_FOREST)
            .addOptionalTag(FOREST)
            .addOptionalTag(TREE_CONIFEROUS)
            .addOptionalTag(TREE_DECIDUOUS)
            .addOptionalTag(IS_TREE_CONIFEROUS)
            .addOptionalTag(IS_TREE_DECIDUOUS)
            .addOptionalTag(ConventionalBiomeTags.IS_CONIFEROUS_TREE)
            .addOptionalTag(ConventionalBiomeTags.IS_DECIDUOUS_TREE);
        this.builder(FowlPlayBiomeTags.SPAWNS_CHICKADEES)
            .addOptionalTag(IS_AUTUMN)
            .addOptionalTag(IS_COLD)
            .addOptionalTag(IS_FRONTIER)
            .addOptionalTag(ConventionalBiomeTags.IS_FOREST)
            .addOptionalTag(FOREST)
            .addOptionalTag(TREE_DECIDUOUS)
            .addOptionalTag(IS_TREE_DECIDUOUS)
            .addOptionalTag(ConventionalBiomeTags.IS_DECIDUOUS_TREE);
        this.builder(FowlPlayBiomeTags.SPAWNS_CROWS)
            .addOptionalTag(IS_ALPINE)
            .addOptionalTag(IS_AUTUMN)
            .addOptionalTag(IS_COLD)
            .addOptionalTag(IS_CYPRESS)
            .addOptionalTag(IS_FRONTIER)
            .addOptionalTag(IS_FREEZING)
            .addOptionalTag(ConventionalBiomeTags.IS_FOREST)
            .addOptionalTag(FOREST)
            .addOptionalTag(TREE_CONIFEROUS)
            .addOptionalTag(TREE_DECIDUOUS)
            .addOptionalTag(IS_TREE_CONIFEROUS)
            .addOptionalTag(IS_TREE_DECIDUOUS)
            .addOptionalTag(ConventionalBiomeTags.IS_CONIFEROUS_TREE)
            .addOptionalTag(ConventionalBiomeTags.IS_DECIDUOUS_TREE);
        this.builder(FowlPlayBiomeTags.SPAWNS_DUCKS)
            .addOptionalTag(IS_WETLAND)
            .addOptionalTag(RIVER)
            .addOptionalTag(SWAMP)
            .addOptionalTag(ConventionalBiomeTags.IS_RIVER)
            .addOptionalTag(ConventionalBiomeTags.IS_SWAMP)
            .addOptionalTag(IS_AUTUMN)
            .addOptionalTag(IS_COLD)
            .addOptionalTag(IS_CYPRESS)
            .addOptionalTag(IS_FRONTIER)
            .addOptionalTag(ConventionalBiomeTags.IS_FOREST)
            .addOptionalTag(FOREST)
            .addOptionalTag(TREE_CONIFEROUS)
            .addOptionalTag(TREE_DECIDUOUS)
            .addOptionalTag(IS_TREE_CONIFEROUS)
            .addOptionalTag(IS_TREE_DECIDUOUS)
            .addOptionalTag(ConventionalBiomeTags.IS_CONIFEROUS_TREE)
            .addOptionalTag(ConventionalBiomeTags.IS_DECIDUOUS_TREE);
        this.builder(FowlPlayBiomeTags.SPAWNS_GEESE)
            .addOptionalTag(IS_WETLAND)
            .addOptionalTag(RIVER)
            .addOptionalTag(SWAMP)
            .addOptionalTag(ConventionalBiomeTags.IS_RIVER)
            .addOptionalTag(ConventionalBiomeTags.IS_SWAMP)
            .addOptionalTag(IS_AUTUMN)
            .addOptionalTag(IS_COLD)
            .addOptionalTag(IS_CYPRESS)
            .addOptionalTag(IS_FRONTIER)
            .addOptionalTag(ConventionalBiomeTags.IS_FOREST)
            .addOptionalTag(FOREST)
            .addOptionalTag(TREE_CONIFEROUS)
            .addOptionalTag(TREE_DECIDUOUS)
            .addOptionalTag(IS_TREE_CONIFEROUS)
            .addOptionalTag(IS_TREE_DECIDUOUS)
            .addOptionalTag(ConventionalBiomeTags.IS_CONIFEROUS_TREE)
            .addOptionalTag(ConventionalBiomeTags.IS_DECIDUOUS_TREE);
        this.builder(FowlPlayBiomeTags.SPAWNS_GULLS)
            .addOptionalTag(IS_COAST)
            .addOptionalTag(BEACH)
            .addOptionalTag(ConventionalBiomeTags.IS_BEACH)
            .addOptionalTag(ConventionalBiomeTags.IS_STONY_SHORES);
        this.builder(FowlPlayBiomeTags.SPAWNS_HAWKS)
            .addOptionalTag(IS_ALPINE)
            .addOptionalTag(IS_AUTUMN)
            .addOptionalTag(IS_COLD)
            .addOptionalTag(IS_CYPRESS)
            .addOptionalTag(IS_FIELD)
            .addOptionalTag(IS_FRONTIER)
            .addOptionalTag(IS_FREEZING)
            .addOptionalTag(VEGETATION_SPARSE)
            .addOptionalTag(ConventionalBiomeTags.IS_VEGETATION_SPARSE)
            .addOptionalTag(ConventionalBiomeTags.IS_VEGETATION_SPARSE_OVERWORLD)
            .add(Biomes.PLAINS)
            .add(Biomes.SAVANNA)
            .add(Biomes.SAVANNA_PLATEAU)
            .add(Biomes.SPARSE_JUNGLE)
            .add(Biomes.SUNFLOWER_PLAINS)
            .add(Biomes.WINDSWEPT_FOREST)
            .add(Biomes.WINDSWEPT_HILLS);
        this.builder(FowlPlayBiomeTags.SPAWNS_PENGUINS)
            .add(Biomes.SNOWY_PLAINS)
            .add(Biomes.SNOWY_BEACH);
        this.builder(FowlPlayBiomeTags.SPAWNS_PIGEONS)
            .addOptionalTag(ConventionalBiomeTags.IS_STONY_SHORES)
            .addOptional(WHITE_CLIFFS);
        this.builder(FowlPlayBiomeTags.SPAWNS_RAVENS)
            .addOptionalTag(IS_ALPINE)
            .addOptionalTag(IS_AUTUMN)
            .addOptionalTag(IS_COLD)
            .addOptionalTag(IS_CYPRESS)
            .addOptionalTag(IS_FRONTIER)
            .addOptionalTag(IS_FREEZING)
            .addOptionalTag(ConventionalBiomeTags.IS_FOREST)
            .addOptionalTag(FOREST)
            .addOptionalTag(TREE_CONIFEROUS)
            .addOptionalTag(TREE_DECIDUOUS)
            .addOptionalTag(IS_TREE_CONIFEROUS)
            .addOptionalTag(IS_TREE_DECIDUOUS)
            .addOptionalTag(ConventionalBiomeTags.IS_CONIFEROUS_TREE)
            .addOptionalTag(ConventionalBiomeTags.IS_DECIDUOUS_TREE);
        this.builder(FowlPlayBiomeTags.SPAWNS_ROBINS)
            .addOptionalTag(IS_ALPINE)
            .addOptionalTag(IS_AUTUMN)
            .addOptionalTag(IS_COLD)
            .addOptionalTag(IS_CYPRESS)
            .addOptionalTag(IS_FRONTIER)
            .addOptionalTag(IS_FREEZING)
            .addOptionalTag(ConventionalBiomeTags.IS_FOREST)
            .addOptionalTag(FOREST)
            .addOptionalTag(TREE_CONIFEROUS)
            .addOptionalTag(TREE_DECIDUOUS)
            .addOptionalTag(IS_TREE_CONIFEROUS)
            .addOptionalTag(IS_TREE_DECIDUOUS)
            .addOptionalTag(ConventionalBiomeTags.IS_CONIFEROUS_TREE)
            .addOptionalTag(ConventionalBiomeTags.IS_DECIDUOUS_TREE);
        this.builder(FowlPlayBiomeTags.SPAWNS_SPARROWS)
            .addOptionalTag(IS_ALPINE)
            .addOptionalTag(IS_AUTUMN)
            .addOptionalTag(IS_COLD)
            .addOptionalTag(IS_CYPRESS)
            .addOptionalTag(IS_FIELD)
            .addOptionalTag(IS_FRONTIER)
            .addOptionalTag(IS_FREEZING)
            .addOptionalTag(IS_WETLAND)
            .addOptionalTag(ConventionalBiomeTags.IS_FOREST)
            .addOptionalTag(FOREST)
            .addOptionalTag(TREE_CONIFEROUS)
            .addOptionalTag(TREE_DECIDUOUS)
            .addOptionalTag(IS_TREE_CONIFEROUS)
            .addOptionalTag(IS_TREE_DECIDUOUS)
            .addOptionalTag(ConventionalBiomeTags.IS_CONIFEROUS_TREE)
            .addOptionalTag(ConventionalBiomeTags.IS_DECIDUOUS_TREE);
    }
}
