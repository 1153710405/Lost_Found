package com.example.a481.lostfound;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class ItemActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_item);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Item item = bundle.getParcelable("item");
    }
}
