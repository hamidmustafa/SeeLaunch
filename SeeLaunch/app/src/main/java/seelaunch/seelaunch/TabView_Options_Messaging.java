package seelaunch.seelaunch;

import android.app.TabActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabHost.TabSpec;
import android.content.Intent;




public class TabView_Options_Messaging extends TabActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_tab_view__options__messaging);

        // TabHost tabHost=(TabHost)findViewById(R.id.tabhost);
        TabHost tabHost=getTabHost();


        TabSpec Tab_ManualAssistance=tabHost.newTabSpec("Manual Assistance");
        Tab_ManualAssistance.setIndicator("Manual Assistance");
        Tab_ManualAssistance.setContent(new Intent(this,StoreNumber.class));



        TabSpec Tab_ImmediateAssistance=tabHost.newTabSpec("Immediate Assistance");
        Tab_ImmediateAssistance.setIndicator("Immediate Assistance");
        Tab_ImmediateAssistance.setContent(new Intent(this,Send_Assistance_Message.class));


        TabSpec Tab_AddContact=tabHost.newTabSpec("Add Contacts");
        Tab_AddContact.setIndicator("Add Contact");
        Tab_AddContact.setContent(new Intent(this,Add_New_Contact.class));

        tabHost.addTab(Tab_ManualAssistance);
        tabHost.addTab(Tab_ImmediateAssistance);
        tabHost.addTab(Tab_AddContact);



    }
}
