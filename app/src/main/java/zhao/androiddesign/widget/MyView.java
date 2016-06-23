package zhao.androiddesign.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import zhao.androiddesign.R;

/**
 * 自定义View
 * Created by 陈科肇 on 2016/6/23.
 */
public class MyView extends View {

    /*属性文本*/
    private String customText;

    /*控件使用的油漆（画笔）*/
    private Paint paint;
    private TextPaint textPaint;


    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //从布局文件中读取的自定义属性解析出来
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyView,defStyleAttr,0);
        int n = a.getIndexCount();
        for(int i=0;i<n;i++){
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.MyView_customText:
                    customText = a.getString(attr);
                    break;
                default:
                    break;
            }

        }

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new TextPaint(paint);
    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 绘图
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {


        textPaint.setAlpha(255);
        textPaint.setTextSize(300);
        textPaint.setColor(0Xffff0000);
        textPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(customText, 0, 400, textPaint);

        paint.setARGB(255,0,255,0);
        canvas.drawRect(new Rect(50, 500, 550, 500+550),paint);

    }
}
