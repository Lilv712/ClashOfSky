package com.clashOfSky.building;

import com.clashOfSky.building.ResourceBuilding.Goods;
import org.bukkit.entity.Player;

import java.util.List;

//支持升级的建筑
public interface CanLevelUp {
    void levelUp(Player player);
    List<Goods> getLevelUpResourceList();
    int getLevelUpTime();
    int getMaxLevel();
    boolean isMaxlevel();

}
