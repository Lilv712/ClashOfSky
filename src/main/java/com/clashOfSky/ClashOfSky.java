package com.clashOfSky;

import com.clashOfSky.building.BuildingCmd;
import net.kyori.adventure.text.Component;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClashOfSky extends JavaPlugin {
    public static ClashOfSky instance;
    @Override
    public void onEnable() {
        try {
            Class.forName("com.clashOfSky.building.BuildingQunYuGe");
            Class.forName("com.clashOfSky.building.BuildingYingHua");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        instance = this;
        this.getServer().sendMessage(Component.text("插件启动"));
        this.getCommand("build").setExecutor(new BuildingCmd());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
