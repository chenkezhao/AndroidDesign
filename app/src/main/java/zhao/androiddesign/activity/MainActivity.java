package zhao.androiddesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private String[] menus = {"AppBarLayout","CollapsingToolbarLayout","BottomSheetBehavior+BottomSheetDialog(代替Popupwindow)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initView();
        initEvent();
    }

    private void init(){

    }
    private void initView(){
        mListView = (ListView) findViewById(R.id.main_lv_menu);
        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menus));
    }

    private void initEvent(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position){
                    case 0://AppBarLayout
                        intent = new Intent(MainActivity.this,AppBarLayoutActivity.class);
                        break;
                    case 1://CollapsingToolbarLayout
                        intent = new Intent(MainActivity.this,CollapsingToolbarActivity.class);
                        break;
                    case 2://BottomSheetBehavior
                        intent = new Intent(MainActivity.this,BottomSheetBehaviorActivity.class);
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
