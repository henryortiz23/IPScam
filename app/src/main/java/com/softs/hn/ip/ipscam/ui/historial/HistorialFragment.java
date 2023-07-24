package com.softs.hn.ip.ipscam.ui.historial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.softs.hn.ip.ipscam.database.Historial;
import com.softs.hn.ip.ipscam.databinding.FragmentHistorialBinding;

import java.util.ArrayList;

public class HistorialFragment extends Fragment implements OnItemClickListenerHistotial<Historial>{

    private HistorialAdapter adaptador;
    private HistorialViewModel viewModel;
    private FragmentHistorialBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        HistorialViewModel historialViewModel =
//                new ViewModelProvider(this).get(HistorialViewModel.class);

        binding = FragmentHistorialBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(HistorialViewModel.class);

        adaptador= new HistorialAdapter(new ArrayList<>(),this);

        viewModel.getHistorialDataset().observe(getViewLifecycleOwner(),historial -> {
            adaptador.setItems(historial);
        });


        setupRecyclerView();
        return root;
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());

        binding.rvHistorial.setLayoutManager(linearLayoutManager);
        binding.rvHistorial.setAdapter(adaptador);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(Historial data) {

    }

    @Override
    public void onItemClickVer(Historial data) {

    }

    @Override
    public void onItemClickDelete(Historial data) {
        HistorialViewModel mHistorialViewModel;
        mHistorialViewModel = new ViewModelProvider(this).get(HistorialViewModel.class);
        mHistorialViewModel.delete(data);
    }
}