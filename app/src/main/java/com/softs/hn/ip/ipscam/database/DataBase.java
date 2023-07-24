package com.softs.hn.ip.ipscam.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, exportSchema = false, entities = {Historial.class})
public abstract class DataBase extends RoomDatabase {
    public abstract HistorialDao historialDao();

    private static volatile DataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataBase.class) {
                if (INSTANCE == null) {

                    Callback miCallback = new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);

                            databaseWriteExecutor.execute(() -> {
                                HistorialDao dao = INSTANCE.historialDao();
                                dao.deleteAll();


                                Historial h1=new Historial("BAA0001");
                                Historial h2=new Historial("PDR6349");
                                Historial h3=new Historial("PDR6347");
                                Historial h4=new Historial("PDT6399");
                                Historial h5=new Historial("TDR6340");

                                dao.insert(h1);
                                dao.insert(h2);
                                dao.insert(h3);
                                dao.insert(h4);
                                dao.insert(h5);
                            });

                        }
                    };
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DataBase.class, "ordenes_db").addCallback(miCallback).build();
                }
            }
        }
        return INSTANCE;
    }
}
