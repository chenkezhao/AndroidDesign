package zhao.androiddesign.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zhao.androiddesign.R;

/**
 * fragment
 */
public class PlanetFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "";

    private TextView tv;
    private int number=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =inflater.inflate(R.layout.fragment_planet, container, false);
        tv = (TextView) view.findViewById(R.id.content_text);
        tv.setText("fragment"+number);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setText(int position){
        number = position;
    }
}
