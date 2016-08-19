package cn.andaction.loadinglayyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_preload).setOnClickListener(this);
        findViewById(R.id.btn_responsive).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_preload:
                intent = new Intent(this, PreloadLayerActivity.class);
                break;
            case R.id.btn_responsive:
                intent = new Intent(this, ResponsiveLayoutActivity.class);
                break;
        }
        startActivity(intent);
    }
}
