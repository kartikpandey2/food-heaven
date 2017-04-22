package com.nightmare.foodheaven;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gopalyadav on 4/22/17.
 */

public class DonateFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private static final String LOG_TAG = DonateFragment.class.getSimpleName();

    public DonateFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Record_Fragment.
     */
    public static DonateFragment newInstance(int position) {
        DonateFragment f = new DonateFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View recordView = inflater.inflate(R.layout.fragment_donate, container, false);

        return recordView;
    }

}
