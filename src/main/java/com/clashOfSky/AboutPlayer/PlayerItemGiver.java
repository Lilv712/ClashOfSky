package com.clashOfSky.AboutPlayer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerItemGiver {
    static public boolean giveItemToPlayer(Player player,Material material,int num){
        if(PlayerStoreHouse.whiteItemMap.containsKey(material)){
            return PlayerStoreHouse.addItemToStoreHouse(player.getUniqueId(),material,num);
        }else{
            ItemStack itemStack = new ItemStack(material,num);
            player.getInventory().addItem(itemStack);
            return true;
        }
    }
}
