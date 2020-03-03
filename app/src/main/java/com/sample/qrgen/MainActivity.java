package com.sample.qrgen;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
    String TAG="GenerateQRCode";
    EditText text;
    ImageView qrcode;
    Button gen;
    Bitmap bitmap;
    String inputvalue;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qrcode=(ImageView)findViewById(R.id.qrcode);
        text=(EditText)findViewById(R.id.text);
        gen=(Button)findViewById(R.id.gen);
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputvalue=text.getText().toString().trim();
                if( inputvalue.length()>0 ) {
                    WindowManager manager=(WindowManager)getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    display.getSize(point);
                    int width=point.x;
                    int height=point.y;
                    int smallerdimension = width<height ? width:height;
                    qrgEncoder = new QRGEncoder(inputvalue, null, QRGContents.Type.TEXT, smallerdimension);

                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();
                        qrcode.setImageBitmap(bitmap);

                    }
                    catch (WriterException e) {

                        Log.v(TAG, e.toString());
                    }
                } else {
                    text.setError("Required");
                }


            }
        });
    }
}
