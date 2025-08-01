package com.example.librarynovel;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;

public class youandme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_youandme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.youandmemain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TextView isiceritayouandme = findViewById(R.id.isiceritayouandme);

// Ambil teks cerita dari strings.xml
        String cerita = getString(R.string.cerita_you_and_me);
        isiceritayouandme.setText(cerita);

    }
}