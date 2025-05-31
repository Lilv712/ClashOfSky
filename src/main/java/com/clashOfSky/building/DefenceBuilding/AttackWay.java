package com.clashOfSky.building.DefenceBuilding;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.util.Vector;


public class AttackWay {
    public static void arrowAttackOnce(Location spawnLocation, Location attackLocation,float damage){
        if(spawnLocation.getWorld() != attackLocation.getWorld())return;
        Vector vector = spawnLocation.toVector().subtract(attackLocation.toVector().normalize());
        Arrow arrow = spawnLocation.getWorld().spawnArrow(spawnLocation,vector,5,0);
        arrow.setDamage(damage);
    }
}
