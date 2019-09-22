package com.example.fitmate;

import android.app.TimePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fitmate.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Sleep extends Fragment {

    EditText age;
    TextView ettime,sleeptime;
    Button calculate;
    String agestring;
    DatabaseReference reff;
    FirebaseUser user;
    String uid;
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sleep, container, false);

        age=rootView.findViewById(R.id.age);
        ettime=rootView.findViewById(R.id.time);
        sleeptime=rootView.findViewById(R.id.sleeptime);
        ettime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                String exm=""+minute,exh=""+hourOfDay;
                                if(minute<10)
                                    exm="0"+exm;
                                if(hourOfDay<10)
                                    exh="0"+exh;
                                ettime.setText(exh+":"+exm);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });
        calculate=rootView.findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(age.getText().toString().equals("")||ettime.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(),"Please enter both age and time",Toast.LENGTH_LONG).show();
                }
                else
                {
                    String s=ettime.getText().toString();
                    int i=0;
                    String h="",m="";
                    for(;s.charAt(i)!=':';i++)
                    {
                        h+=s.charAt(i);
                    }
                    i++;
                    for(;i<s.length();i++)
                    {
                        m+=s.charAt(i);
                    }
                    int hour=Integer.parseInt(h),min=Integer.parseInt(m);
                    if(Integer.parseInt(age.getText().toString())>18)
                        min-=30;
                    if(min<0)
                    {
                        hour--;
                        min+=60;
                    }
                    if(Integer.parseInt(age.getText().toString())>18)
                    {
                        hour-=7;
                    }
                    else
                    {
                        hour-=9;
                    }
                    if(hour<0)
                    {
                        hour+=24;
                    }
                    String exm=""+min,exh=""+hour;
                    if(min<10)
                        exm="0"+exm;
                    if(hour<10)
                        exh="0"+exh;
                    String time12 =  ((hour > 12) ? hour % 12 : hour) + ":" + (min < 10 ? ("0" + min) : min) + " " + ((hour >= 12) ? "PM" : "AM");
                    sleeptime.setText(time12);
                }

            }
        });




        reff= FirebaseDatabase.getInstance().getReference();
        user=FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();


        reff.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                agestring=dataSnapshot.child("User").child(uid).child("age").getValue(String.class);
                age.setText(agestring);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Network Error",Toast.LENGTH_LONG);
            }
        });








        return rootView;
    }
}
