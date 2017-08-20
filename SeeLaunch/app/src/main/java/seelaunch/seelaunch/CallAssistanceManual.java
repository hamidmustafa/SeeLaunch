package seelaunch.seelaunch;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class CallAssistanceManual extends AppCompatActivity {


    String PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_call_assistance_manual);



        final EditText PhoneNumber_EditText = (EditText) findViewById(R.id.editText_PhoneNumberForCall);
        Button CallButton = (Button) findViewById(R.id.btn_Contact);

        CallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String check = PhoneNumber_EditText.getText().toString();
                if (check.matches(""))

                {
                    final AlertDialog.Builder alertDialog_ChooseImage = new AlertDialog.Builder(CallAssistanceManual.this);
                    alertDialog_ChooseImage.setTitle("Sorry");
                    alertDialog_ChooseImage.setMessage("Enter phone number first");
                    alertDialog_ChooseImage.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog_ChooseImage.show();
                } else

                {
                    PhoneNumber = PhoneNumber_EditText.getText().toString();
                    Intent intent1 = new Intent(Intent.ACTION_CALL);
                    intent1.setData(Uri.parse("tel:" + PhoneNumber));
                    if (ActivityCompat.checkSelfPermission(CallAssistanceManual.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent1);

                    PhoneNumber_EditText.setText("");

                }




            }
        });
    }
}
