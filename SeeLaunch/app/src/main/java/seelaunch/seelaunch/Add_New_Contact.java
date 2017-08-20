package seelaunch.seelaunch;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;

public class Add_New_Contact extends AppCompatActivity {
    public static String NameForDB;
    public static String PhoneNumberForDB;
    EditText editTextName;
    EditText editTextPNumber;
    Button SaveContactButton;
    ContentValues contentValues;
    Button ViewContactListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__new__contact);


        SaveContactButton=(Button) findViewById(R.id.btn_SaveContact);
        ViewContactListButton=(Button)  findViewById(R.id.btn_contactlist);

         editTextName= (EditText)findViewById(R.id.editText_Name);
        editTextPNumber=(EditText)findViewById(R.id.editText_pnumber);
        NameForDB=editTextName.getText().toString();
        PhoneNumberForDB=editTextPNumber.getText().toString();

         SaveContactButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 NameForDB = editTextName.getText().toString();
                 PhoneNumberForDB = editTextPNumber.getText().toString();

                 if(NameForDB.matches("")|| PhoneNumberForDB.matches(""))
                 {  final AlertDialog.Builder alertDialog_ChooseImage=new AlertDialog.Builder(Add_New_Contact.this);
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
                 else {

                     contentValues = new ContentValues();
                     contentValues.put(DBData_SeeLaunch.FIELD_NAME, NameForDB);
                     contentValues.put(DBData_SeeLaunch.FIELD_PHONENUMBER, PhoneNumberForDB);
                     getContentResolver().insert(ContentProviderClassForContactDetails.CONTENT_URI_CONTACTDETAILS, contentValues);
                     Toast.makeText(Add_New_Contact.this,"Contact saved",Toast.LENGTH_SHORT).show();
                     editTextName.setText("");
                     editTextPNumber.setText("");
                 }
             }
         });

        ViewContactListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBData_SeeLaunch.getCursorData();
                DBData_SeeLaunch.LoopingData();
                int size=DBData_SeeLaunch.ArrayList_Name.size();
                if(size==0)
                {final AlertDialog.Builder alertDialog_ChooseImage=new AlertDialog.Builder(Add_New_Contact.this);
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
                Intent intent=new Intent(Add_New_Contact.this,ListViewActivity.class);
                startActivity(intent);
                }
            }
        });


    }
}
