package in.co.mybsolutions.practrapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import in.co.mybsolutions.practrapp.App;
import in.co.mybsolutions.practrapp.R;

/**
 * Created by dhaval.mehta on 22-May-18.
 */

public class FragTutorialThree extends Fragment {

    String TAG = "==FragTutorialThree==";
    //
    View viewFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            viewFragment = inflater.inflate(R.layout.frag_tutorial_three, container, false);
            App.showLogTAG(TAG);
            ButterKnife.bind(this, viewFragment);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return viewFragment;
    }
}
