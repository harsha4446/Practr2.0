package in.co.mybsolutions.practrapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yash Ajabiya on 5/1/2018.
 */

public class OrganizorEventCreateEventActivity extends BaseActivity {

    String TAG = "==OrganizorEventCreateEventActivity==";

//    public ImageView imgBack;
//    public LinearLayout lnStart;

    @BindView(R.id.card_view)
    CardView card_view;

    @BindView(R.id.edtEventName)
    EditText edtEventName;

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_organizor_create_event);

            setContentView(R.layout.act_organization_event_create);
            App.showLogTAG(TAG);
            ButterKnife.bind(this);

//        imgBack = (ImageView) findViewById(R.id.imgBack);
//        lnStart = (LinearLayout) findViewById(R.id.lnStart);
//        lnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                App.myStartActivity(OrganizorEventCreateEventActivity.this,
//                        new Intent(OrganizorEventCreateEventActivity.this, ActEventCards.class));
//
//            }
//        });
//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.card_view)
    void continueClick() {
        try {
//            Intent iv = new Intent(OrganizorEventCreateEventActivity.this, ActEventsSlidingUpPanel.class);
            Intent iv = new Intent(OrganizorEventCreateEventActivity.this, ActTutorial.class);
            App.myStartActivity(OrganizorEventCreateEventActivity.this, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.imgBack)
    void backButtonClick() {
        try {
            onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        App.myFinishActivity(OrganizorEventCreateEventActivity.this);
    }
}
