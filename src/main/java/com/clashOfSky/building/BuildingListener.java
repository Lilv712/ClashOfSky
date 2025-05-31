package com.clashOfSky.building;

import com.clashOfSky.building.ResourceBuilding.ResourceBuilding;
import io.papermc.paper.event.block.BlockBreakBlockEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;
import java.util.Set;

public class BuildingListener implements Listener {
    static Set<Material> materialWhiteSet = new HashSet<>();
    static {
//        materialWhiteSet.add(Material.OAK_BUTTON);
        materialWhiteSet.add(Material.STONE_BUTTON);
        materialWhiteSet.add(Material.TORCH);
    }
    @EventHandler
    public void onBreakBlock(BlockBreakEvent event){
        if(materialWhiteSet.contains(event.getBlock().getType()))return;
        Location location = event.getBlock().getLocation();
        if(BuildingManager.searchBuilding(location) == null) return;
        event.setCancelled(true);
        event.getPlayer().sendMessage(ChatColor.RED + "不能破坏该区域！");
    }
    @EventHandler
    public void onPlaceEvent(BlockPlaceEvent event){
        if(materialWhiteSet.contains(event.getBlock().getType()))return;
        Location location = event.getBlock().getLocation();
        if(BuildingManager.searchBuilding(location) == null) return;
        event.setCancelled(true);
        event.getPlayer().sendMessage(ChatColor.RED + "不能在该区域放置方块！");
    }
    @EventHandler
    public void on(PlayerInteractEvent event){
        Block block = event.getClickedBlock();
        Location location = block.getLocation();
        Building building = BuildingManager.searchBuilding(location);
        if(building == null) return;
//        石头按钮，按下收集
        if(block.getType() == Material.STONE_BUTTON){
            if(building instanceof ResourceBuilding resourceBuilding){
                Player player = event.getPlayer();
                resourceBuilding.collect(player);
            }
        }
//        橡木告示牌，点击获取info
        if(block.getType() == Material.OAK_SIGN){
            Player player = event.getPlayer();
            player.sendMessage(building.getInfo());
        }
    }
}
