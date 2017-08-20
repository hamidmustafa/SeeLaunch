package seelaunch.seelaunch;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.database.Cursor;
public class TabActivity_ForReading_Text_Contacts extends TabActivity {
    TabHost tabHost;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_activity__for_reading__text__contacts);
         tabHost=getTabHost();


        final TabHost.TabSpec Tab_UnReadSMS=tabHost.newTabSpec("Unread Messages");
        Tab_UnReadSMS.setIndicator("Unread Messages");
        Tab_UnReadSMS.setContent(new Intent(this, ListView_UnreadSMS.class));




        final TabHost.TabSpec Tab_AddContact=tabHost.newTabSpec("Phone Contacts");
        Tab_AddContact.setIndicator("Phone Contacts");
        Tab_AddContact.setContent(new Intent(this,ListView_Contacts.class));

        tabHost.addTab(Tab_UnReadSMS);
        tabHost.addTab(Tab_AddContact);


    }
}
