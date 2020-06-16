package com.wu.mj.module.home.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wu.mj.R;
import com.wu.mj.module.home.frame.model.AnnouncementInfo;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;


/**
 * 自定义布局，实现类似1号店、淘宝头条的滚动效果
 */
public class TopLineAdapter extends BannerAdapter<AnnouncementInfo, TopLineAdapter.TopLineHolder> {

    public TopLineAdapter(List<AnnouncementInfo> mDatas) {
        super(mDatas);
    }

    @Override
    public TopLineHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new TopLineHolder(BannerUtils.getView(parent, R.layout.top_line_item2));
    }

    @Override
    public void onBindView(TopLineHolder holder, AnnouncementInfo data, int position, int size) {
        holder.message.setText(data.getTitle());
        holder.time.setText(data.getPost_time());
    }

    class TopLineHolder extends RecyclerView.ViewHolder {
        public TextView message;
        public TextView time;

        public TopLineHolder(@NonNull View view) {
            super(view);
            message=view.findViewById(R.id.message);
            time=view.findViewById(R.id.time);
        }
    }
}
