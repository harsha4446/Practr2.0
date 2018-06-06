package in.co.mybsolutions.practrapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.animation.DynamicAnimation;
import android.support.animation.FloatPropertyCompat;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.mybsolutions.practrapp.Adapter.JudgetListAdapter;
import in.co.mybsolutions.practrapp.Model.UserModel;
import in.co.mybsolutions.practrapp.Utils.ExpandingViewPagerTransformer;

/**
 * Created by dhaval.mehta on 05-May-18.
 */

public class ActEventCards extends BaseActivity {

    String TAG = "==ActEventCards==";

    @BindView(R.id.pager_question)
    ViewPager pager_question;

    @BindView(R.id.pager_questionSmall)
    ViewPager pager_questionSmall;

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    @BindView(R.id.cardAddByCode)
    CardView cardAddByCode;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    //
    ArrayList<UserModel> arrayListUser = new ArrayList<>();

    //
    public int pos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ViewGroup.inflate(this, R.layout.act_event_cards, llContainerSub);
            App.showLogTAG(TAG);
            ButterKnife.bind(this);
            //
            initialisation();
            setClickEvents();
            setArrayListData();
            setAdapter();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initialisation() {
        try {
            pager_question.setCurrentItem(0);
            pager_question.setCurrentItem(pos, true);
            pager_question.setClipToPadding(false);
            pager_question.setPadding(120, 120, 120, 20);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setClickEvents() {
        try {
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

//            final AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams)
//                    collapsing_toolbar.getLayoutParams();
//            params.setScrollFlags(0);
//            collapsing_toolbar.setLayoutParams(params);


            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                    App.showLogTAG(TAG + "==verticalOffset==" + verticalOffset);

                    if (Math.abs(verticalOffset) > 1 && Math.abs(verticalOffset) < 480) {
                        showViews(pager_question);
                        hideViews(pager_questionSmall);
                        hideViews(cardAddByCode);
//                        pager_question.setVisibility(View.VISIBLE);
//                        pager_questionSmall.setVisibility(View.GONE);
//                        cardAddByCode.setVisibility(View.GONE);

                    } else if (Math.abs(verticalOffset) > 480 && Math.abs(verticalOffset) < 600) {
//                        pager_question.setVisibility(View.GONE);
//                        cardAddByCode.setVisibility(View.GONE);
//                        pager_questionSmall.setVisibility(View.VISIBLE);

                        showViews(pager_questionSmall);
                        hideViews(pager_question);
                        hideViews(cardAddByCode);

                    } else if (Math.abs(verticalOffset) > 600) {
//                        cardAddByCode.setVisibility(View.VISIBLE);
//                        pager_question.setVisibility(View.GONE);
//                        pager_questionSmall.setVisibility(View.GONE);

                        showViews(cardAddByCode);
                        hideViews(pager_question);
                        hideViews(pager_questionSmall);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setArrayListData() {
        try {
            arrayListUser.add(new UserModel(
                    "Amie Haralson",
                    "Logged in 15 mins ago.",
                    R.drawable.judge_1
            ));

            arrayListUser.add(new UserModel(
                    "Jared Dunn",
                    "Has accepted your invite.",
                    R.drawable.judge_2
            ));

            arrayListUser.add(new UserModel(
                    "Amrit Agarwal",
                    "Has finishing judging.",
                    R.drawable.judge_3
            ));

            arrayListUser.add(new UserModel(
                    "Amie Haralson",
                    "Logged in 15 mins ago.",
                    R.drawable.judge_1
            ));

            arrayListUser.add(new UserModel(
                    "Jared Dunn",
                    "Has accepted your invite.",
                    R.drawable.judge_2
            ));

            arrayListUser.add(new UserModel(
                    "Amrit Agarwal",
                    "Has finishing judging.",
                    R.drawable.judge_3
            ));

            arrayListUser.add(new UserModel(
                    "Amie Haralson",
                    "Logged in 15 mins ago.",
                    R.drawable.judge_1
            ));

            arrayListUser.add(new UserModel(
                    "Jared Dunn",
                    "Has accepted your invite.",
                    R.drawable.judge_2
            ));

            arrayListUser.add(new UserModel(
                    "Amrit Agarwal",
                    "Has finishing judging.",
                    R.drawable.judge_3
            ));

            arrayListUser.add(new UserModel(
                    "Amie Haralson",
                    "Logged in 15 mins ago.",
                    R.drawable.judge_1
            ));

            arrayListUser.add(new UserModel(
                    "Jared Dunn",
                    "Has accepted your invite.",
                    R.drawable.judge_2
            ));

            arrayListUser.add(new UserModel(
                    "Amrit Agarwal",
                    "Has finishing judging.",
                    R.drawable.judge_3
            ));

            arrayListUser.add(new UserModel(
                    "Amie Haralson",
                    "Logged in 15 mins ago.",
                    R.drawable.judge_1
            ));

            arrayListUser.add(new UserModel(
                    "Jared Dunn",
                    "Has accepted your invite.",
                    R.drawable.judge_2
            ));

            arrayListUser.add(new UserModel(
                    "Amrit Agarwal",
                    "Has finishing judging.",
                    R.drawable.judge_3
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showViews(final View v) {
        // TODO uncomment this Hide Footer in android when Scrolling
        v.animate().alpha(1.0f).setDuration(400).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }


    private void hideViews(final View v) {
        // TODO (+mToolbar)  plus means  2 view forward ho jaye or not visible to user
        v.animate().alpha(0f).setDuration(600).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                v.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }


    private void setAdapter() {
        try {
            //
            ViewPagerAdapter mAdapter = new ViewPagerAdapter(ActEventCards.this);
            pager_question.setAdapter(mAdapter);
            //
            ViewPagerAdapterSmall smallAdapter = new ViewPagerAdapterSmall(ActEventCards.this);
            pager_questionSmall.setAdapter(smallAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class ViewPagerAdapter extends PagerAdapter {

        private Context mContext;
        private static final int FINAL_Y_UP_POSITION = -350;
        private static final int FINAL_Y_DOWN_POSITION = 0;

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

            View itemView = LayoutInflater.from(mContext).inflate(R.layout.row_event_cards_pager_items, container, false);

            final CardView card_view = (CardView) itemView.findViewById(R.id.card_view);
            View dummyView = (View) itemView.findViewById(R.id.dummyView);
            /*
            * Edit Mode layout
            * */
            final RelativeLayout rlEditModeList = (RelativeLayout) itemView.findViewById(R.id.rlEditModeList);
            ImageView ivSubBack = (ImageView) itemView.findViewById(R.id.ivSubBack);
            RecyclerView recyclerViewEditMode = (RecyclerView) itemView.findViewById(R.id.recyclerViewEditMode);
            LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(ActEventCards.this);
            recyclerViewEditMode.setLayoutManager(linearLayoutManagerCategory);
            recyclerViewEditMode.setHasFixedSize(true);
            recyclerViewEditMode.setNestedScrollingEnabled(false);
            /*
            * Normal Mode layout
            * */
            final RelativeLayout rlNormalModeList = (RelativeLayout) itemView.findViewById(R.id.rlNormalModeList);
            TextView tvParticipant = (TextView) itemView.findViewById(R.id.tvParticipant);
            ImageView ivSubMenu = (ImageView) itemView.findViewById(R.id.ivSubMenu);
            TextView tvBrandReview = (TextView) itemView.findViewById(R.id.tvBrandReview);
            LinearLayout lnProgress = (LinearLayout) itemView.findViewById(R.id.lnProgress);
            RecyclerView recyclerViewNormalMode = (RecyclerView) itemView.findViewById(R.id.recyclerViewNormalMode);
            LinearLayoutManager linearLayoutManagerNormal = new LinearLayoutManager(ActEventCards.this);
            recyclerViewNormalMode.setLayoutManager(linearLayoutManagerNormal);
            recyclerViewNormalMode.setHasFixedSize(true);
            recyclerViewNormalMode.setNestedScrollingEnabled(false);
            /*
            * Common
            * */
//            final ListView lvJudge = (ListView) itemView.findViewById(R.id.lvJudge);
            LinearLayout lnJudge = (LinearLayout) itemView.findViewById(R.id.lnJudge);
            LinearLayout lnScoreSheet = (LinearLayout) itemView.findViewById(R.id.lnScoreSheet);
            final CardView cardJudgeInvite = (CardView) itemView.findViewById(R.id.cardJudgeInvite);
            final CardView cardScoreSheet = (CardView) itemView.findViewById(R.id.cardScoreSheet);
            final CardView main_card_view = (CardView) itemView.findViewById(R.id.card_view);
            final ImageView imgClose1 = (ImageView) itemView.findViewById(R.id.imgClose1);
            final ImageView imgClose2 = (ImageView) itemView.findViewById(R.id.imgClose2);

            /*
            * Animaiton
            * */
            final float originalButtonTranslation = dummyView.getTranslationY();
            final SpringAnimation springUpAnim = new SpringAnimation(card_view, DynamicAnimation.TRANSLATION_Y, FINAL_Y_UP_POSITION);
            springUpAnim.getSpring().setDampingRatio(1f);
            springUpAnim.getSpring().setStiffness(10);
            //
            final SpringAnimation springDownAnim = new SpringAnimation(card_view, DynamicAnimation.TRANSLATION_Y, FINAL_Y_DOWN_POSITION);
            springDownAnim.getSpring().setDampingRatio(1f);
            springDownAnim.getSpring().setStiffness(10);

//            JudgetListAdapter jlAdapter = new JudgetListAdapter(mContext, 0);
//            lvJudge.setAdapter(jlAdapter);-

            //
            RVAdapterNormalListMode adapterNormal = new RVAdapterNormalListMode(ActEventCards.this, arrayListUser);
            recyclerViewNormalMode.setAdapter(adapterNormal);
            adapterNormal.notifyDataSetChanged();
            //
            RVAdapterEditListMode adapterEdit = new RVAdapterEditListMode(ActEventCards.this, arrayListUser);
            recyclerViewEditMode.setAdapter(adapterEdit);
            adapterEdit.notifyDataSetChanged();
            
            //
            springUpAnim.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled,
                                           float value, float velocity) {
                    card_view.setTranslationX(originalButtonTranslation);
                }
            });


            //
            springDownAnim.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled,
                                           float value, float velocity) {
                    card_view.setTranslationX(originalButtonTranslation);
                }
            });


            lnJudge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    springUpAnim.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cardScoreSheet.setVisibility(View.GONE);
                            cardJudgeInvite.setVisibility(View.VISIBLE);
                        }
                    }, 1000);

                }
            });
            lnScoreSheet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    springUpAnim.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cardScoreSheet.setVisibility(View.VISIBLE);
                            cardJudgeInvite.setVisibility(View.GONE);
                        }
                    }, 1000);


                }
            });

            imgClose1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    springDownAnim.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cardJudgeInvite.setVisibility(View.GONE);
                            cardScoreSheet.setVisibility(View.GONE);
                        }
                    }, 200);

                }
            });
            imgClose2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    springDownAnim.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cardScoreSheet.setVisibility(View.GONE);
                            cardJudgeInvite.setVisibility(View.GONE);
                        }
                    }, 200);
                }
            });


            //
            ivSubMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rlNormalModeList.setVisibility(View.GONE);
                    rlEditModeList.setVisibility(View.VISIBLE);
                }
            });
            //
            ivSubBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rlNormalModeList.setVisibility(View.VISIBLE);
                    rlEditModeList.setVisibility(View.GONE);
                }
            });


//            lvJudge.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    int action = event.getAction();
//                    switch (action) {
//                        case MotionEvent.ACTION_MOVE:
//                            lvJudge.getParent().requestDisallowInterceptTouchEvent(true);
//                            break;
//                    }
//                    return false;
//                }
//            });



            recyclerViewNormalMode.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                @Override
                public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                    int action = e.getAction();
                    switch (action) {
                        case MotionEvent.ACTION_MOVE:
                            rv.getParent().requestDisallowInterceptTouchEvent(true);
                            break;
                    }
                    return false;
                }

                @Override
                public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            });

            recyclerViewEditMode.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                @Override
                public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                    int action = e.getAction();
                    switch (action) {
                        case MotionEvent.ACTION_MOVE:
                            rv.getParent().requestDisallowInterceptTouchEvent(true);
                            break;
                    }
                    return false;
                }

                @Override
                public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            });


            container.addView(itemView);

            return itemView;
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
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.row_event_cards_small_pager_items, container, false);

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


    public class RVAdapterNormalListMode extends RecyclerView.Adapter<RVAdapterNormalListMode.VersionViewHolder> {

        ArrayList<UserModel> mArrayList;
        Context mContext;

        public RVAdapterNormalListMode(Context context, ArrayList<UserModel> arrayList) {
            mContext = context;

            this.mArrayList = arrayList;
        }

        @Override
        public RVAdapterNormalListMode.VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_event_card_normal_list_items, viewGroup, false);
            RVAdapterNormalListMode.VersionViewHolder viewHolder = new RVAdapterNormalListMode.VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final RVAdapterNormalListMode.VersionViewHolder versionViewHolder, final int position) {
            try {
                if (mArrayList != null && mArrayList.size() > 0) {
                    final UserModel model = mArrayList.get(position);
                    //
                    if (model.name != null && model.name.length() > 0) {
                        versionViewHolder.tvName.setText(model.name);
                    }
                    //
                    if (model.desc != null && model.desc.length() > 0) {
                        versionViewHolder.tvDesc.setText(model.desc);
                    }
                    //
                    if (model.image != 0) {
                        versionViewHolder.ivImage.setImageResource(model.image);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @Override
        public int getItemCount() {
            return mArrayList.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {

            TextView tvName, tvDesc;
            ImageView ivImage, ivRightImage;
            RelativeLayout rlMain;

            public VersionViewHolder(View itemView) {
                super(itemView);

                tvName = (TextView) itemView.findViewById(R.id.tvName);
                tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
                ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
                ivRightImage = (ImageView) itemView.findViewById(R.id.ivRightImage);
                rlMain = (RelativeLayout) itemView.findViewById(R.id.rlMain);
            }
        }
    }


    public class RVAdapterEditListMode extends RecyclerView.Adapter<RVAdapterEditListMode.VersionViewHolder> {

        ArrayList<UserModel> mArrayList;
        Context mContext;

        public RVAdapterEditListMode(Context context, ArrayList<UserModel> arrayList) {
            mContext = context;

            this.mArrayList = arrayList;
        }

        @Override
        public RVAdapterEditListMode.VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_event_card_edit_list_items, viewGroup, false);
            RVAdapterEditListMode.VersionViewHolder viewHolder = new RVAdapterEditListMode.VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final RVAdapterEditListMode.VersionViewHolder versionViewHolder, final int position) {
            try {
                if (mArrayList != null && mArrayList.size() > 0) {
                    final UserModel model = mArrayList.get(position);
                    //
                    if (model.name != null && model.name.length() > 0) {
                        versionViewHolder.tvName.setText(model.name);
                    }
                    //
                    if (model.desc != null && model.desc.length() > 0) {
                        versionViewHolder.tvDesc.setText(model.desc);
                    }
                    //
                    if (model.image != 0) {
                        versionViewHolder.ivImage.setImageResource(model.image);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @Override
        public int getItemCount() {
            return mArrayList.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder {

            TextView tvName, tvDesc;
            ImageView ivImage, ivRightImage;
            RelativeLayout rlMain;

            public VersionViewHolder(View itemView) {
                super(itemView);

                tvName = (TextView) itemView.findViewById(R.id.tvName);
                tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
                ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
                ivRightImage = (ImageView) itemView.findViewById(R.id.ivRightImage);
                rlMain = (RelativeLayout) itemView.findViewById(R.id.rlMain);
            }
        }
    }


    @Override
    public void onBackPressed() {
        App.myFinishActivity(ActEventCards.this);
    }
}
