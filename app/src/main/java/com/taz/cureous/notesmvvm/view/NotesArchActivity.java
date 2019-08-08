package com.taz.cureous.notesmvvm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.taz.cureous.R;
import com.taz.cureous.notesmvvm.NoteViewModel;
import com.taz.cureous.notesmvvm.model.Note;

import java.util.List;
import java.util.Objects;

import static com.taz.cureous.notesmvvm.view.EditNoteActivity.*;

public class NotesArchActivity extends AppCompatActivity {

    public static final int REQUEST_NOTE_ADD = 1;
    public static final int REQUEST_NOTE_UPDATE = 2;
    private static final String TAG = NotesArchActivity.class.getSimpleName();
    final NoteRecyclerViewAdapter adapter = new NoteRecyclerViewAdapter();
    RecyclerView recyclerView;
    NoteViewModel viewModel;
    private int noteId = -1;


    private View.OnClickListener fabListener = v -> {
        Intent intent = new Intent(NotesArchActivity.this, EditNoteActivity.class);
        startActivityForResult(intent, REQUEST_NOTE_ADD);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_arch);

        Toolbar toolbar = findViewById(R.id.toolbarNote);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Notes");

        recyclerView = findViewById(R.id.notes_rv);
        RecyclerView.LayoutManager lm = new LinearLayoutManager
                (NotesArchActivity.this, RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new NoteRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int position) {
                Note note = adapter.getItemByPosition(position);
                Intent intent = new Intent(NotesArchActivity.this, EditNoteActivity.class);
                intent.putExtra(NOTE_TITLE, note.getTitle());
                intent.putExtra(NOTE_DESC, note.getDescription());
                intent.putExtra(NOTE_PRI, note.getPriority());
                noteId = note.getId();
                startActivityForResult(intent, REQUEST_NOTE_UPDATE);
            }
        });

        viewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        viewModel.getAllNotes().observe(this, adapter::submitList);

        findViewById(R.id.notes_fab).setOnClickListener(fabListener);
        swipeToDelNote();
    }

    private void swipeToDelNote() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Note note = adapter.getItemByPosition(viewHolder.getAdapterPosition());
                viewModel.delete(note);
                Toast.makeText(NotesArchActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.notes_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String title = data.getStringExtra(NOTE_TITLE);
            String desc = data.getStringExtra(NOTE_DESC);
            int pri = data.getIntExtra(NOTE_PRI, 1);
            Note note = new Note(title, desc, pri);
            if (requestCode == REQUEST_NOTE_ADD) {
                viewModel.insert(note);
                Toast.makeText(this, "Note Created", Toast.LENGTH_SHORT).show();
            } else {
                note.setId(noteId);
                noteId = -1;
                viewModel.update(note);
                Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == REQUEST_NOTE_UPDATE)
            Toast.makeText(this, "Note not Updated", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Note not Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        viewModel.deleAll();
        return true;
    }
}
