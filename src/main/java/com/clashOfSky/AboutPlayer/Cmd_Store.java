package com.clashOfSky.AboutPlayer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Cmd_Store implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player player))return false;
        if(strings.length == 0){
            player.sendMessage(PlayerStoreHouse.ShowStore(player.getUniqueId()));
            return true;
        }
        switch (strings[0]){
            case "in":
                inStore(player,strings);
                return true;
            case "out":
                outStore(player,strings);
                return true;
            default:
                player.sendMessage(ChatColor.RED + "未知子命令");
                return false;
        }
    }
    void inStore(Player player,String[] strings){
        Material material = PlayerStoreHouse.searchMaterialFromWhiteMap(strings[1]);
        if(material == null){
            player.sendMessage(ChatColor.RED + "该物品不能被存储！");
            return;
        }
        int num = Integer.parseInt(strings[2]);
        if(num < 0)return;
        if(!PlayerItemGiver.RemoveItemFromPlayerInventory(player,material,num))return;
        PlayerStoreHouse.addItemToStoreHouse(player.getUniqueId(),material,num);
    }
    void outStore(Player player,String[] strings){
        Material material = PlayerStoreHouse.searchMaterialFromWhiteMap(strings[1]);
        if(material == null){
            player.sendMessage(ChatColor.RED + "该物品不能被存储！");
            return;
        }
        int num = Integer.parseInt(strings[2]);
        if(num < 0)return;
        if(PlayerStoreHouse.searchItemFromPlayerStoreHouse(player.getUniqueId(),material) < num)return;
        PlayerStoreHouse.addItemToStoreHouse(player.getUniqueId(),material,num * -1);
        PlayerItemGiver.giveItemToPlayer(player,material,num);
    }
}
