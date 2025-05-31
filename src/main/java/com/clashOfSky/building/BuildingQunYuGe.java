package com.clashOfSky.building;

import org.bukkit.Location;

import java.util.UUID;

public class BuildingQunYuGe extends Building {
    static String BuildingName = "群玉阁";
    static {
        BuildingFactory.buildingKindList.put(BuildingName, BuildingQunYuGe.class);
    }

    public BuildingQunYuGe(Location loc, UUID owner) {
        super(loc, owner);
    }

}
