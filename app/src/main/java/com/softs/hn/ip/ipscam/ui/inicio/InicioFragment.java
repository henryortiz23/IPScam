package com.softs.hn.ip.ipscam.ui.inicio;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.canhub.cropper.CropImageContract;
import com.canhub.cropper.CropImageContractOptions;
import com.canhub.cropper.CropImageOptions;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.softs.hn.ip.ipscam.R;
import com.softs.hn.ip.ipscam.databinding.FragmentInicioBinding;

public class InicioFragment extends Fragment {

    private final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap;
    Uri fileUri;
    TextRecognizer recon = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
    private FragmentInicioBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnEscanearAhora.setOnClickListener(v -> scroppFoto(1));

        binding.btnGaleria.setOnClickListener(v -> scroppFoto(2));
        binding.txtPlaca.setFocusableInTouchMode(true);
        binding.txtPlaca.requestFocus();

        return root;
    }


    private void extraerTexto() {
        if (imageBitmap != null) {
            InputImage image = InputImage.fromBitmap(imageBitmap, 0);

            recon.process(image)
                    .addOnSuccessListener(visionText -> {
                        CleanText cleanText= new CleanText(visionText.getText(),getContext());
                        String txtResult;
                        txtResult=cleanText.getCleanText();
                        binding.txtPlaca.setText(txtResult);
                        binding.txtPlaca.setSelection(binding.txtPlaca.getText().length());
                    })
                    .addOnFailureListener(e -> Toast.makeText(getContext(), "No se pudo extraer el texto", Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(getContext(), "Por favor selecciona una imagen", Toast.LENGTH_SHORT).show();
        }
    }

    private final ActivityResultLauncher<CropImageContractOptions> cropImage = registerForActivityResult(
            new CropImageContract(),
            result -> {
                if (result.isSuccessful()) {
                    fileUri= result.getUriContent();
                    imageBitmap= result.getBitmap(requireContext());
                    binding.txtPlaca.setText("");
                    extraerTexto();
                }
            }
    );

    private void scroppFoto(int option){
        CropImageOptions cropImageOptions = new CropImageOptions();
        if(option==1) {
            cropImageOptions.imageSourceIncludeCamera = true;
            cropImageOptions.imageSourceIncludeGallery = false;
        }else{
            cropImageOptions.imageSourceIncludeCamera = false;
            cropImageOptions.imageSourceIncludeGallery = true;
        }
        fileUri = null;
        CropImageContractOptions cropImageContractOptions = new CropImageContractOptions(fileUri, cropImageOptions);
        cropImage.launch(cropImageContractOptions);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}