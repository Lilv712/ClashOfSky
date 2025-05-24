package com.clashOfSky.building;

import org.bukkit.Location;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.UUID;

public class BuildingFactory {
    static HashMap<String,Class<? extends Building>> buildingKindList = new HashMap<>();

    public static Building createBuilding(String name, Location loc, UUID owner){
        Class<? extends Building> build = BuildingFactory.buildingKindList.get(name);
        if(build == null)return null;
        try {
            Building building = build.getConstructor(Location.class, UUID.class).newInstance(loc,owner);
            BuildingManager.addBuildingToBuildingList(building);
            BuildingManager.addBuildingToBuildingMap(building.incidence,building);
            return building;
        } catch (InvocationTargetException|InstantiationException|IllegalAccessException|NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
