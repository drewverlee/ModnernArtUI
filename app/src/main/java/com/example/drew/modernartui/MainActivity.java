package com.example.drew.modernartui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;


public class MainActivity extends Activity {

    private RelativeLayout rectangles;
    // --------- from http://stackoverflow.com/questions/4414673/android-color-between-two-colors-based-on-percentage ---
    private float interpolate(float a, float b, float proportion) {
        return (a + ((b - a) * proportion));
    }
    /** Returns an interpoloated color, between <code>a</code> and <code>b</code> */

    private int interpolateColor(int a, int b, float proportion) {
        float[] hsva = new float[3];
        float[] hsvb = new float[3];
        Color.colorToHSV(a, hsva);
        Color.colorToHSV(b, hsvb);
        for (int i = 0; i < 3; i++) {
            hsvb[i] = interpolate(hsva[i], hsvb[i], proportion);
        }
        return Color.HSVToColor(hsvb);
    }
    //** end cite

    public int getNewColor(int i){
        int color;
        switch(i){
            case 0:
                color = Color.YELLOW;
                break;
            case 1:
                color = Color.BLACK;
                break;
            case 2:
                color = Color.BLUE;
                break;
            case 3:
                color = Color.GRAY;
                break;
            case 4:
                color = Color.GREEN;
                break;
            case 5:
                color = Color.RED;
                break;
            case 6:
                color = Color.CYAN;
                break;
            case 7:
                color = Color.MAGENTA;
                break;
            default:
                color = Color.WHITE;

        }
        return color;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout rectangles = (RelativeLayout) findViewById(R.id.rectangles);

        SeekBar seek = (SeekBar) findViewById(R.id.seekBar);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // now we grab our views and change their colors
                for (int i=0; i< rectangles.getChildCount(); i++){
                    View child = rectangles.getChildAt(i);
                    int originalColor = Color.parseColor ( (String) child.getTag());
                    String tag = (String) child.getTag();
                    int newColor = getNewColor(i);

                    // if not white or grey... TODO make this better:
                    int WHITE = -1;
                    int GREY = -5260870;
                    if( (originalColor != WHITE) && (originalColor != GREY) ){
                        child.setBackgroundColor(interpolateColor(originalColor, newColor, ((float) progress)/100));
                    }

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // nothing to do here

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // nothing to do here

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public void openSettings(){
        MOMA dialog = new MOMA();
        dialog.show(getFragmentManager(), dialog.getTag());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
