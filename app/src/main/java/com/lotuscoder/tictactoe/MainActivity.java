package com.lotuscoder.tictactoe;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lotuscoder.tictactoe.animations.BounceInterpolator;

public class MainActivity extends AppCompatActivity {

    private int activePlayer = 0;
    private int[] slots = {2,2,2,2,2,2,2,2,2};
    private int[][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {6,4,2}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

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

            this.changePlayer();

            for (int[] winningPosition : this.winningPositions) {
                if (this.slots[winningPosition[0]] == this.slots[winningPosition[1]]
                        && this.slots[winningPosition[1]] == this.slots[winningPosition[2]]
                        && this.slots[winningPosition[0]] != 2) {

                    this.endGame(this.slots[winningPosition[0]]);

                }
            }
        }
    }

    protected void changePlayer() {
        int color = ContextCompat.getColor(this, R.color.colorBlue);
        CharSequence playerText = getText(R.string.playerBlue);

        if (this.activePlayer == 1) {
            color = ContextCompat.getColor(this, R.color.colorRed);
            playerText = getText(R.string.playerRed);
        }

        TextView activePlayerTextView = (TextView) findViewById(R.id.activePlayer);
        activePlayerTextView.setText(playerText);
        activePlayerTextView.setTextColor(color);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void endGame(final int winner) {
        final RelativeLayout winnerMessageContainer = (RelativeLayout) findViewById(R.id.winnerMessageContainer);
        final RelativeLayout winnerMessageBox = (RelativeLayout) findViewById(R.id.winnerMessageBox);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);

        winnerMessageContainer.startAnimation(fadeIn);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                winnerMessageBox.setTranslationY(-1000f);
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                winnerMessageContainer.setVisibility(View.VISIBLE);

                CharSequence winnerMessage = getText(R.string.playerBlueWinner);
                int color = ContextCompat.getColor(getBaseContext(), R.color.colorBlue);

                if (winner == 1) {
                    winnerMessage = getText(R.string.playerRedWinner);
                    color = ContextCompat.getColor(getBaseContext(), R.color.colorRed);
                }

                TextView winnerMessageTextView = (TextView) findViewById(R.id.winnerMessage);
                winnerMessageTextView.setText(winnerMessage);
                winnerMessageTextView.setTextColor(color);

                winnerMessageBox.animate().translationYBy(1000f).setDuration(300);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}
