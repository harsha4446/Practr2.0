package in.co.mybsolutions.practrapp;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mmin18.widget.RealtimeBlurView;
import com.suke.widget.SwitchButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.co.mybsolutions.practrapp.Model.UserModel;
import in.co.mybsolutions.practrapp.Utils.CarouselEffectTransformer;
import in.co.mybsolutions.practrapp.Utils.ClickListener;
import in.co.mybsolutions.practrapp.Utils.ExpandingViewPagerTransformer;
import in.co.mybsolutions.practrapp.Utils.RecyclerTouchListener;
import in.co.mybsolutions.practrapp.Utils.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by dhaval.mehta on 14-May-18.
 */

public class ActEventsSlidingUpPanel extends BaseActivity {

    String TAG = "==ActEventsSlidingUpPanel==";

    @BindView(R.id.realTimeBlurView)
    RealtimeBlurView realTimeBlurView;

    @BindView(R.id.pager_question)
    ViewPager pager_question;
    //
    @BindView(R.id.rlBottomSheetFirst)
    RelativeLayout rlBottomSheetFirst;

    @BindView(R.id.rlBottomSheetSecond)
    RelativeLayout rlBottomSheetSecond;

    @BindView(R.id.ivSecondBottomSheetIcon)
    ImageView ivSecondBottomSheetIcon;

    @BindView(R.id.switchControl)
    SwitchButton switchControl;
//    Switch switchControl;

    @BindView(R.id.tvQualifiedParticipants)
    TextView tvQualifiedParticipants;

    @BindView(R.id.tvTagEliminate)
    TextView tvTagEliminate;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.cardAddByCode)
    CardView cardAddByCode;

    @BindView(R.id.mLayout)
    SlidingUpPanelLayout mLayout;

    @BindView(R.id.dragView)
    LinearLayout dragView;

    @BindView(R.id.tvEventName)
    TextView tvEventName;

    //
    ArrayList<UserModel> arrayListUser = new ArrayList<>();
    RVAdapterSecondBottomSheet adapter;
    //
    boolean isBottomSheetOpen = false;
    boolean isSwitchOff = false;
    int SCREEN_HEIGHT = 0, SCREEN_WIDTH = 0;
    int BOTTOM_SHEET_HEIGHT = 0;
    int VIEWPAGER_PADDING_TOP = 0;
    int VIEWPAGER_PADDING_BOTTOM = 0;
    int ANIMATION_CARD_Y = 0;
    int PAGER_CARD_MARGIN_JUDGE_SCORESHEET = 0;

    float HEIGHT_BOTTOMSHEET_SMALL = 0.65F; // 65% of screen height
    float HEIGHT_BOTTOMSHEET_BIG = 0.70F; // 65% of screen height
    float PADDING_TOP_VIEWPAGER_CARD = 0.22F; // 22% of screen height
    float PADDING_BOTTOM_VIEWPAGER_CARD = 0.05F; // 25% of screen height
    float ANIMATION_Y_CARD_PAGER = 0.40F; // 40% of screen height
    float PAGER_CARD_JUDGE_SCORESHEET_MARGIN = 0.05F; // 5% of screen height


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ViewGroup.inflate(this, R.layout.act_event_cards_sliding_up_panel, llContainerSub);
            App.showLogTAG(TAG);
            ButterKnife.bind(this);
            //
            initialisation();
            setArrayListData();
            setClickEvents();
            setAdapter();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initialisation() {
        try {
            // Disable click on sliding panel - only drag will work
            mLayout.getChildAt(1).setOnClickListener(null);
            //
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            SCREEN_HEIGHT = displayMetrics.heightPixels;
            SCREEN_WIDTH = displayMetrics.widthPixels;
            App.showLog(TAG + "==SCREEN_WIDTH==" + SCREEN_WIDTH);
            App.showLog(TAG + "==SCREEN_HEIGHT==" + SCREEN_HEIGHT);
            //
            if (SCREEN_HEIGHT > 2000) {
                BOTTOM_SHEET_HEIGHT = (int) (((float) SCREEN_HEIGHT) * HEIGHT_BOTTOMSHEET_BIG);
            } else {
                BOTTOM_SHEET_HEIGHT = (int) (((float) SCREEN_HEIGHT) * HEIGHT_BOTTOMSHEET_SMALL);
            }
            App.showLogTAG("== 1 == 65% =**=" + BOTTOM_SHEET_HEIGHT + " ** 65% **");

            VIEWPAGER_PADDING_TOP = (int) (((float) SCREEN_HEIGHT) * PADDING_TOP_VIEWPAGER_CARD);
            VIEWPAGER_PADDING_BOTTOM = (int) (((float) SCREEN_HEIGHT) * PADDING_BOTTOM_VIEWPAGER_CARD);
            ANIMATION_CARD_Y = (int) (((float) SCREEN_HEIGHT) * ANIMATION_Y_CARD_PAGER);
            PAGER_CARD_MARGIN_JUDGE_SCORESHEET = (int) (((float) SCREEN_HEIGHT) * PAGER_CARD_JUDGE_SCORESHEET_MARGIN);
            App.showLogTAG("== 1 == 22% =**=" + VIEWPAGER_PADDING_TOP + " ** 22% **");
            App.showLogTAG("== 1 == 5% =**=" + VIEWPAGER_PADDING_BOTTOM + " ** 5% **");
            App.showLogTAG("== 1 == 30% =**=" + ANIMATION_CARD_Y + " ** 30% **");
            App.showLogTAG("== 1 == 5% =**=" + PAGER_CARD_MARGIN_JUDGE_SCORESHEET + " ** 5% **");
            //
            ViewGroup.LayoutParams params = dragView.getLayoutParams();
            params.height = BOTTOM_SHEET_HEIGHT;
            params.width = SCREEN_WIDTH;
            dragView.setLayoutParams(params);
            //
//            pager_question.setCurrentItem(0);
            pager_question.setCurrentItem(0, true);
//            pager_question.setClipToPadding(false);
//            pager_question.setPadding(120, 10, 120, 20);
            pager_question.setPageMargin(35); // 40
//            pager_question.setPadding(50, VIEWPAGER_PADDING_TOP, 50, VIEWPAGER_PADDING_BOTTOM);

//            pager_question.setClipToPadding(false);
//            pager_question.setPageTransformer(true, new ExpandingViewPagerTransformer());
            pager_question.setOffscreenPageLimit(3);
            pager_question.setPageTransformer(false, new CarouselEffectTransformer(this)); // Set transformer


            // differentDensityAndScreenSize(ActEventsSlidingUpPanel.this);
            //
            cardAddByCode.setVisibility(View.VISIBLE);
            cardAddByCode.setAlpha(0f);
            //
            LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(ActEventsSlidingUpPanel.this);
            recyclerView.setLayoutManager(linearLayoutManagerCategory);
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);
            //
            // realTimeBlurView.setVisibility(View.GONE);
            //realTimeBlurView.setOverlayColor(getResources().getColor(R.color.clrBlurViewBlack));
            realTimeBlurView.setBlurRadius(TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 0,
                    getResources().getDisplayMetrics()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setClickEvents() {
        try {
            //
            mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {

                @Override
                public void onPanelSlide(View panel, float slideOffset) {
                    // Log.i(TAG, "onPanelSlide, offset " + slideOffset);
                    int blurRadius = (int) (slideOffset * 22);
                    // App.showLog(TAG + "==blurRadius==" + blurRadius);
                    cardAddByCode.setAlpha(slideOffset);

                    if (blurRadius < 2) {
                        realTimeBlurView.setVisibility(View.GONE);
                    } else {
                        if (isSwitchOff == true) {
                            realTimeBlurView.setOverlayColor(getResources().getColor(R.color.clrBlurViewBlack));
                        } else {
                            realTimeBlurView.setOverlayColor(getResources().getColor(R.color.clrBlurViewRed));
                        }
                        realTimeBlurView.setVisibility(View.VISIBLE);
                        realTimeBlurView.setBlurRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                blurRadius,
                                getResources().getDisplayMetrics()));
                    }
                }

                @Override
                public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                    // Log.i(TAG, "onPanelStateChanged " + newState);
                    switch (newState) {
                        case COLLAPSED:
                            App.showLog(TAG + "==STATE_COLLAPSED==");

                            // cardAddByCode.setAlpha(0f);
                            rlBottomSheetSecond.setVisibility(View.GONE);
                            //realTimeBlurView.setVisibility(View.INVISIBLE);
                            rlBottomSheetFirst.setVisibility(View.VISIBLE);
                            isBottomSheetOpen = false;
                            realTimeBlurView.setBlurRadius(TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 0,
                                    getResources().getDisplayMetrics()));
                            break;

                        case DRAGGING:
                            App.showLog(TAG + "==STATE_DRAGGING==");

                            // cardAddByCode.setAlpha(1f);
                            rlBottomSheetFirst.setVisibility(View.GONE);
                            rlBottomSheetSecond.setVisibility(View.VISIBLE);
                            realTimeBlurView.setVisibility(View.VISIBLE);
                            isBottomSheetOpen = true;
                            break;

                        case EXPANDED:
                            App.showLog(TAG + "==STATE_EXPANDED==");

                            break;

                        case HIDDEN:
                            App.showLog(TAG + "==STATE_HIDDEN==");
                            break;

                        default:
                            App.showLog(TAG + "== unknown... ==");
                    }
                }
            });

//            mLayout.setFadeOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mLayout.setPanelState(COLLAPSED);
//                }
//            });


            switchControl.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                    if (isChecked) {
                        isSwitchOff = true;
                        realTimeBlurView.setOverlayColor(getResources().getColor(R.color.clrBlurViewBlack));
                        ivSecondBottomSheetIcon.setImageResource(R.drawable.ic_red_delete);
                        cardAddByCode.setVisibility(View.VISIBLE);
                        tvTagEliminate.setText("to eliminate, Swipe up to manage participants");
                        tvQualifiedParticipants.setText("QUALIFIED PARTICIPANTS");
                    } else {
                        isSwitchOff = false;
                        realTimeBlurView.setOverlayColor(getResources().getColor(R.color.clrBlurViewRed));
                        ivSecondBottomSheetIcon.setImageResource(R.drawable.ic_green_refresh);
                        cardAddByCode.setVisibility(View.GONE);
                        tvTagEliminate.setText("to re-quality, Swipe down to view all rounds.");
                        tvQualifiedParticipants.setText("ELIMINATED PARTICIPANTS");
                    }
                    adapter.notifyDataSetChanged();
                }
            });


//            switchControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (isChecked) {
//                        isSwitchOff = true;
//                        ivSecondBottomSheetIcon.setImageResource(R.drawable.ic_red_delete);
//                        cardAddByCode.setVisibility(View.VISIBLE);
//                        tvTagEliminate.setText("to eliminate, Swipe up to manage participants");
//                        tvQualifiedParticipants.setText("QUALIFIED PARTICIPANTS");
//                    } else {
//                        isSwitchOff = false;
//                        ivSecondBottomSheetIcon.setImageResource(R.drawable.ic_green_refresh);
//                        cardAddByCode.setVisibility(View.GONE);
//                        tvTagEliminate.setText("to re-quality, Swipe down to view all rounds.");
//                        tvQualifiedParticipants.setText("ELIMINATED PARTICIPANTS");
//                    }
//                    adapter.notifyDataSetChanged();
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setAdapter() {
        try {
            //
            ViewPagerAdapter mAdapter = new ViewPagerAdapter(ActEventsSlidingUpPanel.this);
            pager_question.setAdapter(mAdapter);
            //
            isSwitchOff = true;
            adapter = new RVAdapterSecondBottomSheet(ActEventsSlidingUpPanel.this, arrayListUser);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
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


    public class ViewPagerAdapter extends PagerAdapter {

        private Context mContext;
//        private static final int FINAL_Y_UP_POSITION = -350;
        private final int FINAL_Y_UP_POSITION = ANIMATION_CARD_Y * (-1);
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
            try {

                final CardView card_view = (CardView) itemView.findViewById(R.id.card_view);
                View dummyView = (View) itemView.findViewById(R.id.dummyView);
                /*
                * Edit Mode layout
                * */
                final RelativeLayout rlEditModeList = (RelativeLayout) itemView.findViewById(R.id.rlEditModeList);
                ImageView ivSubBack = (ImageView) itemView.findViewById(R.id.ivSubBack);
                RecyclerView recyclerViewEditMode = (RecyclerView) itemView.findViewById(R.id.recyclerViewEditMode);
                LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(ActEventsSlidingUpPanel.this);
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
                LinearLayoutManager linearLayoutManagerNormal = new LinearLayoutManager(ActEventsSlidingUpPanel.this);
                recyclerViewNormalMode.setLayoutManager(linearLayoutManagerNormal);
                recyclerViewNormalMode.setHasFixedSize(true);
                recyclerViewNormalMode.setNestedScrollingEnabled(false);
                /*
                * Common
                * */
                LinearLayout root_container = (LinearLayout) itemView.findViewById(R.id.root_container);
                LinearLayout lnJudge = (LinearLayout) itemView.findViewById(R.id.lnJudge);
                LinearLayout lnScoreSheet = (LinearLayout) itemView.findViewById(R.id.lnScoreSheet);
                final CardView cardJudgeInvite = (CardView) itemView.findViewById(R.id.cardJudgeInvite);
                final CardView cardScoreSheet = (CardView) itemView.findViewById(R.id.cardScoreSheet);
                final CardView main_card_view = (CardView) itemView.findViewById(R.id.card_view);

//                ViewGroup.MarginLayoutParams layoutParams =
//                        (ViewGroup.MarginLayoutParams) cardJudgeInvite.getLayoutParams();
//                layoutParams.setMargins(0, 0, 0, PAGER_CARD_MARGIN_JUDGE_SCORESHEET);
//                cardJudgeInvite.requestLayout();
//
//                ViewGroup.MarginLayoutParams layoutParamsScoreSheet =
//                        (ViewGroup.MarginLayoutParams) cardScoreSheet.getLayoutParams();
//                layoutParamsScoreSheet.setMargins(0, 0, 0, PAGER_CARD_MARGIN_JUDGE_SCORESHEET);
//                cardScoreSheet.requestLayout();



                /*
                * Animaiton
                * */
                final float originalButtonTranslation = dummyView.getTranslationY();
                final SpringAnimation springUpAnim = new SpringAnimation(card_view, DynamicAnimation.TRANSLATION_Y, FINAL_Y_UP_POSITION);
                springUpAnim.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY); // spring bounce ratio - 1 - no bounce
                springUpAnim.getSpring().setStiffness(SpringForce.STIFFNESS_MEDIUM); // animation speed
//                springUpAnim.getSpring().setStiffness(SpringForce.STIFFNESS_LOW); // animation speed
                //
                final SpringAnimation springDownAnim = new SpringAnimation(card_view, DynamicAnimation.TRANSLATION_Y, FINAL_Y_DOWN_POSITION);
                springDownAnim.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY); // spring bounce ratio - 1 - no bounce
                springDownAnim.getSpring().setStiffness(SpringForce.STIFFNESS_MEDIUM); // animation speed
//                springDownAnim.getSpring().setStiffness(SpringForce.STIFFNESS_LOW); // animation speed

                //
                RVAdapterNormalListMode adapterNormal = new RVAdapterNormalListMode(ActEventsSlidingUpPanel.this, arrayListUser);
                recyclerViewNormalMode.setAdapter(adapterNormal);
                adapterNormal.notifyDataSetChanged();
                //
                RVAdapterEditListMode adapterEdit = new RVAdapterEditListMode(ActEventsSlidingUpPanel.this, arrayListUser);
                recyclerViewEditMode.setAdapter(adapterEdit);
                adapterEdit.notifyDataSetChanged();

                // left, top, right, bottom
                // root_container.setPadding(0, VIEWPAGER_PADDING_TOP, 0, VIEWPAGER_PADDING_BOTTOM);

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

                        hideViews(tvEventName);
                        springUpAnim.start();
                        cardScoreSheet.setVisibility(View.GONE);
                        cardJudgeInvite.setVisibility(View.VISIBLE);
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                cardScoreSheet.setVisibility(View.GONE);
//                                cardJudgeInvite.setVisibility(View.VISIBLE);
//                            }
//                        }, 100);

                    }
                });
                lnScoreSheet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        hideViews(tvEventName);
                        springUpAnim.start();
                        cardScoreSheet.setVisibility(View.VISIBLE);
                        cardJudgeInvite.setVisibility(View.GONE);
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                cardScoreSheet.setVisibility(View.VISIBLE);
//                                cardJudgeInvite.setVisibility(View.GONE);
//                            }
//                        }, 100);
                    }
                });


                recyclerViewNormalMode.addOnItemTouchListener(new RecyclerTouchListener(ActEventsSlidingUpPanel.this,
                        recyclerView, new ClickListener() {

                    @Override
                    public void onClick(View view, final int position) {
//                        App.showToastLong(ActEventsSlidingUpPanel.this, "Recycler View Clicked");
                        if (cardJudgeInvite.isShown() || cardScoreSheet.isShown()) {
                            showViews(tvEventName);
                            springDownAnim.start();
                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    cardJudgeInvite.setVisibility(View.GONE);
                                    cardScoreSheet.setVisibility(View.GONE);
                                }
                            }, 200);
                        } else {
                            Intent iv = new Intent(ActEventsSlidingUpPanel.this, ActEventCriteriaTaskSubmission.class);
                            App.myStartActivity(ActEventsSlidingUpPanel.this, iv);
                        }
                    }

                    @Override
                    public void onLongClick(View view, int position) {
//                        Toast.makeText(this, "Long press on position :"+position,
//                                Toast.LENGTH_LONG).show();
                    }
                }));


                recyclerViewEditMode.addOnItemTouchListener(new RecyclerTouchListener(ActEventsSlidingUpPanel.this,
                        recyclerView, new ClickListener() {

                    @Override
                    public void onClick(View view, final int position) {
                        //Values are passing to activity & to fragment as well
//                        App.showToastLong(ActEventsSlidingUpPanel.this, "Recycler View Clicked");
                        if (cardJudgeInvite.isShown() || cardScoreSheet.isShown()) {
                            showViews(tvEventName);
                            springDownAnim.start();
                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    cardJudgeInvite.setVisibility(View.GONE);
                                    cardScoreSheet.setVisibility(View.GONE);
                                }
                            }, 200);
                        } else {
                            Intent iv = new Intent(ActEventsSlidingUpPanel.this, ActEventCriteriaTaskSubmission.class);
                            App.myStartActivity(ActEventsSlidingUpPanel.this, iv);
                        }
                    }

                    @Override
                    public void onLongClick(View view, int position) {
//                        Toast.makeText(this, "Long press on position :"+position,
//                                Toast.LENGTH_LONG).show();
                    }
                }));


                rlNormalModeList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cardJudgeInvite.isShown() || cardScoreSheet.isShown()) {
                            showViews(tvEventName);
                            cardJudgeInvite.setVisibility(View.GONE);
                            cardScoreSheet.setVisibility(View.GONE);
//                            springDownAnim.start();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    springDownAnim.start();
                                }
                            }, 1200);

//                            new Handler().postDelayed(new Runnable() {
//
//                                @Override
//                                public void run() {
//                                    cardJudgeInvite.setVisibility(View.GONE);
//                                    cardScoreSheet.setVisibility(View.GONE);
//                                }
//                            }, 100);
                        } else {
                            Intent iv = new Intent(ActEventsSlidingUpPanel.this, ActEventCriteriaTaskSubmission.class);
                            App.myStartActivity(ActEventsSlidingUpPanel.this, iv);
                        }
                    }
                });

                //
                rlEditModeList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cardJudgeInvite.isShown() || cardScoreSheet.isShown()) {

                            showViews(tvEventName);
                            cardJudgeInvite.setVisibility(View.GONE);
                            cardScoreSheet.setVisibility(View.GONE);
                            //springDownAnim.start();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    springDownAnim.start();
                                }
                            }, 1200);

//                            new Handler().postDelayed(new Runnable() {
//
//                                @Override
//                                public void run() {
//                                    cardJudgeInvite.setVisibility(View.GONE);
//                                    cardScoreSheet.setVisibility(View.GONE);
//                                }
//                            }, 100);
                        } else {
                            Intent iv = new Intent(ActEventsSlidingUpPanel.this, ActEventCriteriaTaskSubmission.class);
                            App.myStartActivity(ActEventsSlidingUpPanel.this, iv);
                        }
                    }
                });

//                rlNormalModeList.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        springDownAnim.start();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                cardJudgeInvite.setVisibility(View.GONE);
//                                cardScoreSheet.setVisibility(View.GONE);
//                            }
//                        }, 200);
//                    }
//                });
//
//                rlEditModeList.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        springDownAnim.start();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                cardJudgeInvite.setVisibility(View.GONE);
//                                cardScoreSheet.setVisibility(View.GONE);
//                            }
//                        }, 200);
//                    }
//                });

//                imgClose1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        springDownAnim.start();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                cardJudgeInvite.setVisibility(View.GONE);
//                                cardScoreSheet.setVisibility(View.GONE);
//                            }
//                        }, 200);
//
//                    }
//                });
//                imgClose2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        springDownAnim.start();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                cardScoreSheet.setVisibility(View.GONE);
//                                cardJudgeInvite.setVisibility(View.GONE);
//                            }
//                        }, 200);
//                    }
//                });

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
            } catch (Exception e) {
                e.printStackTrace();
            }

            return itemView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }


    private void showViews(final View v) {
        // TODO uncomment this Hide Footer in android when Scrolling
        v.animate().alpha(1.0f).setDuration(800).setListener(new Animator.AnimatorListener() {
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
        v.animate().alpha(0f).setDuration(300).setListener(new Animator.AnimatorListener() {
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


    public class RVAdapterSecondBottomSheet extends RecyclerView.Adapter<RVAdapterSecondBottomSheet.VersionViewHolder> {

        ArrayList<UserModel> mArrayList;
        Context mContext;

        public RVAdapterSecondBottomSheet(Context context, ArrayList<UserModel> arrayList) {
            mContext = context;

            this.mArrayList = arrayList;
        }

        @Override
        public RVAdapterSecondBottomSheet.VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_second_bottom_sheet_list_items, viewGroup, false);
            RVAdapterSecondBottomSheet.VersionViewHolder viewHolder = new RVAdapterSecondBottomSheet.VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final RVAdapterSecondBottomSheet.VersionViewHolder versionViewHolder, final int position) {
            try {
                if (mArrayList != null && mArrayList.size() > 0) {
                    final UserModel model = mArrayList.get(position);
                    //
                    if (isSwitchOff == true) {
                        versionViewHolder.ivRightImage.setImageResource(R.drawable.ic_red_delete);
                    } else {
                        versionViewHolder.ivRightImage.setImageResource(R.drawable.ic_green_refresh);
                    }
                    //
                    if (model.name != null && model.name.length() > 0) {
                        versionViewHolder.tvName.setText(model.name);
                    }
                    //
                    if (model.desc != null && model.desc.length() > 0) {
                        versionViewHolder.tvDesc.setText(model.desc);
                    }
                    //
//                    if (model.image != 0) {
//                        versionViewHolder.ivImage.setImageResource(model.image);
//                    }
                    //
                    versionViewHolder.rlMain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent iv = new Intent(ActEventsSlidingUpPanel.this, ActCodeAlpha.class);
                            App.myStartActivity(ActEventsSlidingUpPanel.this, iv);
                        }
                    });
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
        App.myFinishActivity(ActEventsSlidingUpPanel.this);
    }
}
