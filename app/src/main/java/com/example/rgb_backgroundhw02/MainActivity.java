package com.example.rgb_backgroundhw02;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //==========================================
    //Author : Brett Brown
    //Date   : 09-30-2025
    //Desc   : RGB Slider
    //==========================================
   static private ArrayList<ColorInfo> colorInfo;
   private View mainBackground;

  // String[] test = {"i", "am", "testing"};
   ColorListAdapter ciAdapter;


    //connecton from gui to java

    TextView tv_j_hexNum;
    TextView tv_j_redNum;
    TextView tv_j_blueNum;
    TextView tv_j_greenNum;

    TextView tv_j_hexState;
    ListView lv_j_colors;
    Button btn_j_saveColors;

    SeekBar sb_j_redNum;
    SeekBar sb_j_greenNum;
    SeekBar sb_j_blueNum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //connecting gui to java
        tv_j_hexNum = findViewById(R.id.tv_v_hexNum);

        tv_j_redNum = findViewById(R.id.tv_v_redNum);

        tv_j_greenNum = findViewById(R.id.tv_v_greenNum);

        tv_j_blueNum = findViewById(R.id.tv_v_blueNum);

        lv_j_colors = findViewById(R.id.lv_v_colors);

        btn_j_saveColors = findViewById(R.id.btn_v_saveColor);

        sb_j_redNum = findViewById(R.id.sb_v_redNum);

        sb_j_greenNum = findViewById(R.id.sb_v_greenNum);

        sb_j_blueNum = findViewById(R.id.sb_v_blueNum);

        mainBackground = findViewById(R.id.main);

        tv_j_hexState = findViewById(R.id.tv_v_hexState);

        //I need a list to house colors for the program
        colorInfo = new ArrayList<>();
        //set adapter once
        fillListView();

        //we need an adapter to be used with the listview
        //if the cells require more than one string bring displayed
        //we must create our own custom adapter
        //ArrayAdapter<String> colorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,test);

        //lv_j_colors.setAdapter(colorAdapter);



        //saveColor();

        //list click handling
        loadColor();
        //live updates
        seekBarChange();
        //calls saveColor
        saveBtn();
        //fillListView();
        //very good job :)

    }
    private void saveBtn()
    {

        //wire the button to save and list item clicks
        //wire the button to save and list item clicks
        btn_j_saveColors.setOnClickListener(View -> saveColor());


    }
    private void loadColor()
    {
        //when a user taps this, it is listening for it
       lv_j_colors.setOnItemClickListener((parent, view, position, id) ->
       {
           //grab the object for the tapped row
           ColorInfo c = colorInfo.get(position);
           sb_j_redNum.setProgress(c.getRedNum());
           sb_j_greenNum.setProgress(c.getGreenNum());
           sb_j_blueNum.setProgress(c.getBlueNum());


       });
    }


    private void saveColor()
    {
        //read current RGB from seekbars
        int r = sb_j_redNum.getProgress();
        int g = sb_j_greenNum.getProgress();
        int b = sb_j_blueNum.getProgress();
        String hex = tv_j_hexNum.getText().toString();


        ColorInfo box = new ColorInfo(r, g ,b, hex);

        //add to list and refresh
        colorInfo.add(box);
        //tells list view my data changed update refresh yourself
        ciAdapter.notifyDataSetChanged();


        //reset GUI to white
        sb_j_redNum.setProgress(255);
        sb_j_greenNum.setProgress(255);
        sb_j_blueNum.setProgress(255);



    }

    private void seekBarChange()
    {

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                //check which one is being moved and move it and update
                if(seekBar == sb_j_redNum)
                {
                    tv_j_redNum.setText("Red: " + progress);

                }
                else if(seekBar == sb_j_greenNum)
                {
                    tv_j_greenNum.setText("Green: " + progress );
                }
                else if(seekBar == sb_j_blueNum)
                {
                    tv_j_blueNum.setText("Blue: " + progress);
                }

                //update the number textViews
                //this gets all the numbers together
                int r = sb_j_redNum.getProgress();
                int g = sb_j_greenNum.getProgress();
                int b = sb_j_blueNum.getProgress();




                //this gets them together or builds the string if you will
                String hex = String.format("#%02X%02X%02X", r, g ,b);
                tv_j_hexNum.setText(hex);

                //To change the colors live
                mainBackground.setBackgroundColor(Color.rgb(r,g,b));

                //goodJob im proud of me


                //need to make the colors of the letters black if to bright
                //white if too dark
                //going to use the luminance formula

                double brightness = (0.299 * r + 0.587 * g + 0.114 * b);

                int textColor;
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
                //setting it up
                tv_j_redNum.setTextColor(textColor);
                tv_j_greenNum.setTextColor(textColor);
                tv_j_blueNum.setTextColor(textColor);
                tv_j_hexNum.setTextColor(textColor);
                tv_j_hexState.setTextColor(textColor);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        sb_j_redNum.setOnSeekBarChangeListener(listener);
        sb_j_greenNum.setOnSeekBarChangeListener(listener);
        sb_j_blueNum.setOnSeekBarChangeListener(listener);
    }

     private void fillListView()
    {
        //Link the custom adapter to the listview and display the saved colors
        ciAdapter = new ColorListAdapter(this, colorInfo);
        lv_j_colors.setAdapter(ciAdapter);
    }

}