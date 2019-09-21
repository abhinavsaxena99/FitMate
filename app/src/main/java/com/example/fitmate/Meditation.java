package com.example.fitmate;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.fitmate.ui.main.SectionsPagerAdapter;

import java.io.IOException;

public class Meditation extends Fragment {

    MediaPlayer mPlayer2;
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.meditation, container, false);
        ImageButton play=rootView.findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spinner=rootView.findViewById(R.id.spinner);
                String text=spinner.getSelectedItem().toString();

                if(text.equals("5 minutes")) {
//                    MediaPlayer mPlayer2;
                    mPlayer2= MediaPlayer.create(getContext(), R.raw.song1);
                    mPlayer2.start();

                }
                else if(text.equals("10 minutes")) {
//                    MediaPlayer mPlayer2;
                    mPlayer2= MediaPlayer.create(getContext(), R.raw.song2);
                    mPlayer2.start();

                }
//                else if(text.equals("15 minutes")) {
//                    mPlayer2= MediaPlayer.create(this, R.raw.song1);
//                    mPlayer2.start();
//
//                }
            }
        });
        ImageButton stop=rootView.findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer2.stop();
            }
        });
        return rootView;
    }
}
