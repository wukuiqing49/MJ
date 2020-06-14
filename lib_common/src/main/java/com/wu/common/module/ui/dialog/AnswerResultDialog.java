package com.wu.common.module.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wkq.base.utils.DensityUtils;
import com.wkq.base.utils.ScreenUtil;
import com.wu.common.R;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2020/6/10
 * <p>
 * 简介: 答案的dialog
 */
public class AnswerResultDialog {
    Activity context;
    Dialog dialog;
    private View view;

    public AnswerResultDialog(Activity context) {
        this.context = context;
    }

    public AnswerResultDialog build() {
        dialog = new Dialog(context, R.style.Custom_Answer_Dialog);
        view = LayoutInflater.from(context).inflate(R.layout.dialog_answer_result, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        int wdth = ScreenUtil.getScreenWidth(context) - DensityUtils.dp2px(context, 100);
        lp.width = wdth;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
        return this;
    }


    public AnswerResultDialog setTitle(String title) {
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText("答案: " + title);
        return this;

    }

    public AnswerResultDialog setValues(String values) {
        TextView tv_title = (TextView) view.findViewById(R.id.tv_values);
        tv_title.setText("解释: " + values);
        return this;
    }
    public AnswerResultDialog setQuestion(String question) {
        TextView tv_title = (TextView) view.findViewById(R.id.tv_question);
        tv_title.setText("问题: " + question);
        return this;
    }

    public void show() {
        dialog.show();
    }


}
