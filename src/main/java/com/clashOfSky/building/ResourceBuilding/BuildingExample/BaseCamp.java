package com.clashOfSky.building.ResourceBuilding.BuildingExample;

import com.clashOfSky.ClashOfSky;
import com.clashOfSky.building.BuildingFactory;
import com.clashOfSky.building.BuildingQunYuGe;
import com.clashOfSky.building.CanLevelUp;
import com.clashOfSky.building.ResourceBuilding.Goods;
import com.clashOfSky.building.ResourceBuilding.ResourceBuilding;
import net.sandrohc.schematic4j.SchematicLoader;
import net.sandrohc.schematic4j.schematic.Schematic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.*;

public class BaseCamp extends ResourceBuilding implements CanLevelUp {
    static {
        BuildingFactory.buildingKindList.put("大本营", BaseCamp.class);
    }
    static final int[] produceOnceNum = {10,14,18,22,26};
    static final int[] maxInventory = {200,200,200,200,200};
    static final int[] minInventory = {0,0,0,0,0};
    static final int[] maxHealth = {50,55,60,65,70};
    static final int[] UpdateTime = {100,100,100,100,100};

    static final int maxLevel = 5;

    public BaseCamp(Location loc, UUID owner) {
        super(loc, owner);
        level = 0;

    }

    public String getName() {
        return "大本营";
    }

    @Override
    public List<Goods> getGoodsType() {
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Goods(Material.AIR,produceOnceNum[level],"Money"));
        return goodsList;
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
        return 36;
    }

    @Override
    public void levelUp(Player player) {

        new BukkitRunnable(){
            @Override
            public void run(){
                if(level < maxLevel)
                    level++;
                if(player.isOnline()) player.sendMessage(ChatColor.GREEN + "升级成功！");
            }
        }.runTaskLater(ClashOfSky.instance,getLevelUpTime());
    }

    @Override
    public List<Goods> getResourceList() {
        int[] levelUpMoney = {250,2000,24000,88000};
        List<Goods> rescourceList = new ArrayList<>();
        rescourceList.add(new Goods(Material.AIR,levelUpMoney[level],"Money"));
        return rescourceList;
    }

    @Override
    public int getLevelUpTime() {
        return  UpdateTime[level];
    }
}
