package zhao.androiddesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import zhao.androiddesign.R;

/**
 * 主页面
 */
public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private String[] menus = {"AppBarLayout、TabLayout、ViewPager", "CollapsingToolbarLayout", "BottomSheetBehavior、" +
            "BottomSheetDialog(代替Popupwindow)、FloatingActionButton", "Snackbar",
            "SwipeDismissBehavior", "TabLayout TabItem、TextInputLayout","自定义 ActionBar","ActionBar的升级版ToolBar","SlidingPaneLayout","CardView"};

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
    }

    private void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                boolean flag = true;
                switch (position) {
                    case 0://AppBarLayout
                        intent = new Intent(MainActivity.this, AppBarLayoutActivity.class);
                        break;
                    case 1://CollapsingToolbarLayout
                        intent = new Intent(MainActivity.this, CollapsingToolbarActivity.class);
                        break;
                    case 2://BottomSheetBehavior
                        intent = new Intent(MainActivity.this, BottomSheetBehaviorActivity.class);
                        break;
                    case 3://Snackbar
                        final Snackbar snackbar = Snackbar.make(view, "弹出提示了，小子", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        snackbar.setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                            }
                        });
                        flag = false;

                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, SwipeDismissBehaviorActivity.class);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, TabItemActivity.class);
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this, ActionBarActivity.class);
                        break;
                    case 7:
                        intent = new Intent(MainActivity.this, ToolbarActivity.class);
                        break;
                    case 8:
                        intent = new Intent(MainActivity.this, SlidingPaneLayoutActivity.class);
                        break;
                    case 9:
                        intent = new Intent(MainActivity.this, CardViewActivity.class);
                        break;
                    default:
                        flag = false;
                        break;
                }
                if (flag) {
                    startActivity(intent);
                }
            }
        });
    }
}
