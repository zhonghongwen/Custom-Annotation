package utils.utilcode.blankj.com.annotation;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import utils.utilcode.blankj.com.annotation.impl.Animal;
import utils.utilcode.blankj.com.annotation.inject.Inject;
import utils.utilcode.blankj.com.annotation.inject.InjectView;
import utils.utilcode.blankj.com.annotation.inject.onClick;
import utils.utilcode.blankj.com.annotation.utils.InjectUtils;


public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.bind_view_btn)
    Button mBindView;
    @Inject
    Animal mAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtils.injectView(this);
        InjectUtils.injectEvent(this);
        InjectUtils.inject(this);

//        Log.e("MainActivity", mBindView.toString());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                SystemClock.sleep(500);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.e("MainActivity", mBindView.toString());
//
//                    }
//                });
//            }
//        }).start();

//        mBindView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "1111111111", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @onClick({R.id.button})
    public void onclick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (view.getId()) {
            case R.id.button:
                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void test(View v) {
        Toast.makeText(this, mAnimal.fly(), Toast.LENGTH_SHORT).show();
    }
}
