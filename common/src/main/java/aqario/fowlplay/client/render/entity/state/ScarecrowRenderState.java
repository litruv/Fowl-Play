package aqario.fowlplay.client.render.entity.state;

import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

// Scarecrow models are humanoid, so its state extends HumanoidRenderState.
public class ScarecrowRenderState<E> extends HumanoidRenderState {
    public E entity;
    public float partialTick;
}
