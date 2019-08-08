package com.taz.cureous.notesmvvm.model;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM notes_table")
    void deleteAll();

    @Query("SELECT * FROM notes_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();


}
