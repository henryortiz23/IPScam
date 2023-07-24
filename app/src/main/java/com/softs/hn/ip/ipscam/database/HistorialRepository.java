package com.softs.hn.ip.ipscam.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HistorialRepository {

    private HistorialDao dao;
    private LiveData<List<Historial>> dataset;


    public HistorialRepository(Application app){
        DataBase db= DataBase.getDatabase(app);
        this.dao=db.historialDao();
        this.dataset=dao.getHistorial();
    }

    public LiveData<List<Historial>> getDataset(){
        return dataset;
    };

    public void insert(Historial data){
        DataBase.databaseWriteExecutor.execute(()->{
            dao.insert(data);
        });
    }

    public void update(Historial data){
        DataBase.databaseWriteExecutor.execute(()->{
            dao.update(data);
        });
    }

    public void delete(Historial data){
        DataBase.databaseWriteExecutor.execute(()->{
            dao.delete(data);
        });
    }

    public void deleteAll(){
        DataBase.databaseWriteExecutor.execute(()->{
            dao.deleteAll();
        });
    }
}
