package zhao.androiddesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import zhao.androiddesign.R;

/**
 * Created by 陈科肇 on 2016/6/22.
 */
public class RecyclerMultipleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private String[] mTexts;

    public RecyclerMultipleItemAdapter(Context context) {
        this.mContext = context;
        mTexts = context.getResources().getStringArray(R.array.recyclerItem);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new ViewAdapter1(LayoutInflater.from(mContext).inflate(R.layout.recycler_item, null),mContext);
        } else {
            return new ViewAdapter2(LayoutInflater.from(mContext).inflate(R.layout.recycler_item1, null));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewAdapter1) {
            //处理布局显示值
            ((ViewAdapter1)holder).textView.setText(mTexts[position]);
        } else if (holder instanceof ViewAdapter2) {
            //处理布局显示值
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return mTexts == null ? 0 : mTexts.length;
    }


    public static class ViewAdapter1 extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewAdapter1(View itemView,Context mContext) {
            super(itemView);final Context context = mContext;
            textView = (TextView) itemView.findViewById(R.id.recycler_view_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点到我了", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static class ViewAdapter2 extends RecyclerView.ViewHolder {

        public ViewAdapter2(View itemView) {
            super(itemView);
        }
    }
}
