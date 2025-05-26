package com.clashOfSky.building;

import com.clashOfSky.building.ResourceBuilding.Goods;
import org.bukkit.entity.Player;

//可以填充资源的建筑
public interface FillAble {
    void fill(Player player);
    Goods getMaterialType();
    int getAutoFillTime();
    int getMaxFillnum();
}
