package com.app.merbng.mycodelibs.A_TumbleEditText;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.app.merbng.mycodelibs.A_TumbleEditText.custom.CustomEdittext;
import com.app.merbng.mycodelibs.A_TumbleEditText.custom.CustomTextView;
import com.app.merbng.mycodelibs.A_TumbleEditText.custom.RippleView;
import com.app.merbng.mycodelibs.A_TumbleEditText.custom.StereoView;
import com.app.merbng.mycodelibs.A_TumbleEditText.utils.ToastUtil;
import com.app.merbng.mycodelibs.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomEdittext etUsername;
    private CustomEdittext etEmail;
    private CustomEdittext etPassword;
    private RippleView rvUsername;
    private RippleView rvEmail;
    private RippleView rvPassword;
    private StereoView stereoView;
    private LinearLayout lyWelcome;
    private CustomTextView tvWelcome;
    private int translateY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        stereoView.setStartScreen(2);
        stereoView.post(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                stereoView.getLocationOnScreen(location);
                translateY = location[1];
            }
        });
        stereoView.setiStereoListener(new StereoView.IStereoListener() {
            @Override
            public void toPre(int curScreen) {
//                LogUtil.m("跳转到前一页 "+curScreen);
            }

            @Override
            public void toNext(int curScreen) {
//                LogUtil.m("跳转到下一页 "+curScreen);
            }
        });
    }

    private void initView() {
        stereoView = (StereoView) findViewById(R.id.stereoView);
        etUsername = (CustomEdittext) findViewById(R.id.et_username);
        etEmail = (CustomEdittext) findViewById(R.id.et_email);
        etPassword = (CustomEdittext) findViewById(R.id.et_password);
        rvUsername = (RippleView) findViewById(R.id.rv_username);
        rvEmail = (RippleView) findViewById(R.id.rv_email);
        rvPassword = (RippleView) findViewById(R.id.rv_password);
        lyWelcome = (LinearLayout) findViewById(R.id.ly_welcome);
        tvWelcome = (CustomTextView) findViewById(R.id.tv_welcome);
        rvUsername.setOnClickListener(this);
        rvEmail.setOnClickListener(this);
        rvPassword.setOnClickListener(this);
        tvWelcome.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rv_username:
                rvUsername.setiRippleAnimListener(new RippleView.IRippleAnimListener() {
                    @Override
                    public void onComplete(View view) {
                        stereoView.toPre();
                    }
                });
                break;
            case R.id.rv_email:
                rvEmail.setiRippleAnimListener(new RippleView.IRippleAnimListener() {
                    @Override
                    public void onComplete(View view) {
                        stereoView.toPre();
                    }
                });
                break;
            case R.id.rv_password:
                rvPassword.setiRippleAnimListener(new RippleView.IRippleAnimListener() {
                    @Override
                    public void onComplete(View view) {
                        stereoView.toPre();
                    }
                });
                break;
            case R.id.tv_welcome:
                if (TextUtils.isEmpty(etUsername.getText())) {
                    ToastUtil.showInfo(LoginActivity.this, "请输入用户名!");
                    stereoView.setItem(2);
                    return;
                }
                if (TextUtils.isEmpty(etEmail.getText())) {
                    ToastUtil.showInfo(LoginActivity.this, "请输入邮箱!");
                    stereoView.setItem(1);
                    return;
                }
                if (TextUtils.isEmpty(etPassword.getText())) {
                    ToastUtil.showInfo(LoginActivity.this, "请输入密码!");
                    stereoView.setItem(0);
                    return;
                }
                startExitAnim();
                break;
        }
    }

    private void startExitAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(stereoView, "translationY", 0, 100, -translateY);
        animator.setDuration(500).start();
        ToastUtil.showInfo(LoginActivity.this, "登录成功 =.=");
    }
}
