package zhao.androiddesign.activity;

import android.os.Bundle;
import android.support.transition.ChangeBounds;
import android.support.transition.Fade;
import android.support.transition.Transition;
import android.support.transition.TransitionSet;
import android.support.v7.app.AppCompatActivity;
//import android.transition.Scene;
//import android.transition.TransitionInflater;
//import android.transition.TransitionManager;
//import static android.transition.Scene.getSceneForLayout;
import android.support.transition.Scene;
import android.support.transition.TransitionManager;
import static android.support.transition.Scene.getSceneForLayout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import zhao.androiddesign.R;


/**
 * 定制从一个场景布局转化到另一个场景布局的过渡
 * Android 4.4.2 (API level 19) or higher
 * https://developer.android.com/training/transitions/index.html
 * https://developer.android.com/samples/BasicTransition/index.html
 * https://developer.android.com/samples/CustomTransition/index.html
 */
public class TransitionSceneActivity extends AppCompatActivity {

	// 定义这些转变过渡的场景
	private Scene				searchOpenScene;
	private Scene				searchCloseScene;

	// 定制一个转变过渡管理器
	private TransitionManager	mTransitionManagerForSearchCloseScene;

	// 场景的转变过度，都基于这个ViewGroup中
	private ViewGroup			mSceneRoot;

	private TextView transition_title;


	private boolean openSwitch = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transition_scene);

		mSceneRoot = (ViewGroup) findViewById(R.id.scene_root);

		// BEGIN_INCLUDE(instantiation_from_resource)
		// You can also inflate a generate a Scene from a layout resource file.
		searchOpenScene = getSceneForLayout(mSceneRoot, R.layout.view_search_scene_open, this);
		// END_INCLUDE(instantiation_from_resource)

		// BEGIN_INCLUDE(instantiation_from_resource)
		// You can also inflate a generate a Scene from a layout resource file.
		searchCloseScene = getSceneForLayout(mSceneRoot, R.layout.view_search_scene_close, this);
		// END_INCLUDE(instantiation_from_resource)

		// BEGIN_INCLUDE(custom_transition_manager)
		// We create a custom TransitionManager for Scene 3, in which
		// ChangeBounds and Fade
		// take place at the same time.
		//*******************************Android 4.4.2 (API level 19) or higher Android 4.4.2（API级别19）或更高
		//mTransitionManagerForSearchCloseScene = TransitionInflater.from(this).inflateTransitionManager(R.transition.searchopenscene_transition_manager, mSceneRoot);


		//*******************************对于Android的版本早于4.4.2（API等级19），但大于或等于到Android 4.0（API等级14）
//		TransitionSet transitionSet = new TransitionSet();
//		transitionSet.addTransition(new Fade()).addTransition(new ChangeBounds());
//		TransitionManager.beginDelayedTransition(mSceneRoot,transitionSet);
		// END_INCLUDE(custom_transition_manager)

		mSceneRoot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!openSwitch){
					TransitionManager.go(searchOpenScene);
					openSwitch = true;
					Toast.makeText(TransitionSceneActivity.this,"open，"+mSceneRoot.findViewById(R.id.transition_title),Toast.LENGTH_SHORT).show();





					//没有场景时，可以利用当前场景进行改变
					transition_title = (TextView) findViewById(R.id.transition_title);
					transition_title.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							// BEGIN_INCLUDE(transition_dynamic)
							// Alternatively, transition can be invoked dynamically without a Scene.
							// For this, we first call TransitionManager.beginDelayedTransition().
							TransitionManager.beginDelayedTransition(mSceneRoot);
							// Then, we can just change view properties as usual.
							View search = mSceneRoot.findViewById(R.id.search_view);
							ViewGroup.LayoutParams params = search.getLayoutParams();
							int newSize = 100;//getResources().getDimensionPixelSize(R.dimen.square_size_expanded);
							params.width = newSize;
							params.height = newSize;
							search.setLayoutParams(params);
							// END_INCLUDE(transition_dynamic)
							Toast.makeText(TransitionSceneActivity.this,"当前啊。",Toast.LENGTH_SHORT).show();
						}
					});
				}else{
					TransitionManager.go(searchCloseScene);
					openSwitch = false;
					Toast.makeText(TransitionSceneActivity.this,"close，"+mSceneRoot.findViewById(R.id.transition_title),Toast.LENGTH_SHORT).show();
				}
			}
		});

	}
}
