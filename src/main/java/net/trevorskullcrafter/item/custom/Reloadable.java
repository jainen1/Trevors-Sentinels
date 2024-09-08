package net.trevorskullcrafter.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface Reloadable {
    default void reload(ItemStack stack, World world, Entity user){}
}