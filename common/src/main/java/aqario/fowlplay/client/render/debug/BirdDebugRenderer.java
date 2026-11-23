package aqario.fowlplay.client.render.debug;

import aqario.fowlplay.common.util.DebugBirdData;
import aqario.fowlplay.core.FowlPlayDebugSubscriptions;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.client.renderer.debug.PathfindingRenderer;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.gizmos.TextGizmo;
import net.minecraft.util.debug.DebugValueAccess;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class BirdDebugRenderer implements DebugRenderer.SimpleDebugRenderer {
    private final Minecraft client;
    @Nullable
    private UUID targetedEntity;

    public BirdDebugRenderer(Minecraft minecraft) {
        this.client = minecraft;
    }

    private boolean isTargeted(DebugBirdData birdData) {
        return Objects.equals(this.targetedEntity, birdData.uuid());
    }

    @Override
    public void emitGizmos(double camX, double camY, double camZ, DebugValueAccess debugValueAccess, Frustum frustum, float partialTick) {
        debugValueAccess.forEachEntity(FowlPlayDebugSubscriptions.BIRDS, (entity, birdData) -> {
            if(this.client.player != null && this.client.player.closerThan(entity, 30.0)) {
                drawBirdData(birdData, this.isTargeted(birdData), camX, camY, camZ);
            }
        });
        DebugRenderer.getTargetedEntity(this.client.getCameraEntity(), 8).ifPresent(entity -> this.targetedEntity = entity.getUUID());
    }

    private static void drawBirdData(DebugBirdData birdData, boolean targeted, double camX, double camY, double camZ) {
        int i = 0;
        drawString(birdData.pos(), i++, birdData.name(), -1, 0.03F);
        drawString(birdData.pos(), i++, "trusting: " + Arrays.toString(birdData.trusting().toArray()), -3355444, 0.02F);
        drawString(birdData.pos(), i++, "flying: " + birdData.flying(), -1, 0.02F);
        drawString(birdData.pos(), i++, "perched: " + birdData.perched(), -1, 0.02F);
        drawString(birdData.pos(), i++, "ambient: " + birdData.ambient(), -1, 0.02F);
        drawString(birdData.pos(), i++, "move control: " + birdData.moveControl(), -1, 0.02F);
        drawString(birdData.pos(), i++, "navigation: " + birdData.navigation(), -1, 0.02F);

        int j = birdData.health() < birdData.maxHealth() ? -23296 : -1;
        drawString(
            birdData.pos(),
            i++,
            "health: " + String.format(Locale.ROOT, "%.1f", birdData.health()) + " / " + String.format(Locale.ROOT, "%.1f", birdData.maxHealth()),
            j,
            0.02F
        );

        if(!birdData.inventory().isEmpty()) {
            drawString(birdData.pos(), i, birdData.inventory(), -98404, 0.02F);
            i++;
        }

        for(String string : birdData.runningTasks()) {
            drawString(birdData.pos(), i++, string, -16711681, 0.02F);
        }

        for(String string : birdData.possibleActivities()) {
            drawString(birdData.pos(), i++, string, -16711936, 0.02F);
        }

        if(birdData.schedule() != null) {
            drawString(birdData.pos(), i++, birdData.schedule(), -23296, 0.02F);
        }

        if(targeted) {
            for(String string : Lists.reverse(birdData.memories())) {
                drawString(birdData.pos(), i++, string, -3355444, 0.02F);
            }
        }

        if(birdData.path() != null) {
            // PathfindingRenderer draws the line, the target box, and the node boxes.
            PathfindingRenderer.renderPath(birdData.path(), birdData.flying() ? 0.1F : 0.5F, false, false, camX, camY, camZ);
        }
    }

    private static void drawString(Vec3 pos, int offsetY, String string, int color, float size) {
        Gizmos.billboardText(string, pos.add(0.5, 2.4 + offsetY * 0.25, 0.5), TextGizmo.Style.forColor(color).withScale(size));
    }
}
