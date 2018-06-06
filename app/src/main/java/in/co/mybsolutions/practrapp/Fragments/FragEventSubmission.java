package in.co.mybsolutions.practrapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.co.mybsolutions.practrapp.ActCriteriaSubmissionPenalty;
import in.co.mybsolutions.practrapp.ActEventCriteriaTaskSubmission;
import in.co.mybsolutions.practrapp.App;
import in.co.mybsolutions.practrapp.Model.UserModel;
import in.co.mybsolutions.practrapp.R;
import in.co.mybsolutions.practrapp.Utils.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by dhaval.mehta on 15-May-18.
 */

public class FragEventSubmission extends Fragment {

    String TAG = "==FragEventSubmission==";

    @BindView(R.id.tvDateTime)
    TextView tvDateTime;

    @BindView(R.id.llDateTime)
    LinearLayout llDateTime;
    //
    public static LinearLayout llMain;
    public static RecyclerView recyclerView;
    //
    View viewFragment;
    public static boolean isSwitchEnable = false;
    public static boolean isVisible = false;

    SimpleDateFormat dateFormatter;
    SimpleDateFormat sdf3;
    ArrayList<UserModel> arrayListUser = new ArrayList<>();
    RVAdapter adapter;
    static String strTag = "1";


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
            viewFragment = inflater.inflate(R.layout.frag_event_submission, container, false);
            App.showLogTAG(TAG);
            ButterKnife.bind(this, viewFragment);
            //
            initialisation();
            setArrayListData();

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
                if (isVisibleToUser) {
                    if (isSwitchEnable) {
                        ActEventCriteriaTaskSubmission.switchControl.setChecked(true);
                        setListLinear("1");
                    } else {
                        ActEventCriteriaTaskSubmission.switchControl.setChecked(false);
                        setListLinear("0");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initialisation() {
        try {
            //
            dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            sdf3 = new SimpleDateFormat("EEE MM dd kk:mm:ss zzzz yyyy", Locale.ENGLISH);
            //
            llMain = (LinearLayout) viewFragment.findViewById(R.id.llMain);
            recyclerView = (RecyclerView) viewFragment.findViewById(R.id.recyclerView);
            LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManagerCategory);
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);
            //
//            setListLinear("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void setListLinear(String tag) {
        try {
            strTag = tag;
            if (strTag.equalsIgnoreCase("1")) {
                recyclerView.setVisibility(View.VISIBLE);
                llMain.setVisibility(View.GONE);
            } else {
                recyclerView.setVisibility(View.GONE);
                llMain.setVisibility(View.VISIBLE);
            }
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

            adapter = new RVAdapter(getActivity(), arrayListUser);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.VersionViewHolder> {

        ArrayList<UserModel> mArrayList;
        Context mContext;

        public RVAdapter(Context context, ArrayList<UserModel> arrayList) {
            mContext = context;

            this.mArrayList = arrayList;
        }

        @Override
        public RVAdapter.VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_frag_event_submission_items, viewGroup, false);
            RVAdapter.VersionViewHolder viewHolder = new RVAdapter.VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final RVAdapter.VersionViewHolder versionViewHolder, final int position) {
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
//                    if (model.image != 0) {
//                        versionViewHolder.ivImage.setImageResource(model.image);
//                    }
                    //
                    versionViewHolder.rlMain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent iv = new Intent(getActivity(), ActCriteriaSubmissionPenalty.class);
                            App.myStartActivity(getActivity(), iv);
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
            ImageView ivImage/*, ivRightImage*/;
            RelativeLayout rlMain;

            public VersionViewHolder(View itemView) {
                super(itemView);

                tvName = (TextView) itemView.findViewById(R.id.tvName);
                tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
                ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
//                ivRightImage = (ImageView) itemView.findViewById(R.id.ivRightImage);
                rlMain = (RelativeLayout) itemView.findViewById(R.id.rlMain);
            }
        }
    }


    @OnClick(R.id.llDateTime)
    void dateTimeClick() {
        try {
//            DialogFragment newFragment = new DateTimeDialogFragment();
//            newFragment.show(getActivity().getFragmentManager(), "timePicker");

            new SingleDateAndTimePickerDialog.Builder(getActivity())
//                    .bottomSheet()
                    .curved()
//                    .minutesStep(15)

//                    .displayHours(false)
//                    .displayMinutes(false)

                    //.todayText("aujourd'hui")
                    // .setDayFormatter(dateFormatter)
                    .backgroundColor(getResources().getColor(R.color.clrWhite))
                    .mainColor(getResources().getColor(R.color.colorPrimaryDark))
                    .titleTextColor(getResources().getColor(R.color.clrWhite))
                    //.titleColor(getResources().getColor(R.color.button_text_blue))
                    .displayListener(new SingleDateAndTimePickerDialog.DisplayListener() {
                        @Override
                        public void onDisplayed(SingleDateAndTimePicker picker) {
                            //retrieve the SingleDateAndTimePicker
                        }
                    })

                    .title("Set deadline")
                    .listener(new SingleDateAndTimePickerDialog.Listener() {
                        @Override
                        public void onDateSelected(Date date) {
                            tvDateTime.setText(App.getFragEventSubmissionDeadlineDate(date.toString()));
                            App.showLogTAG(TAG + "==date/time==" + App.getFragEventSubmissionDeadlineDate(date.toString()));
                        }
                    }).display();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
