package project.ramezreda.flickrsearch.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Objects;

import project.ramezreda.flickrsearch.R;
import project.ramezreda.flickrsearch.model.Photo;
import project.ramezreda.flickrsearch.utils.UrlBuilder;

public class PhotoActivity extends AppCompatActivity {
    public static final String EXTRA_PHOTO_KEY = "extra_photo_key";

    private ImageView mImageViewFullPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        mImageViewFullPhoto = findViewById(R.id.imageViewFullPhoto);

        loadPhoto();
    }

    private void loadPhoto() {
        Intent intent = getIntent();
        Photo photo = Objects.requireNonNull(intent.getExtras()).getParcelable(EXTRA_PHOTO_KEY);
        assert photo != null;
        Glide.with(this).load(UrlBuilder.INSTANCE.buildBigSizePhotoUrl(photo)).into(mImageViewFullPhoto);
        setTitle(photo.getTitle());
    }
}