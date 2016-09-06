package com.example.genie7.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class GuruDescriptionActivity extends AppCompatActivity {

    ImageView ivGuruprofile;
    TextView tvGuruname, tvGurudesc;
    private String ProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru_description);

        ivGuruprofile = (ImageView) findViewById(R.id.imageviewformuniji);
        tvGuruname = (TextView) findViewById(R.id.textviewtitleformunjiname);
        tvGurudesc = (TextView) findViewById(R.id.munijiDescription);


        Bundle bundle = getIntent().getExtras();

        tvGuruname.setText(bundle.getString("name"));
        tvGurudesc.setText(bundle.getString("description"));

        ProfileImage = getIntent().getStringExtra("image");


        try {
            Picasso.with(getApplicationContext()).load(ProfileImage).into(ivGuruprofile);
        } catch (Exception f) {
            f.printStackTrace();
        }
    }
}
