package in.co.mybsolutions.practrapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.co.mybsolutions.practrapp.Fragments.FragTutorialOne;
import in.co.mybsolutions.practrapp.Fragments.FragTutorialThree;
import in.co.mybsolutions.practrapp.Fragments.FragTutorialTwo;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by dhaval.mehta on 22-May-18.
 */

public class ActTutorial extends BaseActivity {

    String TAG = "==ActTutorial==";

    @BindView(R.id.indicator)
    CircleIndicator indicator;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.llIndicatorWithSkipNext)
    LinearLayout llIndicatorWithSkipNext;

    @BindView(R.id.tvSkip)
    TextView tvSkip;

    @BindView(R.id.tvSubNext)
    TextView tvSubNext;

    @BindView(R.id.cardView)
    CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ViewGroup.inflate(this, R.layout.act_tutorial, llContainerSub);
            App.showLogTAG(TAG);
            ButterKnife.bind(this);
            //
            initialisation();
            setClickEvents();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setClickEvents() {
        //
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    cardView.setVisibility(View.VISIBLE);
                    llIndicatorWithSkipNext.setVisibility(View.GONE);
                } else {
                    cardView.setVisibility(View.GONE);
                    llIndicatorWithSkipNext.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initialisation() {
        try {
            setupViewPager(viewPager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.tvSkip)
    void skipClick() {
        try {
            Intent iv = new Intent(ActTutorial.this, ActEventsSlidingUpPanel.class);
            App.myStartActivity(ActTutorial.this, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.tvSubNext)
    void nextClick() {
        try {
            //viewPager.setCurrentItem(getItem(+1), true); //getItem(-1) for previous
            MoveNext();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void MoveNext() {
        //it doesn't matter if you're already in the last item
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }


    @OnClick(R.id.cardView)
    void cardViewClick() {
        try {
            Intent iv = new Intent(ActTutorial.this, ActEventsSlidingUpPanel.class);
            App.myStartActivity(ActTutorial.this, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }


    private void setupViewPager(ViewPager viewPager) {
        try {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFrag(new FragTutorialOne(), "");
            adapter.addFrag(new FragTutorialTwo(), "");
            adapter.addFrag(new FragTutorialThree(), "");
            viewPager.setAdapter(adapter);
            indicator.setViewPager(viewPager);
            viewPager.setOffscreenPageLimit(3); // prevent refresh all fragments on tab change/swipe

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
            App.myFinishActivity(ActTutorial.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
