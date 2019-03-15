package com.example.zhh.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.zhh.ec.R;
import com.example.zhh.ec.R2;
import com.example.zhh_core.delegates.ZhhDelegate;
import com.example.zhh_core.net.RestClient;
import com.example.zhh_core.net.callback.IError;
import com.example.zhh_core.wechat.ZhhWeChat;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author brett-zhu
 * created at 2019/3/3 20:55
 */
public class SignInDelegate extends ZhhDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;

    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
            //
            RestClient.builder()
//                    .url("https://news.baidu.com")
                    .url("http://192.168.191.1:8080/login_up.json")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(response -> {
                        SignHandler.onSignIn(response,mISignListener);
                        Toast.makeText(getContext(), "验证通过", Toast.LENGTH_LONG).show();
                    }).failure(() -> Toast.makeText(getContext(), "验证失败", Toast.LENGTH_LONG).show()).error(new IError() {
                @Override
                public void onError(int code, String msg) {
                    Toast.makeText(getContext(), "验证错误", Toast.LENGTH_LONG).show();
                }
            }).build().post();
        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    @OnClick(R2.id.icon_sign_in_we_chat)
    void onClickWeChat() {
        ZhhWeChat.getInstance().onSignSuccess(userInfo -> {
            Toast.makeText(getContext(),userInfo,Toast.LENGTH_LONG).show();
        }).signIn();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("邮箱格式错误");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("密码应该大于六位");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public void enqueueAction(Runnable runnable) {

    }
}
