package com.clashOfSky.AboutPlayer;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerStoreHouse {
//    可以在仓库中存储的物资
    static final Map<Material,String> whiteItemMap;
    static Map<UUID,PlayerStoreHouse> mainStoreHouse;
    static {
        mainStoreHouse = new HashMap<>();
        whiteItemMap = new HashMap<>();
        whiteItemMap.put(Material.COBBLESTONE,"圆石");
        whiteItemMap.put(Material.OAK_LOG,"橡木原木");
    }
    UUID ownerUUID;
    Map<Material, Integer> StoreHouse;
    public PlayerStoreHouse(UUID uuid){
        ownerUUID = uuid;
        StoreHouse = new HashMap<>();
        for(Material item: whiteItemMap.keySet()){
            StoreHouse.put(item,0);
        }
        mainStoreHouse.put(uuid,this);
    }
    public static boolean addItemToStoreHouse(UUID uuid,Material material,int num){
        PlayerStoreHouse storeHouse = mainStoreHouse.get(uuid);
        if(storeHouse == null)storeHouse = new PlayerStoreHouse(uuid);
        if(!whiteItemMap.containsKey(material))return false;
        int baseNum = storeHouse.StoreHouse.get(material);
        if(baseNum + num < 0)return false;
        storeHouse.StoreHouse.put(material,baseNum + num);
        return true;
    }
    public static int searchItemFromPlayerStoreHouse(UUID uuid,Material material){
        if(!isItemEnable(material))return -1;
        checkStoreHouse(uuid);
        return mainStoreHouse.get(uuid).StoreHouse.get(material);
    }
    public static boolean isItemEnable(Material material){
        return whiteItemMap.containsKey(material);
    }
    public static void checkStoreHouse(UUID uuid){
        if(mainStoreHouse.containsKey(uuid))return;
        mainStoreHouse.put(uuid,new PlayerStoreHouse(uuid));
    }
}
