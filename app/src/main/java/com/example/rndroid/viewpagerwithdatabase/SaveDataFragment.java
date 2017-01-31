package com.example.rndroid.viewpagerwithdatabase;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaveDataFragment extends Fragment {

    EditText editTextName, editTextSubject, editTextEmail;
    Button buttonSubmit;

    public SaveDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_save_data, container, false);
        editTextName = (EditText) v.findViewById(R.id.edittextname);
        editTextSubject = (EditText) v.findViewById(R.id.edittextsubject);
        editTextEmail = (EditText) v.findViewById(R.id.edittextemail);
        buttonSubmit = (Button) v.findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity m = (MainActivity) getActivity();
                m.myDatabase.insertStudInfo(editTextName.getText().toString(), editTextSubject.getText().toString(), editTextEmail.getText().toString());
                Toast.makeText(getActivity(), "Data Saved", Toast.LENGTH_SHORT).show();
                m.notifyData();
            }
        });
        return v;
    }

}
