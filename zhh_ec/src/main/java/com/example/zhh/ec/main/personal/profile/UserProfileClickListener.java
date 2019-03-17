package com.example.zhh.ec.main.personal.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.zhh.ec.R;
import com.example.zhh.ec.main.personal.list.ListBean;
import com.example.zhh_core.delegates.ZhhDelegate;
import com.example.zhh_core.net.RestClient;
import com.example.zhh_core.net.callback.ISuccess;
import com.example.zhh_core.util.ZhhLogger;
import com.example.zhh_core.util.callbacks.CallbackManager;
import com.example.zhh_core.util.callbacks.CallbackType;
import com.example.zhh_core.util.callbacks.IGlobalCallback;
import com.example.zhh_ui.data.DataDialogUtil;

/**
 * @author brett-zhu
 * created at 2019/3/16 16:08
 */
public class UserProfileClickListener extends SimpleClickListener {

    private final ZhhDelegate DELEGATE;

    private String[] mGender = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(ZhhDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                //开始照相机或选择图片
                CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(Uri args) {
                        ZhhLogger.d("ON_CROP", args);
                        final ImageView avatar = (ImageView) view.findViewById(R.id.img_user_avatar);
                        Glide.with(DELEGATE)
                                .load(args)
                                .into(avatar);
                        RestClient.builder()
                                .url(UploadConfig.UPLOAD_IMG)
                                .loader(DELEGATE.getContext())
                                .file(args.getPath())
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        ZhhLogger.d("ON_CROP_UPLOAD", response);
                                    }
                                }).build().upload();
                    }
                });
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                //
                final ZhhDelegate nameDelegate = bean.getDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGender[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                final DataDialogUtil dataDialogUtil = new DataDialogUtil();
                dataDialogUtil.setDateListener(new DataDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dataDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        if (DELEGATE.getContext() != null) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
            builder.setSingleChoiceItems(mGender, 0, listener);
            builder.show();
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
