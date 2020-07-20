package com.example.dagger;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import javax.inject.Inject;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    /*
    By adding @Inject annotation, dagger2 can automatically create an instance
    from that object like our example Motor object in Vehicle class.
     */
    @Inject
    Vehicle vehicle;

    /*
    Dagger2 can automatically inject dependencies in constructors,
    but Android components (activities, fragments, etc.) are
    instantiated by Android framework which makes it difficult to use dependency injection on them,
    so we should inject them manually like below code:
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyComponent component = DaggerMyComponent.builder().myModule(new MyModule()).build();
        component.inject(this);

        ((TextView) findViewById(R.id.result)).setText(String.format("Speed: %s", vehicle.getSpeed()));
        new Thread(new Runnable() {
            public void run() {
                try {
                    while(true) {
                        sleep(1000);
                        MainActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                vehicle.increaseSpeed(60);
                                ((TextView) findViewById(R.id.result)).setText(String.format("Speed: %s", vehicle.getSpeed()));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}