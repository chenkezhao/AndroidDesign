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
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewAdapter>{

    private Context mContext;
    private String[] mTexts;

    public RecyclerAdapter(Context context){
        this.mContext = context;
        mTexts = context.getResources().getStringArray(R.array.recyclerItem);
    }

    @Override
    public RecyclerViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewAdapter(mContext,LayoutInflater.from(mContext).inflate(R.layout.recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter holder, int position) {
        holder.textView.setText(mTexts[position]);
        //手动更改高度，不同位置的高度有所不同
        holder.textView.setHeight(500 + (position % 3) * 100);
    }

    @Override
    public int getItemCount() {
        return mTexts == null ? 0 : mTexts.length;
    }


    public static class RecyclerViewAdapter extends RecyclerView.ViewHolder{

        TextView textView;

        public RecyclerViewAdapter(Context mContext,View itemView) {
            super(itemView);
            final Context context = mContext;
            textView = (TextView) itemView.findViewById(R.id.recycler_view_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点到我了", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
