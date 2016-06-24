package zhao.androiddesign.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import zhao.androiddesign.R;

public class GraphicsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Spinner mSpinner;
    private LinearLayout mView;

    /**
     * 枚举类型
     */
    private enum Type {
        canvas {

            @Override
            public View get(Context context) {
                return new CanvasView(context);
            }

            @Override
            public String getName() {
                return "Canvas画布之图形绘制";
            }
        },
        canvasText {

            @Override
            public View get(Context context) {
                return new CanvasTextView(context);
            }

            @Override
            public String getName() {
                return "Canvas画布之文字绘制";
            }
        },
        canvasSample1{
            @Override
            public View get(Context context) {
                return new CanvasSample1(context);
            }

            @Override
            public String getName() {
                return "Canvas例子-时钟1";
            }
        },
        canvasSample2{
            @Override
            public View get(Context context) {
                return new CanvasSample2(context);
            }

            @Override
            public String getName() {
                return "Canvas例子2-画板";
            }
        };

        public abstract View get(Context context);

        public abstract String getName();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);
        initView();
    }


    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.graphics_tb_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mView = (LinearLayout) findViewById(R.id.graphics_ll_view);
        mSpinner = (Spinner) findViewById(R.id.graphics_spinner);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        for (Type type : Type.values()) {
            spinnerAdapter.add(type.getName());
        }
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                View v = Type.values()[position].get(GraphicsActivity.this);
                if(mView.getChildCount()!=0){
                    mView.removeAllViews();
                }
                mView.addView(v);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 自定义画布View
     * View中的Canvas对象，绘制一些自定义形状，然后调用View. invalidate方法让View重新刷新，然后绘制一个新的形状，这样达到2D动画效果
     * Canvas对象的获取方式有两种：
     * 一种我们通过重写View.onDraw方法(官方推荐)；
     * 另一种就是当你想创建一个Canvas对象时使用的方法
     * Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
     * Canvas c = new Canvas(b);
     */
    static class CanvasView extends View {
        private Paint paint;
        private TextPaint textPaint;


        public CanvasView(Context context) {
            super(context);
            paint = new Paint();//设置一个笔刷大小是3的黄色的画笔
            paint.setColor(Color.YELLOW);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(3);
        }

        /**
         * canvas提供的绘制图形的方法都是以draw开头的
         *
         * @param canvas
         */
        @Override
        protected void onDraw(Canvas canvas) {
            //canvas背景色
            canvas.drawARGB(255, 255, 0, 0);
            //圆
            canvas.drawCircle(500, 250, 200, paint);
            //圆角
            canvas.drawRoundRect(100, 500, 500, 700, 30, 30, paint);
            //矩形区域内切椭圆
            RectF oval = new RectF(300, 700, 700, 1000);
            canvas.drawOval(oval, paint);

            //弧形
            RectF rectF = new RectF(100, 800, 300, 1000);
            canvas.drawArc(rectF,
                    0,//开始角度
                    90,//扫过角度
                    true,//是否使用中心
                    paint);
            //线
            canvas.drawLine(100, 100, 800, 800, paint);

            //多边形
            Path path = new Path();//定义一条路径
            path.moveTo(100,1000);
            path.lineTo(700,1300);
            path.lineTo(800,1600);
            path.lineTo(100,1000);
            canvas.drawPath(path,paint);

        }
    }

    /**
     * 文字绘制
     */
    static class CanvasTextView extends View{

        private Paint paint;

        public CanvasTextView(Context context) {
            super(context);
            paint = new Paint();//设置一个笔刷大小是3的黄色的画笔
            paint.setColor(Color.BLACK);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(3);
            paint.setTextSize(200);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Path path = new Path(); //定义一条路径
            path.moveTo(100, 200); //移动到 坐标10,10
            path.lineTo(500, 160);
            path.lineTo(800,880);
            path.lineTo(100, 100);
            canvas.drawTextOnPath("陈科肇陈科肇陈科肇", path, 10, 10, paint);
        }
    }

    /**
     * 例子1-时钟
     */
    static class  CanvasSample1 extends View{
        private Paint paint;

        public CanvasSample1(Context context) {
            super(context);
            paint = new Paint();//设置一个笔刷大小是3的黄色的画笔
            paint.setColor(Color.BLACK);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(3);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            canvas.translate(canvas.getWidth()/2, 200); //将位置移动画纸的坐标点:150,150
            canvas.drawCircle(0, 0, 100, paint); //画圆圈

            //使用path绘制路径文字
            canvas.save();
            canvas.translate(-75, -75);
            Path path = new Path();
            path.addArc(new RectF(0,0,150,150), -180, 180);
            Paint citePaint = new Paint(paint);
            citePaint.setTextSize(14);
            citePaint.setStrokeWidth(1);
            canvas.drawTextOnPath("http://www.android777.com", path, 28, 0, citePaint);
            canvas.restore();

            Paint tmpPaint = new Paint(paint); //小刻度画笔对象
            tmpPaint.setStrokeWidth(1);

            float  y=100;
            int count = 60; //总刻度数

            for(int i=0 ; i <count ; i++){
                if(i%5 == 0){
                    canvas.drawLine(0f, y, 0, y+12f, paint);
                    canvas.drawText(String.valueOf(i/5+1), -4f, y+25f, tmpPaint);

                }else{
                    canvas.drawLine(0f, y, 0f, y +5f, tmpPaint);
                }
                canvas.rotate(360/count,0f,0f); //旋转画纸
            }

            //绘制指针
            tmpPaint.setColor(Color.GRAY);
            tmpPaint.setStrokeWidth(4);
            canvas.drawCircle(0, 0, 7, tmpPaint);
            tmpPaint.setStyle(Paint.Style.FILL);
            tmpPaint.setColor(Color.YELLOW);
            canvas.drawCircle(0, 0, 5, tmpPaint);
            canvas.drawLine(0, 10, 0, -65, paint);
        }
    }
    /**
     * 例子2-画板
     */
    static class  CanvasSample2 extends View{
        private Paint paint;
        private Canvas mCanvas;
        private Bitmap mBitmap;
        private float startX;
        private float startY ;

        public CanvasSample2(Context context) {
            super(context);
            paint = new Paint();//设置一个笔刷大小是3的黄色的画笔
            paint.setColor(Color.BLACK);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(30);
            DisplayMetrics dm = getResources().getDisplayMetrics();
            mBitmap = Bitmap.createBitmap(dm.widthPixels,dm.heightPixels,Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX = event.getX();
                    startY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float stopX = event.getX();
                    float stopY = event.getY();
                    mCanvas.drawLine(startX, startY, stopX, stopY, paint);
                    startX = event.getX();
                    startY = event.getY();
                    invalidate();//call onDraw()
                    break;
                default:
                    break;
            }

            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            if(mBitmap!=null){
                canvas.drawBitmap(mBitmap,0,0,paint);
            }
        }
    }
}
