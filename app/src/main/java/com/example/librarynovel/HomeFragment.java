package com.example.librarynovel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private View rootView;
    private SharedPreferences preferences;

    private static final String PREF_NAME = "NovelPrefs";
    private static final String KEY_SENJA = "fav_senja";
    private static final String KEY_PARAREL = "fav_pararel";
    private static final String KEY_UNM = "fav_unm";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        preferences = requireContext().getSharedPreferences(PREF_NAME, 0);

        setupImageSlider();
        setupCardViewListeners();
        setupFavoriteButtons();
        setupLogoutButton();

        return rootView;
    }

    private void setupImageSlider() {
        ImageSlider imageSlider = rootView.findViewById(R.id.Imageslider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.imageunm, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.paralelnaif, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.sahabatsenja, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }

    private void setupCardViewListeners() {
        CardView cardSenja = rootView.findViewById(R.id.cardsahabatsenja);
        CardView cardParalel = rootView.findViewById(R.id.cardparalelnaif);
        CardView cardUnm = rootView.findViewById(R.id.cardunm);

        cardSenja.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), sahabatsenja.class);
            startActivity(intent);
        });

        cardParalel.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), paralelnaif.class);
            startActivity(intent);
        });

        cardUnm.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), youandme.class);
            startActivity(intent);
        });
    }

    private void setupFavoriteButtons() {
        ImageButton btnFavSenja = rootView.findViewById(R.id.btnFavSahabatSenja);
        ImageButton btnFavPararel = rootView.findViewById(R.id.btnFavparalelnaif);
        ImageButton btnFavUnm = rootView.findViewById(R.id.btnFavyouandme);

        setFavoriteState(btnFavSenja, KEY_SENJA);
        setFavoriteState(btnFavPararel, KEY_PARAREL);
        setFavoriteState(btnFavUnm, KEY_UNM);

        btnFavSenja.setOnClickListener(v -> toggleFavorite(btnFavSenja, KEY_SENJA));
        btnFavPararel.setOnClickListener(v -> toggleFavorite(btnFavPararel, KEY_PARAREL));
        btnFavUnm.setOnClickListener(v -> toggleFavorite(btnFavUnm, KEY_UNM));
    }

    private void setFavoriteState(ImageButton button, String key) {
        boolean isFavorite = preferences.getBoolean(key, false);
        button.setImageResource(isFavorite ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);
    }

    private void toggleFavorite(ImageButton button, String key) {
        boolean current = preferences.getBoolean(key, false);
        SharedPreferences.Editor editor = preferences.edit();

        if (current) {
            editor.putBoolean(key, false);
            button.setImageResource(R.drawable.ic_favorite_border);
        } else {
            editor.putBoolean(key, true);
            button.setImageResource(R.drawable.ic_favorite);
        }

        editor.apply();
    }

    private void setupLogoutButton() {
        MaterialButton btnLogout = rootView.findViewById(R.id.btnLogout);  // FIXED CAST
        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(requireContext(), "Logout berhasil", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(requireContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
