package zhao.androiddesign.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.MenuItem;
import android.view.Window;

import zhao.androiddesign.R;

public class MaterialTransitionsActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inside your activity (if you did not enable transitions in your theme)，如果没有在你的主题使用转换，加上以下代码
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        // set an exit transition
        getWindow().setExitTransition(new Explode());


        setContentView(R.layout.activity_material_transitions2);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //如果要在完成第二项操作行为时反转场景转换动画，请调用 Activity.finishAfterTransition() 方法而非 Activity.finish()。
                finishAfterTransition();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
