package zhao.androiddesign.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhao.androiddesign.R;

/**
 * Created by ManJay on 2016/5/20.
 */
public class AppBarViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private String[] titles = null;

    public AppBarViewPagerAdapter(Context context, String[] titles) {
        this.titles = titles;
        this.mContext = context;
    }

    //开始调用显示页面
    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    //决定是否与一个特定的页面视图返回的关键对象
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);//删除选项卡
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        RelativeLayout rl = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.view_appbar_content,null);
        ListView mListView = (ListView) rl.findViewById(R.id.viewAppBar_lv_list);
        mListView.setAdapter(new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,getData()));
        TextView tv = (TextView) rl.findViewById(R.id.viewAppBar_tv_content);
        tv.setText(titles[position]);
        container.addView(rl);//添加页卡
        return rl;
    }


    private String[] getData(){
        String[] data = {};
        List<String> list = new ArrayList<String>();
        for(int i=0;i<100;i++){
            list.add(i+"");
        }
        return list.toArray(data);
    }
}
