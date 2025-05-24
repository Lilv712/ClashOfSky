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
    static Schematic staticSchematic;
    static Size size;
    static {
        try{
            staticSchematic = SchematicLoader.load(new File("./" + buildingFolder + "/" + BuildingName + ".litematic"));
            size = new Size(
                    staticSchematic.width(),
                    staticSchematic.length(),
                    staticSchematic.height());
            System.out.println("加载建筑 樱花庄园 成功");
        }catch (IOException | ParsingException e){
            e.printStackTrace();
            staticSchematic = null;
        }
    }
    static {
        BuildingFactory.buildingKindList.put(BuildingName, BuildingYingHua.class);
    }

    public BuildingYingHua(Location loc, UUID owner) {
        super(loc, owner);
        incidence = new Incidence(loc,
                staticSchematic.width(),
                staticSchematic.height(),
                staticSchematic.length()
        );
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
