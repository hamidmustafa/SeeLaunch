package seelaunch.seelaunch;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.location.Location;

import java.io.IOException;
import java.util.List;
import android.telephony.SmsManager;


public class StoreNumber extends AppCompatActivity
{
    private double Latitude_Value;
    private double Longitude_Value;
    private List<Address> list;
    public Address address;
    public String Address_Converted;
    public Geocoder gecoder;
    public String PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_number);
        final EditText  PhoneNumber_EditText=(EditText)findViewById(R.id.editText_PhoneNumber);
        Button    SendMessage_Button=(Button)findViewById(R.id.btn_Contact);
        gecoder= new Geocoder(this);
        SendMessage_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                String check=PhoneNumber_EditText.getText().toString();
                if (check.matches(""))

                {   final AlertDialog.Builder alertDialog_ChooseImage=new AlertDialog.Builder(StoreNumber.this);
                    alertDialog_ChooseImage.setTitle("Sorry");
                    alertDialog_ChooseImage.setMessage("Enter phone number first");
                    alertDialog_ChooseImage.setPositiveButton("Ok", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {}
                    });
                    alertDialog_ChooseImage.show();
                }

                else

                {
                    PhoneNumber = PhoneNumber_EditText.getText().toString();

                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    Latitude_Value = location.getLatitude();
                    Longitude_Value = location.getLongitude();

                    try {
                        list = gecoder.getFromLocation(Latitude_Value, Longitude_Value, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (list != null) {
                        address = list.get(0);
                        Address_Converted = address.getAddressLine(0) + ", " + address.getLocality()+ ", " + address.getCountryName();
                    } else {
                        Toast.makeText(getBaseContext(), "Sorry Current Location Address not available right now", Toast.LENGTH_SHORT).show();
                    }


                    SmsManager.getDefault().sendTextMessage(PhoneNumber, null, "Latitude: " + String.valueOf(Latitude_Value) +
                            "\n" + "Longititude: " + String.valueOf(Longitude_Value) + "\n" + "Address: " + Address_Converted, null, null);

                    Toast.makeText(getBaseContext(), "Sent", Toast.LENGTH_SHORT).show();
                    PhoneNumber_EditText.setText("");

                }




            }
        });


    }
}
