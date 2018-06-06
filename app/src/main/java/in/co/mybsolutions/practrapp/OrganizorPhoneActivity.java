package in.co.mybsolutions.practrapp;

import android.app.Activity;
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

public class OrganizorPhoneActivity extends Activity {

    String TAG = "==OrganizorPhoneActivity==";

//    public TextView tvLabel;
//    public ImageView imgBack;
//    public LinearLayout lnStart;

    @BindView(R.id.card_view)
    CardView card_view;

    @BindView(R.id.edtNumber)
    EditText edtNumber;

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_organizorphone);
            setContentView(R.layout.act_organization_phone);
            App.showLogTAG(TAG);
            ButterKnife.bind(this);

//            lnStart = (LinearLayout) findViewById(R.id.lnStart);
//            imgBack = (ImageView) findViewById(R.id.imgBack);
//            imgBack.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onBackPressed();
//                }
//            });
//            lnStart.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(OrganizorPhoneActivity.this, OrganizorOTPActivity.class));
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.card_view)
    void continueClick() {
        try {
            Intent iv = new Intent(OrganizorPhoneActivity.this, OrganizorOTPActivity.class);
            App.myStartActivity(OrganizorPhoneActivity.this, iv);
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
        App.myFinishActivity(OrganizorPhoneActivity.this);
    }
}
