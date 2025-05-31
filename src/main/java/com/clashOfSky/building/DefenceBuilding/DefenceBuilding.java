package com.clashOfSky.building.DefenceBuilding;

import com.clashOfSky.AboutPlayer.PlayerItemGiver;
import com.clashOfSky.ClashOfSky;
import com.clashOfSky.building.Building;
import com.clashOfSky.building.CanLevelUp;
import com.clashOfSky.building.FillAble;
import com.clashOfSky.building.Incidence;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public abstract class DefenceBuilding extends Building implements CanLevelUp {
    Incidence attackIncidence;
    float attackDamage;
    float attackFreq;
    int level;
    public DefenceBuilding(Location loc, UUID owner) {
        super(loc, owner);
        level = 0;
    }
    public String getInfo(){
        String info = "";
        info += super.getInfo();
        info += ("等级:" + (level + 1) + "\n");
        info += ("攻击伤害:" + String.format("%.2g",attackDamage));
        info += ("攻击频率:" + String.format("#.2g",attackFreq));

        return info;
    }
    @Override
    public void levelUp(Player player) {
        if(isOwnerOrPartner(player))return;
        if(isMaxlevel()){
            player.sendMessage(ChatColor.RED + "已经满级！");
            return;
        }
        if(!PlayerItemGiver.RemoveItemFromPlayer(player,getLevelUpResourceList())){
            player.sendMessage(ChatColor.RED + "资源不足!");
            return;
        }
        isEnable = false;
        new BukkitRunnable(){
            @Override
            public void run(){
                level++;
                isEnable = true;
                if(player.isOnline()) player.sendMessage(ChatColor.GREEN + "升级成功！");
            }
        }.runTaskLater(ClashOfSky.instance,getLevelUpTime());
    }
    public void BuildingManagerRegister(){

    }
}
