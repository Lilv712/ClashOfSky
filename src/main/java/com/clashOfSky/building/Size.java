package com.clashOfSky.building;

public class Size {
    public int X;
    public int Y;
    public int Z;
    public Size(int x, int y, int z){
        X = Math.abs(x);
        Y = Math.abs(y);
        Z = Math.abs(z);
    }

    @Override
    public String toString() {
        return X + "*" + Y + "*" + Z;
    }
}
