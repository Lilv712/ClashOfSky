package com.clashOfSky.AboutPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerStoreHouse {
//    可以在仓库中存储的物资
    static final Map<Material,String> whiteItemMap;
    static Map<UUID,PlayerStoreHouse> mainStoreHouse;
    static Map<UUID,UUID> UUIDReflectMap;
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
        uuid = UUIDReflect(uuid);
        PlayerStoreHouse storeHouse = mainStoreHouse.get(uuid);
        if(storeHouse == null)storeHouse = new PlayerStoreHouse(uuid);
        if(!whiteItemMap.containsKey(material))return false;
        int baseNum = storeHouse.StoreHouse.get(material);
        if(baseNum + num < 0)return false;
        storeHouse.StoreHouse.put(material,baseNum + num);
        return true;
    }
    public static boolean setItemToStoreHouse(UUID uuid,Material material,int num){
        uuid = UUIDReflect(uuid);
        PlayerStoreHouse storeHouse = mainStoreHouse.get(uuid);
        if(storeHouse == null)storeHouse = new PlayerStoreHouse(uuid);
        if(!whiteItemMap.containsKey(material))return false;
        storeHouse.StoreHouse.put(material,num);
        return true;
    }
    public static int searchItemFromPlayerStoreHouse(UUID uuid,Material material){
        uuid = UUIDReflect(uuid);
        if(!isItemEnable(material))return 0;
        checkStoreHouse(uuid);
        return mainStoreHouse.get(uuid).StoreHouse.get(material);
    }
    public static boolean isItemEnable(Material material){
        return whiteItemMap.containsKey(material);
    }
    public static void checkStoreHouse(UUID uuid){
        uuid = UUIDReflect(uuid);
        if(mainStoreHouse.containsKey(uuid))return;
        mainStoreHouse.put(uuid,new PlayerStoreHouse(uuid));
    }
    public static UUID UUIDReflect(UUID uuid){
        if(UUIDReflectMap.containsKey(uuid))
            return  UUIDReflectMap.get(uuid);
        return uuid;
    }
    public static PlayerStoreHouse getPlayerStoreHouse(UUID uuid){
        uuid = UUIDReflect(uuid);
        checkStoreHouse(uuid);
        return  mainStoreHouse.get(uuid);
    }
    public static void bindPlayer(UUID master,UUID guest){
        checkStoreHouse(master);
        checkStoreHouse(guest);
        Map<Material, Integer> masterStoreHouse = PlayerStoreHouse.getPlayerStoreHouse(master).getStoreHouse();
        Map<Material, Integer> guestStoreHouse = PlayerStoreHouse.getPlayerStoreHouse(guest).getStoreHouse();
        for(Material material:masterStoreHouse.keySet())
            masterStoreHouse.put(material,masterStoreHouse.get(material) + guestStoreHouse.get(material));
        PlayerStoreHouse.mainStoreHouse.remove(guest);
        UUIDReflectMap.put(guest,master);
    }
    public static String ShowStore(UUID player){
        player = UUIDReflect(player);
        Map<Material, Integer> storeHouse = PlayerStoreHouse.getPlayerStoreHouse(player).getStoreHouse();
        String op = "";
        op += "库存:\n";
        for(Material material : PlayerStoreHouse.whiteItemMap.keySet()){
            op += (PlayerStoreHouse.whiteItemMap.get(material) + "*" + storeHouse.get(material) + "\n");
        }
        return op;
    }
    public Map<Material, Integer> getStoreHouse(){
        return StoreHouse;
    }
    public static Material searchMaterialFromWhiteMap(String string){
        for(Material material : whiteItemMap.keySet()){
            if(whiteItemMap.get(material).equals(string))return material;
        }
        return null;
    }
}
