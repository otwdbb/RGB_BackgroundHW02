package com.example.rgb_backgroundhw02;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorListAdapter extends BaseAdapter
{
    //Does not come with a constructor
    //we need to make one
    Context context;

    ArrayList<ColorInfo> colorInfo;

    public ColorListAdapter(Context c, ArrayList<ColorInfo> ci)
    {
        context = c;
        colorInfo = ci;

    }


    @Override
    public int getCount()
    {
        return colorInfo.size();
    }

    @Override
    public Object getItem(int position)
    {
        return colorInfo.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        if(view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.color_cell, null);
        }

        //find GUI in custom cell

        TextView redCNum = view.findViewById(R.id.tv_v_cell_Red);
        TextView greenCNum = view.findViewById(R.id.tv_v_cell_Green);
        TextView blueCNum = view.findViewById(R.id.tv_v_cell_Blue);
        TextView hexCNum = view.findViewById(R.id.tv_v_cell_Hex);
        View colorV = view.findViewById(R.id.colorcell_xmll);

        //get date for the color from colorInfo
        //we can access different elements off the
        //Position value passed to this function
        ColorInfo color = colorInfo.get(position);

        //set the gui for the custom cell








        redCNum.setText("Red: " + color.getRedNum());
        greenCNum.setText("Green: " +color.getGreenNum());
        blueCNum.setText("Blue: " +color.getBlueNum());
        hexCNum.setText(color.getHexRep());


        int backColor = Color.rgb(color.getRedNum(),color.getGreenNum(),color.getBlueNum());
        colorV.setBackgroundColor(backColor);


        int r = color.getRedNum();
        int g = color.getGreenNum();
        int b = color.getBlueNum();

        double brightness = (0.299 * r  + 0.587 * g + 0.114 * b);

        int textColor ;
        //if it is too dark make it white
        //else make it black
        if(brightness < 128)
        {
            textColor = Color.WHITE;

        }
        else
        {
            textColor = Color.BLACK;
        }

        redCNum.setTextColor(textColor);
        greenCNum.setTextColor(textColor);
        blueCNum.setTextColor(textColor);
        hexCNum.setTextColor(textColor);










        return view;
    }
}
