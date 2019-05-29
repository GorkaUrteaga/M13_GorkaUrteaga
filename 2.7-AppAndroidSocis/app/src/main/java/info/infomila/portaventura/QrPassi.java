package info.infomila.portaventura;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;

import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import static info.infomila.portaventura.PassisUsuari.CODI_PASSI;

public class QrPassi extends AppCompatActivity {

    private int codiPassi;
    private static ImageView imgQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_passi);

        imgQr = findViewById(R.id.imgQr);

        Intent i = getIntent();
        codiPassi = (int) i.getIntExtra(CODI_PASSI,0);

        if(codiPassi == 0){
            Toast.makeText(this, "No s'ha pogut generar el qr", Toast.LENGTH_LONG).show();
        }else{
            generateQRCodeImage(codiPassi+"");
        }

    }

    private static void generateQRCodeImage(String text){
        //QRCodeWriter qrCodeWriter = new QRCodeWriter();
        // Whatever you need to encode in the QR code
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imgQr.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

}
