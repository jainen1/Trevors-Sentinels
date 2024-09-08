package net.trevorskullcrafter.item.custom.unique;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.trevorskullcrafter.item.PhaserModifiersComponent;
import net.trevorskullcrafter.item.TSItems;
import net.trevorskullcrafter.item.custom.PhaserItem;
import net.trevorskullcrafter.sound.ModSounds;
import net.trevorskullcrafter.util.TextUtil;

import java.awt.*;

public class LivingPhaserItem extends PhaserItem {
	public LivingPhaserItem(Item.Settings settings) { super(settings); }

	@Override public Item getFuelItem() { return TSItems.Tech.MEAT_PELLET; }
	@Override public Color getProjectileColor(ItemStack stack) { return TextUtil.FLESH_PUS; }
	@Override public void playFuelSound(Entity entity) { entity.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1f, 2f + entity.getWorld().getRandom().nextFloat() * 0.4F); }

	@Override public SoundEvent getShootSound(PhaserModifiersComponent.Type type){ return switch (type){
		case PhaserModifiersComponent.Type.PISTOL -> SoundEvents.ENTITY_LLAMA_SPIT;
		case PhaserModifiersComponent.Type.SNIPER -> SoundEvents.ENTITY_LLAMA_SPIT;
		case PhaserModifiersComponent.Type.SHOTGUN -> SoundEvents.ENTITY_LLAMA_SPIT;
		case PhaserModifiersComponent.Type.RIFLE -> SoundEvents.ENTITY_LLAMA_SPIT;
		default -> SoundEvents.ENTITY_LLAMA_SPIT; };
	}
	@Override public SoundEvent getShootFailSound(PhaserModifiersComponent.Type type){ return switch (type){
		case PhaserModifiersComponent.Type.PISTOL -> ModSounds.FLESH_HIT;
		case PhaserModifiersComponent.Type.SNIPER -> ModSounds.FLESH_HIT;
		case PhaserModifiersComponent.Type.SHOTGUN -> ModSounds.FLESH_HIT;
		case PhaserModifiersComponent.Type.RIFLE -> ModSounds.FLESH_HIT;
		default -> ModSounds.FLESH_HIT; };
	}
	@Override public SoundEvent getReloadSound(PhaserModifiersComponent.Type type){ return switch (type){
		case PhaserModifiersComponent.Type.PISTOL -> SoundEvents.ENTITY_PLAYER_BURP;
		case PhaserModifiersComponent.Type.SNIPER -> SoundEvents.ENTITY_PLAYER_BURP;
		case PhaserModifiersComponent.Type.SHOTGUN -> SoundEvents.ENTITY_PLAYER_BURP;
		case PhaserModifiersComponent.Type.RIFLE -> SoundEvents.ENTITY_PLAYER_BURP;
		default -> SoundEvents.ENTITY_PLAYER_BURP; };
	}
}
