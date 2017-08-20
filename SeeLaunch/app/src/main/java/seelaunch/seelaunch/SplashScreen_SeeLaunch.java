package seelaunch.seelaunch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.widget.TextView;
public class SplashScreen_SeeLaunch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen__see_launch);
        getSupportActionBar().hide();
        TextView textViewSplash=(TextView)findViewById(R.id.textView_splash);
        textViewSplash.setGravity(Gravity.CENTER);
        new CountDownTimer(3000,1000) {

            @Override
            public void onFinish() {
                Intent intent = new Intent(getBaseContext(), MainActivity_Seelaunch.class);
                startActivity(intent);
                finish();

            }


            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();
    }
}
