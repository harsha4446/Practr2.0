package in.co.mybsolutions.practrapp;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dhaval.mehta on 25-May-18.
 */

public class ActCriteriaSubmissionPenalty extends BaseActivity {

    String TAG = "==ActCriteriaSubmissionPenalty==";

    @BindView(R.id.ivSubBack)
    ImageView ivSubBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ViewGroup.inflate(this, R.layout.act_criteria_submission_penalty, llContainerSub);
            App.showLogTAG(TAG);
            ButterKnife.bind(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivSubBack)
    void subBackClick() {
        try {
            onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        App.myFinishActivity(ActCriteriaSubmissionPenalty.this);
    }
}
