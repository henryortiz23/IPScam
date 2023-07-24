package com.softs.hn.ip.ipscam.ui.inicio;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanText {
    private String cleanText;
    private Context context;

    public CleanText(String texto, Context mcontext) {
        texto = texto.toUpperCase();
        texto = texto.replaceAll("\\s+", "");
        this.context = mcontext;

        cleaningText(texto);
    }

    private void cleaningText(String text) {
        text=text.toUpperCase();
        String pattern = "([A-Za-z]{3}\\d{4})";
        String txtResult = "";

        Pattern regexPattern = Pattern.compile(pattern, Pattern.DOTALL);
        Matcher matcher = regexPattern.matcher(text);

        if (matcher.find()) {
            txtResult = matcher.group(1);
        } else {
            text=text.replaceAll("O","0");
            matcher = regexPattern.matcher(text);
            if (matcher.find()) {
                txtResult = matcher.group(1).toUpperCase();
            } else {
                txtResult="";
                Toast.makeText(context, "NO SE ENCONTRO EL PATRON", Toast.LENGTH_SHORT).show();
            }
        }

        this.cleanText= txtResult;
    }

    public String getCleanText() {
        return cleanText;
    }

}
