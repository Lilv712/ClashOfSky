package com.clashOfSky.building;

import org.bukkit.Location;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.UUID;

public class buildingFactory {
    static HashMap<String,Class<? extends Building>> buildingKindList = new HashMap<>();

    public static Building createBuilding(String name, Location loc, UUID owner){
        Class<? extends Building> build = buildingFactory.buildingKindList.get(name);
        if(build == null)return null;
        try {
            return build.getConstructor(Location.class).newInstance(loc);
        } catch (InvocationTargetException|InstantiationException|IllegalAccessException|NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
