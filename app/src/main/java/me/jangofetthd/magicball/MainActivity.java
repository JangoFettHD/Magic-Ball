package me.jangofetthd.magicball;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.yandex.metrica.YandexMetrica;

public class MainActivity extends AppCompatActivity {

    public static TextView text;
    public String[] db;
    private ShakeListener mShaker;
    public static Context mContext;

    //apprate
    public static String title;
    public static String message1;
    public static String message2;
    public static String rate;
    public static String remindLater;
    public static String dismiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        db= new String[]{MainActivity.mContext.getString(R.string.no), MainActivity.mContext.getString(R.string.change_urself), MainActivity.mContext.getString(R.string.ask_ur_mom), MainActivity.mContext.getString(R.string.no_doubts), MainActivity.mContext.getString(R.string.try_it), MainActivity.mContext.getString(R.string.find_a_job), MainActivity.mContext.getString(R.string.forget_it), MainActivity.mContext.getString(R.string.Tomorrow), MainActivity.mContext.getString(R.string.aspire_to_this), MainActivity.mContext.getString(R.string.yes), MainActivity.mContext.getString(R.string.today), MainActivity.mContext.getString(R.string.the_morning_is_wiser), MainActivity.mContext.getString(R.string.maybe), MainActivity.mContext.getString(R.string.have_a_rest), MainActivity.mContext.getString(R.string.think), MainActivity.mContext.getString(R.string.soon), MainActivity.mContext.getString(R.string.ask_in_another_time)};

        //apprate
        title = getResources().getString(R.string.apprate_title);
        message1 = getResources().getString(R.string.apprate_message_p1);
        message2 = getResources().getString(R.string.apprate_message_p2);
        rate = getResources().getString(R.string.apprate_rate);
        remindLater = getResources().getString(R.string.apprate_remindLater);
        dismiss = getResources().getString(R.string.apprate_dismiss);

        // Инициализация AppMetrica SDK
        YandexMetrica.activate(getApplicationContext(), "4878bdbe-3fa8-43eb-932e-83d9f98667d4");
        // Отслеживание активности пользователей
        YandexMetrica.enableActivityAutoTracking(getApplication());

        new AppRate(this)
                //.setMinDaysUntilPrompt(7)
                .setMinLaunchesUntilPrompt(5)
                //.setShowIfAppHasCrashed(false)
                .init();

        Toast.makeText(this, getString(R.string.intro), Toast.LENGTH_LONG).show();
        text = (TextView) findViewById(R.id.text);

        View.OnClickListener random = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rand();
            }
        };
        text.setOnClickListener(random);

        mShaker = new ShakeListener(this);
        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
            public void onShake()
            {
                rand();
            }
        });
    }

    public void rand(){
        final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        text.startAnimation(animation);
        text.setText(db[(int)(Math.random()*db.length)]);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mShaker.resume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mShaker.pause();
    }
}
