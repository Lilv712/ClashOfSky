package com.clashOfSky.building;

import org.bukkit.Location;
import org.bukkit.World;

public class Incidence{
    Location location;
    Size size;

    public Incidence(Location loc,Size s){
        location = loc;
        size = s;
    }
    public Incidence(Location loc,int x,int y,int z){
        this(loc,new Size(x,y,z));
    }
    public Size getSize(){
        return size;
    }
    public Location getLocation(){
        return location;
    }
    public Location getCenterPoint(){
        return new Location(
                location.getWorld(),
                location.getBlockX() + size.X/2,
                location.getBlockY() + size.Y/2,
                location.getBlockZ() + size.Z/2
        );
    }
    public static Incidence newIncidenceFromCenterPoint(Location center,Size size){
        Location location = new Location(
                center.getWorld(),
                center.getBlockX() - size.X / 2,
                center.getBlockY() - size.Y / 2,
                center.getBlockZ() - size.Z / 2
        );
        // 创建并返回新的 Incidence 对象
        return new Incidence(location, size);
    }
    public boolean isInIncidence(Location loc){
//      判断世界
        if(!loc.getWorld().equals(location.getWorld()))return false;
//        判断是否在建筑范围内
        Size size = getSize();
        if(loc.getBlockX() < location.getBlockX())return false;
        if(loc.getBlockY() < location.getBlockY())return false;
        if(loc.getBlockZ() < location.getBlockZ())return false;
        if(loc.getBlockX() >= location.getBlockX() + size.X)return false;
        if(loc.getBlockY() >= location.getBlockY() + size.Y)return false;
        if(loc.getBlockZ() >= location.getBlockZ() + size.Z)return false;
        return true;
    }
    public boolean isInIncidence(Location loc, Size size){
        if(!loc.getWorld().equals(location.getWorld()))return false;
        Size sizeThis = getSize();
        if(loc.getBlockX() >= location.getBlockX() + sizeThis.X)return false;
        if(loc.getBlockX() + size.X <= location.getBlockX())return false;
        if(loc.getBlockY() >= location.getBlockY() + sizeThis.Y)return false;
        if(loc.getBlockY() + size.Y <= location.getBlockY())return false;
        if(loc.getBlockZ() >= location.getBlockZ() + sizeThis.Z)return false;
        if(loc.getBlockZ() + size.Z <= location.getBlockZ())return false;
        return true;
    }
    public boolean isInIncidence(Location loc, int x, int y, int z){
        return isInIncidence(loc,new Size(x,y,z));
    }
    public boolean isInIncidence(World world, int x1, int y1, int z1, int x2, int y2, int z2){
        return isInIncidence(new Location(world,x1,y1,z1),new Size(x2,y2,z2));
    }
    public boolean isInIncidence(Incidence incidence){
        return isInIncidence(incidence.getLocation(),incidence.getSize());
    }
}
