package com.clashOfSky.building.DefenceBuilding.DefenceBuildingExample;

import com.clashOfSky.building.BuildingFactory;
import com.clashOfSky.building.DefenceBuilding.DefenceBuilding;
import com.clashOfSky.building.ResourceBuilding.BuildingExample.BaseCamp;
import com.clashOfSky.building.ResourceBuilding.Goods;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class ArrowTower extends DefenceBuilding {
    static {
        BuildingFactory.buildingKindList.put("箭塔", ArrowTower.class);
    }
    public ArrowTower(Location loc, UUID owner) {
        super(loc, owner);
    }

    @Override
    public String getName() {
        return "箭塔";
    }

    @Override
    public void levelUp(Player player) {

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
}
