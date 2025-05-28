package com.clashOfSky.building.ResourceBuilding.BuildingExample;

import com.clashOfSky.building.ResourceBuilding.Goods;
import com.clashOfSky.building.ResourceBuilding.ResourceBuilding;
import org.bukkit.Location;

import java.util.List;
import java.util.UUID;

public class LogCamp extends ResourceBuilding {
    public LogCamp(Location loc, UUID owner) {
        super(loc, owner);
    }

    @Override
    public String getName() {
        return "伐木场";
    }

    @Override
    public List<Goods> getLevelUpResourceList() {
        return List.of();
    }

    @Override
    public int getLevelUpTime() {
        return 0;
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public boolean isMaxlevel() {
        return false;
    }

    @Override
    public List<Goods> getGoodsType() {
        return List.of();
    }

    @Override
    public int getMaxInventory() {
        return 0;
    }

    @Override
    public int getMinInventory() {
        return 0;
    }

    @Override
    public int getProduceOneGoodsTime() {
        return 0;
    }
}
