package net.trevorskullcrafter.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trevorskullcrafter.block.entity.ModBlockEntities;
import net.trevorskullcrafter.block.entity.PhaseplateBlockEntity;
import net.trevorskullcrafter.item.TSItems;
import net.trevorskullcrafter.util.TextUtil;
import org.jetbrains.annotations.Nullable;

public class PhaseplateBlock extends BlockWithEntity {
    public PhaseplateBlock(Settings settings) { super(settings); }
    @Override protected MapCodec<? extends PhaseplateBlock> getCodec() { return createCodec(PhaseplateBlock::new); }
    @Nullable @Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) { return ModBlockEntities.PHASEPLATE.instantiate(pos, state); }
    @Override public BlockRenderType getRenderType(BlockState state) { return BlockRenderType.MODEL; }

    @Override protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(stack.isOf(TSItems.Tech.PALETTE_CLEANSER)){
            if(world.getBlockEntity(pos) instanceof PhaseplateBlockEntity phaseplateBlockEntity){
                phaseplateBlockEntity.color = TextUtil.WHITE.getRGB();
                phaseplateBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);
            }
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    //@Override protected void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
    //    if(projectile instanceof PhaserProjectileEntity phaserProjectile){
    //        world.getBlockEntity(hit.getBlockPos(), ModBlockEntities.PHASEPLATE).ifPresent(phaseplateBlockEntity -> phaseplateBlockEntity.setColor(phaserProjectile.getColor()));
    //    }
    //}
}
