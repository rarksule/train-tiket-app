package com.rarksule.train_ticket;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BookedTicket extends AppCompatActivity {
    String ID;
    TextView Idtv;
    ImageView imageView;
    Button CopyId;
    int width = 300;
    int height = 300;
    Bitmap qrCodeBitmap;
    // Create bitmap from QR code image
    Bitmap bitmap; // your QR code bitmap here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_ticket);

        Idtv = findViewById(R.id.TicketID);
        CopyId = findViewById(R.id.CopyID);
        Intent intent = getIntent();
        ID = intent.getStringExtra("Ticket Id");
        Idtv.setText(ID);
        qrCodeBitmap = generateQRCode(ID, width, height);
        imageView = findViewById(R.id.qr_code_image);
        imageView.setImageBitmap(qrCodeBitmap);
        CopyId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyId();

            }
        });

    }

    private void copyId() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("id",ID);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(BookedTicket.this,"ID copied to clipboard",Toast.LENGTH_SHORT).show();
    }

    private Bitmap generateQRCode(String data, int width, int height) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
            int matrixWidth = bitMatrix.getWidth();
            int matrixHeight = bitMatrix.getHeight();
            int[] pixels = new int[matrixWidth * matrixHeight];
            for (int y = 0; y < matrixHeight; y++) {
                int offset = y * matrixWidth;
                for (int x = 0; x < matrixWidth; x++) {
                    pixels[offset + x] = bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE;
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(matrixWidth, matrixHeight, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, matrixWidth, matrixHeight);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }



}