package com.clashOfSky.building.ResourceBuilding;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlayerItemGiver {
    static public boolean GiveItemToPlayer(Player player,Material material,int num){
        if(player.isOnline())return false;

        return true;
    }
}
