package com.example.sqllitepractice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String name,price;
    private EditText etName,etPrice;
    private Button btnAdd;
    private MyDatabaseHelper  myDatabaseHelper;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
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
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        etName = view.findViewById(R.id.etName);
        etPrice = view.findViewById(R.id.etPrice);
        btnAdd = view.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);
        myDatabaseHelper = new MyDatabaseHelper(requireContext());



        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == btnAdd ){
            Toast.makeText(requireContext(), "owo", Toast.LENGTH_SHORT).show();
            if(!etName.getText().toString().isEmpty()&&!etPrice.getText().toString().isEmpty()){
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                name = etName.getText().toString();
                price = etPrice.getText().toString();

                myDatabaseHelper.addItem(name,price);

                ListFragment listFragment = (ListFragment)fragmentManager.findFragmentById(R.id.fragmentList);
                listFragment.updateTable();

            }
        }
    }
    public MyDatabaseHelper GetData(){
        return this.myDatabaseHelper;
    }

}