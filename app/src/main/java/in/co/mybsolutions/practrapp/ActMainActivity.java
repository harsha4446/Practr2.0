package in.co.mybsolutions.practrapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dhaval.mehta on 28-May-18.
 */

public class ActMainActivity extends BaseActivity {

    String TAG = "==ActMainActivity==";

    @BindView(R.id.imageSwitcher)
    ImageSwitcher imageSwitcher;
    
    @BindView(R.id.tvTagIam)
    TextView tvTagIam;

    @BindView(R.id.tvTagLabel)
    TextView tvTagLabel;

    @BindView(R.id.tvFirst)
    TextView tvFirst;

    @BindView(R.id.ivFirst)
    ImageView ivFirst;

    @BindView(R.id.tvSecond)
    TextView tvSecond;

    @BindView(R.id.ivSecond)
    ImageView ivSecond;
    

    // 0- organiaor, 1- judge, 2- participant
    public int currentRole = 0;
    public int firstRole = 1, secondRole = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ViewGroup.inflate(this, R.layout.act_main, llContainerSub);
            App.showLogTAG(TAG);
            ButterKnife.bind(this);
            //
            initialisation();
            setClickEvents();
            //
             showImage(R.drawable.organizor_bg);
//            imageSwitcher.setImageResource(R.drawable.organizor_bg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initialisation() {
        try {
            Animation out= AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
            Animation in= AnimationUtils.loadAnimation(this, android.R.anim.fade_in);

            imageSwitcher.setInAnimation(in);
            imageSwitcher.setOutAnimation(out);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }


    private void setClickEvents() {
        try {
            //
            imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                // Returns the view to show Image
                // (Usually should use ImageView)
                @Override
                public View makeView() {
                    ImageView imageView = new ImageView(getApplicationContext());
//                    imageView.setBackgroundColor(Color.LTGRAY);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    ImageSwitcher.LayoutParams params= new ImageSwitcher.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                    imageView.setLayoutParams(params);
                    return imageView;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showImage(int imgIndex)  {
        try {
            int resId= imgIndex;
            if(resId!=  0) {
                this.imageSwitcher.setImageResource(resId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.rlFirst)
    void firstRlClick() {
        if (currentRole == 0) {
            currentRole = 1;

            showImage(R.drawable.judge_bg);
            //
            tvTagIam.setText("I am a");
            tvTagLabel.setText("Judge");
            ivFirst.setImageResource(R.drawable.participant_selector);
            tvFirst.setText("Participant");

            ivSecond.setImageResource(R.drawable.organizor_selector);
            tvSecond.setText("Organiser");
            firstRole = 2;
            secondRole = 0;
        } else if (currentRole == 1) {

            showImage(R.drawable.participant_bg);
            //
            tvTagIam.setText("I am a");
            tvTagLabel.setText("Participant");
            currentRole = 2;

            ivFirst.setImageResource(R.drawable.organizor_selector);
            tvFirst.setText("Organiser");

            ivSecond.setImageResource(R.drawable.judge_selector);
            tvSecond.setText("Judge");

            firstRole = 0;
            secondRole = 1;
        } else if (currentRole == 2) {
            currentRole = 0;

            showImage(R.drawable.organizor_bg);
            //
            tvTagIam.setText("I am an");
            tvTagLabel.setText("Event Head");
            ivFirst.setImageResource(R.drawable.judge_selector);
            tvFirst.setText("Judge");

            ivSecond.setImageResource(R.drawable.participant_selector);
            tvSecond.setText("Participant");
            firstRole = 1;
            secondRole = 2;
        }
    }


    @OnClick(R.id.rlSecond)
    void secondRlClick() {
        if (currentRole == 0) {

            showImage(R.drawable.participant_bg);
            //
            tvTagIam.setText("I am a");
            tvTagLabel.setText("Participant");
            currentRole = 2;
            ivFirst.setImageResource(R.drawable.organizor_selector);
            tvFirst.setText("Organiser");

            ivSecond.setImageResource(R.drawable.judge_selector);
            tvSecond.setText("Judge");

            firstRole = 0;
            secondRole = 1;
        } else if (currentRole == 2) {
            currentRole = 1;

            showImage(R.drawable.judge_bg);
            //
            tvTagIam.setText("I am a");
            tvTagLabel.setText("Judge");
            ivFirst.setImageResource(R.drawable.participant_selector);
            tvFirst.setText("Participant");

            ivSecond.setImageResource(R.drawable.organizor_selector);
            tvSecond.setText("Organiser");
            firstRole = 2;
            secondRole = 0;
        } else if (currentRole == 1) {
            currentRole = 0;

            showImage(R.drawable.organizor_bg);
            //
            tvTagIam.setText("I am an");
            tvTagLabel.setText("Event Head");
            ivFirst.setImageResource(R.drawable.judge_selector);
            tvFirst.setText("Judge");

            ivSecond.setImageResource(R.drawable.participant_selector);
            tvSecond.setText("Participant");
            firstRole = 1;
            secondRole = 2;
        }
    }


    @OnClick(R.id.card_view)
    void cardClick() {
        if (currentRole == 0) {
            navigateIntent();
        } else if (currentRole == 1) {
            navigateIntent();
        } if (currentRole == 2) {
            navigateIntent();
        }
    }


    void navigateIntent() {
        Intent iv = new Intent(ActMainActivity.this, OrganizorNameActivity.class);
//        Intent iv = new Intent(ActMainActivity.this, ActEventsSlidingUpPanel.class);
        App.myStartActivity(ActMainActivity.this, iv);
    }
}
