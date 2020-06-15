package com.wu.common.base.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;


import com.wu.common.R;
import com.wu.common.base.HomeTopBean;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;


/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2020/5/23
 * <p>
 * 简介:
 */
public class MoveTopAdapter extends BannerAdapter<HomeTopBean, ImageTitleHolder> {

    public MoveTopAdapter(List<HomeTopBean> mDatas) {
        super(mDatas);
    }

    @Override
    public ImageTitleHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ImageTitleHolder(BannerUtils.getView(parent,R.layout.banner_image));
    }

    @Override
    public void onBindView(ImageTitleHolder holder, HomeTopBean data, int position, int size) {
        //通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
        RequestOptions requestOptions = RequestOptions.centerCropTransform().centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontAnimate();
        Glide.with(holder.itemView)
                .load(data.getPath())
                .apply(requestOptions)
                .into(holder.imageView);

        holder.title.setText(data.getTitle());
    }

}
