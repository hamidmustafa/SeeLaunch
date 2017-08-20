package seelaunch.seelaunch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ZoomControls;

public class MainActivity_Seelaunch extends AppCompatActivity {
    ZoomControls zoom, zoom1;
    ImageView img;

    TextView title, txtview_speech, txtview_call_assistance, txtview_sms_assistance, txtview_browse_online;
    public static int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity__seelaunch);

        title = (TextView) findViewById(R.id.textView);
        txtview_browse_online = (TextView) findViewById(R.id.browse_online);
        txtview_call_assistance = (TextView) findViewById(R.id.call_assitance);
        txtview_sms_assistance = (TextView) findViewById(R.id.sms_assistance);
        txtview_speech = (TextView) findViewById(R.id.txtview_speech);

        final Button button_LN=(Button)findViewById(R.id.btn_lnassistance);
        final Button button_TextSpeech = (Button) findViewById(R.id.btn_textspeeching);
        final Button button_CallAssistance = (Button) findViewById(R.id.btn_quickcall);
        final Button button_browse = (Button) findViewById(R.id.btn_browse);


        zoom = (ZoomControls) findViewById(R.id.zoomControls1);

        zoom.setOnZoomInClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               // while (counter <= 3) {
                    float x_title = title.getScaleX();
                    float y_title = title.getScaleY();

                    float x_txtview_speech_text = txtview_speech.getScaleX();
                    float y_txtview_speech_text = txtview_speech.getScaleY();

                    float x_txtview_browse = txtview_browse_online.getScaleX();
                    float y_txtview_browse = txtview_browse_online.getScaleY();

                    float x_txtview_call = txtview_call_assistance.getScaleX();
                    float y_txtview_call = txtview_call_assistance.getScaleY();

                    float x_txtview_sms = txtview_sms_assistance.getScaleX();
                    float y_txtview_sms = txtview_sms_assistance.getScaleY();

                    float x_text_speeching = button_TextSpeech.getScaleX();
                    float y_text_speeching = button_TextSpeech.getScaleY();

                    float x_sms_assistance = button_LN.getScaleX();
                    float y_sms_assistance = button_LN.getScaleY();

                    float x_call_assistance = button_CallAssistance.getScaleX();
                    float y_call_assistance = button_CallAssistance.getScaleY();

                    float x_browse = button_browse.getScaleX();
                    float y_browse = button_browse.getScaleY();


                    txtview_call_assistance.setScaleX((float) (x_txtview_call + 0.1));
                    txtview_call_assistance.setScaleY((float) (y_txtview_call + 0.1));


                    txtview_sms_assistance.setScaleX((float) (x_txtview_sms + 0.1));
                    txtview_sms_assistance.setScaleY((float) (y_txtview_sms + 0.1));

                    txtview_browse_online.setScaleX((float) (x_txtview_browse + 0.1));
                    txtview_browse_online.setScaleY((float) (y_txtview_browse + 0.1));

                    txtview_speech.setScaleX((float) (x_txtview_speech_text + 0.1));
                    txtview_speech.setScaleY((float) (y_txtview_speech_text + 0.1));


                    title.setScaleX((float) (x_title + 0.1));
                    title.setScaleY((float) (y_title + 0.1));

                    button_TextSpeech.setScaleX((float) (x_text_speeching + 0.1));
                    button_TextSpeech.setScaleY((float) (y_text_speeching + 0.1));

                    button_CallAssistance.setScaleX((float) (x_call_assistance + 0.1));
                    button_CallAssistance.setScaleY((float) (y_call_assistance + 0.1));

                    button_browse.setScaleX((float) (x_browse + 0.1));
                    button_browse.setScaleY((float) (y_browse + 0.1));

                    button_LN.setScaleX((float) (x_sms_assistance + 0.1));
                    button_LN.setScaleY((float) (y_sms_assistance + 0.1));
                  //  zoom.setIsZoomInEnabled(true);
                  //  counter++;
              //  }


               // if(counter==4){
                //    zoom.setIsZoomInEnabled(false);

                  //  zoom.setIsZoomOutEnabled(true);

            }




         //   }


        });


        zoom.setOnZoomOutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                float x_title = title.getScaleX();
                float y_title = title.getScaleY();

                float x_txtview_speech_text = txtview_speech.getScaleX();
                float y_txtview_speech_text = txtview_speech.getScaleY();

                float x_txtview_browse = txtview_browse_online.getScaleX();
                float y_txtview_browse = txtview_browse_online.getScaleY();

                float x_txtview_call = txtview_call_assistance.getScaleX();
                float y_txtview_call = txtview_call_assistance.getScaleY();

                float x_txtview_sms = txtview_sms_assistance.getScaleX();
                float y_txtview_sms = txtview_sms_assistance.getScaleY();

                float x_text_speeching = button_TextSpeech.getScaleX();
                float y_text_speeching = button_TextSpeech.getScaleY();

                float x_sms_assistance = button_LN.getScaleX();
                float y_sms_assistance = button_LN.getScaleY();

                float x_call_assistance = button_CallAssistance.getScaleX();
                float y_call_assistance = button_CallAssistance.getScaleY();

                float x_browse = button_browse.getScaleX();
                float y_browse = button_browse.getScaleY();


                txtview_call_assistance.setScaleX((float) (x_txtview_call - 0.1));
                txtview_call_assistance.setScaleY((float) (y_txtview_call - 0.1));

                txtview_sms_assistance.setScaleX((float) (x_txtview_sms - 0.1));
                txtview_sms_assistance.setScaleY((float) (y_txtview_sms - 0.1));

                txtview_browse_online.setScaleX((float) (x_txtview_browse - 0.1));
                txtview_browse_online.setScaleY((float) (y_txtview_browse - 0.1));

                txtview_speech.setScaleX((float) (x_txtview_speech_text - 0.1));
                txtview_speech.setScaleY((float) (y_txtview_speech_text - 0.1));


                title.setScaleX((float) (x_title - 0.1));
                title.setScaleY((float) (y_title - 0.1));

                button_TextSpeech.setScaleX((float) (x_text_speeching - 0.1));
                button_TextSpeech.setScaleY((float) (y_text_speeching - 0.1));

                button_CallAssistance.setScaleX((float) (x_call_assistance - 0.1));
                button_CallAssistance.setScaleY((float) (y_call_assistance - 0.1));

                button_browse.setScaleX((float) (x_browse - 0.1));
                button_browse.setScaleY((float) (y_browse - 0.1));

                button_LN.setScaleX((float) (x_sms_assistance - 0.1));
                button_LN.setScaleY((float) (y_sms_assistance - 0.1));
               // zoom.setIsZoomOutEnabled(false);
                //zoom.setIsZoomInEnabled(true);
            }

        });



        button_LN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity_Seelaunch.this,TabView_Options_Messaging.class);
                startActivity(intent);
            }
        });



        button_TextSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity_Seelaunch.this,TabActivity_ForReading_Text_Contacts.class);
                startActivity(intent);
            }
        });

        button_CallAssistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity_Seelaunch.this,TabActivity_PhoneCalling.class);
                startActivity(intent);
            }
        });


        button_browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity_Seelaunch.this,WebBrowser.class);
                startActivity(intent);
            }
        });
    }
}
