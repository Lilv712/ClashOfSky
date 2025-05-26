package com.clashOfSky.building;

import com.clashOfSky.building.ResourceBuilding.Goods;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Map;

//用于支持/collect收集资源
public interface CollectAble {
    void collect(Player player);
    Goods getGoodsType();
    int getInventory();
    int getMaxInventory();
    int getMinInventory();
    int getProduceOneGoodsTime();
}
