package seelaunch.seelaunch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import android.app.TabActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabHost.TabSpec;
import android.content.Intent;


public class TabActivity_PhoneCalling extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_activity__phone_calling);
        TabHost tabHost=getTabHost();


        TabHost.TabSpec Tab_ManualAssistance=tabHost.newTabSpec("Manual Call Assitance");
        Tab_ManualAssistance.setIndicator("Manual Call Assitance");
        Tab_ManualAssistance.setContent(new Intent(this,CallAssistanceManual.class));





        TabHost.TabSpec Tab_AddContact=tabHost.newTabSpec("Call App Contacts");
        Tab_AddContact.setIndicator("Call App Contacts");
        Tab_AddContact.setContent(new Intent(this,AddNew_ContactForCall.class));

        tabHost.addTab(Tab_ManualAssistance);

        tabHost.addTab(Tab_AddContact);
    }
}
