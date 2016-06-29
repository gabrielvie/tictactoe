package com.lotuscoder.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int activePlayer = 0;
    private int[] slots = {2,2,2,2,2,2,2,2,2};

    public void dropIn(View view) {
        ImageView imageSlot = (ImageView) view;
        int imageSlotPosition = Integer.parseInt(imageSlot.getTag().toString());

        if (this.slots[imageSlotPosition] == 2) {

            imageSlot.setScaleX(0f);
            imageSlot.setScaleY(0f);

            this.slots[imageSlotPosition] = this.activePlayer;

            if (this.activePlayer == 0) {
                imageSlot.setImageResource(R.drawable.circle);
                this.activePlayer = 1;
            } else {
                imageSlot.setImageResource(R.drawable.delete);
                this.activePlayer = 0;
            }

            imageSlot.animate().scaleXBy(1f).scaleYBy(1f).setDuration(100);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
