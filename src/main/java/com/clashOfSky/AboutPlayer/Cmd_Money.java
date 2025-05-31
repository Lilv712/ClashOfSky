package com.clashOfSky.AboutPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class Cmd_Money implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player;
        if(strings != null){
            player = Bukkit.getPlayer(strings[0]);
            if(player == null){
                commandSender.sendMessage(ChatColor.RED + "玩家不存在!");
            }
        }else{
            if(!(commandSender instanceof Player))return false;
            player = (Player) commandSender;
        }
        BigDecimal Money =  MoneyManager.ShowMoney(player.getUniqueId());
        commandSender.sendMessage(ChatColor.GREEN + "$:" + Money);
        return true;
    }
}
