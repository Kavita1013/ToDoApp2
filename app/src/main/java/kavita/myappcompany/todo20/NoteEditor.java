package kavita.myappcompany.todo20;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;

public class NoteEditor extends AppCompatActivity {
    int noteId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText = findViewById(R.id.editText);


        Intent intent = getIntent();


        noteId = intent.getIntExtra("noteId", -1);
        if (noteId != -1) {
            editText.setText(ScrollingActivity.notes.get(noteId));
        } else {

            ScrollingActivity.notes.add("");
            noteId = ScrollingActivity.notes.size() - 1;
            ScrollingActivity.arrayAdapter.notifyDataSetChanged();

        }


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ScrollingActivity.notes.set(noteId, String.valueOf(charSequence));
                ScrollingActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(ScrollingActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}