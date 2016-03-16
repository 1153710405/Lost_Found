package com.example.a481.lostfound;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.listener.SaveListener;

public class PublishActivity extends Activity implements View.OnClickListener {

    private Button button;

    private EditText editText1;

    private EditText editText2;

    private EditText editText3;

    private EditText editText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_publish);
        button = (Button) findViewById(R.id.Enter_Publish);
        editText1 = (EditText) findViewById(R.id.Kind);
        editText2 = (EditText) findViewById(R.id.Time);
        editText3 = (EditText) findViewById(R.id.Place);
        editText4 = (EditText) findViewById(R.id.Description);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Enter_Publish :
                final LostFound_Information Information = new LostFound_Information();
                Information.setKind(editText1.getText().toString());
                Information.setTime(editText2.getText().toString());
                Information.setPlace(editText3.getText().toString());
                Information.setDescription(editText4.getText().toString());

                Information.save(this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(PublishActivity.this, "添加数据成功，返回objectId为："+Information.getObjectId(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        Toast.makeText(PublishActivity.this, "创建数据失败：" + msg, Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            default:
                break;
        }
    }
}
