package com.adrianjlane.prove05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.Script;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_SCRIPTURE = "com.adrianjlane.prove05.SCRIPTURE";
    public static final String MyPREFERENCES = "FavoriteScripturePrefs" ;
    private static final String TAG = "MainActivity";

    EditText bookText;
    EditText chapterText;
    EditText verseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendScripture(View view) {

        Scripture favScripture = parseScriptureInput();

        if (favScripture != null) {
            Log.v(TAG, "MainActivity.sendScripture() - creating intent with \"" + favScripture.toString() + "\"");
            Intent intent = new Intent(this, DisplayScriptureActivity.class);
            // Create scripture object and attach to intent
            intent.putExtra(EXTRA_SCRIPTURE, favScripture);
            startActivity(intent);
        }
        else {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Values cannot be empty, chapter and verse must be numbers.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    public void loadScripture(View view) {
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Log.v(TAG, "MainActivity.loadScripture() - attempting to load scripture");
        String storedPreference = sharedpreferences.getString("SavedFavoriteScripture", null);
        if (storedPreference != null) {

            Scripture favScripture = Scripture.fromJson(storedPreference);

            bookText = findViewById(R.id.editTextBook);
            chapterText = findViewById(R.id.editTextChapter);
            verseText = findViewById(R.id.editTextVerse);

            bookText.setText(favScripture.getBook());
            chapterText.setText(String.valueOf(favScripture.getChapter()));
            verseText.setText(String.valueOf(favScripture.getVerse()));
        }
        else {
            Toast.makeText(MainActivity.this,"No scripture saved" ,Toast.LENGTH_SHORT).show();
        }

    }

    private Scripture parseScriptureInput() {

        boolean canParse = false;
        Scripture scripture = null;

        // Get values from input fields
        bookText = findViewById(R.id.editTextBook);
        chapterText = findViewById(R.id.editTextChapter);
        verseText = findViewById(R.id.editTextVerse);

        String scriptureBookText = bookText.getText().toString();
        String scriptureChapterText = chapterText.getText().toString();
        String scriptureVerseText = verseText.getText().toString();

        // Basic validation input
        Log.v(TAG, "MainActivity.parseScriptureInput() - validating input");
        if (tryParseInt(scriptureChapterText) && tryParseInt(scriptureVerseText) && scriptureBookText.length() != 0) {
            Log.v(TAG, "MainActivity.parseScriptureInput() - input validated");
            canParse = true;
        }

        // Create scripture object from input if valid
        if (canParse) {
            Log.v(TAG, "MainActivity.parseScriptureInput() - parsing input");
            // Cast values to proper types for verse object
            int scriptureChapterInt = Integer.parseInt(scriptureChapterText);
            int scriptureVerseInt = Integer.parseInt(scriptureVerseText);

            Log.v(TAG, "MainActivity.parseScriptureInput() - creating Scripture object from input");
            scripture = new Scripture(scriptureBookText, scriptureChapterInt, scriptureVerseInt);
        }

        return scripture;
    }

    private boolean tryParseInt(String value) {
        try {
            Log.v(TAG, "MainActivity.tryParseInt(String value) - attempting to parse " + value + " into an int");
            Integer.parseInt(value);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
