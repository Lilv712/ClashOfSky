package com.clashOfSky.building;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.maven.model.Build;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.*;

public class BuildingManager {
//    建筑分布图，建筑会把所有能够影响到的区块都加入哈希表
    static Map<Chunk, Set<Building>> buildingMap = new HashMap<>();
//    服务器中所有建筑的存储
    static Map<UUID, Set<Building>> buildingList = new HashMap<>();
//    buildingplace方法中每tick最多放置的方块
    static int maxBlocksPlacedPerTick = 1000;
    public static void addBuildingToBuildingList(Building building){
        UUID owner = building.getOwner();
        //            如果该玩家没有建筑
        //            则为玩家创建建筑列表
        buildingList.computeIfAbsent(owner, k -> new HashSet<>())
                .add(building);
    }
    public static void addBuildingToBuildingMap(Incidence maxIncidence,Building building) {
        Chunk chunkbase = maxIncidence.location.getChunk();
        int baseX = chunkbase.getX();
        int baseZ = chunkbase.getZ();
        int x = (maxIncidence.size.X + 15) / 16;
        int z = (maxIncidence.size.Z + 15) / 16;
        for (int xchunk = baseX; xchunk < baseX + x; xchunk++)
            for (int zchunk = baseZ; zchunk < baseZ + z; zchunk++) {
                Chunk chunk = chunkbase.getWorld().getChunkAt(xchunk, zchunk);
                Set<Building> set = buildingMap.computeIfAbsent(chunk, k -> new HashSet<>());
                set.add(building);
            }
    }
    public static void removeBuildingFromBuildingMap(Incidence maxIncidence,Building building){
        Chunk chunkbase = maxIncidence.location.getChunk();
        int baseX = chunkbase.getX();
        int baseZ = chunkbase.getZ();
        int x = (maxIncidence.size.X + 15) / 16;
        int z = (maxIncidence.size.Z + 15) / 16;
        for (int xchunk = baseX; xchunk < baseX + x; xchunk++)
            for (int zchunk = baseZ; zchunk < baseZ + z; zchunk++) {
                Chunk chunk = chunkbase.getWorld().getChunkAt(xchunk, zchunk);
                Set<Building> set = buildingMap.get(chunk);
                if(set == null)continue;
                set.remove(building);
                if(set.isEmpty())
                    buildingMap.remove(chunk);
            }
    }
    public static Building searchBuilding(Location loc){
        Chunk chunk = loc.getChunk();
        Set<Building> maybeBuildingList = buildingMap.get(chunk);
        if(maybeBuildingList == null)return null;
        for(Building building : maybeBuildingList){
            if(building.incidence.isInBuilding(loc))return building;
        }
        return null;
    }
    public static Set<Building> searchBuilding(Incidence incidence){
        Set<Building> returnSet = new HashSet<>();
        Chunk chunkbase = incidence.location.getChunk();
        int baseX = chunkbase.getX();
        int baseZ = chunkbase.getZ();
        int chunkWidth = (incidence.size.X + 15) / 16;
        int chunkLength = (incidence.size.Z + 15) / 16;
        for (int xchunk = baseX; xchunk < baseX + chunkWidth; xchunk++)
            for (int zchunk = baseZ; zchunk < baseZ + chunkLength; zchunk++) {
                Chunk chunk = chunkbase.getWorld().getChunkAt(xchunk, zchunk);
                Set<Building> set = buildingMap.get(chunk);
                if(set == null)continue;
                for(Building building : set){
                    if(building.incidence.isInBuilding(incidence))
                        returnSet.add(building);
                }
            }
        return returnSet;
    }
}
