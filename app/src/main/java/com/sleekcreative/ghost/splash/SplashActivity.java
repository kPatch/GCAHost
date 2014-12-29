package com.sleekcreative.ghost.splash;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sleekcreative.ghost.MainActivity;
import com.sleekcreative.ghost.R;

public class SplashActivity extends Activity {

    /**
     * Check if the app is running.
     */
    private boolean isRunning;
    private Animation anim;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        isRunning = true;
        startAnimations();
        startSplash();
    }

    /**
     * Starts the count down timer for 3-seconds. It simply sleeps the thread
     * for 3-seconds.
     */
    private void startSplash() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            doFinish();
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        doFinish();
        return super.onTouchEvent(event);
    }

    /**
     * If the app is still running than this method will start the MainActivity
     * and finish the Splash.
     */
    private synchronized void doFinish() {
        if (isRunning) {
            isRunning = false;
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isRunning = false;
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void startAnimations() {
        anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();

/*        LinearLayout l = (LinearLayout) findViewById(R.id.splash_layout);
        l.clearAnimation();
        l.startAnimation(anim);*/

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();

        TextView tv = (TextView) findViewById(R.id.logo);
        tv.clearAnimation();
        tv.startAnimation(anim);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy(){
        if(!anim.hasEnded()) {
            anim.cancel();
        }
        super.onDestroy();
    }
}