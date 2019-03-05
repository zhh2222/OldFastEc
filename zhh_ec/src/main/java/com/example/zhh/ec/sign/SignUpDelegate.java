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
import com.example.zhh_core.net.callback.IFailure;
import com.example.zhh_core.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author brett-zhu
 * created at 2019/3/3 20:02
 */
public class SignUpDelegate extends ZhhDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;
    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        start(new SignInDelegate());
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            //
            RestClient.builder()
//                    .url("https://news.baidu.com")
                    .url("http://192.168.191.1:8080/login_up.json")
                    .params("name", mName.getText().toString())
                    .params("email", mEmail.getText().toString())
                    .params("phone", mPhone.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            SignHandler.onSignUp(response, mISignListener);
                            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_LONG).show();
                        }
                    }).failure(new IFailure() {
                @Override
                public void onFailure() {
                    Toast.makeText(getContext(), "验证失败", Toast.LENGTH_LONG).show();
                }
            }).error(new IError() {
                @Override
                public void onError(int code, String msg) {
                    Toast.makeText(getContext(), "验证错误", Toast.LENGTH_LONG).show();
                }
            }).build().post();
        }
    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;
        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("邮箱格式错误");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("电话号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("密码应该大于六位");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
