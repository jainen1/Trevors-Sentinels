package net.trevorskullcrafter.item.custom.unique;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.custom.PillarAligned;
import net.trevorskullcrafter.util.Pillars;

import java.util.List;
import java.util.random.RandomGenerator;

public class KingsBladeItem extends SwordItem implements PillarAligned {
    public KingsBladeItem(ToolMaterial toolMaterial, Settings settings) { super(toolMaterial, settings); }

    @Override public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        if(world instanceof ServerWorld serverWorld && hand == Hand.MAIN_HAND){
            if(user.isSneaking()){
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 5, 255,false,false,false));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,5,3,false,false,false));
                serverWorld.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 2.0F, RandomGenerator.getDefault().nextFloat());
                user.sendMessage(Text.literal("Parry!").formatted(Formatting.GRAY),true);
                user.getItemCooldownManager().set(this, 100);
            } return TypedActionResult.success(itemStack);
        } return super.use(world, user, hand);
    }

    @Override public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) { return true; }

	private int state(ItemStack stack){
		List<Integer> runes = stack.get(ModDataComponentTypes.KINGS_RUNES);
		int[][][] rune_board = {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, {{10, 11, 12}, {13, 14, 15}, {16, 17, 18}}, {{19, 20, 21}, {22, 23, 24}, {25, 26, 27}}};
		if(runes == null){ return 0; }
		else { return rune_board[runes.get(0)][runes.get(1)][runes.get(2)]; }
	}

	private String runeOf(int num) {
		return switch (num) {
			case 1 -> "ᚾ"; case 2 -> "A"; case 3 -> "B";
			case 4 -> "ᛁ"; case 5 -> "C"; case 6 -> "D";
			case 7 -> "ᛉ"; case 8 -> "E"; case 9 -> "F";
			default -> "-";
		};
	}

	@Override public Pillars.Pillar getPillar(ItemStack stack) { return Pillars.PURITY; }

	@Override public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType config) {
		List<Integer> runes = stack.get(ModDataComponentTypes.KINGS_RUNES);
		if(runes == null) { stack.set(ModDataComponentTypes.KINGS_RUNES, List.of(1, 4, 7)); }
		else {
			tooltip.add(Text.literal(runeOf(runes.get(0)) + " " + runeOf(runes.get(1)) + " " + runeOf(runes.get(2))).formatted(Formatting.GRAY));
			switch (state(stack)){
				case 1, 4, 7, 10, 13, 16, 19, 22, 25 -> {
					tooltip.add(Text.literal("Ice Shield").formatted(Formatting.AQUA));
					tooltip.add(Text.literal("Right click to parry!").formatted(Formatting.AQUA));
				}
				case 2, 5, 8, 11, 14, 17, 20, 23, 26 -> {
					tooltip.add(Text.literal("Blizzard Barrage").formatted(Formatting.AQUA));
					tooltip.add(Text.literal("Attacks release snowflake shurikens!").formatted(Formatting.AQUA));
				}
				case 3, 6, 9, 12, 15, 18, 21, 24, 27 -> {
					tooltip.add(Text.literal("Snow Blast").formatted(Formatting.AQUA));
					tooltip.add(Text.literal("Right click to release a freezing snowball!").formatted(Formatting.AQUA));
				}
				default -> tooltip.add(Text.literal("NaN"));
			}
		}
		super.appendTooltip(stack, context, tooltip, config);
	}
}
