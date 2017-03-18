package zhao.androiddesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import zhao.androiddesign.R;

/**
 * 主页面
 */
public class MainActivity extends SwipeBackActivity {

    private ListView mListView;
    private String[] menus = {
            "AppBarLayout、TabLayout、ViewPager", "CollapsingToolbarLayout",
            "BottomSheetBehavior、BottomSheetDialog(代替Popupwindow)、FloatingActionButton", "Snackbar",
            "SwipeDismissBehavior", "TabLayout TabItem、TextInputLayout","自定义 ActionBar","ActionBar的升级版ToolBar","CardView",
            "SlidingPaneLayout滑动菜单","DrawerLayout抽屉菜单","NavigationDrawer导航菜单",
            "RecyclerView(ListView、GridView、瀑布流)，Spinner选择面板及enum枚举类型使用","RecyclerView MultipleItem",
            "CustomView自定义View","CustomViewGroup自定义ViewGroup","Graphics图形绘制","Transition Scene场景和转换(动画视图)"

    };
    private Class[] classs = {
            AppBarLayoutActivity.class,CollapsingToolbarActivity.class,
            BottomSheetBehaviorActivity.class,null,SwipeDismissBehaviorActivity.class,TabItemActivity.class,
            ActionBarActivity.class,ToolbarActivity.class,CardViewActivity.class,SlidingPaneLayoutActivity.class,
            DrawerLayoutActivity.class,NavigationDrawerActivity.class,
            RecyclerViewActivity.class,RecyclerMultipleItemActivity.class,CustomViewActivity.class,
            CustomViewGroupActivity.class,GraphicsActivity.class,TransitionSceneActivity.class
    };

    private SwipeBackLayout swipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initView();
        initEvent();
    }

    private void init() {

    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.main_lv_menu);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menus));

        //mListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_title,null));
        mListView.addFooterView(LayoutInflater.from(this).inflate(R.layout.layout_title,null));

        //左滑动关闭
        swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    private void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>classs.length-1){
                    return;
                }
                if(position==3){//Snackbar
                    final Snackbar snackbar = Snackbar.make(view, "弹出提示了，小子", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    snackbar.setAction("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                    return;
                }
                startActivity(new Intent(MainActivity.this, classs[position]));
            }
        });
    }
}
