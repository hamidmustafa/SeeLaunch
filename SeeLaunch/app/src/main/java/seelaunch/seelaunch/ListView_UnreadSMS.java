package seelaunch.seelaunch;

import android.database.Cursor;
import android.net.Uri;
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

public class ListView_UnreadSMS extends AppCompatActivity {
    Cursor CursorForSMS;
    Uri SMSUri;
    String AddressOfSMS;
    String BodyOfSMS;
    String Name;
    String Number;

    ArrayAdapter<String> arrayAdapter_UnreadSMS;
    public ArrayList<String> ArrayList_UnreadSMS;
    ArrayList<String>  ArrayList_Name;

    ArrayList<String>  ArrayList_UnReadMessageName;
    ArrayList<String>  ArrayList_UnReadMessageNumber;
    ArrayList<String>  ArrayList_UnReadMessageBody;
    ArrayList<String>  ArrayList_Number;
    Cursor CursorForNameNumber;

    ListView ListView_UnreadSMS;
    ArrayList<String>  ArrayList_SpeakingName;
    ArrayList<String>  ArrayList_SpeakingMessage;
    TextToSpeech textToSpeech;
    double value=0.7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view__unread_sms);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR)
                    textToSpeech.setLanguage(Locale.US);
                textToSpeech.setSpeechRate((float) value);


            }
        });
        ListView_UnreadSMS=(ListView)findViewById(R.id.listView_UnreadSMS);
        ArrayList_UnreadSMS=new ArrayList<String>();
        ArrayList_Name=new ArrayList<String>();
        ArrayList_Number=new ArrayList<String>();
        ArrayList_UnReadMessageName=new ArrayList<String>();
        ArrayList_UnReadMessageNumber=new ArrayList<String>();
        ArrayList_UnReadMessageBody=new ArrayList<String>();

        ArrayList_SpeakingName=new ArrayList<String>();
        ArrayList_SpeakingMessage=new ArrayList<String>();





        SMSUri=Uri.parse("content://sms/inbox");
        CursorForSMS=getContentResolver().query(SMSUri,null,"read = 0",null,null);
        CursorForNameNumber = getContentResolver().query(ContactsContract.
                CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);


        while(CursorForNameNumber.moveToNext())
        {

            Name=CursorForNameNumber.getString(CursorForNameNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            ArrayList_Name.add(Name);
            for(int i=0;i<ArrayList_Name.size();i++)
                Number=CursorForNameNumber.getString(CursorForNameNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            ArrayList_Number.add(Number);


        }
        CursorForNameNumber.close();

        if(CursorForSMS!=null && CursorForSMS.getCount()>0)
        {

            while(CursorForSMS.moveToNext())
            {
                AddressOfSMS=CursorForSMS.getString(CursorForSMS.getColumnIndex("address"));
                BodyOfSMS=CursorForSMS.getString(CursorForSMS.getColumnIndex("body"));


                for(int j=0; j<ArrayList_Number.size();j++)
                { String gett=ArrayList_Number.get(j);
                    String trimmed=gett.substring(1);

                    if(AddressOfSMS.equals("+92"+trimmed))

                    {
                        ArrayList_SpeakingName.add(ArrayList_Name.get(j));
                        ArrayList_SpeakingMessage.add(BodyOfSMS);
                        ArrayList_UnreadSMS.add("Name: "+ArrayList_Name.get(j)+"\n"+"Contact#: "+ AddressOfSMS+"\n\n"+"Message: "+BodyOfSMS);
                        break;
                    }

                    else if(AddressOfSMS.equals(gett))

                    {
                        ArrayList_SpeakingName.add(ArrayList_Name.get(j));
                        ArrayList_SpeakingMessage.add(BodyOfSMS);
                        ArrayList_UnreadSMS.add("Name: "+ArrayList_Name.get(j)+"\n"+"Contact#: "+ AddressOfSMS+"\n\n"+"Message: "+BodyOfSMS);
                        break;
                    }
             /*
                    else

                     {
                         ArrayList_UnreadSMS.add("Number not saved"+"\n"+"Contact#: "+ AddressOfSMS+"\n\n"+"Message: "+BodyOfSMS);
                         break;
                     }*/

                }



                //   ArrayList_UnreadSMS.add(AddressOfSMS +"\n" + "\n" + BodyOfSMS);


            }
            CursorForSMS.close();

            arrayAdapter_UnreadSMS=new ArrayAdapter<String>(this,R.layout.simplerow,R.id.rowTextView_UnreadContacts,ArrayList_UnreadSMS);
            ListView_UnreadSMS.setAdapter(arrayAdapter_UnreadSMS);
        }

        else
        {
            Toast.makeText(getBaseContext(), "Sorry! there are no UNREAD SMS from your contacts", Toast.LENGTH_SHORT).show();
        }


        ListView_UnreadSMS.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                textToSpeech.speak(ArrayList_SpeakingName.get(position) +"is saying that   " +ArrayList_SpeakingMessage.get(position), TextToSpeech.QUEUE_FLUSH, null);

                return false;
            }
        });
    }
}
