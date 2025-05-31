package com.clashOfSky.building;

import net.sandrohc.schematic4j.SchematicLoader;
import net.sandrohc.schematic4j.exception.ParsingException;
import net.sandrohc.schematic4j.schematic.Schematic;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class BuildingYingHua extends Building {
    static String BuildingName = "樱花庄园";
    static Size size;
    static {
        BuildingFactory.buildingKindList.put(BuildingName, BuildingYingHua.class);
    }

    public BuildingYingHua(Location loc, UUID owner) {
        super(loc, owner);
    }

    public String getName() {
        return BuildingName;
    }

}
