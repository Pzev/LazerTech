package com.pzev.lazertech.API;

public class Laz
{
    private static int[] Block1Coords = new int[3];
    private static int[] Block2Coords = new int[3];
    private static int color1, color2, thickness;
    private static int LazID ;

    public static void laz(int b1x, int b1y, int b1z, int b2x, int b2y, int b2z, int c1, int c2, int t, int iD)
    {
        Block1Coords[0] = b1x;
        Block1Coords[1] = b1y;
        Block1Coords[2] = b1z;

        Block2Coords[0] = b2x;
        Block2Coords[1] = b2y;
        Block2Coords[2] = b2z;

        color1 = c1;
        color2 = c2;
        thickness = t;
        LazID = iD;
    }

    public void setcolor1(int i)
    {
        color1 = i;
    }

    public int getcolor1()
    {
        return color1;
    }

    public void setcolor2(int i)
    {
        color2 = i;
    }

    public int getcolor2()
    {
        return color2;
    }

    public void setthickness(int i)
    {
        thickness = i;
    }

    public int getthickness()
    {
        return thickness;
    }

    public void setBlock1Coords(int x, int y, int z)
    {
        Block1Coords[1] = x;
        Block1Coords[2] = y;
        Block1Coords[3] = z;
    }

    public int[] getBlock1Coords()
    {
        return Block1Coords;
    }

    public void setBlock2Coords(int x, int y, int z)
    {
        Block2Coords[1] = x;
        Block2Coords[2] = y;
        Block2Coords[3] = z;
    }

    public int[] getBlock2Coords()
    {
        return Block2Coords;
    }

    public void setLazID(int i)
    {
        LazID = i;
    }

    public int getLazID()
    {
        return LazID;
    }
    
}
