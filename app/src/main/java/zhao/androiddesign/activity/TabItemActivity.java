package zhao.androiddesign.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import zhao.androiddesign.R;

public class TabItemActivity extends AppCompatActivity {

    private TextInputEditText tie_logonName,tie_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_item);
        initView();

        tie_logonName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //检测错误输入，当输入错误时，hint会变成红色并提醒
                //检查实际是否匹配，由自己实现
                if (s.toString().length()>3) {
                    tie_logonName.setError("长度不能大于3");
                    return;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tie_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //检测错误输入，当输入错误时，hint会变成红色并提醒
                //检查实际是否匹配，由自己实现
                if (s.toString().length()>3) {
                    tie_password.setError("长度不能大于3哈哈哈哈");
                    return;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initView(){
        tie_logonName = (TextInputEditText) findViewById(R.id.tie_logonName);
        tie_password = (TextInputEditText) findViewById(R.id.tie_password);
    }
}
