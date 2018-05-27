package com.example.cynthia.zscy.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cynthia.httphelper.HttpHelper;
import com.example.cynthia.httphelper.Response.Callback;
import com.example.cynthia.httphelper.Response.Response;
import com.example.cynthia.zscy.Config.Config;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;
import com.example.cynthia.zscy.Utils.ToastUtils;

public class LogInActivity extends BaseActivity {

    private Toolbar logInToolbar;
    private TextView title;
    private ImageView back;
    private EditText account;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initView();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ac = account.getText().toString();
                final String pw = password.getText().toString();
                if (ac.equals("") || pw.equals("")){
                    ToastUtils.showError("未填写用户名和账号");
                } else {
                    helper = new HttpHelper.set().mode("POST").url(Config.VERIFY).
                            param("stuNum="+ac+"&idNum="+pw).build();
                    new Response.from(helper).get(new Callback() {
                        @Override
                        public void succeed(String response) {
                            Application.setAc(ac);
                            Application.setPw(pw);
                            startNew(false);
                        }

                        @Override
                        public void error(Exception e, int status) {
                            ToastUtils.showError(e.toString());
                        }
                    });
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        logInToolbar = findViewById(R.id.loginbar);
        title = findViewById(R.id.login_title);
        back = findViewById(R.id.login_back);
        account = findViewById(R.id.account);
        password  = findViewById(R.id.pw);
        login = findViewById(R.id.login);
    }

    private void startNew(Boolean reload){
            if (!reload) {
                SharedPreferences sharedPreferences =
                        getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("ac", Application.getAc());
                editor.putString("pw", Application.getPw());
                editor.commit();
            }
            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
    }


}

