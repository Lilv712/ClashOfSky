package com.clashOfSky.building;

import com.clashOfSky.building.ResourceBuilding.CollectAble;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class BuildingCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length == 0){
            return false;
        }
        String[] SonCommand = Arrays.copyOfRange(strings,1,strings.length);
        switch (strings[0]){
            case "placehere":
                placeHere(SonCommand,commandSender);
                return true;
            case "info":
                info(commandSender);
                return true;
            case "collect":
                collect(commandSender);
            default:
                return false;
        }
    }
    void placeHere(String[] strings,CommandSender sender){
        if(!(sender instanceof Player player))return;
        player.sendMessage("注册的建筑列表：" + BuildingFactory.buildingKindList.keySet());
        player.sendMessage("开始建造：" + strings[0]);
        Location loc = player.getLocation();
        Building build = BuildingFactory.createBuilding(strings[0],loc,player.getUniqueId());
    }
    void info(CommandSender sender){
        if(!(sender instanceof Player player))return;
        Location loc = player.getLocation();
        Building building = BuildingManager.searchBuilding(loc);
        if(building == null)return;
        player.sendMessage(building.getInfo());
    }
    void collect(CommandSender sender){
        if(!(sender instanceof Player player))return;
        Location loc = player.getLocation();
        Building building = BuildingManager.searchBuilding(loc);
        if(building == null)return;
        if(!(building instanceof CollectAble collectAble))return;
        collectAble.collect(player);
    }
}
