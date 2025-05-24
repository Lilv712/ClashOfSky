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
    public boolean isInBuilding(Location loc){
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
    public boolean isInBuilding(Location loc, Size size){
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
    public boolean isInBuilding(Location loc,int x,int y,int z){
        return isInBuilding(loc,new Size(x,y,z));
    }
    public boolean isInBuilding(World world, int x1, int y1, int z1, int x2, int y2, int z2){
        return isInBuilding(new Location(world,x1,y1,z1),new Size(x2,y2,z2));
    }
    public boolean isInBuilding(Incidence incidence){
        return isInBuilding(incidence.getLocation(),incidence.getSize());
    }
}
