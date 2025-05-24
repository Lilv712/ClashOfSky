package com.clashOfSky.building;

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

            default:
                return false;
        }
    }
    void placeHere(String[] strings,CommandSender sender){
        if(!(sender instanceof Player player))return;
        player.sendMessage("注册的建筑列表：" + buildingFactory.buildingKindList.keySet());
        player.sendMessage("开始建造：" + strings[0]);
        Location loc = player.getLocation();
        Building build = buildingFactory.createBuilding(strings[0],loc,player.getUniqueId());
        build.placeBuilding();
    }
}
