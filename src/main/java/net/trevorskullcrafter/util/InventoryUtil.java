package net.trevorskullcrafter.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public class InventoryUtil {
    public static boolean hasPlayerStackInInventory(PlayerEntity player, Item item){
        return getFirstInventoryIndex(player, item) != -1;
    }

    public static int getFirstInventoryIndex(PlayerEntity player, Item item){
        for(int i = 0; i < player.getInventory().size(); i++){ if(player.getInventory().getStack(i).isOf(item)) { return i; }}
        return -1;
    }
}