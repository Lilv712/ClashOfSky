package com.clashOfSky.building.ResourceBuilding;

import com.clashOfSky.AboutPlayer.PlayerItemGiver;
import com.clashOfSky.ClashOfSky;
import com.clashOfSky.building.Building;
import com.clashOfSky.building.CanLevelUp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public abstract class ResourceBuilding extends Building implements CollectAble, CanLevelUp {
    int inventory;
    int LastProduceTick;
    public int level;
    public ResourceBuilding(Location loc, UUID owner) {
        super(loc, owner);
        level = 0;
        LastProduceTick = Bukkit.getCurrentTick();

    }
    public void RefreshInventory(){
        int Nowtick = Bukkit.getCurrentTick();
        int GoodsNum = (Nowtick - LastProduceTick) / getProduceOneGoodsTime();
        LastProduceTick += GoodsNum * getProduceOneGoodsTime();
        inventory += GoodsNum;
        if(inventory > getMaxInventory())inventory = getMaxInventory();
    }
    @Override
    public void collect(Player player) {
        if(isOwnerOrPartner(player))return;
        RefreshInventory();
        if(inventory <= getMinInventory())return;
        int goodsNum = inventory - getMinInventory();
        inventory = inventory - goodsNum;
        for(Goods goods:getGoodsType()){
            PlayerItemGiver.giveItemToPlayer(player,goods.material,goods.num * goodsNum);
            player.sendMessage(ChatColor.GREEN + "获得 " + goods.name + " * " + goodsNum*goods.num);
        }
    }
    @Override
    public void levelUp(Player player) {
        if(isOwnerOrPartner(player))return;
        collect(player);
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
    @Override
    public String getInfo(){
        RefreshInventory();
        String info = super.getInfo();
        String goodsStr = "";
        for(Goods goods:getGoodsType())
            goodsStr += (goods.name + "*" + goods.num + "、");
        goodsStr = goodsStr.substring(0,goodsStr.length()-1);
        info += ("等级:" + (level + 1) + "\n");
        info += ("生产货物:" + goodsStr + "\n");
        info += ("最大存储数量:" + getMaxInventory() + "\n");
        info += ("最小保留数量:" + getMinInventory() + "\n");
        info += ("生产时间:" + getProduceOneGoodsTime() + "\n");
        info += ("当前库存:" + getInventory() + "\n");
        return info;
    }
    public int getInventory(){
        return inventory;
    }

    public abstract String getName();
}
