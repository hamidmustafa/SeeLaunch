package seelaunch.seelaunch;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ZoomButtonsController;

import pl.polidea.view.ZoomView;


public class AddNew_ContactForCall extends AppCompatActivity {
    public static String NameForDB1;
    public static String PhoneNumberForDB1;
    EditText editTextName1;
    EditText editTextPNumber1;
    Button SaveContactButton1;
    ContentValues contentValues1;
    Button ViewContactListButton1;
    private ZoomView zoomView;
    private RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new__contact_for_call);
        SaveContactButton1=(Button) findViewById(R.id.btn_SaveContact);
        ViewContactListButton1=(Button)  findViewById(R.id.btn_contactlist);

        editTextName1= (EditText)findViewById(R.id.editText_Name);
        editTextPNumber1=(EditText)findViewById(R.id.editText_pnumber);

        NameForDB1=editTextName1.getText().toString();
        PhoneNumberForDB1=editTextPNumber1.getText().toString();

        SaveContactButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NameForDB1 = editTextName1.getText().toString();
                PhoneNumberForDB1 = editTextPNumber1.getText().toString();

                if(NameForDB1.matches("")|| PhoneNumberForDB1.matches(""))
                {  final AlertDialog.Builder alertDialog_ChooseImage=new AlertDialog.Builder(AddNew_ContactForCall.this);
                    // alertDialog_ChooseImage.setIcon(R.drawable.sadsmily_blue_96px);
                    alertDialog_ChooseImage.setTitle("Sorry");
                    alertDialog_ChooseImage.setMessage("Number or Name is empty");
                    alertDialog_ChooseImage.setPositiveButton("Ok", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {}
                    });
                    alertDialog_ChooseImage.show(); }
                else
                {

                    contentValues1 = new ContentValues();
                    contentValues1.put(DBData_SeeLaunch.FIELD_NAME_Call, NameForDB1);
                    contentValues1.put(DBData_SeeLaunch.FIELD_PHONENUMBER_Call, PhoneNumberForDB1);
                    getContentResolver().insert(ContentProviderClassForPhoneCallDetails.CONTENT_URI_CONTACTDETAILS_PhoneCall, contentValues1);
                    Toast.makeText(AddNew_ContactForCall.this,"Contact saved",Toast.LENGTH_SHORT).show();
                    editTextName1.setText("");
                    editTextPNumber1.setText("");
                }
            }
        });

        ViewContactListButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBData_SeeLaunch.getCursorDataPhoneCalls();
                DBData_SeeLaunch.LoopingDataPhoneCall();
                int size=DBData_SeeLaunch.ArrayList_Name_Call.size();
                if(size==0)
                {final AlertDialog.Builder alertDialog_ChooseImage=new AlertDialog.Builder(AddNew_ContactForCall.this);
                    alertDialog_ChooseImage.setTitle("Sorry");
                    alertDialog_ChooseImage.setMessage("No contact is stored in app");
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
                    Intent intent=new Intent(AddNew_ContactForCall.this,ListView_CallContacts.class);
                    startActivity(intent);
                }
            }
        });



    }
}

