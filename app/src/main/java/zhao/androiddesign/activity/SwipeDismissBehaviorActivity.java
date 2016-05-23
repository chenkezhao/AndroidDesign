package zhao.androiddesign.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import zhao.androiddesign.R;

public class SwipeDismissBehaviorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_dismiss_behavior);
        TextView swipeView = (TextView) findViewById(R.id.swip);
        final SwipeDismissBehavior<View> swipe
                = new SwipeDismissBehavior();

        swipe.setSwipeDirection(
                SwipeDismissBehavior.SWIPE_DIRECTION_ANY);

        swipe.setListener(
                new SwipeDismissBehavior.OnDismissListener() {
                    @Override
                    public void onDismiss(View view) {

                    }

                    @Override
                    public void onDragStateChanged(int state) {
                    }
                });

        CoordinatorLayout.LayoutParams coordinatorParams =
                (CoordinatorLayout.LayoutParams) swipeView.getLayoutParams();

        coordinatorParams.setBehavior(swipe);
    }
}
