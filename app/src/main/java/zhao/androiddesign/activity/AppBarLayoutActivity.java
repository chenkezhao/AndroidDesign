package zhao.androiddesign.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import zhao.androiddesign.R;
import zhao.androiddesign.adapter.AppBarViewPagerAdapter;

public class AppBarLayoutActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private String[] tabTitles={"Tabl1","Tab2","Tab3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);
        init();
        initView();

        //显示菜单
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //第一步
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //第二步
        AppBarViewPagerAdapter mAdapter = new AppBarViewPagerAdapter(this,tabTitles);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        //第三步
        final TabLayout.TabLayoutOnPageChangeListener listener = new TabLayout.TabLayoutOnPageChangeListener(mTabLayout);
        mViewPager.addOnPageChangeListener(listener);
        mViewPager.setAdapter(mAdapter);
    }


    private void init(){
    }
    private void initView(){
        mTabLayout = (TabLayout) findViewById(R.id.appbar_tl_tab);
        mViewPager = (ViewPager) findViewById(R.id.appbar_vp_pager);
        mToolbar = (Toolbar) findViewById(R.id.appbar_tb_toolbar);
        setSupportActionBar(mToolbar);
    }
}
