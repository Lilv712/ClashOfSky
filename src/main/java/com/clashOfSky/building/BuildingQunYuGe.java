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
    static Schematic staticSchematic;
    static Size size;
    static {
        try{
            staticSchematic = SchematicLoader.load(new File("./" + buildingFolder + "/" + BuildingName + ".litematic"));
            size = new Size(
                    staticSchematic.width(),
                    staticSchematic.length(),
                    staticSchematic.height());
            System.out.println("加载建筑 群玉阁 成功");
        }catch (IOException | ParsingException e){
            e.printStackTrace();
            staticSchematic = null;
        }
    }
    static {
        buildingFactory.buildingKindList.put(BuildingName, BuildingQunYuGe.class);
    }

    public BuildingQunYuGe(Location loc, UUID owner) {
        super(loc, owner);
        incidence = new Incidence(loc,
                staticSchematic.width(),
                staticSchematic.length(),
                staticSchematic.height());
    }

    @Override
    public String getName() {
        return BuildingName;
    }

    @Override
    public Schematic getSchematic() {
        return staticSchematic;
    }

}
