package com.example.rndroid.viewpagerwithdatabase;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    ListView listView;
    Cursor cursor;
    SimpleCursorAdapter cursorAdapter;

    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_fragment_two, container, false);
        listView = (ListView) v.findViewById(R.id.listview1);

        MainActivity m = (MainActivity) getActivity();
        cursor = null;
        cursor = m.myDatabase.queryCursorRead();
        cursorAdapter = new SimpleCursorAdapter(getActivity(),R.layout.row,cursor,
                new String[] { "_id", "sname", "ssubject", "semail"},
                new int[]{R.id.textViewSno, R.id.textView2,R.id.textView3, R.id.textViewemail});

        listView.setAdapter(cursorAdapter);
        return v;

    }

}
