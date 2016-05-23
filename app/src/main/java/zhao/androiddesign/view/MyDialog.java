package zhao.androiddesign.view;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import java.lang.reflect.Field;

/**
 * Base class for {@link android.app.Dialog}s styled as a bottom sheet.
 * 在用户角度来说，使BottomSheetDialog 消失有三种方法
 * 点击阴影区域。
 * 点击物理返回键。
 * 在BottomSheetDialog 区域中向下滑动。
 * 最后一种是 BottomSheetDialog 特有的，当实例化一个BottomSheetDialog后，使用第一、二种方法使其消失后，在调用 show() 方法后一切正常，但是当使用第三种方法使其消失然后在调用 show() 后，只见屏幕出现阴影，但是之前的View却未出现。
 */
public class MyDialog extends AppCompatDialog {

    private BottomSheetBehavior bottomSheetBehavior;

    public MyDialog(@NonNull Context context) {
        this(context, 0);
    }

    public MyDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, getThemeResId(context, theme));
        // We hide the title bar for any style configuration. Otherwise, there will be a gap
        // above the bottom sheet when it is expanded.
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    protected MyDialog(@NonNull Context context, boolean cancelable,
                       OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResId) {
        super.setContentView(wrapInBottomSheet(layoutResId, null, null));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(wrapInBottomSheet(0, view, null));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(wrapInBottomSheet(0, view, params));
    }

    private View wrapInBottomSheet(int layoutResId, View view, ViewGroup.LayoutParams params) {
        final CoordinatorLayout coordinator = (CoordinatorLayout) View.inflate(getContext(),
                android.support.design.R.layout.design_bottom_sheet_dialog, null);
        if (layoutResId != 0 && view == null) {
            view = getLayoutInflater().inflate(layoutResId, coordinator, false);
        }
        FrameLayout bottomSheet = (FrameLayout) coordinator.findViewById(android.support.design.R.id.design_bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(mBottomSheetCallback);
        if (params == null) {
            bottomSheet.addView(view);
        } else {
            bottomSheet.addView(view, params);
        }
        // We treat the CoordinatorLayout as outside the dialog though it is technically inside
        if (shouldWindowCloseOnTouchOutside()) {
            coordinator.findViewById(android.support.design.R.id.touch_outside).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (isShowing()) {
                                cancel();
                            }
                        }
                    });
        }
        return coordinator;
    }

    private boolean shouldWindowCloseOnTouchOutside() {
        if (Build.VERSION.SDK_INT < 11) {
            return true;
        }
        TypedValue value = new TypedValue();
        //noinspection SimplifiableIfStatement
        if (getContext().getTheme()
                .resolveAttribute(android.R.attr.windowCloseOnTouchOutside, value, true)) {
            return value.data != 0;
        }
        return false;
    }

    private static int getThemeResId(Context context, int themeId) {
        if (themeId == 0) {
            // If the provided theme is 0, then retrieve the dialogTheme from our theme
            TypedValue outValue = new TypedValue();
            if (context.getTheme().resolveAttribute(
                    android.support.design.R.attr.bottomSheetDialogTheme, outValue, true)) {
                themeId = outValue.resourceId;
            } else {
                // bottomSheetDialogTheme is not provided; we default to our light theme
                themeId = android.support.design.R.style.Theme_Design_Light_BottomSheetDialog;
            }
        }
        return themeId;
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback
            = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet,
                                   @BottomSheetBehavior.State int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
//        会有异常
//        if (bottomSheetBehavior != null)
// bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        //暂时解决方法
        try {
            Field field = BottomSheetBehavior.class.getDeclaredField("mState");
            field.setAccessible(true);
            field.setInt(bottomSheetBehavior, BottomSheetBehavior.STATE_EXPANDED);
        } catch (NoSuchFieldException e) {
            Log.e("BottomSheetBehavior", "Error setting BottomSheetBehavior initial state (1)", e);
        } catch (IllegalAccessException e) {
            Log.e("BottomSheetBehavior", "Error setting BottomSheetBehavior initial state (2)", e);
        }
    }
}
