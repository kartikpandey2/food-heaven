package com.nightmare.foodheaven;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by gopalyadav on 4/22/17.
 */

public class DonateFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private static final String LOG_TAG = DonateFragment.class.getSimpleName();
    private int rType = 0;

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

        ImageView imageView = (ImageView) recordView.findViewById(R.id.donate_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donateDialog();
            }
        });

        return recordView;
    }

    private void donateDialog() {

        // File rename dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_donate, null);

        final Button thought = (Button) view.findViewById(R.id.thought);
        final Button task = (Button) view.findViewById(R.id.task);

        task.setBackgroundResource(R.drawable.rectangle_disabled);
        task.setTextColor(ContextCompat.getColor(getActivity(), R.color.grey));

        thought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thought.setBackgroundResource(R.drawable.rectangle_accent);
                thought.setTextColor(ContextCompat.getColor(getActivity(), R.color.darkAccent));
                task.setBackgroundResource(R.drawable.rectangle_disabled);
                task.setTextColor(ContextCompat.getColor(getActivity(), R.color.grey));
                rType = 0;
            }
        });

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thought.setBackgroundResource(R.drawable.rectangle_disabled);
                thought.setTextColor(ContextCompat.getColor(getActivity(), R.color.grey));
                task.setBackgroundResource(R.drawable.rectangle_flip);
                task.setTextColor(ContextCompat.getColor(getActivity(), R.color.darkFlip));
                rType = 1;
            }
        });

        final EditText input = (EditText) view.findViewById(R.id.new_name);

        builder.setTitle(getActivity().getString(R.string.dialog_title_manage));
        builder.setCancelable(true);
        builder.setPositiveButton(getActivity().getString(R.string.dialog_action_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            if (input.getText().toString().trim().equals("")) {
                                Toast.makeText(getActivity(), "Submitted.", Toast.LENGTH_SHORT).show();
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("Thank You")
                                        .setMessage("Your donation request has been submitted.\nYou will be notified when the pickup specialist comes at your door step.\nYour request id is: #FH212341.")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // continue with delete
                                            }
                                        })
                                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // do nothing
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }

                        } catch (Exception e) {
                            Log.e(LOG_TAG, "exception", e);
                        }

                        dialog.cancel();
                    }
                });
        builder.setNegativeButton(getActivity().getString(R.string.dialog_action_cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder.setView(view);
        AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }

}
