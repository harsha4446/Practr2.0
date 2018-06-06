package in.co.mybsolutions.practrapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yash Ajabiya on 5/1/2018.
 */

public class OrganizorNameActivity extends Activity {

    String TAG = "==OrganizorNameActivity==";

//    public LinearLayout lnStart;
//    public ImageView imgBack;
//    public EditText etName;

    @BindView(R.id.card_view)
    CardView card_view;

    @BindView(R.id.etName)
    EditText etName;

    @BindView(R.id.imgBack)
    ImageView imgBack;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.act_organization_name);
//            ViewGroup.inflate(this, R.layout.activity_organizorname, llContainerSub);
            App.showLogTAG(TAG);
            ButterKnife.bind(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        lnStart = (LinearLayout) findViewById(R.id.lnStart);
//        imgBack = (ImageView) findViewById(R.id.imgBack);
//        etName = (EditText) findViewById(R.id.etName);
//        lnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              /*  if (etName.getText().toString().equalsIgnoreCase("")) {
//                    etName.setError("");
//                    Toast.makeText(OrganizorNameActivity.this, "Enter Name ", Toast.LENGTH_SHORT).show();
//                }else{*/
//                startActivity(new Intent(OrganizorNameActivity.this, OrganizorPhoneActivity.class));
//                // }
//            }
//        });
//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
    }


    @OnClick(R.id.card_view)
    void continueClick() {
        try {
            Intent iv = new Intent(OrganizorNameActivity.this, OrganizorPhoneActivity.class);
            App.myStartActivity(OrganizorNameActivity.this, iv);
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
        App.myFinishActivity(OrganizorNameActivity.this);
    }
}
