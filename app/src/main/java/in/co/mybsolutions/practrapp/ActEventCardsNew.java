package in.co.mybsolutions.practrapp;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.github.mmin18.widget.RealtimeBlurView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.mybsolutions.practrapp.Model.UserModel;
import in.co.mybsolutions.practrapp.Utils.ClickListener;
import in.co.mybsolutions.practrapp.Utils.CustomBottomSheetBehavior;
import in.co.mybsolutions.practrapp.Utils.ExpandingViewPagerTransformer;
import in.co.mybsolutions.practrapp.Utils.RecyclerTouchListener;

/**
 * Created by dhaval.mehta on 08-May-18.
 */

public class ActEventCardsNew extends BaseActivity {

    String TAG = "==ActEventCardsNew==";

//    @BindView(R.id.nsView)
//    NestedScrollView nsView;

    @BindView(R.id.realTimeBlurView)
    RealtimeBlurView realTimeBlurView;

//    @BindView(R.id.blurring_view)
//    BlurringView blurring_view;

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
    Switch switchControl;

    @BindView(R.id.tvQualifiedParticipants)
    TextView tvQualifiedParticipants;

    @BindView(R.id.tvTagEliminate)
    TextView tvTagEliminate;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.cardAddByCode)
    CardView cardAddByCode;

    @BindView(R.id.rlFooter)
    RelativeLayout rlFooter;

    //
    ArrayList<UserModel> arrayListUser = new ArrayList<>();
    RVAdapterSecondBottomSheet adapter;
    BottomSheetBehavior<View> bottomSheetBehavior;
//    CustomBottomSheetBehavior<View> bottomSheetBehavior;
    boolean isBottomSheetOpen = false;
    boolean isSwitchOff = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ViewGroup.inflate(this, R.layout.act_event_cards_new, llContainerSub);
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
            pager_question.setCurrentItem(0);
            pager_question.setCurrentItem(0, true);
            pager_question.setClipToPadding(false);
            pager_question.setPadding(120, 120, 120, 20);
            pager_question.setPageMargin(10);
            pager_question.setClipToPadding(false);
            pager_question.setPageTransformer(true, new ExpandingViewPagerTransformer());
            //
            cardAddByCode.setVisibility(View.VISIBLE);
            cardAddByCode.setAlpha(0f);
            //
            LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(ActEventCardsNew.this);
            recyclerView.setLayoutManager(linearLayoutManagerCategory);
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);
            //
            realTimeBlurView.setVisibility(View.GONE);
            realTimeBlurView.setBlurRadius(TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 0,
                    getResources().getDisplayMetrics()));
            //
            bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.rlFooter));
            bottomSheetBehavior.setPeekHeight(150);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setClickEvents() {
        try {
            //
//            recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//                @Override
//                public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                    int action = e.getAction();
//                    switch (action) {
//                        case MotionEvent.ACTION_MOVE:
//                            rv.getParent().requestDisallowInterceptTouchEvent(true);
//                            break;
//                    }
//                    return false;
//                }
//
//                @Override
//                public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//                }
//
//                @Override
//                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//                }
//            });
            //
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    switch (newState) {
                        case BottomSheetBehavior.STATE_COLLAPSED:
                            App.showLog(TAG + "==STATE_COLLAPSED==");
//                            rlFooter.setPadding(0, 0, 0, 0);

                            rlBottomSheetFirst.setVisibility(View.VISIBLE);
                            rlBottomSheetSecond.setVisibility(View.GONE);
                            realTimeBlurView.setVisibility(View.GONE);
                            isBottomSheetOpen = false;
                            break;

                        case BottomSheetBehavior.STATE_DRAGGING:
                            App.showLog(TAG + "==STATE_DRAGGING==");
//                            rlFooter.setPadding(0, 300, 0, 0);

                            rlBottomSheetFirst.setVisibility(View.GONE);
                            rlBottomSheetSecond.setVisibility(View.VISIBLE);
                            realTimeBlurView.setVisibility(View.VISIBLE);
                            isBottomSheetOpen = true;
                            break;

                        case BottomSheetBehavior.STATE_EXPANDED:
                            App.showLog(TAG + "==STATE_EXPANDED==");

                            break;

                        case BottomSheetBehavior.STATE_HIDDEN:
                            App.showLog(TAG + "==STATE_HIDDEN==");
                            break;

                        case BottomSheetBehavior.STATE_SETTLING:
                            App.showLog(TAG + "==STATE_SETTLING==");
                            break;

                        default:
                            App.showLog(TAG + "== unknown... ==");
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    int blurRadius = (int) (slideOffset * 22);
                    //                    App.showLog(TAG + "==blurRadius==" + blurRadius);
                    realTimeBlurView.setBlurRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                            blurRadius,
                            getResources().getDisplayMetrics()));

                    cardAddByCode.setAlpha(slideOffset);

                        /*
                        * Working code to know - Bottom sheet scroll donw/up
                        * */
                    //                    float x = (newSlideOffset - slideOffset);
                    //                    newSlideOffset = slideOffset;
                    //                    App.showLogTAG(TAG + "==x==" + x);
                    //                    if (x > 0) {
                    //                        //scroll up
                    //                        App.showLogTAG(TAG + "==Scroll DOWN==");
                    //                    } else if (x < 0) {
                    //                        //scroll down
                    //                        App.showLogTAG(TAG + "==Scroll UP==");
                    //                    } else {
                    //
                    //                    }
                }
            });

            //
            switchControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        isSwitchOff = true;
                        ivSecondBottomSheetIcon.setImageResource(R.drawable.ic_red_delete);
                        cardAddByCode.setVisibility(View.VISIBLE);
                        tvTagEliminate.setText("to eliminate, Swipe up to manage participants");
                        tvQualifiedParticipants.setText("QUALIFIED PARTICIPANTS");
                    } else {
                        isSwitchOff = false;
                        ivSecondBottomSheetIcon.setImageResource(R.drawable.ic_green_refresh);
                        cardAddByCode.setVisibility(View.GONE);
                        tvTagEliminate.setText("to re-quality, Swipe down to view all rounds.");
                        tvQualifiedParticipants.setText("ELIMINATED PARTICIPANTS");
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setAdapter() {
        try {
            //
            ViewPagerAdapter mAdapter = new ViewPagerAdapter(ActEventCardsNew.this);
            pager_question.setAdapter(mAdapter);
            //
            isSwitchOff = true;
            adapter = new RVAdapterSecondBottomSheet(ActEventCardsNew.this, arrayListUser);
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


    private void showViews(final View v) {
        // TODO uncomment this Hide Footer in android when Scrolling
        v.animate().alpha(1.0f).setDuration(1200).setListener(new Animator.AnimatorListener() {
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
        v.animate().alpha(0f).setDuration(1000).setListener(new Animator.AnimatorListener() {
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
            try {
                final CardView card_view = (CardView) itemView.findViewById(R.id.card_view);
                View dummyView = (View) itemView.findViewById(R.id.dummyView);
                /*
                * Edit Mode layout
                * */
                final RelativeLayout rlEditModeList = (RelativeLayout) itemView.findViewById(R.id.rlEditModeList);
                ImageView ivSubBack = (ImageView) itemView.findViewById(R.id.ivSubBack);
                RecyclerView recyclerViewEditMode = (RecyclerView) itemView.findViewById(R.id.recyclerViewEditMode);
                LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(ActEventCardsNew.this);
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
                LinearLayoutManager linearLayoutManagerNormal = new LinearLayoutManager(ActEventCardsNew.this);
                recyclerViewNormalMode.setLayoutManager(linearLayoutManagerNormal);
                recyclerViewNormalMode.setHasFixedSize(true);
                recyclerViewNormalMode.setNestedScrollingEnabled(false);
                /*
                * Common
                * */
                LinearLayout lnJudge = (LinearLayout) itemView.findViewById(R.id.lnJudge);
                LinearLayout lnScoreSheet = (LinearLayout) itemView.findViewById(R.id.lnScoreSheet);
                final CardView cardJudgeInvite = (CardView) itemView.findViewById(R.id.cardJudgeInvite);
                final CardView cardScoreSheet = (CardView) itemView.findViewById(R.id.cardScoreSheet);
                final CardView main_card_view = (CardView) itemView.findViewById(R.id.card_view);
//                final ImageView imgClose1 = (ImageView) itemView.findViewById(R.id.imgClose1);
//                final ImageView imgClose2 = (ImageView) itemView.findViewById(R.id.imgClose2);

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

                //
                RVAdapterNormalListMode adapterNormal = new RVAdapterNormalListMode(ActEventCardsNew.this, arrayListUser);
                recyclerViewNormalMode.setAdapter(adapterNormal);
                adapterNormal.notifyDataSetChanged();
                //
                RVAdapterEditListMode adapterEdit = new RVAdapterEditListMode(ActEventCardsNew.this, arrayListUser);
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


                recyclerViewNormalMode.addOnItemTouchListener(new RecyclerTouchListener(ActEventCardsNew.this,
                        recyclerView, new ClickListener() {

                    @Override
                    public void onClick(View view, final int position) {
//                        Toast.makeText(ActEventCardsNew.this, "Single Click on position        :"+position,
//                                Toast.LENGTH_SHORT).show();
                        springDownAnim.start();
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                cardJudgeInvite.setVisibility(View.GONE);
                                cardScoreSheet.setVisibility(View.GONE);
                            }
                        }, 200);
                    }

                    @Override
                    public void onLongClick(View view, int position) {
//                        Toast.makeText(ActEventCardsNew.this, "Long press on position :"+position,
//                                Toast.LENGTH_LONG).show();
                    }
                }));


                recyclerViewEditMode.addOnItemTouchListener(new RecyclerTouchListener(ActEventCardsNew.this,
                        recyclerView, new ClickListener() {

                    @Override
                    public void onClick(View view, final int position) {
                        //Values are passing to activity & to fragment as well
//                        Toast.makeText(ActEventCardsNew.this, "Single Click on position        :"+position,
//                                Toast.LENGTH_SHORT).show();
                        springDownAnim.start();
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                cardJudgeInvite.setVisibility(View.GONE);
                                cardScoreSheet.setVisibility(View.GONE);
                            }
                        }, 200);
                    }

                    @Override
                    public void onLongClick(View view, int position) {
//                        Toast.makeText(ActEventCardsNew.this, "Long press on position :"+position,
//                                Toast.LENGTH_LONG).show();
                    }
                }));


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

}
