package com.clashOfSky.building;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Map;

//支持升级的建筑
public interface CanLevelUp {
    void levelUp(Player player);
    Map<Material, Integer> getResourceList();
    int getLevelUpTime();

}
