package in.co.mybsolutions.practrapp;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dhaval.mehta on 25-May-18.
 */

public class ActCodeAlpha extends BaseActivity {

    String TAG = "==ActCodeAlpha==";

    @BindView(R.id.ivSubBack)
    ImageView ivSubBack;

    @BindView(R.id.tvTagCodeAlpha)
    TextView tvTagCodeAlpha;

    @BindView(R.id.tvTagLinkParticipants)
    TextView tvTagLinkParticipants;

    //
    @BindView(R.id.card1)
    CardView card1;

    @BindView(R.id.edt1)
    EditText edt1;

    @BindView(R.id.ivRemove1)
    ImageView ivRemove1;

    //
    @BindView(R.id.card2)
    CardView card2;

    @BindView(R.id.edt2)
    EditText edt2;

    @BindView(R.id.ivRemove2)
    ImageView ivRemove2;

    //
    @BindView(R.id.card3)
    CardView card3;

    @BindView(R.id.edt3)
    EditText edt3;

    @BindView(R.id.ivRemove3)
    ImageView ivRemove3;

    //
    @BindView(R.id.card4)
    CardView card4;

    @BindView(R.id.edt4)
    EditText edt4;

    @BindView(R.id.ivRemove4)
    ImageView ivRemove4;

    //
    @BindView(R.id.card5)
    CardView card5;

    @BindView(R.id.edt5)
    EditText edt5;

    @BindView(R.id.ivRemove5)
    ImageView ivRemove5;

    //
    @BindView(R.id.card6)
    CardView card6;

    @BindView(R.id.edt6)
    EditText edt6;

    @BindView(R.id.ivRemove6)
    ImageView ivRemove6;

    //
    @BindView(R.id.card7)
    CardView card7;

    @BindView(R.id.edt7)
    EditText edt7;

    @BindView(R.id.ivRemove7)
    ImageView ivRemove7;

    //
    @BindView(R.id.card8)
    CardView card8;

    @BindView(R.id.edt8)
    EditText edt8;

    @BindView(R.id.ivRemove8)
    ImageView ivRemove8;

    //
    @BindView(R.id.card9)
    CardView card9;

    @BindView(R.id.edt9)
    EditText edt9;

    @BindView(R.id.ivRemove9)
    ImageView ivRemove9;

    //
    @BindView(R.id.card10)
    CardView card10;

    @BindView(R.id.edt10)
    EditText edt10;

    @BindView(R.id.ivRemove10)
    ImageView ivRemove10;

    //
    @BindView(R.id.cardEnterOptionalNumber)
    CardView cardEnterOptionalNumber;

    @BindView(R.id.tvTagEnterNo)
    TextView tvTagEnterNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            ViewGroup.inflate(this, R.layout.act_code_alpha, llContainerSub);
            App.showLogTAG(TAG);
            ButterKnife.bind(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivSubBack)
    void subBackClick() {
        try {
            onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.cardEnterOptionalNumber)
    void enterOptionalNumberCardClick() {
        try {
            phoneCardViewShow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivRemove1)
    void remove1Click() {
        try {
            phoneCardViewHide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivRemove2)
    void remove2Click() {
        try {
            phoneCardViewHide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivRemove3)
    void remove3Click() {
        try {
            phoneCardViewHide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivRemove4)
    void remove4Click() {
        try {
            phoneCardViewHide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivRemove5)
    void remove5Click() {
        try {
            phoneCardViewHide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivRemove6)
    void remove6Click() {
        try {
            phoneCardViewHide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivRemove7)
    void remove7Click() {
        try {
            phoneCardViewHide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivRemove8)
    void remove8Click() {
        try {
            phoneCardViewHide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivRemove9)
    void remove9Click() {
        try {
            phoneCardViewHide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.ivRemove10)
    void remove10Click() {
        try {
            phoneCardViewHide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void phoneCardViewShow() {
        try {
            if (!card2.isShown()) {
                showViews(card2);
            } else if (!card3.isShown()) {
                showViews(card3);
            } else if (!card4.isShown()) {
                showViews(card4);
            } else if (!card5.isShown()) {
                showViews(card5);
            } else if (!card6.isShown()) {
                showViews(card6);
            } else if (!card7.isShown()) {
                showViews(card7);
            } else if (!card8.isShown()) {
                showViews(card8);
            } else if (!card9.isShown()) {
                showViews(card9);
            } else if (!card10.isShown()) {
                showViews(card10);
                hideViews(cardEnterOptionalNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void phoneCardViewHide() {
        try {
            if (card1.isShown() && card2.isShown() && card3.isShown()
                    && card4.isShown() && card5.isShown() && card6.isShown() &&
                    card7.isShown() && card8.isShown() && card9.isShown()
                    && card10.isShown())
            {
                hideViews(card10);
                showViews(cardEnterOptionalNumber);
            }
            else if (card1.isShown() && card2.isShown() && card3.isShown()
                    && card4.isShown() && card5.isShown() && card6.isShown() &&
                    card7.isShown() && card8.isShown() && card9.isShown()
                    && !card10.isShown())
            {
                hideViews(card9);
            }
            else if (card1.isShown() && card2.isShown() && card3.isShown()
                    && card4.isShown() && card5.isShown() && card6.isShown() &&
                    card7.isShown() && card8.isShown() && !card9.isShown()
                    && !card10.isShown())
            {
                hideViews(card8);
            }
            else if (card1.isShown() && card2.isShown() && card3.isShown()
                    && card4.isShown() && card5.isShown() && card6.isShown() &&
                    card7.isShown() && !card8.isShown() && !card9.isShown()
                    && !card10.isShown())
            {
                hideViews(card7);
            }
            else if (card1.isShown() && card2.isShown() && card3.isShown()
                    && card4.isShown() && card5.isShown() && card6.isShown() &&
                    !card7.isShown() && !card8.isShown() && !card9.isShown()
                    && !card10.isShown())
            {
                hideViews(card6);
            }
            else if (card1.isShown() && card2.isShown() && card3.isShown()
                    && card4.isShown() && card5.isShown() && !card6.isShown() &&
                    !card7.isShown() && !card8.isShown() && !card9.isShown()
                    && !card10.isShown())
            {
                hideViews(card5);
            }
            else if (card1.isShown() && card2.isShown() && card3.isShown()
                    && card4.isShown() && !card5.isShown() && !card6.isShown() &&
                    !card7.isShown() && !card8.isShown() && !card9.isShown()
                    && !card10.isShown())
            {
                hideViews(card4);
            }
            else if (card1.isShown() && card2.isShown() && card3.isShown()
                    && !card4.isShown() && !card5.isShown() && !card6.isShown() &&
                    !card7.isShown() && !card8.isShown() && !card9.isShown()
                    && !card10.isShown())
            {
                hideViews(card3);
            }
            else if (card1.isShown() && card2.isShown() && !card3.isShown()
                    && !card4.isShown() && !card5.isShown() && !card6.isShown() &&
                    !card7.isShown() && !card8.isShown() && !card9.isShown()
                    && !card10.isShown())
            {
                hideViews(card2);
            }
        } catch (Exception e) {
            e.printStackTrace();
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


    @Override
    public void onBackPressed() {
        App.myFinishActivity(ActCodeAlpha.this);
    }
}
