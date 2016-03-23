package com.example.a481.lostfound;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {

    private BmobUser user;

    private Button Register;

    private Button Login;

    private EditText username;

    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        Register = (Button)findViewById(R.id.register);
        Login = (Button)findViewById(R.id.signin);
        username = (EditText)findViewById(R.id.sign_username);
        password = (EditText)findViewById(R.id.sign_password);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());

                user.login(LoginActivity.this, new SaveListener() {

                    @Override
                    public void onSuccess() {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                        dialog.setTitle("错误");
                        dialog.setCancelable(false);
                        switch (i) {
                            case 108 :
                                dialog.setMessage("必须输入用户名及密码");
                                break;

                            case 9016 :
                                dialog.setMessage("无网络连接");
                                break;

                            case 9018:
                                dialog.setMessage("用户名或密码不能为空");
                                break;

                            case 109:
                                dialog.setMessage("用户名或密码不能为空");
                                break;

                            case 205:
                                dialog.setMessage("用户不存在");
                                break;

                            default:
                                break;
                        }

                        dialog.show();
                    }
                });
            }
        });
    }
}
