package com.clashOfSky.building.ResourceBuilding.BuildingExample;

import com.clashOfSky.building.ResourceBuilding.Goods;
import com.clashOfSky.building.ResourceBuilding.ResourceBuilding;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LogCamp extends ResourceBuilding {
    static final int[] produceOnceNum = {2,3,8,10,25,30,100,150,200};
    static final int[] produceOnceTimeMinute = {1,1,2,2,4,4,10,10,15};
    public LogCamp(Location loc, UUID owner) {
        super(loc, owner);
    }

    @Override
    public String getName() {
        return "伐木场";
    }

    @Override
    public List<Goods> getLevelUpResourceList() {
        List<Goods> GoodsList = new ArrayList<>();
        return GoodsList;
    }

    @Override
    public int getLevelUpTime() {
        return 0;
    }

    @Override
    public int getMaxLevel() {
        return 8;
    }

    @Override
    public boolean isMaxlevel() {
        return  level >= 8;
    }

    @Override
    public List<Goods> getGoodsType() {
        List<Goods> GoodsList = new ArrayList<>();
        GoodsList.add(new Goods(Material.OAK_LOG,produceOnceNum[level],"橡木原木"));
        return GoodsList;
    }

    @Override
    public int getMaxInventory() {
        return 128;
    }

    @Override
    public int getMinInventory() {
        return 0;
    }

    @Override
    public int getProduceOneGoodsTime() {
        return produceOnceTimeMinute[level] * 20 * 60;
    }
}
