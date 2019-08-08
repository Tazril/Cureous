package com.taz.cureous.notesmvvm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.taz.cureous.R;

import java.util.Objects;


public class EditNoteActivity extends AppCompatActivity {

    public static final String NOTE_TITLE = "NotesAppMVVM.note_title";
    public static final String NOTE_DESC = "NotesAppMVVM.note_desc";
    public static final String NOTE_PRI = "NotesAppMVVM.note_pri";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbarEditNote);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Edit Note");

        editTextTitle = findViewById(R.id.note_edit_title_tv);
        editTextDescription = findViewById(R.id.note_edit_desc_tv);
        numberPickerPriority = findViewById(R.id.note_edit_priority_np);

        Intent data = getIntent();
        if (data != null) {
            String title = data.getStringExtra(NOTE_TITLE);
            String desc = data.getStringExtra(NOTE_DESC);
            int pri = data.getIntExtra(NOTE_PRI, 1);
            editTextTitle.setText(title);
            editTextDescription.setText(desc);
            numberPickerPriority.setValue(pri);
        }

        numberPickerPriority.setMaxValue(10);
        numberPickerPriority.setMinValue(1);

//        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.edit_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_note_save_menu_item) return saveNote();
        return super.onOptionsItemSelected(item);
    }

    private boolean saveNote() {
        String title = editTextTitle.getText().toString();
        String desc = editTextDescription.getText().toString();
        int pri = numberPickerPriority.getValue();
        if (title.trim().isEmpty() || desc.trim().isEmpty()) {
            Toast.makeText(this, "Please Enter Title and Description", Toast.LENGTH_SHORT).show();
            return false;
        }

        Intent data = new Intent();
        data.putExtra(NOTE_TITLE, title);
        data.putExtra(NOTE_DESC, desc);
        data.putExtra(NOTE_PRI, pri);
        setResult(RESULT_OK, data);
        finish();
        return true;
    }
}
