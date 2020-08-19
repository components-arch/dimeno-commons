package com.dimeno.commons.sample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dimeno.commons.annotation.DoubleClick;
import com.dimeno.commons.net.NetUtils;
import com.dimeno.commons.ui.AppUtils;

/**
 * MainActivity
 * Created by wangzhen on 2020/8/19.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppUtils.install(getApplication());
        String hostIP = NetUtils.getHostIP();
    }

    @DoubleClick
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_double_click:
                Log.e("TAG", "-> click");
                break;
        }
    }
}