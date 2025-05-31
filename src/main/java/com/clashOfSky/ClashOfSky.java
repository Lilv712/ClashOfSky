package com.clashOfSky;

import com.clashOfSky.AboutPlayer.Cmd_Money;
import com.clashOfSky.AboutPlayer.Cmd_Pay;
import com.clashOfSky.AboutPlayer.Cmd_Store;
import com.clashOfSky.building.BuildingCmd;
import com.clashOfSky.building.BuildingListener;
import net.kyori.adventure.text.Component;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClashOfSky extends JavaPlugin {
    public static ClashOfSky instance;
    @Override
    public void onEnable() {
        try {
            Class.forName("com.clashOfSky.building.BuildingQunYuGe");
            Class.forName("com.clashOfSky.building.BuildingYingHua");
            Class.forName("com.clashOfSky.building.ResourceBuilding.BuildingExample.BaseCamp");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        instance = this;
        this.getCommand("build").setExecutor(new BuildingCmd());
        this.getCommand("pay").setExecutor(new Cmd_Pay());
        this.getCommand("money").setExecutor(new Cmd_Money());
        this.getCommand("store").setExecutor(new Cmd_Store());
        this.getServer().getPluginManager().registerEvents(new BuildingListener(),this);
        this.getServer().sendMessage(Component.text("插件启动"));
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
