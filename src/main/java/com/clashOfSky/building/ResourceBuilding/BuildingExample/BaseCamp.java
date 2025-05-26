package com.clashOfSky.building.ResourceBuilding.BuildingExample;

import com.clashOfSky.building.BuildingFactory;
import com.clashOfSky.building.BuildingQunYuGe;
import com.clashOfSky.building.CanLevelUp;
import com.clashOfSky.building.ResourceBuilding.Goods;
import com.clashOfSky.building.ResourceBuilding.ResourceBuilding;
import net.sandrohc.schematic4j.SchematicLoader;
import net.sandrohc.schematic4j.schematic.Schematic;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BaseCamp extends ResourceBuilding implements CanLevelUp {
    static {
        BuildingFactory.buildingKindList.put("大本营", BuildingQunYuGe.class);
    }
    static final int[] produceOnceNum = {10,14,18,22,26};
    static final int[] maxInventory = {};
    static final int[] minInventory = {};
    static final int[] produceOneGoodsTime = {};
    static final int[] maxHealth = {50,55,60,65,70}

    static final int maxLevel = 5;

    public BaseCamp(Location loc, UUID owner) {
        super(loc, owner);
    }

    public String getName() {
        return "大本营";
    }

    @Override
    public Goods getGoodsType() {
        return new Goods(Material.AIR,produceOnceNum[level],"Money");
    }

    @Override
    public int getMaxInventory() {
        return maxInventory[level];
    }

    @Override
    public int getMinInventory() {
        return minInventory[level];
    }

    @Override
    public int getProduceOneGoodsTime() {
        return produceOneGoodsTime[level];
    }

    @Override
    public void levelUp(Player player) {

    }

    @Override
    public Map<Material, Integer> getResourceList() {
        int[] levelUpMoney = {250,2000,24000,88000};
        HashMap<Material,Integer> RescourceMap = new HashMap<>();
        RescourceMap.put(Material.AIR,levelUpMoney[level]);
        return RescourceMap;
    }

    @Override
    public int getLevelUpTime() {
        return 36 * 60 * 20;
    }
}
