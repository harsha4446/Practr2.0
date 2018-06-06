package in.co.mybsolutions.practrapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    public int currentRole = 0; //0- organiaor, 1- judge, 2- participant
    public TextView tvIam, tvLabel, tvFirst, tvSecond;
    public LinearLayout /*lnStart,*/ lnFirst, lnSecond;
    public int firstRole = 1, secondRole = 2;
    public ImageView constMain;

    @BindView(R.id.card_view)
    CardView cardView;
    //
//    int SCREEN_HEIGHT = 0, SCREEN_WIDTH = 0;
//    int IMAGE_WIDTH = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
//            lnStart = (LinearLayout) findViewById(R.id.lnStart);
            lnFirst = (LinearLayout) findViewById(R.id.lnFirst);
            lnSecond = (LinearLayout) findViewById(R.id.lnSecond);
            tvIam = (TextView) findViewById(R.id.tvIam);
            tvLabel = (TextView) findViewById(R.id.tvLabel);
            tvFirst = (TextView) findViewById(R.id.tvFirst);
            tvSecond = (TextView) findViewById(R.id.tvSecond);
            constMain = (ImageView) findViewById(R.id.constMain);

            lnFirst.setOnClickListener(this);
            lnSecond.setOnClickListener(this);
            cardView.setOnClickListener(this);

//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            SCREEN_HEIGHT = displayMetrics.heightPixels;
//            SCREEN_WIDTH = displayMetrics.widthPixels;
//            App.showLog(TAG + "==SCREEN_HEIGHT==" + SCREEN_HEIGHT);
//            App.showLog(TAG + "==SCREEN_WIDTH==" + SCREEN_WIDTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.lnFirst:
                    if (currentRole == 0) {
                        currentRole = 1;
                        //  constMain.setBackgroundResource(R.drawable.judge_bg);
                        ImageViewAnimatedChange(MainActivity.this, constMain, R.drawable.judge_bg);
                        //
                        tvIam.setText("I am a");
                        tvLabel.setText("Judge");
                        lnFirst.setBackgroundResource(R.drawable.participant_selector);
                        tvFirst.setText("Participant");

                        lnSecond.setBackgroundResource(R.drawable.organizor_selector);
                        tvSecond.setText("Organiser");
                        firstRole = 2;
                        secondRole = 0;
                    } else if (currentRole == 1) {
                        //constMain.setBackgroundResource(R.drawable.participant_bg);
                        ImageViewAnimatedChange(MainActivity.this, constMain, R.drawable.participant_bg);
                        //
                        tvIam.setText("I am a");
                        tvLabel.setText("Participant");
                        currentRole = 2;

                        lnFirst.setBackgroundResource(R.drawable.organizor_selector);
                        tvFirst.setText("Organiser");

                        lnSecond.setBackgroundResource(R.drawable.judge_selector);
                        tvSecond.setText("Judge");

                        firstRole = 0;
                        secondRole = 1;
                    } else if (currentRole == 2) {
                        currentRole = 0;
                        // constMain.setBackgroundResource(R.drawable.organizor_bg);
                        ImageViewAnimatedChange(MainActivity.this, constMain, R.drawable.organizor_bg);
                        //
                        tvIam.setText("I am an");
                        tvLabel.setText("Event Head");
                        lnFirst.setBackgroundResource(R.drawable.judge_selector);
                        tvFirst.setText("Judge");

                        lnSecond.setBackgroundResource(R.drawable.participant_selector);
                        tvSecond.setText("Participant");
                        firstRole = 1;
                        secondRole = 2;
                    }
                    break;

                case R.id.lnSecond:
                    if (currentRole == 0) {
                        // constMain.setBackgroundResource(R.drawable.participant_bg);
                        ImageViewAnimatedChange(MainActivity.this, constMain, R.drawable.participant_bg);
                        //
                        tvIam.setText("I am a");
                        tvLabel.setText("Participant");
                        currentRole = 2;
                        lnFirst.setBackgroundResource(R.drawable.organizor_selector);
                        tvFirst.setText("Organiser");

                        lnSecond.setBackgroundResource(R.drawable.judge_selector);
                        tvSecond.setText("Judge");

                        firstRole = 0;
                        secondRole = 1;
                    } else if (currentRole == 2) {
                        currentRole = 1;
                        // constMain.setBackgroundResource(R.drawable.judge_bg);
                        ImageViewAnimatedChange(MainActivity.this, constMain, R.drawable.judge_bg);
                        //
                        tvIam.setText("I am a");
                        tvLabel.setText("Judge");
                        lnFirst.setBackgroundResource(R.drawable.participant_selector);
                        tvFirst.setText("Participant");

                        lnSecond.setBackgroundResource(R.drawable.organizor_selector);
                        tvSecond.setText("Organiser");
                        firstRole = 2;
                        secondRole = 0;
                    } else if (currentRole == 1) {
                        currentRole = 0;
                        // constMain.setBackgroundResource(R.drawable.organizor_bg);
                        ImageViewAnimatedChange(MainActivity.this, constMain, R.drawable.organizor_bg);
                        //
                        tvIam.setText("I am an");
                        tvLabel.setText("Event Head");
                        lnFirst.setBackgroundResource(R.drawable.judge_selector);
                        tvFirst.setText("Judge");

                        lnSecond.setBackgroundResource(R.drawable.participant_selector);
                        tvSecond.setText("Participant");
                        firstRole = 1;
                        secondRole = 2;
                    }
                    break;


                case R.id.card_view:
                    if (currentRole == 0) {
//                        Intent iv = new Intent(MainActivity.this, OrganizorNameActivity.class);
                        Intent iv = new Intent(MainActivity.this, ActEventsSlidingUpPanel.class);
                        App.myStartActivity(MainActivity.this, iv);
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void ImageViewAnimatedChange1(Context c, final ImageView v, final int new_image) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, R.anim.fadeout);
        final Animation anim_in  = AnimationUtils.loadAnimation(c, R.anim.fadein);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                v.setImageResource(new_image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                v.startAnimation(anim_in);
            }
        });
        v.startAnimation(anim_out);
    }




    public static void ImageViewAnimatedChange(Context c, final ImageView v, final int new_image) {

        try {
            final Animation anim_in = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
//            final Animation anim_in = AnimationUtils.loadAnimation(c, R.anim.fadein);

            AlphaAnimation alpha = new AlphaAnimation(0.5F, 0.5F);
            alpha.setDuration(100);
            alpha.setFillAfter(true);
            v.startAnimation(alpha);

            alpha.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    v.setImageResource(new_image);
                    anim_in.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                        }
                    });
                    v.startAnimation(anim_in);
                }
            });

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }
}
