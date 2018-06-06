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
 * Created by Yash Ajabiya on 5/2/2018.
 */

public class OrganizorOTPActivity extends BaseActivity {

    String TAG = "==OrganizorOTPActivity==";

//    public ImageView imgBack;
//    public LinearLayout lnStart;

    @BindView(R.id.card_view)
    CardView card_view;

    @BindView(R.id.cardViewResend)
    CardView cardViewResend;

    @BindView(R.id.edtOTP)
    EditText edtOTP;

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_organizorotp);
        setContentView(R.layout.act_organization_otp);
        App.showLogTAG(TAG);
        ButterKnife.bind(this);
//        lnStart=(LinearLayout)findViewById(R.id.lnStart);
//        imgBack = (ImageView) findViewById(R.id.imgBack);
//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//        lnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(OrganizorOTPActivity.this,OrganizorEventCreateEventActivity.class));
//            }
//        });
    }


    @OnClick(R.id.card_view)
    void continueClick() {
        try {
            Intent iv = new Intent(OrganizorOTPActivity.this, OrganizorEventCreateEventActivity.class);
            App.myStartActivity(OrganizorOTPActivity.this, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.cardViewResend)
    void resendClick() {
        try {
            // Resend otp card view click
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
        App.myFinishActivity(OrganizorOTPActivity.this);
    }
}
