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

import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitmate.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Diet extends Fragment {

    ProgressBar progressBar;
    TextView count;
    ImageView increase;
    TextView welcome;
    int curprog=0;
    String name;
    String age;
    FirebaseUser user;
    String uid;
    private DatabaseReference reff;
    List<String> itemlist;
    final String TAG = "UIDgaga";
    View rootView;
    TextView welcomeage;
    TextView HyperLink;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.diet, container, false);
        user=FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
//        Toast.makeText(getContext(),uid,Toast.LENGTH_LONG);
        itemlist = new ArrayList<>();
        reff=FirebaseDatabase.getInstance().getReference();

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemlist.clear();
                Log.d(TAG, uid);
                name=dataSnapshot.child("User").child(uid).child("name").getValue(String.class);
                age=dataSnapshot.child("User").child(uid).child("age").getValue(String.class);
                Log.d(TAG, "previous"+name+age);


                welcome=rootView.findViewById(R.id.welcome);
                progressBar=rootView.findViewById(R.id.progressBar);
                count=rootView.findViewById(R.id.count);
                increase=rootView.findViewById(R.id.increase);
                welcomeage=rootView.findViewById(R.id.welcomeage);

                String welcometext="Make sure to drink lots of water!\n" +
                        "8 glasses per day is required for good health";
                Log.d(TAG, name+age);
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

                String welcomeagetext="For a "+age+" year old, we recommend the following diet plan:- ";
                welcomeage.setText(welcomeagetext);

                if(Integer.parseInt(age)<=25)
                {
                    HyperLink = (TextView)rootView.findViewById(R.id.textView1);

                    String text = "<a href='https://www.mealplansite.com/age/adolescent.aspx'> For ages < 25 </a>";
                    HyperLink.setMovementMethod(LinkMovementMethod.getInstance());
                    HyperLink.setText(Html.fromHtml(text));
                }
                else if(Integer.parseInt(age)>=60)
                {
                    HyperLink = (TextView)rootView.findViewById(R.id.textView1);

                    String text = "<a href='https://www.mealplansite.com/age/elderly.aspx'> For ages > 60 </a>";
                    HyperLink.setMovementMethod(LinkMovementMethod.getInstance());
                    HyperLink.setText(Html.fromHtml(text));
                }
                else
                {
                    HyperLink = (TextView)rootView.findViewById(R.id.textView1);

                    String text = "<a href='https://www.mealplansite.com/age/male-adult.aspx'> For ages 25-60 </a>";
                    HyperLink.setMovementMethod(LinkMovementMethod.getInstance());
                    HyperLink.setText(Html.fromHtml(text));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Network Error",Toast.LENGTH_LONG);
            }
        });

//        Log.d(TAG, name+age);



        return rootView;
    }

}
