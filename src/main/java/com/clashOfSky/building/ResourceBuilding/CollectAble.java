package com.clashOfSky.building.ResourceBuilding;

import org.bukkit.entity.Player;

import java.util.List;

//用于支持/collect收集资源
public interface CollectAble {
    void collect(Player player);
    List<Goods> getGoodsType();
    int getInventory();
    int getMaxInventory();
    int getMinInventory();
    int getProduceOneGoodsTime();
}
