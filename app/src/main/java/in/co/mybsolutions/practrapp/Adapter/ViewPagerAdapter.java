package in.co.mybsolutions.practrapp.Adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

import in.co.mybsolutions.practrapp.EventCardsActivity;
import in.co.mybsolutions.practrapp.R;

/**
 * Created by Yash Ajabiya on 3/15/2018.
 */

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