package com.clashOfSky.building;

import org.bukkit.entity.Player;

//可以填充资源的建筑
public interface FillAble {
    void fill(Player player);
    int getAutoFillTime();
    int getMaxFillnum();
}
