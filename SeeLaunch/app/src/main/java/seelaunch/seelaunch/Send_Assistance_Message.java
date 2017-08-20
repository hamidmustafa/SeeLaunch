package seelaunch.seelaunch;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.telephony.SmsManager;
import java.io.IOException;
import java.util.List;


public class Send_Assistance_Message extends AppCompatActivity {

    Button  ImmediateAssistanceButton;
    private double Latitude_Value;
    private double Longitude_Value;
    private List<Address> list;
    public Address address;
    public String Address_Converted;
    public Geocoder gecoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_send__assistance__message);

        gecoder= new Geocoder(this);
        ImmediateAssistanceButton=(Button)findViewById(R.id.btn_immediateassistance);

        ImmediateAssistanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBData_SeeLaunch.getCursorData();
                DBData_SeeLaunch.LoopingData();
                int size = DBData_SeeLaunch.ArrayList_PhoneNumber.size();

                if(size==0)
                {
                    final AlertDialog.Builder alertDialog_ChooseImage=new AlertDialog.Builder(Send_Assistance_Message.this);
                    alertDialog_ChooseImage.setTitle("Sorry");
                    alertDialog_ChooseImage.setMessage("No contact stored in app contact list");
                    alertDialog_ChooseImage.setPositiveButton("Ok", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {}
                    });
                    alertDialog_ChooseImage.show();}

                else
                {
                    if (size >= 5) {
                    for (int i = 0; i < 5; i++) {
                        String number = DBData_SeeLaunch.ArrayList_PhoneNumber.get(i);
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
                        SmsManager.getDefault().sendTextMessage(number, null, "Latitude: " + String.valueOf(Latitude_Value) +
                                "\n" + "Longititude: " + String.valueOf(Longitude_Value) + "\n" + "Address: " + Address_Converted, null, null);
                    }
                    Toast.makeText(getBaseContext(), "Sent", Toast.LENGTH_SHORT).show();
                }

                else if(size<5)
                { for (int i = 0; i < size; i++) {
                    String number = DBData_SeeLaunch.ArrayList_PhoneNumber.get(i);
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

                    SmsManager.getDefault().sendTextMessage(number, null, "I need help, my current location is:"+"\n"+"Latitude: " + String.valueOf(Latitude_Value) +
                            "\n" + "Longititude: " + String.valueOf(Longitude_Value) + "\n" + "Address: " + Address_Converted, null, null);


                }
                    Toast.makeText(getBaseContext(), "Assistance message sent to 5 contacts", Toast.LENGTH_SHORT).show();}
            }
            }
        });

    }

}
