package com.softs.hn.ip.ipscam.ui.historial;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.softs.hn.ip.ipscam.database.Historial;
import com.softs.hn.ip.ipscam.database.HistorialRepository;

import java.util.List;

public class HistorialViewModel extends AndroidViewModel {

    private HistorialRepository repository;
    private final LiveData<List<Historial>> historialDataset;
    public HistorialViewModel(@NonNull Application app) {
        super(app);
        this.repository=new HistorialRepository(app);
        this.historialDataset= repository.getDataset();
    }

    private HistorialRepository getRepository(){
        return repository;
    }

    public LiveData<List<Historial>> getHistorialDataset() {
        return historialDataset;
    }

    public void insert(Historial data){
        repository.insert(data);
    }

    public void update(Historial data){
        repository.update(data);
    }

    public void delete(Historial data){
        repository.delete(data);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}