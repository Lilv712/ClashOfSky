package com.clashOfSky.AboutPlayer;

import com.clashOfSky.building.ResourceBuilding.Goods;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;
import java.util.List;

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
    static public boolean RemoveItemFromPlayer(Player player, List<Goods> resourcesList){
        for(Goods goods:resourcesList){
            if(PlayerStoreHouse.whiteItemMap.containsKey(goods.material)
//                没写完
        }
    }
}
