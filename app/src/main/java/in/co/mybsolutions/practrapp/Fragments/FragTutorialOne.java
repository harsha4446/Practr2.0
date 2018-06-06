package in.co.mybsolutions.practrapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.mybsolutions.practrapp.App;
import in.co.mybsolutions.practrapp.R;
import in.co.mybsolutions.practrapp.Utils.materialshadows.MaterialShadowViewWrapper;

/**
 * Created by dhaval.mehta on 22-May-18.
 */

public class FragTutorialOne extends Fragment {

    String TAG = "==FragTutorialOne==";
    //
    View viewFragment;

    @BindView(R.id.shadow_wrapper)
    MaterialShadowViewWrapper shadowViewWrapper;

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
            viewFragment = inflater.inflate(R.layout.frag_tutorial_one, container, false);
            App.showLogTAG(TAG);
            ButterKnife.bind(this, viewFragment);
            //
//            shadowViewWrapper = (MaterialShadowViewWrapper) viewFragment.findViewById(R.id.shadow_wrapper);
            shadowViewWrapper.setShouldCalculateAsync(true);
            shadowViewWrapper.setShowShadowsWhenAllReady(true);
            shadowViewWrapper.requestLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return viewFragment;
    }
}
