package com.community.jboss.leadmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import static com.community.jboss.leadmanagement.SettingsActivity.PREF_DARK_THEME;

public class AboutActivity extends AppCompatActivity {

    public static boolean useDarkTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        LinearLayout contributors = findViewById(R.id.btm_contrib);
        LinearLayout license = findViewById(R.id.btm_license);
        LinearLayout github = findViewById(R.id.social_github);
        LinearLayout twitter = findViewById(R.id.social_twitter);
        LinearLayout gitter = findViewById(R.id.social_gitter);
        contributors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showContributors();
            }
        });
        license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLink("https://raw.githubusercontent.com/JBossOutreach/lead-management-android/master/LICENSE");
            }
        });
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLink("https://github.com/JBossOutreach/lead-management-android");
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLink("https://twitter.com/jboss");
            }
        });
        gitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLink("https://gitter.im/JBossOutreach");
            }
        });
    }
    private void showContributors() {
        Intent intent = new Intent(AboutActivity.this, ContributorsActivity.class);
        startActivity(intent);
    }
    private void openLink(String url){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(i);
    }

}