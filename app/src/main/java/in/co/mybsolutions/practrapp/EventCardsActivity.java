package in.co.mybsolutions.practrapp;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.co.mybsolutions.practrapp.Adapter.CarouselPagerAdapter;
import in.co.mybsolutions.practrapp.Adapter.JudgetListAdapter;
import in.co.mybsolutions.practrapp.Adapter.ViewPagerAdapter;
import in.co.mybsolutions.practrapp.Utils.AnchorSheetBehavior;
import in.co.mybsolutions.practrapp.Utils.ExpandingViewPagerTransformer;

/**
 * Created by Yash Ajabiya on 5/2/2018.
 */

public class EventCardsActivity extends BaseActivity {


    private AnchorSheetBehavior<View> anchorBehavior;

    public static ViewPager pager_question;
    public static ViewPager pager_questionSmall;
    private ViewPagerAdapter mAdapter;
    public int pos = 0;
    public static int count = 5;
    public final static int LOOPS = 1000;
    public static int FIRST_PAGE = 10;

    String str_device;
//public CarouselPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventcards);
        pager_question = (ViewPager) findViewById(R.id.pager_question);
        pager_questionSmall = (ViewPager) findViewById(R.id.pager_questionSmall);
        differentDensityAndScreenSize(getApplicationContext());

        anchorBehavior = AnchorSheetBehavior.from(findViewById(R.id.rlFooter));
//         anchorBehavior.setHideable(true);
        anchorBehavior.setState(AnchorSheetBehavior.STATE_COLLAPSED);
        anchorBehavior.setAnchorSheetCallback(new AnchorSheetBehavior.AnchorSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, @AnchorSheetBehavior.State int newState) {

                if (newState == AnchorSheetBehavior.STATE_ANCHOR) {
                    pager_question.setVisibility(View.GONE);
                    pager_questionSmall.setVisibility(View.VISIBLE);
                } else if (newState == AnchorSheetBehavior.STATE_COLLAPSED) {
                    pager_question.setVisibility(View.VISIBLE);
                    pager_questionSmall.setVisibility(View.GONE);
                }

                // content.setText(newState == AnchorSheetBehavior.STATE_EXPANDED ? R.string.slide_me_down : R.string.slide_me_up);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });


        pager_question.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int mScrollState = ViewPager.SCROLL_STATE_IDLE;

            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                    return;
                }
                pager_questionSmall.scrollTo(pager_question.getScrollX(), pager_questionSmall.getScrollY());
            }

            @Override
            public void onPageSelected(final int position) {

            }

            @Override
            public void onPageScrollStateChanged(final int state) {
                mScrollState = state;
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    pager_questionSmall.setCurrentItem(pager_question.getCurrentItem(), false);
                }
            }
        });


        pager_questionSmall.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int mScrollState = ViewPager.SCROLL_STATE_IDLE;

            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                    return;
                }
                pager_question.scrollTo(pager_questionSmall.getScrollX(), pager_question.getScrollY());
            }

            @Override
            public void onPageSelected(final int position) {

            }

            @Override
            public void onPageScrollStateChanged(final int state) {
                mScrollState = state;
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    pager_question.setCurrentItem(pager_questionSmall.getCurrentItem(), false);
                }
            }
        });


        setAdapter();
    }


    private void setAdapter() {
        pager_question.setCurrentItem(0);
        pager_question.setCurrentItem(pos, true);
        pager_question.setClipToPadding(false);
        pager_question.setPadding(120, 0, 120, 0);
        pager_question.setPageMargin(10);
        pager_question.setClipToPadding(false);

        pager_questionSmall.setCurrentItem(0);
        pager_questionSmall.setCurrentItem(pos, true);
        pager_questionSmall.setClipToPadding(false);
        pager_questionSmall.setPadding(120, 0, 120, 0);
        pager_questionSmall.setPageMargin(10);
        pager_questionSmall.setClipToPadding(false);

        pager_question.setPageTransformer(true, new ExpandingViewPagerTransformer());
        pager_questionSmall.setPageTransformer(true, new ExpandingViewPagerTransformer());
        mAdapter = new ViewPagerAdapter(EventCardsActivity.this);
        pager_question.setAdapter(mAdapter);


        ViewPagerAdapterSmall smallAdapter = new ViewPagerAdapterSmall(EventCardsActivity.this);
        pager_questionSmall.setAdapter(smallAdapter);
    }


    public class ViewPagerAdapter extends PagerAdapter {

        private Context mContext;

        public ViewPagerAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return 7;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);

            final ListView lvJudge = (ListView) itemView.findViewById(R.id.lvJudge);
            final LinearLayout lnJudge = (LinearLayout) itemView.findViewById(R.id.lnJudge);
            final LinearLayout lnScoreSheet = (LinearLayout) itemView.findViewById(R.id.lnScoreSheet);
            final LinearLayout lnProgress = (LinearLayout) itemView.findViewById(R.id.lnProgress);
            final TextView tvParticipant = (TextView) itemView.findViewById(R.id.tvParticipant);
            final ImageView imgBack = (ImageView) itemView.findViewById(R.id.imgBack);
            final ImageView imgMenu = (ImageView) itemView.findViewById(R.id.imgMenu);
            final RelativeLayout rel = (RelativeLayout) itemView.findViewById(R.id.rel);
            final TextView tvEventName = (TextView) itemView.findViewById(R.id.tvEventName);
            final CardView cardJudgeInvite = (CardView) itemView.findViewById(R.id.cardJudgeInvite);
            final CardView cardScoreSheet = (CardView) itemView.findViewById(R.id.cardScoreSheet);
            final CardView main_card_view = (CardView) itemView.findViewById(R.id.card_view);
            final ImageView imgClose1 = (ImageView) itemView.findViewById(R.id.imgClose1);
            final ImageView imgClose2 = (ImageView) itemView.findViewById(R.id.imgClose2);
            ;
            JudgetListAdapter jlAdapter = new JudgetListAdapter(mContext, 0);
            lvJudge.setAdapter(jlAdapter);

            lnJudge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rel.setVisibility(View.VISIBLE);
                    cardJudgeInvite.setVisibility(View.VISIBLE);
                    cardScoreSheet.setVisibility(View.GONE);
                    tvEventName.setVisibility(View.GONE);
                    slideUp(rel);
                    slideUp(main_card_view);

                }
            });
            lnScoreSheet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rel.setVisibility(View.VISIBLE);
                    cardJudgeInvite.setVisibility(View.GONE);
                    cardScoreSheet.setVisibility(View.VISIBLE);
                    tvEventName.setVisibility(View.GONE);
                    slideUp(rel);
                    slideUp(main_card_view);
                }
            });
            imgMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvParticipant.setVisibility(View.GONE);
                    imgMenu.setVisibility(View.GONE);
                    imgBack.setVisibility(View.VISIBLE);
                    lnProgress.setVisibility(View.INVISIBLE);

                    JudgetListAdapter jlAdapter = new JudgetListAdapter(mContext, 1);
                    lvJudge.setAdapter(jlAdapter);
                }
            });
            imgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvParticipant.setVisibility(View.VISIBLE);
                    imgMenu.setVisibility(View.VISIBLE);
                    lnProgress.setVisibility(View.VISIBLE);
                    imgBack.setVisibility(View.GONE);
                    JudgetListAdapter jlAdapter = new JudgetListAdapter(mContext, 0);
                    lvJudge.setAdapter(jlAdapter);
                }
            });

            imgClose1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rel.setVisibility(View.GONE);
                    cardJudgeInvite.setVisibility(View.GONE);
                    cardScoreSheet.setVisibility(View.GONE);
                    tvEventName.setVisibility(View.VISIBLE);
                    slideDown(rel);

                }
            });
            imgClose2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rel.setVisibility(View.GONE);
                    cardJudgeInvite.setVisibility(View.GONE);
                    cardScoreSheet.setVisibility(View.GONE);
                    tvEventName.setVisibility(View.VISIBLE);
                    slideDown(rel);

                }
            });
            container.addView(itemView);

            return itemView;
        }

        public void slideUp(View view) {
            view.setVisibility(View.VISIBLE);
            TranslateAnimation animate = new TranslateAnimation(
                    0,                 // fromXDelta
                    0,                 // toXDelta
                    view.getHeight(),  // fromYDelta
                    0);                // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);
        }

        public void slideDown(View view) {
            TranslateAnimation animate = new TranslateAnimation(
                    0,                 // fromXDelta
                    0,                 // toXDelta
                    0,                 // fromYDelta
                    view.getHeight()); // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }


    public class ViewPagerAdapterSmall extends PagerAdapter {

        private Context mContext;

        public ViewPagerAdapterSmall(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return 7;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_small_item, container, false);

            final ListView lvJudge = (ListView) itemView.findViewById(R.id.lvJudge);
            final LinearLayout lnJudge = (LinearLayout) itemView.findViewById(R.id.lnJudge);
            final LinearLayout lnScoreSheet = (LinearLayout) itemView.findViewById(R.id.lnScoreSheet);
            final LinearLayout lnProgress = (LinearLayout) itemView.findViewById(R.id.lnProgress);
            final TextView tvParticipant = (TextView) itemView.findViewById(R.id.tvParticipant);
            final ImageView imgBack = (ImageView) itemView.findViewById(R.id.imgBack);
            final ImageView imgMenu = (ImageView) itemView.findViewById(R.id.imgMenu);
            final RelativeLayout rel = (RelativeLayout) itemView.findViewById(R.id.rel);
            final TextView tvEventName = (TextView) itemView.findViewById(R.id.tvEventName);
            final CardView cardJudgeInvite = (CardView) itemView.findViewById(R.id.cardJudgeInvite);
            final CardView cardScoreSheet = (CardView) itemView.findViewById(R.id.cardScoreSheet);
            final CardView main_card_view = (CardView) itemView.findViewById(R.id.card_view);
            final ImageView imgClose1 = (ImageView) itemView.findViewById(R.id.imgClose1);
            final ImageView imgClose2 = (ImageView) itemView.findViewById(R.id.imgClose2);
            ;
//            JudgetListAdapter jlAdapter = new JudgetListAdapter(mContext, 0);
//            lvJudge.setAdapter(jlAdapter);

//            lnJudge.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    rel.setVisibility(View.VISIBLE);
//                    cardJudgeInvite.setVisibility(View.VISIBLE);
//                    cardScoreSheet.setVisibility(View.GONE);
//                    tvEventName.setVisibility(View.GONE);
//                    slideUp(rel);
//                    slideUp(main_card_view);
//
//                }
//            });
//            lnScoreSheet.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    rel.setVisibility(View.VISIBLE);
//                    cardJudgeInvite.setVisibility(View.GONE);
//                    cardScoreSheet.setVisibility(View.VISIBLE);
//                    tvEventName.setVisibility(View.GONE);
//                    slideUp(rel);
//                    slideUp(main_card_view);
//                }
//            });
//            imgMenu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    tvParticipant.setVisibility(View.GONE);
//                    imgMenu.setVisibility(View.GONE);
//                    imgBack.setVisibility(View.VISIBLE);
//                    lnProgress.setVisibility(View.INVISIBLE);
//
//                    JudgetListAdapter jlAdapter = new JudgetListAdapter(mContext, 1);
//                    lvJudge.setAdapter(jlAdapter);
//                }
//            });
//            imgBack.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    tvParticipant.setVisibility(View.VISIBLE);
//                    imgMenu.setVisibility(View.VISIBLE);
//                    lnProgress.setVisibility(View.VISIBLE);
//                    imgBack.setVisibility(View.GONE);
//                    JudgetListAdapter jlAdapter = new JudgetListAdapter(mContext, 0);
//                    lvJudge.setAdapter(jlAdapter);
//                }
//            });
//
//            imgClose1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    rel.setVisibility(View.GONE);
//                    cardJudgeInvite.setVisibility(View.GONE);
//                    cardScoreSheet.setVisibility(View.GONE);
//                    tvEventName.setVisibility(View.VISIBLE);
//                    slideDown(rel);
//
//                }
//            });
//            imgClose2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    rel.setVisibility(View.GONE);
//                    cardJudgeInvite.setVisibility(View.GONE);
//                    cardScoreSheet.setVisibility(View.GONE);
//                    tvEventName.setVisibility(View.VISIBLE);
//                    slideDown(rel);
//
//                }
//            });
            container.addView(itemView);

            return itemView;
        }

        public void slideUp(View view) {
            view.setVisibility(View.VISIBLE);
            TranslateAnimation animate = new TranslateAnimation(
                    0,                 // fromXDelta
                    0,                 // toXDelta
                    view.getHeight(),  // fromYDelta
                    0);                // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);
        }

        public void slideDown(View view) {
            TranslateAnimation animate = new TranslateAnimation(
                    0,                 // fromXDelta
                    0,                 // toXDelta
                    0,                 // fromYDelta
                    view.getHeight()); // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }


    public int differentDensityAndScreenSize(Context context) {
        int value = 20;
        String str = "";
        if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:
                    str = "small-ldpi";
                    // Log.e("small 1","small-ldpi");
                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:
                    str = "small-mdpi";
                    // Log.e("small 1","small-mdpi");
                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_HIGH:
                    str = "small-hdpi";
                    // Log.e("small 1","small-hdpi");
                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_XHIGH:
                    str = "small-xhdpi";
                    // Log.e("small 1","small-xhdpi");
                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:
                    str = "small-xxhdpi";
                    // Log.e("small 1","small-xxhdpi");
                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:
                    str = "small-xxxhdpi";
                    //Log.e("small 1","small-xxxhdpi");
                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_TV:
                    str = "small-tvdpi";
                    // Log.e("small 1","small-tvdpi");
                    value = 20;
                    break;
                default:
                    str = "small-unknown";
                    value = 20;
                    break;
            }

        } else if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:
                    str = "normal-ldpi";
                    // Log.e("normal-ldpi 1","normal-ldpi");
                    str_device = "normal-ldpi";
                    value = 82;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:
                    // Log.e("normal-mdpi 1","normal-mdpi");
                    str = "normal-mdpi";
                    value = 82;
                    str_device = "normal-mdpi";
                    break;
                case DisplayMetrics.DENSITY_HIGH:
                    // Log.e("normal-hdpi 1","normal-hdpi");
                    str = "normal-hdpi";
                    str_device = "normal-hdpi";
                    value = 82;
                    break;
                case DisplayMetrics.DENSITY_XHIGH:
                    //Log.e("normal-xhdpi 1","normal-xhdpi");
                    str = "normal-xhdpi";
                    str_device = "normal-xhdpi";
                    value = 90;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:
                    // Log.e("normal-xxhdpi 1","normal-xxhdpi");
                    str = "normal-xxhdpi";
                    str_device = "normal-xxhdpi";
                    value = 96;
                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:
                    //Log.e("normal-xxxhdpi","normal-xxxhdpi");
                    str = "normal-xxxhdpi";
                    str_device = "normal-xxxhdpi";
                    value = 96;
                    break;
                case DisplayMetrics.DENSITY_TV:
                    //Log.e("DENSITY_TV 1","normal-mdpi");
                    str = "normal-tvdpi";
                    str_device = "normal-tvmdpi";
                    value = 96;
                    break;
                default:
                    // Log.e("normal-unknown","normal-unknown");
                    str = "normal-unknown";
                    str_device = "normal-unknown";
                    value = 82;
                    break;
            }
        } else if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:
                    str = "large-ldpi";
                    // Log.e("large-ldpi 1","normal-ldpi");
                    value = 78;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:
                    str = "large-mdpi";
                    //Log.e("large-ldpi 1","normal-mdpi");
                    value = 78;
                    break;
                case DisplayMetrics.DENSITY_HIGH:
                    //Log.e("large-ldpi 1","normal-hdpi");
                    str = "large-hdpi";
                    value = 78;
                    break;
                case DisplayMetrics.DENSITY_XHIGH:
                    // Log.e("large-ldpi 1","normal-xhdpi");
                    str = "large-xhdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:
                    //Log.e("large-ldpi 1","normal-xxhdpi");
                    str = "large-xxhdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:
                    // Log.e("large-ldpi 1","normal-xxxhdpi");
                    str = "large-xxxhdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_TV:
                    //Log.e("large-ldpi 1","normal-tvdpi");
                    str = "large-tvdpi";
                    value = 125;
                    break;
                default:
                    str = "large-unknown";
                    value = 78;
                    break;
            }

        } else if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:
                    // Log.e("large-ldpi 1","normal-ldpi");
                    str = "xlarge-ldpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:
                    // Log.e("large-ldpi 1","normal-mdpi");
                    str = "xlarge-mdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_HIGH:
                    //Log.e("large-ldpi 1","normal-hdpi");
                    str = "xlarge-hdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XHIGH:
                    // Log.e("large-ldpi 1","normal-hdpi");
                    str = "xlarge-xhdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:
                    // Log.e("large-ldpi 1","normal-xxhdpi");
                    str = "xlarge-xxhdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:
                    // Log.e("large-ldpi 1","normal-xxxhdpi");
                    str = "xlarge-xxxhdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_TV:
                    //Log.e("large-ldpi 1","normal-tvdpi");
                    str = "xlarge-tvdpi";
                    value = 125;
                    break;
                default:
                    str = "xlarge-unknown";
                    value = 125;
                    break;
            }
        }

        return value;
    }

}
