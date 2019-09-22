package com.example.fitmate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod; 
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Exercise extends Fragment {

//    ListView listView;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.exercise, container, false);

        TextView text1=(TextView)rootView.findViewById(R.id.textView1);
        TextView text2=(TextView)rootView.findViewById(R.id.textView2);
        TextView text3=(TextView)rootView.findViewById(R.id.textView3);
        TextView text4=(TextView)rootView.findViewById(R.id.textView4);
        TextView text5=(TextView)rootView.findViewById(R.id.textView5);
        TextView text6=(TextView)rootView.findViewById(R.id.textView6);
        text1.setMovementMethod(LinkMovementMethod.getInstance());
        text2.setMovementMethod(LinkMovementMethod.getInstance());
        text3.setMovementMethod(LinkMovementMethod.getInstance());
        text4.setMovementMethod(LinkMovementMethod.getInstance());
        text5.setMovementMethod(LinkMovementMethod.getInstance());
        text6.setMovementMethod(LinkMovementMethod.getInstance());



//        listView=rootView.findViewById(R.id.listView);





        return rootView;
    }
}
