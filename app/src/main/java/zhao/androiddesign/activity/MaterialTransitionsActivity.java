package zhao.androiddesign.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.util.Pair;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioGroup;

import zhao.androiddesign.R;

/**
 * 支持 Android 5.0（API 级别 21）进入与退出转换
 * <p>
 * 如果要在两个拥有共享元素的操作行为之间安排屏幕转换动画：
 * 1. 请在您的主题中启用窗口内容转换。
 * 2. 在您的风格中指定一个共享元素转换。
 * 3. 将您的转换定义为 XML 资源。
 * 4. 利用 android:transitionName 属性对两个布局中的共享元素指定一个通用名称。
 * 5. 使用 ActivityOptions.makeSceneTransitionAnimation() 方法。
 */
public class MaterialTransitionsActivity extends AppCompatActivity {

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inside your activity (if you did not enable transitions in your theme)，如果没有在你的主题使用转换，加上以下代码
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        // set an exit transition
        getWindow().setExitTransition(new Explode());


        setContentView(R.layout.activity_material_transitions);
        imageView1 = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);

        radioGroup = (RadioGroup) findViewById(R.id.optRG);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                Intent intent = new Intent(MaterialTransitionsActivity.this, MaterialTransitionsActivity2.class);
                ActivityOptions options = null;
                switch (checkedId) {
                    case R.id.oneRB:
                        //以共享元素启动一个操作行为
                        // create the transition animation - the images in the layouts
                        // of both activities are defined with android:transitionName="robot"
                        options = ActivityOptions.makeSceneTransitionAnimation(MaterialTransitionsActivity.this, imageView3, "robot");

                        break;
                    case R.id.multipleRB:
                        //以多个共享元素启动一个操作行为
                        //请以 android:transitionName 属性（或在两个操作行为中使用 View.setTransitionName() 方法）定义共享元素
                        Pair pair1 = Pair.create(imageView1, "agreedName1");
                        Pair pair2 = Pair.create(imageView2, "agreedName2");
                        options = ActivityOptions.makeSceneTransitionAnimation(MaterialTransitionsActivity.this,
                                pair1,
                                pair2);
                        break;
                    default:
                        break;
                }
                // start the new activity
                startActivity(intent, options.toBundle());
            }
        });

    }
}
