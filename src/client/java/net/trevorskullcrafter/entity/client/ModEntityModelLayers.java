package net.trevorskullcrafter.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.TrevorsSentinels;

public class ModEntityModelLayers {
    public static final EntityModelLayer PLASMA_PROJECTILE = new EntityModelLayer(Identifier.of(TrevorsSentinels.MOD_ID, "plasma_projectile"), "main");
    public static final EntityModelLayer SENTINEL = new EntityModelLayer(Identifier.of(TrevorsSentinels.MOD_ID, "sentinel"), "main");
	public static final EntityModelLayer ROOMBA = new EntityModelLayer(Identifier.of(TrevorsSentinels.MOD_ID, "roomba"), "main");
	public static final EntityModelLayer FLORBUS = new EntityModelLayer(Identifier.of(TrevorsSentinels.MOD_ID, "florbus"), "main");
}
