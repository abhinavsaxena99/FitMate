package com.example.fitmate;

import android.os.Bundle;

import com.example.fitmate.ui.main.PageViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fitmate.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Diet extends Fragment {

    ProgressBar progressBar;
    TextView count;
    ImageView increase;
    TextView welcome;
    int curprog=0;
    String name;
    String email;
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.diet, container, false);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        name=user.getDisplayName();

        welcome=rootView.findViewById(R.id.welcome);
        progressBar=rootView.findViewById(R.id.progressBar);
        count=rootView.findViewById(R.id.count);
        increase=rootView.findViewById(R.id.increase);

        String welcometext="Make sure to drink lots of water!\n" +
                "8 glasses per day is required for good health";
        welcome.setText("Hi "+name+" "+welcometext);

        count.setText(""+progressBar.getProgress()+"/8");

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curprog+=1;
                if(curprog>8)
                    curprog=8;
                progressBar.setProgress(curprog);
                count.setText(""+curprog+"/8");
            }
        });



        return rootView;
    }

}
