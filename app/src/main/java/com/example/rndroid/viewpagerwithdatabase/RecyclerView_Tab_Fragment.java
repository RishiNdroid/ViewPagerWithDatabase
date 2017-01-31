package com.example.rndroid.viewpagerwithdatabase;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerView_Tab_Fragment extends Fragment {

    // DECLARE ALL REQUIRED VARIABLES
    studendDetails_for_recycler for_recycler;
    RecyclerView recyclerView;
    ArrayList<studendDetails_for_recycler> studDetails;
    MainActivity m;
    MyRecyclerAdapter recyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         m = (MainActivity) getActivity();
    }

    public RecyclerView_Tab_Fragment() {
        // Required empty public constructor
    }

    // CREATE A CUSTOM ADAPTER FOR RECYCLER VIEW
    public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>{

        public MyRecyclerAdapter(ArrayList<studendDetails_for_recycler> MystudDetails){
                studDetails = MystudDetails;
        }
        //DECLARE ALL VIEW OF row_recycler_view.XML
        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txNo, txName, txSub, txEmail;
            public MyViewHolder(View itemView) {
                super(itemView);
                txNo = (TextView) itemView.findViewById(R.id.textViewSno_rv);
                txName = (TextView) itemView.findViewById(R.id.textViewName_rv);
                txSub = (TextView) itemView.findViewById(R.id.textViewSub_rv);
                txEmail = (TextView) itemView.findViewById(R.id.textView_email_rv);
            }
        }

        //INFLATE THE VIEW ON RECYCLER VIEW EVERY TIME
        @Override
        public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = getActivity().getLayoutInflater().inflate(R.layout.row_recycler_view, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(v);
            return viewHolder;
        }

        //THIS METHOD WILL GET THE DATA FROM SOURCE AND PUT ONTO RESPECTIVE VIEWS
        @Override
        public void onBindViewHolder(MyRecyclerAdapter.MyViewHolder holder, int position) {

            holder.txNo.setText(studDetails.get(position).getSno());
            holder.txName.setText(studDetails.get(position).getName());
            holder.txSub.setText(studDetails.get(position).getSubject());
            holder.txEmail.setText(studDetails.get(position).getEmail());
        }

        @Override
        public int getItemCount() {
            return studDetails.size();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recycler_view__tab_, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.myRecyclerView);
        Cursor c = null;
        studDetails = new ArrayList<>();

        c = m.myDatabase.queryCursorRead();
        if (c != null){
            while (c.moveToNext()){
                for_recycler = new studendDetails_for_recycler();

                for_recycler.setSno(c.getString(c.getColumnIndex("_id")));
                for_recycler.setName(c.getString(c.getColumnIndex("sname")));
                for_recycler.setSubject(c.getString(c.getColumnIndex("ssubject")));
                for_recycler.setEmail(c.getString(c.getColumnIndex("semail")));
                studDetails.add(for_recycler);
            }
        }else {
            Toast.makeText(getActivity(), "Database Error", Toast.LENGTH_SHORT).show();
        }

        recyclerAdapter = new MyRecyclerAdapter(studDetails);
        recyclerView.setAdapter(recyclerAdapter);

        // THIS LAYOUT MANAGER WILL COLLECT AND REUSE ROW WHICH IS OUT OF BOUND AND KEEP THEM READY FOR NEXT ROW
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return v;
    }
}
