package zhao.androiddesign.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zhao.androiddesign.R;
import zhao.androiddesign.adapter.AppBarListAdapter;
import zhao.androiddesign.adapter.AppBarViewPagerAdapter;

public class CollapsingToolbarActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbar;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
        initView();
        setSupportActionBar(toolbar);


        //初始化appBarLayout
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset==0){//展开状态
                    //appBarLayout.setExpanded(false);
                    Log.i("xx",0+"");
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {//关闭状态
                    Log.i("xx",verticalOffset+"");
                } else {//其它
                    Log.i("xx",verticalOffset+"");
                }
            }
        });
        //显示默认工具栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //默认工具栏事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //设置标题
        collapsingToolbar.setTitle("陈科肇");
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        collapsingToolbar.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        AppBarListAdapter appBarListAdapter = new AppBarListAdapter(this,initData());
        recyclerView.setAdapter(appBarListAdapter);
        appBarListAdapter.setOnItemClickLitener(new AppBarListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(CollapsingToolbarActivity.this, position+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });


    }


    private void initView(){
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.colToolbar_ctl_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.colToolbar_rv_list);
        toolbar = (Toolbar) findViewById(R.id.colToolbar_tb_toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.colToolbar_abl_appbar);
    }

    protected List<String> initData() {
        List<String> mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
        return mDatas;
    }
}
