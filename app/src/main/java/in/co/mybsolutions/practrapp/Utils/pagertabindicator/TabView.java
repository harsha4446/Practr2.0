package in.co.mybsolutions.practrapp.Utils.pagertabindicator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by dhaval.mehta on 17-May-18.
 */

public class TabView extends FrameLayout {
    private static final String TAG = TabView.class.getSimpleName();

    private ValueAnimator bgAnimator;
    private int pressColor = Color.TRANSPARENT;
    private int currentBgColor = pressColor;
    private float offset = 0;

    public TabView(Context context) {
        super(context);
        setWillNotDraw(false);
    }


    public TabView(Context context, View child) {
        this(context);
        addView(child);
    }

    public TabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

    }

    public TabView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);

    }

    public void onOffset(float offset) {
        this.offset=offset;
        invalidate();
    }

}
