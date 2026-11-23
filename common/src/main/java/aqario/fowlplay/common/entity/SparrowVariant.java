package aqario.fowlplay.common.entity;

import aqario.fowlplay.core.FowlPlay;
import aqario.fowlplay.core.FowlPlayRegistries;
import aqario.fowlplay.core.platform.PlatformHelper;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;

public record SparrowVariant(Identifier texture) {
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<SparrowVariant>> PACKET_CODEC = ByteBufCodecs.holderRegistry(FowlPlayRegistries.SPARROW_VARIANT);
    public static final ResourceKey<SparrowVariant> BROWN = register("brown");
    public static final ResourceKey<SparrowVariant> PALE = register("pale");

    private static ResourceKey<SparrowVariant> register(String id) {
        ResourceKey<SparrowVariant> key = ResourceKey.create(FowlPlayRegistries.SPARROW_VARIANT, FowlPlay.id(id));
        Identifier texture = FowlPlay.id("textures/entity/sparrow/" + key.identifier().getPath() + "_sparrow.png");
        PlatformHelper.registerVariant(id, key, () -> new SparrowVariant(texture));
        return key;
    }

    public static void init() {
    }
}
