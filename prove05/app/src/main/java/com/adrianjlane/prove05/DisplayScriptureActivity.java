package com.adrianjlane.prove05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayScriptureActivity extends AppCompatActivity {

    private static final String TAG = "DisplayScriptureActivit"; // Truncated due to 23 character limit
    public static final String MyPREFERENCES = "FavoriteScripturePrefs" ;
    private static Scripture favScripture = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_scripture);

        Intent intent = getIntent();
        favScripture = intent.getParcelableExtra(MainActivity.EXTRA_SCRIPTURE);
        Log.v(TAG, "DisplayScriptureActivity.onCreate() - received intent with \"" + favScripture.toString() + "\"");

        TextView textView = findViewById(R.id.current_scripture);
        textView.setText(favScripture.toString());
    }

    public void saveScripture(View view) {
        if (favScripture != null) {
            Log.v(TAG, "DisplayScriptureActivity.saveScripture() converting to json: " + favScripture.toJson());
            String scriptureString = favScripture.toJson();

            Log.v(TAG, "DisplayScriptureActivity.saveScripture() - Saving to shared preferences");
            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("SavedFavoriteScripture", scriptureString);
            editor.commit();

            Toast.makeText(DisplayScriptureActivity.this,"\"" + favScripture.toString() + "\" saved" ,Toast.LENGTH_SHORT).show();
        }
    }
}
