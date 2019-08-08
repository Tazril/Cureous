package com.taz.cureous.notesmvvm.model;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = Note.class, version = 1, exportSchema = false)
abstract class NoteDatabse extends RoomDatabase {

    private static NoteDatabse instance;
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();

        }
    };

    synchronized static NoteDatabse getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabse.class, "note_database")
                    .fallbackToDestructiveMigration().addCallback(callback).build();
        }
        return instance;
    }

    abstract NoteDao noteDao();

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        PopulateDbAsyncTask(NoteDatabse db) {
            this.noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Sample Note", "This is a sample Note", 1));
            return null;
        }
    }


}
