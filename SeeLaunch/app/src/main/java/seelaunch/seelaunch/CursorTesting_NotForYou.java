package seelaunch.seelaunch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class CursorTesting_NotForYou extends AppCompatActivity {
    Button ClickMe;
    TextView SeeData_TextView;
    int size_name_arraylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_testing__not_for_you);
        ClickMe=(Button)findViewById(R.id.btn_clickme);
        SeeData_TextView=(TextView)findViewById(R.id.textview_testdata);

        ClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
              DBData_SeeLaunch.getCursorData();
              DBData_SeeLaunch.LoopingData();
              size_name_arraylist=DBData_SeeLaunch.ArrayList_Name.size();
              for(int j=0; j<size_name_arraylist;j++)
              { SeeData_TextView.append( DBData_SeeLaunch.ArrayList_Name.get(j));
                  SeeData_TextView.append(":");
                  SeeData_TextView.append( DBData_SeeLaunch.ArrayList_PhoneNumber.get(j));
                  SeeData_TextView.append(" ");
              }




            }
        });
            }
}
