package zhao.androiddesign.activity;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;

import zhao.androiddesign.R;
import zhao.androiddesign.adapter.AppBarListAdapter;
import zhao.androiddesign.view.MyDialog;

public class BottomSheetBehaviorActivity extends AppCompatActivity {

    private BottomSheetBehavior bottomSheetBehavior;
    private FloatingActionButton button1,button2;
    private RecyclerView recyclerView;
    private MyDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_behavior);
        initView();

    }



    private void initView(){
        //bottomSheetBehavior初始化
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.sheetBehavior_nsv_window));

        //简单的弹框
        button1 = (FloatingActionButton) findViewById(R.id.sheetBehavior_fab_btn1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        //recyclerView列表弹框
        View view = LayoutInflater.from(BottomSheetBehaviorActivity.this).inflate(R.layout.layout_recycler,null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BottomSheetBehaviorActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_rv_list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new AppBarListAdapter(BottomSheetBehaviorActivity.this,initData()));

        dialog = new MyDialog(this);
        dialog.setContentView((View) recyclerView.getParent());


       /*
       //设置开始弹出的默认高度，屏高
        View parent = (View) view.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        view.measure(0, 0);
        //behavior.setPeekHeight(view.getMeasuredHeight());//屏幕高度
        int temp = 500;
        Log.i("xx",view.getMeasuredHeight()+"");
        Log.i("xx",temp+"");
        behavior.setPeekHeight(temp);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) parent.getLayoutParams();
        params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        parent.setLayoutParams(params);*/

        button2 = (FloatingActionButton) findViewById(R.id.sheetBehavior_fab_btn2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    protected List<String> initData() {
        List<String> mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
        return mDatas;
    }
}
