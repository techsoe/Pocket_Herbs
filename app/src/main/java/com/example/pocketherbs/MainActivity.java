package com.example.pocketherbs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startButton, exitButton, howToUseButton, aboutUsButton, cnnButton;
    Dialog tutorialDialogFragment, aboutUsDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.loadingScreenTitleCol));
            window.setNavigationBarColor(getResources().getColor(R.color.buttonColor));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.buttonColor)));
            SpannableString title = new SpannableString("Pocket Herbs");
            title.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, title.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            getSupportActionBar().setTitle(title);
        }

        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCaptureActivity();
            }
        });

        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeApp();
            }
        });

        tutorialDialogFragment = new Dialog(MainActivity.this);
        tutorialDialogFragment.setContentView(R.layout.fragment_tutorial_dialog);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tutorialDialogFragment.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_tutorial_fragment));
        }
        tutorialDialogFragment.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tutorialDialogFragment.getWindow().getAttributes().windowAnimations = R.style.tutorialFragmentAnimation;

        howToUseButton = findViewById(R.id.howToUseButton);
        howToUseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tutorialDialogFragment.show();
            }
        });

        aboutUsDialogFragment = new Dialog(MainActivity.this);
        aboutUsDialogFragment.setContentView(R.layout.fragment_about_us);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            aboutUsDialogFragment.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_tutorial_fragment));
        }
        aboutUsDialogFragment.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        aboutUsDialogFragment.getWindow().getAttributes().windowAnimations = R.style.tutorialFragmentAnimation;

        aboutUsButton = findViewById(R.id.aboutButton);
        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutUsDialogFragment.show();
            }
        });

        cnnButton = findViewById(R.id.cnnButton);
        cnnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCnnActivity();
            }
        });


    }

    private void closeApp() {

        new AlertDialog.Builder(this)
                .setTitle("Pocket Plants")
                .setMessage("Do you want to close the application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(getApplicationContext(), "Nothing Happend", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }

    public void openCaptureActivity(){

        Intent intent = new Intent(this, Capture_Gallery.class);
        startActivity(intent);

    }

    public void openCnnActivity(){

        Intent intent = new Intent(this, Cnn.class);
        startActivity(intent);

    }

}