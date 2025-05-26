package com.clashOfSky.building;

import net.sandrohc.schematic4j.SchematicLoader;
import net.sandrohc.schematic4j.exception.ParsingException;
import net.sandrohc.schematic4j.schematic.Schematic;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class BuildingQunYuGe extends Building {
    static String BuildingName = "群玉阁";
    static {
        BuildingFactory.buildingKindList.put(BuildingName, BuildingQunYuGe.class);
    }

    public BuildingQunYuGe(Location loc, UUID owner) {
        super(loc, owner);
    }

    @Override
    public String getName() {
        return BuildingName;
    }

}
