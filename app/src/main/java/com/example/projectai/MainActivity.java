package com.example.projectai;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

public class MainActivity extends AppCompatActivity {
    ImageButton image;
    Button button;
    TextView text1, text2, text5;
    ImageView smileEmoji, jollyEmoji, sadEmoji;
    private FaceDetector faceDetector;
    private Bitmap capturedBitmap; // ✅ store the captured image

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);
       /* button = findViewById(R.id.button);*/
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text5 = findViewById(R.id.text5);
        smileEmoji = findViewById(R.id.happyid);
        jollyEmoji = findViewById(R.id.jollyid);
        sadEmoji = findViewById(R.id.sadid);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //noinspection deprecation
                startActivityForResult(open_camera, 100);
            }
        });

        FaceDetectorOptions options =
                new FaceDetectorOptions.Builder()
                        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                        .build();
        faceDetector = FaceDetection.getClient(options);

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (capturedBitmap != null) {
                    analyzeImage(capturedBitmap); // ✅ use stored image
                } else {
                    Toast.makeText (MainActivity.this, "Please take a photo first", Toast.LENGTH_SHORT).show ( );
                }
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            capturedBitmap = (Bitmap) data.getExtras().get("data"); // ✅ store the image
            image.setImageBitmap(capturedBitmap);
            text5.setVisibility(View.INVISIBLE);
            // text1.setVisibility(View.INVISIBLE); // Keep text1 visible initially or set it based on analysis
            // text2.setVisibility(View.INVISIBLE); // Keep text2 visible initially or set it based on analysis
            // Clear previous results
            text1.setVisibility(View.VISIBLE); // Ensure text1 is visible to show "Face Analysis:" or "No faces detected"
            text2.setVisibility(View.VISIBLE); // Ensure text2 is visible for analysis results
            text1.setText("");
            text2.setText("");
            if (smileEmoji != null) smileEmoji.setVisibility(View.INVISIBLE);
            if (jollyEmoji != null) jollyEmoji.setVisibility(View.INVISIBLE);
            if (sadEmoji != null) sadEmoji.setVisibility(View.INVISIBLE);
            analyzeImage(capturedBitmap); // ✅ Analyze image immediately after capture
        }
    }

    private void analyzeImage(Bitmap bitmap) {
        InputImage image = InputImage.fromBitmap(bitmap, 0);
        faceDetector.process(image)
                .addOnSuccessListener(
                        faces -> {
                            if (faces.isEmpty()) {
                                text1.setText("No faces detected");
                                text2.setText("");
                                if (smileEmoji != null) smileEmoji.setVisibility(View.INVISIBLE);
                                if (jollyEmoji != null) jollyEmoji.setVisibility(View.INVISIBLE);
                                if (sadEmoji != null) sadEmoji.setVisibility(View.INVISIBLE);
                                return;
                            }

                            Face face = faces.get(0);
                            StringBuilder resultBuilder = new StringBuilder();

                            if (face.getSmilingProbability() != null) {
                                float smileProb = face.getSmilingProbability();
                                boolean isSmiling = smileProb > 0.5;
                                resultBuilder.append("Smiling: ").append(isSmiling ? "Yes" : "No").append("\n");

                                if (isSmiling) {
                                    if (smileProb > 1.1) {
                                        if (jollyEmoji != null) jollyEmoji.setVisibility(View.VISIBLE);
                                        if (smileEmoji != null) smileEmoji.setVisibility(View.INVISIBLE);
                                        if (sadEmoji != null) sadEmoji.setVisibility(View.INVISIBLE);
                                    } else { // Covers smileProb between 0.3 and 0.7 (inclusive of 0.5 to 0.7)
                                        if (smileEmoji != null) smileEmoji.setVisibility(View.VISIBLE);
                                        if (jollyEmoji != null) jollyEmoji.setVisibility(View.INVISIBLE);
                                        if (sadEmoji != null) sadEmoji.setVisibility(View.INVISIBLE);
                                    }
                                } else { // Covers smileProb <= 0.5
                                    if (smileEmoji != null) smileEmoji.setVisibility(View.INVISIBLE);
                                    if (jollyEmoji != null) jollyEmoji.setVisibility(View.INVISIBLE);
                                    if (sadEmoji != null) sadEmoji.setVisibility(View.VISIBLE);
                                }
                            }
                            else {
                                // If smiling probability is not available, assume not smiling and show sad emoji
                                if (smileEmoji != null) smileEmoji.setVisibility(View.INVISIBLE);
                                if (jollyEmoji != null) jollyEmoji.setVisibility(View.INVISIBLE);
                                if (sadEmoji != null) sadEmoji.setVisibility(View.VISIBLE);
                            }

                            if (face.getRightEyeOpenProbability() != null) {
                                float rightEyeOpenProb = face.getRightEyeOpenProbability();
                                resultBuilder.append("Right Eye Open: ").append(rightEyeOpenProb > 0.5 ? "Yes" : "No").append("\n");
                            }

                            if (face.getLeftEyeOpenProbability() != null) {
                                float bnleftEyeOpenProb = face.getLeftEyeOpenProbability();
                                resultBuilder.append("Left Eye Open: ").append(bnleftEyeOpenProb > 0.5 ? "Yes" : "No").append("\n");
                            }

                            text1.setText("Face Analysis:");
                            text2.setText(resultBuilder.toString());
                            text1.setVisibility(View.VISIBLE);
                            text2.setVisibility(View.VISIBLE);
                        })
                .addOnFailureListener(e -> text1.setText("Face detection failed: " + e.getMessage()));
    }
}
