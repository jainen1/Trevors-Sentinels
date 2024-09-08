package net.trevorskullcrafter.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.trevorskullcrafter.TrevorsSentinels;

public class TeleporterBlockEntity extends BlockEntity {
    private ItemStack portkey = ItemStack.EMPTY;

    public TeleporterBlockEntity(BlockPos pos, BlockState state) { super(ModBlockEntities.TELEPORTER, pos, state); }

    public ItemStack getPortkey(){ return this.portkey; }
    public void setPortkey(ItemStack stack) { portkey = stack; markDirty(); }

    @Override protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        if(!portkey.isEmpty()) { nbt.put("portkey", portkey.encode(registryLookup)); }
    }

    @Override protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        if(nbt.contains(TrevorsSentinels.MOD_ID, NbtElement.COMPOUND_TYPE)) {
            var modidData = nbt.getCompound(TrevorsSentinels.MOD_ID);
            this.portkey = ItemStack.fromNbtOrEmpty(registryLookup, modidData.getCompound("portkey"));
        }
    }
}
