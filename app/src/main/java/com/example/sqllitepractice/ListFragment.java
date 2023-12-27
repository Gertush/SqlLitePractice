package com.example.sqllitepractice;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MyDatabaseHelper myDatabaseHelper;

    private AddFragment addFragment;
    private TableLayout tableLayout;
    Cursor cursor;
    private LinearLayout mainLinearLayout;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        myDatabaseHelper = new MyDatabaseHelper(requireContext());
        cursor = myDatabaseHelper.readAllData();
        myDatabaseHelper.deleteAllData();
        mainLinearLayout = view.findViewById(R.id.mainLinearLayout);
         tableLayout = new TableLayout(requireContext());
        TableRow headRow = new TableRow(requireContext());
        TextView tvName = new TextView(requireContext());
        TextView tvPrice = new TextView(requireContext());
        tvName.setText("Name");
        tvPrice.setText("Price");
        headRow.addView(tvName);
        headRow.addView(tvPrice);

        tableLayout.addView(headRow);
        mainLinearLayout.addView(tableLayout);







        return view;
    }
    public void updateTable(){
        cursor = myDatabaseHelper.readAllData();
        tableLayout.removeAllViews();

        TableRow headRow = new TableRow(requireContext());
        TextView tvName = new TextView(requireContext());
        TextView tvPrice = new TextView(requireContext());
        tvName.setText("Name");
        tvPrice.setText("Price");
        headRow.addView(tvName);
        headRow.addView(tvPrice);
        tableLayout.addView(headRow);


        cursor.moveToPosition(-1);
        TableRow tableRow;
        TextView tv1,tv2;
//
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(30,0,30,0);
        while(cursor.moveToNext()){
//
            tableRow = new TableRow(requireContext());
            tv1 = new TextView(requireContext());
            tv2 = new TextView(requireContext());
            tv1.setText(cursor.getString(1));
            tv2.setText(cursor.getString(2));
            tableRow.addView(tv1);
            tableRow.addView(tv2);
            if(tableLayout==null){
                Toast.makeText(requireContext(), "bad", Toast.LENGTH_SHORT).show();
            }
            else{
                tableLayout.addView(tableRow);
            }

            Toast.makeText(requireContext(), cursor.getString(0)+" " +cursor.getString(1)+" "+ cursor.getString(2), Toast.LENGTH_SHORT).show();
        }
    }
}