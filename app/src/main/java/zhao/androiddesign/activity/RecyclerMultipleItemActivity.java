package zhao.androiddesign.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import zhao.androiddesign.R;
import zhao.androiddesign.adapter.RecyclerMultipleItemAdapter;

public class RecyclerMultipleItemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_multiple_item);
        initView();
    }

    private void initView(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_multiple);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerMultipleItemAdapter(this));
    }
}
