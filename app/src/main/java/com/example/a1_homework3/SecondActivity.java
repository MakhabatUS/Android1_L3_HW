package com.example.a1_homework3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class SecondActivity extends AppCompatActivity {
    private ImageView chooseImage;
    private EditText typeText;
    private Button sendData;

    static final String SEND_TEXT = "sendText";
    static final String SEND_PIC = "sendPic";
    static final int PICK_IMAGE = 1;
    static Uri uriOfImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        chooseImage = findViewById(R.id.iv_chooseImage);
        typeText = findViewById(R.id.et_sendText);
        sendData = findViewById(R.id.btn_sendTheData);

        chooseImage.setOnClickListener(view -> {
            Intent gallery = new Intent();
            gallery.setType("image/*");
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(gallery, "Select an Image"), PICK_IMAGE);
        });


        sendData.setOnClickListener(view -> {
            Intent backToMainAct = new Intent(SecondActivity.this, MainActivity.class);
            backToMainAct.putExtra(SEND_TEXT, typeText.getText().toString());
            backToMainAct.putExtra(SEND_PIC, String.valueOf(chooseImage));
            startActivity(backToMainAct);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            uriOfImage = data.getData();
            chooseImage.setImageURI(uriOfImage);
        }
    }
}





