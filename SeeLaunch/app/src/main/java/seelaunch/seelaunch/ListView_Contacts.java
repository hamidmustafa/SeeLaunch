package seelaunch.seelaunch;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Toast;


import java.util.ArrayList;
import java.util.Locale;

public class ListView_Contacts extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter_Contacts;
    public ArrayList<String> ArrayList_UnreadSMS;
    ArrayList<String>  ArrayList_Name;
    ArrayList<String>  ArrayList_Number;
    ArrayList<String>  ArrayList_NameNumber;
    Cursor CursorForNameNumber;

    ListView ListView_Contacts;
    TextToSpeech textToSpeech;
    double value=0.7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view__contacts);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR)
                    textToSpeech.setLanguage(Locale.UK);

                textToSpeech.setSpeechRate((float) value);

            }
        });
        ListView_Contacts=(ListView)findViewById(R.id.listView_Contacts);
        ArrayList_UnreadSMS=new ArrayList<String>();
        ArrayList_Name=new ArrayList<String>();
        ArrayList_Number=new ArrayList<String>();
        ArrayList_NameNumber=new ArrayList<String>();






        CursorForNameNumber = getContentResolver().query(ContactsContract.
                CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);

        while(CursorForNameNumber.moveToNext())
        {                String Name=CursorForNameNumber.getString(CursorForNameNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String Number=CursorForNameNumber.getString(CursorForNameNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            ArrayList_Name.add(Name);
            ArrayList_Number.add(Number);
            ArrayList_NameNumber.add("Name: "+Name+"\n"+"Phone#: "+Number);


        }
        CursorForNameNumber.close();



        if(CursorForNameNumber!=null)
        {
            arrayAdapter_Contacts=new ArrayAdapter<String>(this,R.layout.simplerow,R.id.rowTextView_UnreadContacts,ArrayList_NameNumber);
            ListView_Contacts.setAdapter(arrayAdapter_Contacts);
        }

        else
        {
            Toast.makeText(getBaseContext(), "Sorry Cursor is NUll", Toast.LENGTH_SHORT).show();
        }


        ListView_Contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                textToSpeech.speak(ArrayList_Name.get(position)+"Phone number is"  +ArrayList_Number.get(position), TextToSpeech.QUEUE_FLUSH, null);


            }


        });
    }
}
