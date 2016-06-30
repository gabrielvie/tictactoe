package com.lotuscoder.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.lotuscoder.tictactoe.animations.BounceInterpolator;

public class MainActivity extends AppCompatActivity {

    private int activePlayer = 0;
    private int[] slots = {2,2,2,2,2,2,2,2,2};
    private int[][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {6,4,2}
    };

    public void dropIn(View view) {
        ImageView imageSlot = (ImageView) view;
        int imageSlotPosition = Integer.parseInt(imageSlot.getTag().toString());

        if (this.slots[imageSlotPosition] == 2) {

            this.slots[imageSlotPosition] = this.activePlayer;

            if (this.activePlayer == 0) {
                imageSlot.setImageResource(R.drawable.circle);
                this.activePlayer = 1;
            } else {
                imageSlot.setImageResource(R.drawable.delete);
                this.activePlayer = 0;
            }

            final Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);

            BounceInterpolator interpolator = new BounceInterpolator(0.1, 15);

            bounce.setInterpolator(interpolator);
            imageSlot.startAnimation(bounce);

            for (int[] winningPosition : this.winningPositions) {
                if (this.slots[winningPosition[0]] == this.slots[winningPosition[1]]
                        && this.slots[winningPosition[1]] == this.slots[winningPosition[2]]
                        && this.slots[winningPosition[0]] != 2) {

                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
