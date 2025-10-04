package com.example.rgb_backgroundhw02;

import java.io.Serializable;
public class ColorInfo implements Serializable
{
    // Color info in comprised of red, green, blue and Hex Representation


    private int redNum;
    private int greenNum;

    private int blueNum;

    private String hexRep;
    //private can only be accessed, with getters and setters
    //private means i can be accessed in the class i was created

    public int getRedNum()
    {
        return redNum;
    }

    public void setRedNum(int redNum)
    {
        this.redNum = redNum;
    }

    public int getGreenNum()
    {
        return greenNum;
    }

    public void setGreenNum(int greenNum)
    {
        this.greenNum = greenNum;
    }

    public int getBlueNum()
    {
        return blueNum;
    }

    public void setBlueNum(int blueNum)
    {
        this.blueNum = blueNum;
    }

    public String getHexRep()
    {
        return hexRep;
    }

    public void setHexRep(String hexRep)
    {
        this.hexRep = hexRep;
    }
    //default constructor
    public ColorInfo()
    {

    }
    //overloaded constructor
    //need each variable in it
    public ColorInfo( int r, int g, int b,String hex)
    {
        redNum = r;
        greenNum = g;
        blueNum = b;
        hexRep = hex;

    }






}
