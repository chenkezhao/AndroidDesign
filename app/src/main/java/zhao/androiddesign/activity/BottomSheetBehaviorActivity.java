package zhao.androiddesign.activity;

import android.content.DialogInterface;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import zhao.androiddesign.R;
import zhao.androiddesign.adapter.AppBarListAdapter;

public class BottomSheetBehaviorActivity extends AppCompatActivity {

    private BottomSheetBehavior bottomSheetBehavior;
    private FloatingActionButton button1,button2;
    private RecyclerView recyclerView;
    private BottomSheetDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_behavior);
        initView();

    }



    private void initView(){
        //bottomSheetBehavior初始化
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.sheetBehavior_nsv_window));
        if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

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
        View view = LayoutInflater.from(this).inflate(R.layout.layout_recycler,null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_rv_list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new AppBarListAdapter(this,initData()));

        dialog = new BottomSheetDialog(this);
        dialog.setContentView((View) recyclerView.getParent());
        button2 = (FloatingActionButton) findViewById(R.id.sheetBehavior_fab_btn2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
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
