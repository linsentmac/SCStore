package com.lenovo.smartShop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.lenovo.smartShop.R;
import com.lenovo.smartShop.utils.StateMachine;

/**
 * Created by linsen3 on 2017/9/11.
 */

public class DownLoadButton extends AppCompatButton {

    private final String TAG = "SC-DownLoadButton";

    private Paint paint;
    /**
     * 文本颜色
     */
    private int textColor;
    /**
     * 未下载状态背景
     */
    private Drawable normalBackground;
    /**
     * 已下载进度背景
     */
    private Drawable downLoadBackground;
    /**
     * 下载完成背景
     */
    private Drawable completeBackground;
    /**
     * 下载中状态背景
     */
    private Drawable downLoadingBackground;
    /**
     * 未下载状态
     */
    public final static int STATE_NORMAL = 0;
    /**
     * 下载中
     */
    public final static int STATE_DOWNLOADING = 1;
    /**
     * 下载完成
     */
    public final static int STATE_COMPLETE = 2;
    /**
     *  下载完成未安装
     */
    public final static int STATE_INSTALL = 3;
    /**
     * 下载中断等待状态
     */
    public final static int STATE_WAITTING = 4;
    /**
     * 安装中状态
     */
    public final static int STATE_INSTALLING = 5;

    /**
     * 当前状态
     */
    private int curState = 0;
    /**
     * 当前下载进度
     * 百分比
     */
    private int curPrecent = 0;

    private String mPackageName;

    private OnDownLoadButtonClickListener onDownLoadButtonClickListener;

    public DownLoadButton(Context context) {
        this(context, null);
    }

    public DownLoadButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DownLoadButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DownLoadButton, defStyleAttr, 0);
        normalBackground = getResources().getDrawable(R.drawable.rect_normal_bg);
        downLoadBackground = getResources().getDrawable(R.drawable.rect_downloaded_bg);
        downLoadingBackground = getResources().getDrawable(R.drawable.rect_downloading_bg);
        completeBackground = getResources().getDrawable(R.drawable.rect_complete_bg);

        final int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.DownLoadButton_normalBackground:
                    normalBackground = a.getDrawable(attr);
                    break;
                case R.styleable.DownLoadButton_downLoadedBackground:
                    downLoadBackground = a.getDrawable(attr);
                    break;
                case R.styleable.DownLoadButton_downLoadCompleteBackground:
                    completeBackground = a.getDrawable(attr);
                    break;
                case R.styleable.DownLoadButton_textColor:
                    textColor = a.getColor(attr, getResources().getColor(R.color.color_white));
                    break;
            }
        }
        /**
         * 设置button本身的文字为透明以免干扰我们自己绘制上去的文字
         */
        setTextColor(getResources().getColor(R.color.color_transparent));
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(getTextSize());
        paint.setColor(textColor);

        curState = STATE_NORMAL;
        setGravity(Gravity.CENTER);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDownLoadButtonClickListener != null) {
                    //点击时返回当前的状态
                    onDownLoadButtonClickListener.onClick(v, curState);
                }
            }
        });
    }

    private static DownLoadButton mInstance;
    public static DownLoadButton getInstance(Context context){
        if(mInstance == null){
            mInstance = new DownLoadButton(context);
        }
        return mInstance;
    }

    /**
     * 设置当前状态
     *
     * @param state
     */
    public void setState(String packageName, int state) {
        this.mPackageName = packageName;
        this.curState = state;
        Log.d(TAG, "setState = " + state);
        StateMachine.getInstance().setDownLoadState(packageName,state);
        postInvalidate();
    }

    public int getState(){
        return curState;
    }

    /**
     * 设置下载进度
     *
     * @param percent
     *        完成进度百分比
     */
    public void setDownLoadProgress(int state, int percent, String packageName) {
        StateMachine.getInstance().setDownloadPercent(packageName, percent);
        StateMachine.getInstance().setDownLoadState(packageName, state);
        this.mPackageName = packageName;
        this.curState = state;
        this.curPrecent = percent;
        Log.d(TAG, "curPrecent = " + curPrecent);
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;
        /**
         * 计算文本显示所需宽高
         */
        Rect textBound = new Rect();
        String tip = getResources().getString(R.string.download_complete);
        paint.getTextBounds("下载完成", 0, tip.length(), textBound);

        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize+getPaddingLeft()+getPaddingRight();
        }else{
            width = textBound.width()+getPaddingLeft()+getPaddingRight();
        }

        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize+getPaddingTop()+getPaddingBottom();
        }else{
            height = textBound.height()+getPaddingTop()+getPaddingBottom();
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String tip = "";
        int downLoadState = StateMachine.getInstance().getDownLoadState(mPackageName);
        Log.d(TAG, "onDraw = " + downLoadState);
        switch (downLoadState) {
            case STATE_NORMAL:
                tip = getResources().getString(R.string.download);
                StateMachine.getInstance().setDownloadPercent(mPackageName, StateMachine.defaultPercent);
                setBackgroundDrawable(normalBackground);
                break;
            case STATE_DOWNLOADING:
                setBackground(normalBackground);
                int percent = StateMachine.getInstance().getDownloadPercent(mPackageName);
                Log.d(TAG, "downloading curPrecent = " + percent);
                tip = percent+"%";
                //计算当前进度所需宽度
                int downLoadedWidth = (int) (getMeasuredWidth() * ((double) percent / 100));
                Rect rect = new Rect(0, 0, downLoadedWidth, getMeasuredHeight());
                downLoadBackground.setBounds(rect);
                downLoadBackground.draw(canvas);
                break;
            /*case STATE_DOWNLOADING:
                Log.d(TAG, "STATE_DOWNLOADING");
                tip = getResources().getString(R.string.downloading);
                setBackgroundDrawable(downLoadingBackground);
                break;*/
            case STATE_COMPLETE:
                Log.d(TAG, "STATE_COMPLETE");
                tip = getResources().getString(R.string.download_complete);
                StateMachine.getInstance().setDownloadPercent(mPackageName, StateMachine.defaultPercent);
                setBackgroundDrawable(completeBackground);
                break;
            case STATE_INSTALL:
                Log.d(TAG, "STATE_INSTALL");
                tip = getResources().getString(R.string.install);
                //StateMachine.getInstance().setDownloadPercent(mPackageName, StateMachine.defaultPercent);
                setBackgroundDrawable(completeBackground);
                break;
            case STATE_WAITTING:
                Log.d(TAG, "STATE_WAITTING");
                tip = getResources().getString(R.string.waitting);
                setBackgroundDrawable(completeBackground);
                break;
            case STATE_INSTALLING:
                Log.d(TAG, "STATE_INSTALLING");
                tip = getResources().getString(R.string.installing);
                setBackgroundDrawable(completeBackground);
                break;
        }
        /**
         * 绘制提示文本
         */
        Rect textBound = new Rect();
        paint.getTextBounds(tip, 0, tip.length(), textBound);
        canvas.drawText(tip,(getMeasuredWidth()-textBound.width())/2,(getMeasuredHeight()+textBound.height())/2, paint);
    }

    public void setOnDownLoadButtonClickListener(OnDownLoadButtonClickListener onDownLoadButtonClickListener) {
        this.onDownLoadButtonClickListener = onDownLoadButtonClickListener;

    }

    public interface OnDownLoadButtonClickListener {
        void onClick(View v, int curState);
    }

}
