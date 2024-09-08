package net.trevorskullcrafter.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;
import net.trevorskullcrafter.block.entity.ModBlockEntities;
import net.trevorskullcrafter.block.entity.TeleporterBlockEntity;
import net.trevorskullcrafter.effect.ModEffects;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.PortkeyComponent;
import net.trevorskullcrafter.util.TextUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class TeleporterBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final MapCodec<TeleporterBlock> CODEC = createCodec(TeleporterBlock::new);

    public TeleporterBlock(Settings settings) { super(settings); }

    @Override protected MapCodec<? extends BlockWithEntity> getCodec() { return CODEC; }

    @Override protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(stack.get(ModDataComponentTypes.PORTKEY) != null){
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof TeleporterBlockEntity teleporter && teleporter.getPortkey() == ItemStack.EMPTY){
                teleporter.setPortkey(stack.copyWithCount(1));
                if(!player.getAbilities().creativeMode) { stack.decrement(1); }
                return ItemActionResult.SUCCESS;
            }
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if(blockEntity instanceof TeleporterBlockEntity teleporter && teleporter.getPortkey() != ItemStack.EMPTY){
            Direction side = hit.getSide();
            if(side != Direction.UP && side != Direction.DOWN){
                BlockPos dropPos = switch(side){
                    case Direction.NORTH -> pos.north(); case Direction.SOUTH -> pos.south(); case Direction.EAST -> pos.east(); case Direction.WEST -> pos.west();
                    default -> throw new IllegalStateException("Unexpected value: " + side); };
                world.spawnEntity(new ItemEntity(world, dropPos.getX()+0.5, dropPos.getY(), dropPos.getZ()+0.5, teleporter.getPortkey()));
                teleporter.setPortkey(ItemStack.EMPTY);
                return ActionResult.SUCCESS;
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }

    @Override public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
        if(world instanceof ServerWorld serverWorld && world.isReceivingRedstonePower(pos) && ((entity instanceof LivingEntity living && !living.isSneaking() &&
                (!living.hasStatusEffect(ModEffects.TETHERED) || (living instanceof PlayerEntity player && player.getAbilities().creativeMode))) || !(entity instanceof LivingEntity))){
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof TeleporterBlockEntity teleporter){
                ItemStack stack = teleporter.getPortkey();
                if(stack != null){
                    PortkeyComponent portkey = stack.get(ModDataComponentTypes.PORTKEY);
                    if(portkey != null && portkey.active()){
                        GlobalPos globalPos = portkey.globalPos();
                        if(entity.getWorld().getRegistryKey() == globalPos.dimension()) {
                            BlockPos targetPos = globalPos.pos();
                            serverWorld.playSound(null, entity.getBlockPos(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.PLAYERS, 1.0f, 1.5f);
                            entity.teleport(serverWorld, targetPos.getX() + 0.5, targetPos.getY(), targetPos.getZ() + 0.5, Set.of(), entity.getYaw(), entity.getPitch());
                            serverWorld.playSound(null, targetPos, SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.PLAYERS, 1.0f, 1.5f);
                            if (entity instanceof LivingEntity living) {
                                living.addStatusEffect(new StatusEffectInstance(ModEffects.TETHERED, (int) (80 + Math.pow(entity.getBlockPos().getSquaredDistance(targetPos) / 10, 0.33))));
                                if (living instanceof PlayerEntity player) {
                                    player.sendMessage(TextUtil.coloredText(Text.translatable("tooltip.trevorssentinels.teleported",
                                            targetPos.getX(), targetPos.getY(), targetPos.getZ()), TextUtil.TETHERED), true);
                                }
                            }
                        } else {
                            serverWorld.playSound(null, entity.getBlockPos(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.PLAYERS, 0.6f, 1.1f);
                            serverWorld.playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK, SoundCategory.PLAYERS, 1.2f, 0.8f);
                            if(entity instanceof LivingEntity living){
                                living.addStatusEffect(new StatusEffectInstance(ModEffects.TETHERED, 60));
                                if(living instanceof PlayerEntity player){
                                    player.sendMessage(Text.translatable("tooltip.trevorssentinels.impossible_teleport").formatted(Formatting.RED), true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Nullable @Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) { return ModBlockEntities.TELEPORTER.instantiate(pos, state); }
    @Override public BlockRenderType getRenderType(BlockState state) { return BlockRenderType.MODEL; }
}
