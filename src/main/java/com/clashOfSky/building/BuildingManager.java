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
    Map<Chunk, Set<Building>> buildingMap = new HashMap<>();
//    服务器中所有建筑的存储
    Map<UUID, Set<Building>> buildingList = new HashMap<>();

    void addBuildingToBuildingList(Building building){
        UUID owner = building.getOwner();
        //            如果该玩家没有建筑
        //            则为玩家创建建筑列表
        buildingList.computeIfAbsent(owner, k -> new HashSet<>())
                .add(building);
    }
    void addBuildingToBuildingMap(Incidence maxIncidence,Building building) {
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
    void removeBuildingFromBuildingMap(Incidence maxIncidence,Building building){
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
}
