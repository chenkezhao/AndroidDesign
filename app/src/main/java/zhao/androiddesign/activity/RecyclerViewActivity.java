package zhao.androiddesign.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import zhao.androiddesign.R;
import zhao.androiddesign.adapter.RecyclerAdapter;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Spinner spinner;

    /**
     * 枚举类型
     */
    private enum Type {
        AlphaIn {
            @Override public AnimationAdapter get(Context context) {
                RecyclerAdapter adapter = new RecyclerAdapter(context);
                return new AlphaInAnimationAdapter(adapter);
            }
        },
        ScaleIn {
            @Override public AnimationAdapter get(Context context) {
                RecyclerAdapter adapter = new RecyclerAdapter(context);
                return new ScaleInAnimationAdapter(adapter);
            }
        },
        SlideInBottom {
            @Override public AnimationAdapter get(Context context) {
                RecyclerAdapter adapter = new RecyclerAdapter(context);
                return new SlideInBottomAnimationAdapter(adapter);
            }
        },
        SlideInLeft {
            @Override public AnimationAdapter get(Context context) {
                RecyclerAdapter adapter = new RecyclerAdapter(context);
                return new SlideInLeftAnimationAdapter(adapter);
            }
        },
        SlideInRight {
            @Override public AnimationAdapter get(Context context) {
                RecyclerAdapter adapter = new RecyclerAdapter(context);
                return new SlideInRightAnimationAdapter(adapter);
            }
        };

        public abstract AnimationAdapter get(Context context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//线性布局，类似ListView
        //适配器
        recyclerView.setAdapter(new RecyclerAdapter(this));



        //选择面板
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        for(Type type:Type.values()){
            spinnerAdapter.add(type.name());
        }
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AnimationAdapter adapter = Type.values()[position].get(RecyclerViewActivity.this);
                adapter.setFirstOnly(true);
                adapter.setDuration(500);
                adapter.setInterpolator(new OvershootInterpolator(0.5f));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.recycler_view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_rv_linear1://线性垂直
                recyclerView.setLayoutManager(new LinearLayoutManager(this));//线性布局，类似ListView
                break;
            case R.id.menu_rv_linear2://线性水平
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                break;
            case R.id.menu_rv_grid://网格
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));//线性宫格显示，类似GridView
                break;
            case R.id.menu_rv_grid_staggered://网格瀑布流
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));//线性宫格显示，瀑布流
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
