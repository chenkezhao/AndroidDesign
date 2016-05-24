package zhao.androiddesign.activity;

import android.support.v4.internal.view.SupportMenuItem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import zhao.androiddesign.R;

public class ToolbarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        initView();
        toolbar.setTitle("陈科肇");
        toolbar.setSubtitle("哈哈哈哈哈哈");
//        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

    }


    private void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_t_menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reaction_activity,menu);
        SupportMenuItem searchItem = (SupportMenuItem)menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                Toast.makeText(this, "你点击了“保存”按键！", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_delete:
                Toast.makeText(this, "你点击了“删除”按键！", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_call:
                Toast.makeText(this, "你点击了“呼叫”按键！", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
