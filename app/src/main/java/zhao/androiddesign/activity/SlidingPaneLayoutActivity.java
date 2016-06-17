package zhao.androiddesign.activity;

import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import zhao.androiddesign.R;

public class SlidingPaneLayoutActivity extends AppCompatActivity {

    private SlidingPaneLayout mSlidingPaneLayout;
    private TextView menu,content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_pane_layout);
        menu = (TextView) findViewById(R.id.tv_left);
        content = (TextView) findViewById(R.id.tv_right);
        mSlidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.spl_root);
        mSlidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                //滑动效果
                menu.setScaleY(slideOffset / 2 + 0.5F);
                menu.setScaleX(slideOffset / 2 + 0.5F);
                content.setScaleY(1 - slideOffset / 5);
            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });
    }
}
