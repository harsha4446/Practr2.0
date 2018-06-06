package in.co.mybsolutions.practrapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.co.mybsolutions.practrapp.Fragments.FragEventCriteriaNew;
import in.co.mybsolutions.practrapp.Fragments.FragEventSubmission;
import in.co.mybsolutions.practrapp.Fragments.FragEventTask;
import in.co.mybsolutions.practrapp.Utils.pagertabindicator.PagerTabsIndicator;

/**
 * Created by dhaval.mehta on 15-May-18.
 */

public class ActEventCriteriaTaskSubmission extends BaseActivity {

    String TAG = "==ActEventCriteriaTaskSubmission==";

    @BindView(R.id.ivSubBack)
    ImageView ivSubBack;

//    @BindView(R.id.switchControl)
//    public static Switch switchControl;
    public static SwitchButton switchControl;

    @BindView(R.id.indicatorTab)
    PagerTabsIndicator indicatorTab;

    @BindView(R.id.viewPager)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ViewGroup.inflate(this, R.layout.act_event_criteria_task_submission, llContainerSub);
            App.showLogTAG(TAG);
            ButterKnife.bind(this);
            //
            initialisation();
            setClickEvents();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initialisation() {
        try {
//            switchControl = (Switch) findViewById(R.id.switchControl);
            switchControl = (SwitchButton) findViewById(R.id.switchControl);
            setupViewPager(viewPager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivSubBack)
    void backButtonClick() {
        try {
            onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setClickEvents() {
        try {
            switchControl.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                    if (isChecked) {
                        if (FragEventTask.isVisible) {
                            FragEventTask.isSwitchEnable = true;
                            FragEventTask.setExColl("1");
                        }

                        if (FragEventSubmission.isVisible) {
                            FragEventSubmission.isSwitchEnable = true;
                            FragEventSubmission.setListLinear("1");
                        }

                        if (FragEventCriteriaNew.isVisible) {
                            FragEventCriteriaNew.isSwitchEnable = true;
                            FragEventCriteriaNew.setChartCrieList("1");
                        }
                    }
                    else {
                        if (FragEventTask.isVisible) {
                            FragEventTask.isSwitchEnable = false;
                            FragEventTask.setExColl("0");
                        }

                        if (FragEventSubmission.isVisible) {
                            FragEventSubmission.isSwitchEnable = false;
                            FragEventSubmission.setListLinear("0");
                        }

                        if (FragEventCriteriaNew.isVisible) {
                            FragEventCriteriaNew.isSwitchEnable = false;
                            FragEventCriteriaNew.setChartCrieList("0");
                        }
                    }
                }
            });


//            switchControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                    if (isChecked) {
//                        if (FragEventTask.isVisible) {
//                            FragEventTask.isSwitchEnable = true;
//                            FragEventTask.setExColl("1");
//                        }
//
//                        if (FragEventSubmission.isVisible) {
//                            FragEventSubmission.isSwitchEnable = true;
//                            FragEventSubmission.setListLinear("1");
//                        }
//
//                        if (FragEventCriteriaNew.isVisible) {
//                            FragEventCriteriaNew.isSwitchEnable = true;
//                            FragEventCriteriaNew.setChartCrieList("1");
//                        }
//                    }
//                    else {
//                        if (FragEventTask.isVisible) {
//                            FragEventTask.isSwitchEnable = false;
//                            FragEventTask.setExColl("0");
//                        }
//
//                        if (FragEventSubmission.isVisible) {
//                            FragEventSubmission.isSwitchEnable = false;
//                            FragEventSubmission.setListLinear("0");
//                        }
//
//                        if (FragEventCriteriaNew.isVisible) {
//                            FragEventCriteriaNew.isSwitchEnable = false;
//                            FragEventCriteriaNew.setChartCrieList("0");
//                        }
//                    }
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setupViewPager(ViewPager viewPager) {
        try {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//            adapter.addFrag(new FragEventCriteria(), "CRITERIA");
            adapter.addFrag(new FragEventCriteriaNew(), "CRITERIA");
            adapter.addFrag(new FragEventTask(), "TASK");
            adapter.addFrag(new FragEventSubmission(), "SUBMISSION");
            viewPager.setAdapter(adapter);
            indicatorTab.setViewPager(viewPager);
            viewPager.setOffscreenPageLimit(3); // prevent refresh all fragments on tab change/swipe


            ViewGroup vg = (ViewGroup) indicatorTab.getChildAt(0);
            int tabsCount = vg.getChildCount();
            for (int j = 0; j < tabsCount; j++) {
                ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
                int tabChildsCount = vgTab.getChildCount();
                for (int i = 0; i < tabChildsCount; i++) {
                    View tabViewChild = vgTab.getChildAt(i);
                    if (tabViewChild instanceof TextView) {
                        ((TextView) tabViewChild).setTypeface(null, Typeface.BOLD);
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }


        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public void onBackPressed() {
        try {
            FragEventCriteriaNew.isSwitchEnable = false;
            FragEventSubmission.isSwitchEnable = false;
            FragEventTask.isSwitchEnable = false;

            FragEventCriteriaNew.isVisible = false;
            FragEventSubmission.isVisible= false;
            FragEventTask.isVisible = false;

            App.myFinishActivity(ActEventCriteriaTaskSubmission.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
