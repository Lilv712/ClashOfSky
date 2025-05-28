package com.clashOfSky.AboutPlayer;

import com.clashOfSky.building.ResourceBuilding.Goods;
import org.bukkit.Material;
import org.bukkit.entity.Item;
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
            ItemStack itemStack = new ItemStack(material);
            int maxStack = material.getMaxStackSize();
            while (num > 0) {
                int giveAmount = Math.min(num, maxStack);
                itemStack.setAmount(giveAmount);
                player.getInventory().addItem(itemStack.clone()); // 必须 clone，否则会重复引用
                num -= giveAmount;
            }

            return true;
        }
    }
    static public boolean RemoveItemFromPlayerInventory(Player player, Material material, int num) {
        int remaining = num;
        ItemStack[] contents = player.getInventory().getContents();

        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];
            if (item == null) continue;
            if (item.getType() != material) continue;

            int amount = item.getAmount();
            if (amount <= remaining) {
                // 移除整个 ItemStack
                remaining -= amount;
                contents[i] = null;
            } else {
                // 只移除部分
                item.setAmount(amount - remaining);
                remaining = 0;
                break;
            }

            if (remaining <= 0) break;
        }

        player.getInventory().setContents(contents);
        player.updateInventory(); // 保证客户端同步显示
        return remaining == 0; // 返回是否成功移除全部数量
    }

    static public boolean RemoveItemFromPlayer(Player player, List<Goods> resourcesList){
//        检测物品数量是否足够
        for(Goods goods:resourcesList){
            if(goods.material == Material.AIR){
                if(MoneyManager.ShowMoney(player.getUniqueId()).compareTo(new BigDecimal(goods.num)) >= 0 ){
                    continue;
                }else return false;
            }

            int amountNum = PlayerStoreHouse.searchItemFromPlayerStoreHouse(player.getUniqueId(),goods.material);
            if(amountNum < goods.num)return false;
        }
//        开始扣除
        for(Goods goods:resourcesList){
            int removeNum = goods.num;
            int storeHouseNum = PlayerStoreHouse.searchItemFromPlayerStoreHouse(player.getUniqueId(),goods.material);
            if (removeNum <= storeHouseNum) {
                // 仅从仓库扣除
                PlayerStoreHouse.setItemToStoreHouse(player.getUniqueId(), goods.material, storeHouseNum - removeNum);
            } else {
                // 仓库不够，清空仓库，然后从背包扣除剩下的
                PlayerStoreHouse.setItemToStoreHouse(player.getUniqueId(), goods.material, 0);
                int leftToRemove = removeNum - storeHouseNum;
                PlayerItemGiver.RemoveItemFromPlayerInventory(player, goods.material, leftToRemove);
            }
        }
        return true;
    }
    static int CheckItemFromPlayerInventory(Player player,Material material){
        int amountFound = 0;
        for(ItemStack item:player.getInventory().getContents()){
            if(!(item == null))
                if(item.getType() == material)
                    amountFound += item.getAmount();
        }
        return amountFound;
    }
}
