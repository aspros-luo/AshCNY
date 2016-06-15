package ash.aiqinhaigou.com.ashcny.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import ash.aiqinhaigou.com.ashcny.R;

/**
 * Created by Aspros on 16/6/14.
 */
public class SlideView extends HorizontalScrollView {

    private int mScrollWidth;

    private ImageView img_delete;

    private boolean isOpen = false;
    private boolean once = false;

    private ISlideViewListener mISlideViewListener;

    public SlideView(Context context) {
        this(context, null);
    }

    public SlideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOverScrollMode(OVER_SCROLL_NEVER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!once) {
            img_delete = (ImageView) findViewById(R.id.item_shopping_delete);
            once = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(0, 0);
            mScrollWidth = img_delete.getWidth();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mISlideViewListener.onDownMove(this);
                openMenu();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                changeScrollX();
                return true;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
//        img_delete.setTranslationX(l - mScrollWidth);
        img_delete.setTranslationX(mScrollWidth - l);
    }

    public void changeScrollX() {
        if (getScrollX() >= mScrollWidth / 2) {
            this.smoothScrollTo(mScrollWidth, 0);
            isOpen = true;
            mISlideViewListener.onMenuIsOpen(this);
        } else {
            this.smoothScrollTo(0, 0);
            isOpen = false;
        }
    }

    public void openMenu() {
        if (isOpen) {
            return;
        }
        this.smoothScrollTo(mScrollWidth, 0);
        mISlideViewListener.onMenuIsOpen(this);
        isOpen = true;
    }

    public void closeMenu() {
        if (!isOpen) {
            return;
        }
        this.smoothScrollTo(0, 0);
        isOpen = false;
    }

    public void setSlideViewListener(ISlideViewListener listener) {
        mISlideViewListener = listener;
    }

    public interface ISlideViewListener {
        void onMenuIsOpen(View view);

        void onDownMove(SlideView slideView);
    }
}
