package com.clashOfSky.building;

import com.clashOfSky.ClashOfSky;
import net.sandrohc.schematic4j.schematic.Schematic;
import net.sandrohc.schematic4j.schematic.types.Pair;
import net.sandrohc.schematic4j.schematic.types.SchematicBlock;
import net.sandrohc.schematic4j.schematic.types.SchematicBlockPos;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

abstract public class Building {

    static String buildingFolder = "schematic";

    boolean isEnable;
    boolean placeSuccess;
    Incidence incidence;
    UUID ownerUUID;
    UUID uuid;
    public Building(Location loc,UUID owner) {
        isEnable = false;
        ownerUUID = owner;
        uuid = UUID.randomUUID();
        placeBuilding();
    }
    public abstract String getName();
    public abstract Schematic getSchematic();
    public UUID getOwner(){
        return ownerUUID;
    }
    public boolean isEnable(){
        return isEnable;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(uuid, building.uuid);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    public void placeBuilding(){
        isEnable = false;
        Stream<Pair<SchematicBlockPos, SchematicBlock>> blockstream = getSchematic().blocks();
        Iterator<Pair<SchematicBlockPos, SchematicBlock>> iterator = blockstream.iterator();
        new BukkitRunnable() {
            @Override
            public void run() {
                int blocksPlaced = 0;
                while (iterator.hasNext() && blocksPlaced < 1000) { // 每tick最多放10个，避免卡顿
                    Pair<SchematicBlockPos, SchematicBlock> scheblock = iterator.next();

                    int xoffset = Math.abs(scheblock.left.x);
                    int yoffset = Math.abs(scheblock.left.y);
                    int zoffset = Math.abs(scheblock.left.z);

                    Location location = incidence.getLocation();
                    Block block = location.getWorld().getBlockAt(
                            (int) (location.x() + xoffset),
                            (int) (location.y() + yoffset),
                            (int) (location.z() + zoffset)
                    );

                    String blockdatastring = SchematicBlock.blockNameAndStatesToString(
                            scheblock.right.block(),
                            scheblock.right.states()
                    );

                    BlockData blockdata = Bukkit.createBlockData(blockdatastring);
                    block.setBlockData(blockdata);
                    if(!(block.getType() == Material.AIR))
                        blocksPlaced++;
                }

                if (!iterator.hasNext()) {
                    isEnable = true;
                    Bukkit.getPlayer(ownerUUID).sendMessage("建造完成");
                    cancel();
                }
            }
        }.runTaskTimer(ClashOfSky.instance, 0, 2);
    }
}
