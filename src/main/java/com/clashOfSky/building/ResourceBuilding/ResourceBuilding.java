package com.clashOfSky.building.ResourceBuilding;

import com.clashOfSky.AboutPlayer.PlayerItemGiver;
import com.clashOfSky.building.Building;
import com.clashOfSky.building.CollectAble;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class ResourceBuilding extends Building implements CollectAble{
    int inventory;
    int LastProduceTick;
    public int level;
    public ResourceBuilding(Location loc, UUID owner) {
        super(loc, owner);
        LastProduceTick = Bukkit.getCurrentTick();

    }
    public void RefreshInventory(){
        int Nowtick = Bukkit.getCurrentTick();
        int GoodsNum = (Nowtick - LastProduceTick) / getProduceOneGoodsTime();
        LastProduceTick += GoodsNum * getProduceOneGoodsTime();
        inventory += GoodsNum;
        if(inventory > getMaxInventory())inventory = getMaxInventory();
    }
    public void collect(Player player) {
        RefreshInventory();
        if(inventory <= getMinInventory())return;
        int goodsNum = inventory - getMinInventory();
        Goods goods = getGoodsType();
        PlayerItemGiver.giveItemToPlayer(player,goods.material,goods.num * goodsNum);
        player.sendMessage(ChatColor.GREEN + "获得 " + goods.name + " * " + goodsNum*goods.num);
    }

    public String getInfo(){
        RefreshInventory();
        String info = super.getInfo();
        info += ("等级:" + level + "\n");
        info += ("生产货物:" + getGoodsName() + "\n");
        info += ("最大存储数量:" + getMaxInventory() + "\n");
        info += ("最小保留数量:" + getMinInventory() + "\n");
        info += ("生产时间:" + getProduceOneGoodsTime() + "\n");
        info += ("当前库存:" + getInventory() + "\n");
        return info;
    }
    public int getInventory(){
        return inventory;
    }
}
