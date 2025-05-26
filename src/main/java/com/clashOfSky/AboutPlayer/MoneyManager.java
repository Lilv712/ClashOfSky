package com.clashOfSky.AboutPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MoneyManager {
    static Map<UUID, BigDecimal> playerMoney = new HashMap<>();
    public BigDecimal ShowMoney(UUID player){
        checkAccount(player);
        return playerMoney.get(player);
    }
    public static void Pay(UUID Giver,String ReceiverName,BigDecimal num){
        UUID Receiver = getUUIDByName(ReceiverName);
        if(Receiver == null){
            Bukkit.getPlayer(Giver).sendMessage(ChatColor.RED + "玩家不存在！");
            return;
        }
        if(Receiver == Giver){
            Bukkit.getPlayer(Giver).sendMessage(ChatColor.RED + "不可向自己转账！");
            return;
        }
        checkAccount(Giver);
        checkAccount(Receiver);
        if(num.compareTo(BigDecimal.ZERO) < 0)return;
        BigDecimal GiverMoney = playerMoney.get(Giver);
        BigDecimal ReceiverMoney = playerMoney.get(Receiver);
        if(GiverMoney.compareTo(num) < 0){
            Bukkit.getPlayer(Giver).sendMessage(ChatColor.RED + "余额不足！");
            return;
        }
        playerMoney.put(Giver,GiverMoney.subtract(num));
        playerMoney.put(Receiver,ReceiverMoney.add(num));
        Bukkit.getPlayer(Giver).sendMessage(ChatColor.GREEN + "转账成功，已向"+Bukkit.getPlayer(Receiver).getName()+"转账$"+num.toString());
        if(!Bukkit.getPlayer(Receiver).isOnline())return;
        Bukkit.getPlayer(Receiver).sendMessage(ChatColor.GREEN + "收到来自"+Bukkit.getPlayer(Giver).getName()+"的$"+num.toString());
    }
    public static void addMoney(UUID player,BigDecimal num){
        checkAccount(player);
        playerMoney.compute(player, (k, Money) -> Money.add(num));
    }
    public static void reduceMoney(UUID player,BigDecimal num){
        checkAccount(player);
        playerMoney.compute(player, (k, Money) -> Money.subtract(num));
    }
    static void checkAccount(UUID uuid){
        if(playerMoney.containsKey(uuid))return;
        playerMoney.put(uuid,BigDecimal.ZERO);
    }
    public static UUID getUUIDByName(String playerName) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerName);

        // 判断玩家是否存在（曾经登录过服务器）
        if (offlinePlayer.hasPlayedBefore() || offlinePlayer.isOnline()) {
            return offlinePlayer.getUniqueId();
        } else {
            return null; // 玩家从未进服过，等同于不存在
        }
    }

}
