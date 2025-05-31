package com.clashOfSky.AboutPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class Cmd_Pay implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player giver))return false;
        Player receiver = Bukkit.getPlayer(strings[0]);
        if(receiver == null){
            commandSender.sendMessage(ChatColor.RED + "玩家不存在!");
            return true;
        }
        MoneyManager.Pay(giver.getUniqueId(),strings[0],new BigDecimal(strings[1]));
        return true;
    }
}
