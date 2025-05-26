package com.clashOfSky.AboutPlayer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;

public class PlayerItemGiver {
    static public boolean giveItemToPlayer(Player player,Material material,int num){
        if(material == Material.AIR){
            MoneyManager.addMoney(player.getUniqueId(),new BigDecimal(num));
            return true;
        }
        if(PlayerStoreHouse.whiteItemMap.containsKey(material)){
            return PlayerStoreHouse.addItemToStoreHouse(player.getUniqueId(),material,num);
        }else{
            ItemStack itemStack = new ItemStack(material,num);
            player.getInventory().addItem(itemStack);
            return true;
        }
    }
}
