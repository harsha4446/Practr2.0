package in.co.mybsolutions.practrapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import in.co.mybsolutions.practrapp.R;

/**
 * Created by Yash Ajabiya on 5/2/2018.
 */

public class JudgetListAdapter extends BaseAdapter {

    public Context ct;
    public int rowFlag = 0;

    public JudgetListAdapter(Context ct, int flag) {
        this.ct = ct;
        this.rowFlag = flag;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (rowFlag == 0) {
            view = LayoutInflater.from(ct).inflate(R.layout.custom_judge_row, null);
            TextView tvName = (TextView) view.findViewById(R.id.tvName);
            TextView tvTimeStamp = (TextView) view.findViewById(R.id.tvTimeStamp);
            ImageView imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
            View vStatus = (View) view.findViewById(R.id.vStatus);

            if (i == 0) {
                tvName.setText("Amie Haralson");
                tvTimeStamp.setText("Logged in 15 mins ago.");
                imgLogo.setImageResource(R.drawable.judge_1);
                vStatus.setBackgroundResource(R.drawable.green_dot_judge);
            } else if (i == 1) {
                tvName.setText("Jared Dunn");
                tvTimeStamp.setText("Has accepted your invite.");
                imgLogo.setImageResource(R.drawable.judge_2);
                vStatus.setBackgroundResource(R.drawable.green_dot_judge);
            } else if (i == 2) {
                tvName.setText("Amie Haralson");
                tvTimeStamp.setText("Logged in 15 mins ago.");
                imgLogo.setImageResource(R.drawable.judge_1);
                vStatus.setBackgroundResource(R.drawable.green_dot_judge);
            } else if (i == 3) {
                tvName.setText("Jared Dunn");
                tvTimeStamp.setText("Has accepted your invite.");
                imgLogo.setImageResource(R.drawable.judge_2);
                vStatus.setBackgroundResource(R.drawable.green_dot_judge);
            } else if (i == 4) {
                tvName.setText("Amie Haralson");
                tvTimeStamp.setText("Logged in 15 mins ago.");
                imgLogo.setImageResource(R.drawable.judge_1);
                vStatus.setBackgroundResource(R.drawable.green_dot_judge);
            } else if (i == 5) {
                tvName.setText("Jared Dunn");
                tvTimeStamp.setText("Has accepted your invite.");
                imgLogo.setImageResource(R.drawable.judge_2);
                vStatus.setBackgroundResource(R.drawable.green_dot_judge);
            } else if (i == 6) {
                tvName.setText("Amie Haralson");
                tvTimeStamp.setText("Logged in 15 mins ago.");
                imgLogo.setImageResource(R.drawable.judge_1);
                vStatus.setBackgroundResource(R.drawable.green_dot_judge);
            } else if (i == 7) {
                tvName.setText("Jared Dunn");
                tvTimeStamp.setText("Has accepted your invite.");
                imgLogo.setImageResource(R.drawable.judge_2);
                vStatus.setBackgroundResource(R.drawable.green_dot_judge);
            } else if (i == 8) {
                tvName.setText("Amie Haralson");
                tvTimeStamp.setText("Logged in 15 mins ago.");
                imgLogo.setImageResource(R.drawable.judge_1);
                vStatus.setBackgroundResource(R.drawable.green_dot_judge);
            } else if (i == 9) {
                tvName.setText("Jared Dunn");
                tvTimeStamp.setText("Has accepted your invite.");
                imgLogo.setImageResource(R.drawable.judge_2);
                vStatus.setBackgroundResource(R.drawable.green_dot_judge);
            } else {
                tvName.setText("Amrit Agarwal");
                tvTimeStamp.setText("Has finishing judging.");
                imgLogo.setImageResource(R.drawable.judge_3);
                vStatus.setBackgroundResource(R.drawable.blue_dot_judge);
            }
        } else {
            view = LayoutInflater.from(ct).inflate(R.layout.custom_judge_row_sec, null);
            TextView tvName = (TextView) view.findViewById(R.id.tvName);
            TextView tvTimeStamp = (TextView) view.findViewById(R.id.tvTimeStamp);
            ImageView imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
            View vStatus = (View) view.findViewById(R.id.vStatus);

            if (i == 0) {
                tvName.setText("Amie Haralson");
                tvTimeStamp.setText("Logged in 15 mins ago.");
                imgLogo.setImageResource(R.drawable.judge_1);
                vStatus.setBackgroundResource(R.drawable.green_dot_judge);
            } else if (i == 1) {
                tvName.setText("Jared Dunn");
                tvTimeStamp.setText("Has accepted your invite.");
                imgLogo.setImageResource(R.drawable.judge_2);
                vStatus.setBackgroundResource(R.drawable.green_dot_judge);
            } else {
                tvName.setText("Amrit Agarwal");
                tvTimeStamp.setText("Has finishing judging.");
                imgLogo.setImageResource(R.drawable.judge_3);
                vStatus.setBackgroundResource(R.drawable.blue_dot_judge);
            }
        }

        return view;
    }
}
