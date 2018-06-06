package in.co.mybsolutions.practrapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.mybsolutions.practrapp.ActEventCriteriaTaskSubmission;
import in.co.mybsolutions.practrapp.App;
import in.co.mybsolutions.practrapp.Model.EventTaskModel;
import in.co.mybsolutions.practrapp.R;
import in.co.mybsolutions.practrapp.Utils.ExpandableTextView;

/**
 * Created by dhaval.mehta on 15-May-18.
 */

public class FragEventTask extends Fragment {

    String TAG = "==FragEventTask==";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //
    View viewFragment;
    public static boolean isSwitchEnable = false;
    public static boolean isVisible = false;

    ArrayList<EventTaskModel> arrayListTask;
    public static RVAdapter adapter;
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
            viewFragment = inflater.inflate(R.layout.frag_event_task, container, false);
            App.showLogTAG(TAG);
            ButterKnife.bind(this, viewFragment);
            //
            initialisation();
            setArrayListData();
            setAdapter();
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
                        setExColl("1");
                    } else {
                        ActEventCriteriaTaskSubmission.switchControl.setChecked(false);
                        setExColl("0");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initialisation() {
        try {
            LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManagerCategory);
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setArrayListData() {
        try {
            arrayListTask = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                arrayListTask.add(new EventTaskModel(
                        "Description",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                        false
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setAdapter() {
        try {
            adapter = new RVAdapter(getActivity(), arrayListTask);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void setExColl(String tag) {
        try {
            if (adapter != null) {
                strTag = tag;
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class RVAdapter extends RecyclerView.Adapter<RVAdapter.VersionViewHolder> {

        ArrayList<EventTaskModel> mArrayList;
        Context mContext;

        public RVAdapter(Context context, ArrayList<EventTaskModel> arrayList) {
            mContext = context;

            this.mArrayList = arrayList;
        }

        @Override
        public RVAdapter.VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_frag_event_task_items, viewGroup, false);
            RVAdapter.VersionViewHolder viewHolder = new RVAdapter.VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final RVAdapter.VersionViewHolder versionViewHolder, final int position) {
            try {
                if (mArrayList != null && mArrayList.size() > 0) {
                    final EventTaskModel model = mArrayList.get(position);
                    // versionViewHolder.tvDesc.collapse();
                    //
                    if (model.isChecked) {
                        versionViewHolder.ivCheck.setImageResource(R.drawable.checkbox_on);
                    } else {
                        versionViewHolder.ivCheck.setImageResource(R.drawable.checkbox_off);
                    }

                    if (strTag != null) {
                        if (strTag.equalsIgnoreCase("1")) {
                            versionViewHolder.tvDesc.expand();
                        } else {
                            versionViewHolder.tvDesc.collapse();
                        }
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
                    versionViewHolder.rlMain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    if (mArrayList.get(position).isChecked == true) {

                                    } else {
                                        for (int i = 0; i < mArrayList.size(); i++) {
                                            if (i == position) {
                                                mArrayList.get(i).isChecked = true;
                                            } else {
                                                mArrayList.get(i).isChecked = false;
                                            }
                                        }
                                    }
                                    adapter.notifyDataSetChanged();
                                }
                            }, 200);
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


        public static class VersionViewHolder extends RecyclerView.ViewHolder {

            TextView tvName/*, tvDesc*/;
            ExpandableTextView tvDesc;
            ImageView ivCheck;
            RelativeLayout rlMain;

//            public static void expand() {
//                tvDesc.expand();
//            }
//
//            public static void collpse () {
//                tvDesc.collapse();
//            }


            public VersionViewHolder(View itemView) {
                super(itemView);

                tvName = (TextView) itemView.findViewById(R.id.tvName);
                tvDesc = (ExpandableTextView) itemView.findViewById(R.id.tvDesc);
                ivCheck = (ImageView) itemView.findViewById(R.id.ivCheck);
                rlMain = (RelativeLayout) itemView.findViewById(R.id.rlMain);
                /*
                * set interpolators for both expanding and collapsing animations
                * */
                tvDesc.setExpandInterpolator(new OvershootInterpolator());
                tvDesc.setCollapseInterpolator(new OvershootInterpolator());
            }
        }
    }
}
