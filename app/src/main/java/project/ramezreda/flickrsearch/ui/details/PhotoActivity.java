package project.ramezreda.flickrsearch.ui.details;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import project.ramezreda.flickrsearch.R;
import project.ramezreda.flickrsearch.model.Photo;
import project.ramezreda.flickrsearch.utils.UrlBuilder;

public class PhotoActivity extends AppCompatActivity {
    public static final String EXTRA_PHOTO_KEY = "extra_photo_key";

    private ImageView mImageViewFullPhoto;

    private boolean mVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mVisible = true;
        mImageViewFullPhoto = findViewById(R.id.imageViewFullPhoto);

        loadPhoto();
    }

    private void loadPhoto() {
        Photo photo = getIntent().getExtras().getParcelable(EXTRA_PHOTO_KEY);
        Glide.with(this).load(UrlBuilder.INSTANCE.buildBigSizePhotoUrl(photo)).into(mImageViewFullPhoto);
        setTitle(photo.getTitle());
    }
}