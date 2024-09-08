package net.trevorskullcrafter.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.function.BiFunction;

public class ModRenderLayers extends RenderLayer {
    public ModRenderLayers(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    private static final BiFunction<Identifier, Boolean, RenderLayer> ENTITY_TRANSLUCENT_CULL_EMISSIVE = Util.memoize((texture, affectsOutline) -> {
        MultiPhaseParameters multiPhaseParameters = MultiPhaseParameters.builder().program(ShaderProgram.EYES_PROGRAM).texture(new Texture(texture, false, false))
                .transparency(TRANSLUCENT_TRANSPARENCY).cull(ENABLE_CULLING).writeMaskState(COLOR_MASK).lightmap(DISABLE_LIGHTMAP).overlay(ENABLE_OVERLAY_COLOR).build(affectsOutline);
        return of("entity_translucent_cull_emissive", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, true, true, multiPhaseParameters);
    });


    public static RenderLayer getEntityTranslucentCullEmissive(Identifier texture, boolean affectsOutline) {
        return ENTITY_TRANSLUCENT_CULL_EMISSIVE.apply(texture, affectsOutline);
    }

    public static RenderLayer getEntityTranslucentCullEmissive(Identifier texture) {
        return getEntityTranslucentCullEmissive(texture, true);
    }
}
