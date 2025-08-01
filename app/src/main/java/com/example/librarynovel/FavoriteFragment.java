package com.example.librarynovel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class FavoriteFragment extends Fragment {

    private LinearLayout containerLayout;
    private SharedPreferences preferences;

    private static final String PREF_NAME = "NovelPrefs";
    private static final String KEY_SENJA = "fav_senja";
    private static final String KEY_PARAREL = "fav_pararel";
    private static final String KEY_UNM = "fav_unm";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        containerLayout = rootView.findViewById(R.id.favoriteContainer);

        preferences = requireContext().getSharedPreferences(PREF_NAME, 0);

        loadFavoriteCards();

        return rootView;
    }

    private void loadFavoriteCards() {
        if (preferences.getBoolean(KEY_SENJA, false)) {
            addFavoriteCard("Sahabat Senja", R.drawable.sahabatsenja, sahabatsenja.class);
        }

        if (preferences.getBoolean(KEY_PARAREL, false)) {
            addFavoriteCard("Paralel Naif", R.drawable.paralelnaif, paralelnaif.class);
        }

        if (preferences.getBoolean(KEY_UNM, false)) {
            addFavoriteCard("You and Me", R.drawable.imageunm, youandme.class);
        }
    }

    private void addFavoriteCard(String title, int imageResId, Class<?> targetActivity) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        CardView card = (CardView) inflater.inflate(R.layout.item_favorite_card, null);

        ImageView image = card.findViewById(R.id.imageNovel);
        TextView text = card.findViewById(R.id.titleNovel);
        ImageButton favBtn = card.findViewById(R.id.btnFavToggle);

        image.setImageResource(imageResId);
        text.setText(title);

        favBtn.setImageResource(R.drawable.ic_favorite);
        favBtn.setEnabled(false); // nonaktifkan tombol favorit

        // Saat CardView diklik, buka Activity
        card.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), targetActivity);
            startActivity(intent);
        });

        containerLayout.addView(card);
    }
}
