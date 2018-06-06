package in.co.mybsolutions.practrapp.Fragments;

import android.animation.Animator;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.BubbleThumbSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;
import com.github.mmin18.widget.RealtimeBlurView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.co.mybsolutions.practrapp.ActEventCriteriaTaskSubmission;
import in.co.mybsolutions.practrapp.App;
import in.co.mybsolutions.practrapp.Model.UserModel;
import in.co.mybsolutions.practrapp.R;
import in.co.mybsolutions.practrapp.Utils.charts.animation.Easing;
import in.co.mybsolutions.practrapp.Utils.charts.charts.PieChart;
import in.co.mybsolutions.practrapp.Utils.charts.components.Legend;
import in.co.mybsolutions.practrapp.Utils.charts.data.PieData;
import in.co.mybsolutions.practrapp.Utils.charts.data.PieDataSet;
import in.co.mybsolutions.practrapp.Utils.charts.data.PieEntry;
import in.co.mybsolutions.practrapp.Utils.charts.formatter.PercentFormatter;
import in.co.mybsolutions.practrapp.Utils.charts.utils.ColorTemplate;
import in.co.mybsolutions.practrapp.Utils.charts.utils.MPPointF;
import in.co.mybsolutions.practrapp.Utils.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by dhaval.mehta on 22-May-18.
 */

public class FragEventCriteriaNew extends Fragment {

    String TAG = "==FragEventCriteriaNew==";

    @BindView(R.id.tvTagSimply)
    TextView tvTagSimply;

    @BindView(R.id.rlMainCri0)
    RelativeLayout rlMainCri0;

    @BindView(R.id.ivCri0)
    ImageView ivCri0;

    @BindView(R.id.edtCri0)
    EditText edtCri0;

    @BindView(R.id.edtCriPts0)
    TextView edtCriPts0;

    //
    @BindView(R.id.rlMainCri1)
    RelativeLayout rlMainCri1;

    @BindView(R.id.ivCri1)
    ImageView ivCri1;

    @BindView(R.id.edtCri1)
    EditText edtCri1;

    @BindView(R.id.edtCriPts1)
    TextView edtCriPts1;

    //
    @BindView(R.id.rlMainCri2)
    RelativeLayout rlMainCri2;

    @BindView(R.id.ivCri2)
    ImageView ivCri2;

    @BindView(R.id.edtCri2)
    EditText edtCri2;

    @BindView(R.id.edtCriPts2)
    TextView edtCriPts2;

    //
    @BindView(R.id.rlMainCri3)
    RelativeLayout rlMainCri3;

    @BindView(R.id.ivCri3)
    ImageView ivCri3;

    @BindView(R.id.edtCri3)
    EditText edtCri3;

    @BindView(R.id.edtCriPts3)
    TextView edtCriPts3;

    //
    @BindView(R.id.rlMainCri4)
    RelativeLayout rlMainCri4;

    @BindView(R.id.ivCri4)
    ImageView ivCri4;

    @BindView(R.id.edtCri4)
    EditText edtCri4;

    @BindView(R.id.edtCriPts4)
    TextView edtCriPts4;

    //
    @BindView(R.id.rlMainCri5)
    RelativeLayout rlMainCri5;

    @BindView(R.id.ivCri5)
    ImageView ivCri5;

    @BindView(R.id.edtCri5)
    EditText edtCri5;

    @BindView(R.id.edtCriPts5)
    TextView edtCriPts5;
    /*
    *
    * */
    @BindView(R.id.tvNewCriteria)
    TextView tvNewCriteria;

    @BindView(R.id.cardView)
    CardView cardView;

    @BindView(R.id.tvAddWeight)
    TextView tvAddWeight;

    @BindView(R.id.ivRemoveWeight)
    ImageView ivRemoveWeight;

    @BindView(R.id.tvWeightageValue)
    TextView tvWeightageValue;

    @BindView(R.id.rlWeight)
    RelativeLayout rlWeight;

    @BindView(R.id.seekBar)
    CrystalSeekbar seekBar;
//    BubbleThumbSeekbar seekBar;

    @BindView(R.id.pieChart)
    PieChart pieChart;

    public static RealtimeBlurView realTimeBlurView;
    public static LinearLayout dragView;
    public static CardView cardViewSlidingCriteria;
    public static SlidingUpPanelLayout mLayout;
    public static LinearLayout llCriteria, llChart;
    public static NestedScrollView nsView;

    int SCREEN_HEIGHT = 0, SCREEN_WIDTH = 0;
    int BOTTOM_SHEET_HEIGHT = 0;
    float HEIGHT_BOTTOMSHEET = 0.65F; // 65% of screen height
    //
    View viewFragment;
    public static boolean isVisible = false;
    public static boolean isSwitchEnable = false;

    float floatCriteria1 = 18.5f,
            floatCriteria2 = 12.7f,
            floatCriteria3 = 7.2f,
            floatCriteria4 = 43.7f,
            floatCriteria5 = 17.9f;

    protected String[] mCriteria = new String[]{
            "Criteria One",
            "Criteria Two",
            "Criteria Three",
            "Criteria Four",
            "Criteria Five"
    };

    public static final int[] APP_CHART_COLOR = {
            Color.rgb(83, 61, 247), // R.color.clrCriteria1
            Color.rgb(71, 85, 247), // R.color.clrCriteria2
            Color.rgb(51, 130, 248), // R.color.clrCriteria3
            Color.rgb(59, 202, 214), // R.color.clrCriteria4
            Color.rgb(70, 232, 157) // R.color.clrCriteria5
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            App.showLogTAG(TAG);
            viewFragment = inflater.inflate(R.layout.frag_event_criteria_new, container, false);
            ButterKnife.bind(this, viewFragment);
            //
            initialisation();
            setClickEvents();
            setChartData();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return viewFragment;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        try {
            super.setUserVisibleHint(isVisibleToUser);
            isVisible = isVisibleToUser;
            App.showLogTAG(TAG + "==isVisibleToUser==" + isVisibleToUser);
            if (isVisibleToUser) {
                if (isSwitchEnable) {
                    ActEventCriteriaTaskSubmission.switchControl.setChecked(true);
                    setChartCrieList("1");
                } else {
                    ActEventCriteriaTaskSubmission.switchControl.setChecked(false);
                    setChartCrieList("0");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initialisation() {
        try {
            mLayout = (SlidingUpPanelLayout) viewFragment.findViewById(R.id.mLayout);
            realTimeBlurView = (RealtimeBlurView) viewFragment.findViewById(R.id.realTimeBlurView);
            dragView = (LinearLayout) viewFragment.findViewById(R.id.dragView);
            cardViewSlidingCriteria = (CardView) viewFragment.findViewById(R.id.cardViewSlidingCriteria);
            llCriteria = (LinearLayout) viewFragment.findViewById(R.id.llCriteria);
            llChart = (LinearLayout) viewFragment.findViewById(R.id.llChart);
            nsView = (NestedScrollView) viewFragment.findViewById(R.id.nsView);
            //
//            dragView.setVisibility(View.GONE);
            mLayout.setEnabled(false);
            cardViewSlidingCriteria.setVisibility(View.GONE);

            // Disable click on sliding panel - only drag will work
            mLayout.getChildAt(1).setOnClickListener(null);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            SCREEN_HEIGHT = displayMetrics.heightPixels;
            SCREEN_WIDTH = displayMetrics.widthPixels;
            App.showLog(TAG + "==SCREEN_HEIGHT==" + SCREEN_HEIGHT);
            //
            BOTTOM_SHEET_HEIGHT = (int) (((float) SCREEN_HEIGHT) * HEIGHT_BOTTOMSHEET);
            App.showLogTAG("== 1 == 65% =**=" + BOTTOM_SHEET_HEIGHT + " ** 65% **");
            //
            ViewGroup.LayoutParams params = dragView.getLayoutParams();
            params.height = BOTTOM_SHEET_HEIGHT;
            params.width = SCREEN_WIDTH;
            dragView.setLayoutParams(params);

            pieChart.setUsePercentValues(true);
            pieChart.getDescription().setEnabled(true);
            pieChart.setExtraOffsets(30, 10, 30, 10);

            pieChart.setDragDecelerationFrictionCoef(0.95f);

            // pieChart.setCenterTextTypeface(mTfLight);
            pieChart.setCenterText("");
            pieChart.setDrawHoleEnabled(true);
            pieChart.setHoleColor(Color.WHITE);

            pieChart.setTransparentCircleColor(Color.WHITE);
            pieChart.setTransparentCircleAlpha(110);

            pieChart.setHoleRadius(55f);
            pieChart.setTransparentCircleRadius(61f);

            pieChart.setDrawCenterText(false);
            pieChart.setEntryLabelTextSize(8f);
            pieChart.setEntryLabelColor(Color.BLACK);// i.e. Criteria1, Criteria2 etc..

            pieChart.setRotationAngle(0);
            // enable rotation of the chart by touch

            pieChart.setRotationEnabled(false);
            pieChart.setHighlightPerTapEnabled(true);

            //
            realTimeBlurView.setBlurRadius(TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 0,
                    getResources().getDisplayMetrics()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setChartData() {

        try {
            ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
            entries.add(new PieEntry(
                    floatCriteria1,
                    mCriteria[0],
                    getResources().getDrawable(R.drawable.ic_add)
            ));
            entries.add(new PieEntry(
                    floatCriteria2,
                    mCriteria[1],
                    getResources().getDrawable(R.drawable.ic_add)
            ));
            entries.add(new PieEntry(
                    floatCriteria3,
                    mCriteria[2],
                    getResources().getDrawable(R.drawable.ic_add)
            ));
            entries.add(new PieEntry(
                    floatCriteria4,
                    mCriteria[3],
                    getResources().getDrawable(R.drawable.ic_add)
            ));
            entries.add(new PieEntry(
                    floatCriteria5,
                    mCriteria[4],
                    getResources().getDrawable(R.drawable.ic_add)
            ));


            PieDataSet dataSet = new PieDataSet(entries, ""); // Election Results
            dataSet.setDrawIcons(false);
            dataSet.setSliceSpace(3f);
            dataSet.setIconsOffset(new MPPointF(0, 10));
            dataSet.setSelectionShift(5f);

            // add a lot of colors
            ArrayList<Integer> colors = new ArrayList<Integer>();

            for (int c : APP_CHART_COLOR)
                colors.add(c);

            for (int c : APP_CHART_COLOR)
                colors.add(c);

            for (int c : APP_CHART_COLOR)
                colors.add(c);

            for (int c : APP_CHART_COLOR)
                colors.add(c);

            for (int c : APP_CHART_COLOR)
                colors.add(c);
            colors.add(ColorTemplate.getHoloBlue());

            dataSet.setColors(colors);
            //dataSet.setSelectionShift(0f);

            PieData data = new PieData(dataSet);
            data.setValueFormatter(new PercentFormatter());
            data.setValueTextSize(8f);
            data.setValueTextColor(Color.BLACK); // in chart, percentage value color i.e. 43.7%, etc..
            // data.setValueTypeface(mTfLight);
            pieChart.setData(data);

            // undo all highlights
            pieChart.highlightValues(null);
            pieChart.invalidate();

            pieChart.animateY(1400, Easing.EaseInOutQuad);

            Legend l = pieChart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            l.setDrawInside(true);
            l.setXEntrySpace(7f);
            l.setYEntrySpace(0f);
            l.setYOffset(0f);

            //setChartCrieList("1");
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }


    static String strTag = "";
    public static void setChartCrieList(String tag) {
        try {
            strTag = tag;
            if (strTag.equalsIgnoreCase("1")) {
                llChart.setVisibility(View.VISIBLE);
                llCriteria.setVisibility(View.GONE);
                mLayout.setEnabled(true);
                cardViewSlidingCriteria.setVisibility(View.VISIBLE);
                realTimeBlurView.setVisibility(View.VISIBLE);
            } else {
                llChart.setVisibility(View.GONE);
                llCriteria.setVisibility(View.VISIBLE);
                mLayout.setEnabled(false);
                cardViewSlidingCriteria.setVisibility(View.GONE);
                realTimeBlurView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.cardView)
    void cardViewClick() {
        try {
            rlWeight.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivRemoveWeight)
    void removeWeightLayoutClick() {
        try {
            rlWeight.setVisibility(View.GONE);
            cardView.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.tvNewCriteria)
    void newCriteriaAddClick() {
        try {
            criteriaRLShow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivCri1)
    void imgCri1Click() {
        criteriaRLHide();
    }


    @OnClick(R.id.ivCri2)
    void imgCri2Click() {
        criteriaRLHide();
    }


    @OnClick(R.id.ivCri3)
    void imgCri3Click() {
        criteriaRLHide();
    }


    @OnClick(R.id.ivCri4)
    void imgCri4Click() {
        criteriaRLHide();
    }


    @OnClick(R.id.ivCri5)
    void imgCri5Click() {
        criteriaRLHide();
    }


    private void setClickEvents() {
        try {
            //
//            ActEventCriteriaTaskSubmission.switchControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                    if (isChecked) {
//                        ActEventCriteriaTaskSubmission.switchControl.setChecked(true);
//                        setChartCrieList("1");
//                        isSwitchEnable = true;
//                    } else {
//                        ActEventCriteriaTaskSubmission.switchControl.setChecked(false);
//                        setChartCrieList("0");
//                        isSwitchEnable = false;
//                    }
//                }
//            });
            //
            mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {

                @Override
                public void onPanelSlide(View panel, float slideOffset) {
                    // Log.i(TAG, "onPanelSlide, offset " + slideOffset);
                    int blurRadius = (int) (slideOffset * 22);
                    // App.showLog(TAG + "==blurRadius==" + blurRadius);
                    if (blurRadius < 2) {
                        realTimeBlurView.setVisibility(View.GONE);
                    } else {
                        realTimeBlurView.setVisibility(View.VISIBLE);
                        realTimeBlurView.setBlurRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                blurRadius,
                                getResources().getDisplayMetrics()));
                    }
                    // cardAddByCode.setAlpha(slideOffset);
                }

                @Override
                public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                    // Log.i(TAG, "onPanelStateChanged " + newState);
                    switch (newState) {
                        case COLLAPSED:
                            App.showLog(TAG + "==STATE_COLLAPSED==");

                            realTimeBlurView.setBlurRadius(TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 0,
                                    getResources().getDisplayMetrics()));
                            break;

                        case DRAGGING:
                            App.showLog(TAG + "==STATE_DRAGGING==");

                            realTimeBlurView.setVisibility(View.VISIBLE);
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
            //
            seekBar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
                @Override
                public void valueChanged(Number minValue) {
                    DecimalFormat dlat = new DecimalFormat("#.#");
                    String sdlat = dlat.format(minValue);
                    tvWeightageValue.setText(sdlat);
                }
            });
//            //
//            seekBar.setOnSeekbarFinalValueListener(new OnSeekbarFinalValueListener() {
//                @Override
//                public void finalValue(Number value) {
//                    Log.d("CRS=>", String.valueOf(value));
//                    tvWeightageValue.setText(String.valueOf(value));
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void criteriaRLShow() {
        try {
            if (!rlMainCri1.isShown()) {
                showViews(rlMainCri1);
            } else if (!rlMainCri2.isShown()) {
                showViews(rlMainCri2);
            } else if (!rlMainCri3.isShown()) {
                showViews(rlMainCri3);
            } else if (!rlMainCri4.isShown()) {
                showViews(rlMainCri4);
            } else if (!rlMainCri5.isShown()) {
                showViews(rlMainCri5);
                hideViews(tvNewCriteria);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void criteriaRLHide() {
        if (rlMainCri0.isShown() && rlMainCri1.isShown() && rlMainCri2.isShown()
                && rlMainCri3.isShown() && rlMainCri4.isShown() && rlMainCri5.isShown()) {
            hideViews(rlMainCri5);
            showViews(tvNewCriteria);
        } else if (rlMainCri0.isShown() && rlMainCri1.isShown() && rlMainCri2.isShown()
                && rlMainCri3.isShown() && rlMainCri4.isShown() && !rlMainCri5.isShown()) {
            hideViews(rlMainCri4);
        } else if (rlMainCri0.isShown() && rlMainCri1.isShown() && rlMainCri2.isShown()
                && rlMainCri3.isShown() && !rlMainCri4.isShown() && !rlMainCri5.isShown()) {
            hideViews(rlMainCri3);
        } else if (rlMainCri0.isShown() && rlMainCri1.isShown() && rlMainCri2.isShown()
                && !rlMainCri3.isShown() && !rlMainCri4.isShown() && !rlMainCri5.isShown()) {
            hideViews(rlMainCri2);
        } else if (rlMainCri0.isShown() && rlMainCri1.isShown() && !rlMainCri2.isShown()
                && !rlMainCri3.isShown() && !rlMainCri4.isShown() && !rlMainCri5.isShown()) {
            hideViews(rlMainCri1);
        }
    }


    private void showViews(final View v) {
        // TODO uncomment this Hide Footer in android when Scrolling
        v.animate().alpha(1.0f).setDuration(300).setListener(new Animator.AnimatorListener() {
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
        v.animate().alpha(0f).setDuration(200).setListener(new Animator.AnimatorListener() {
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
}
