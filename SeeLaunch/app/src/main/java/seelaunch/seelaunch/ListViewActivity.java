package seelaunch.seelaunch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.telephony.SmsManager;

import seelaunch.seelaunch.ListAdapter.customButtonListener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

public class ListViewActivity extends AppCompatActivity implements customButtonListener {

    private ListView listView;
    ListAdapter adapter;
    ArrayList<String> ArrayListt_Name_Number = new ArrayList<String>();
    private double Latitude_Value;
    private double Longitude_Value;
    private List<Address> list;
    public Address address;
    public String Address_Converted;
    public Geocoder gecoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        gecoder = new Geocoder(this);
        listView = (ListView) findViewById(R.id.listView);
        DBData_SeeLaunch.getCursorData();
        DBData_SeeLaunch.LoopingData();
        final int size_namearraylist = DBData_SeeLaunch.ArrayList_Name.size();
        for (int i = 0; i < size_namearraylist; i++) {
            String name = String.valueOf(DBData_SeeLaunch.ArrayList_Name.get(i));
            String phonenumber = String.valueOf(DBData_SeeLaunch.ArrayList_PhoneNumber.get(i));
            ArrayListt_Name_Number.add(name + "\n" + phonenumber);


        }
        adapter = new ListAdapter(ListViewActivity.this, ArrayListt_Name_Number);
        adapter.setCustomButtonListner(ListViewActivity.this);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                DBData_SeeLaunch.getCursorData();
                DBData_SeeLaunch.LoopingData();
                String RetrievedName = DBData_SeeLaunch.ArrayList_Name.get(position);
                adapter.remove(ArrayListt_Name_Number.get(position));
                getContentResolver().delete(ContentProviderClassForContactDetails.CONTENT_URI_CONTACTDETAILS, DBData_SeeLaunch.FIELD_NAME + "=?", new String[]{RetrievedName});
                if (listView.getCount() == 0) {
                    Intent intent = new Intent(ListViewActivity.this, TabView_Options_Messaging.class);
                    startActivity(intent);
                }

                listView.setAdapter(adapter);

                return false;
            }
        });
    }

    @Override
    public void onButtonClickListner(int position, String value) {
        DBData_SeeLaunch.getCursorData();
        DBData_SeeLaunch.LoopingData();
        String number = DBData_SeeLaunch.ArrayList_PhoneNumber.get(position);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
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
        Toast.makeText(ListViewActivity.this, "Immediate assistance message sent to " + DBData_SeeLaunch.ArrayList_Name.get(position),Toast.LENGTH_SHORT).show();


    }


}
