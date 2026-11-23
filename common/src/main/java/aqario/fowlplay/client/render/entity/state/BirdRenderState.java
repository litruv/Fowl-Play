package aqario.fowlplay.client.render.entity.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

// Bird models animate from the entity, so the state carries it plus the partial tick.
public class BirdRenderState<E> extends LivingEntityRenderState {
    public E entity;
    public float partialTick;
}
