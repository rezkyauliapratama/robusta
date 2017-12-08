package android.rezkyauliapratama.id.robusta.controller.activity;

import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

/**
 * Created by Rezky Aulia Pratama on 12/8/2017.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkAppPermission();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(3));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}