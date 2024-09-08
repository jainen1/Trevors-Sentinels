package net.trevorskullcrafter.particle;

import net.minecraft.client.particle.ExplosionLargeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class MuzzleFlashParticle extends ExplosionLargeParticle {
	protected MuzzleFlashParticle(ClientWorld world, double x, double y, double z, double scale, SpriteProvider spriteProvider) {
		super(world, x, y, z, scale, spriteProvider);
		this.maxAge = 1;
	}

	public static class Factory implements ParticleFactory<SimpleParticleType> {
		private final SpriteProvider spriteProvider;
		public Factory(SpriteProvider spriteProvider) { this.spriteProvider = spriteProvider; }

		public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			MuzzleFlashParticle suspendParticle = new MuzzleFlashParticle(clientWorld, d, e, f, 1.5, spriteProvider);
			suspendParticle.setSprite(this.spriteProvider);
			suspendParticle.setColor((float) g, (float) h, (float) i);
			return suspendParticle;
		}
	}
}
