package seelaunch.seelaunch;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListView_CallContacts extends AppCompatActivity implements ListAdapter_ForCall.customButtonListener {
    private ListView ListView_CallContacts;
    ListAdapter_ForCall adapter;
    ArrayList<String> ArrayListt_Name_Number = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view=getLayoutInflater().inflate(R.layout.activity_list_view__call_contacts,null);

        //setContentView(R.layout.activity_call_assistance_manual);
        setContentView(view);












        ListView_CallContacts = (ListView) findViewById(R.id.listView_CallContacts);
        DBData_SeeLaunch.getCursorDataPhoneCalls();
        DBData_SeeLaunch.LoopingDataPhoneCall();
        final int size_namearraylist = DBData_SeeLaunch.ArrayList_Name_Call.size();
        for (int i = 0; i < size_namearraylist; i++) {
            String name = String.valueOf(DBData_SeeLaunch.ArrayList_Name_Call.get(i));
            String phonenumber = String.valueOf(DBData_SeeLaunch.ArrayList_PhoneNumber_Call.get(i));
            ArrayListt_Name_Number.add(name + "\n" + phonenumber);


        }
        adapter = new ListAdapter_ForCall(ListView_CallContacts.this, ArrayListt_Name_Number);
        adapter.setCustomButtonListner(ListView_CallContacts.this);
        ListView_CallContacts.setAdapter(adapter);






        ListView_CallContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                view.setOnClickListener(new View.OnClickListener() {
                    float zoomFactor = 1.5f;
                    boolean zoomedOut = false;

                    @Override
                    public void onClick(View v) {
                        if(zoomedOut) {
                            v.setScaleX(1);
                            v.setScaleY(1);
                            zoomedOut = false;
                        }
                        else
                        {
                            v.setScaleX(zoomFactor);
                            v.setScaleY(zoomFactor);
                            zoomedOut = true;
                        }
                    }
                });


            }

        });














        ListView_CallContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                DBData_SeeLaunch.getCursorDataPhoneCalls();
                DBData_SeeLaunch.LoopingDataPhoneCall();
                String RetrievedName = DBData_SeeLaunch.ArrayList_Name_Call.get(position);
                adapter.remove(ArrayListt_Name_Number.get(position));
                getContentResolver().delete(ContentProviderClassForPhoneCallDetails.CONTENT_URI_CONTACTDETAILS_PhoneCall, DBData_SeeLaunch.FIELD_NAME_Call + "=?", new String[]{RetrievedName});
                if (ListView_CallContacts.getCount() == 0) {
                    Intent intent = new Intent(ListView_CallContacts.this, TabView_Options_Messaging.class);
                    startActivity(intent);
                }

                ListView_CallContacts.setAdapter(adapter);

                return false;
            }
        });
    }

    @Override
    public void onButtonClickListner(int position, String value) {
        DBData_SeeLaunch.getCursorDataPhoneCalls();
        DBData_SeeLaunch.LoopingDataPhoneCall();
        String PhoneNumber = DBData_SeeLaunch.ArrayList_PhoneNumber_Call.get(position);
        Intent intent1 = new Intent(Intent.ACTION_CALL);
        intent1.setData(Uri.parse("tel:" + PhoneNumber));
        if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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

    }
}
