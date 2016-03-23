package com.example.a481.lostfound;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    private BmobUser user;

    private EditText username;

    private EditText password1;

    private EditText password2;

    private EditText phonenumber;

    private Button ensure_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        username = (EditText) findViewById(R.id.edit_username);
        password1 = (EditText) findViewById(R.id.edit_password1);
        password2 = (EditText) findViewById(R.id.edit_password2);
        phonenumber = (EditText) findViewById(R.id.edit_phonenumber);
        ensure_register = (Button) findViewById(R.id.ensure_register);

        ensure_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(password1.getText().toString().equals(password2.getText().toString()))) {
                    TextView textView = (TextView) findViewById(R.id.tip_password2);
                    textView.setText(R.string.tip_password2);
                } else {
                    user = new BmobUser();
                    user.setUsername(username.getText().toString());
                    user.setPassword(password1.getText().toString());
                    user.setMobilePhoneNumber(phonenumber.getText().toString());

                    user.signUp(RegisterActivity.this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                            dialog.setTitle("错误");
                            dialog.setCancelable(false);
                            switch (i) {
                                case 108 :
                                    dialog.setMessage("必须输入用户名及密码");
                                    break;

                                case 139 :
                                    dialog.setMessage("用户名已存在或不合格式");
                                    break;

                                case 209:
                                    dialog.setMessage("该手机号已被注册");
                                    break;

                                default:
                                    break;
                            }

                            dialog.show();
                        }
                    });
                }
            }
        });
    }
}
