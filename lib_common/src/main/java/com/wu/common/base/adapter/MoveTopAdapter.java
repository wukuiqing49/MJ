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
public class MoveTopAdapter extends BannerAdapter<HomeTopBean, ImageHolder> {

    public MoveTopAdapter(List<HomeTopBean> mDatas) {
        super(mDatas);
    }

    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) BannerUtils.getView(parent, R.layout.banner_image);
        //通过裁剪实现圆角
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            BannerUtils.setBannerRound(imageView, 20);
//        }
        return new ImageHolder(imageView);
    }

    @Override
    public void onBindView(ImageHolder holder, HomeTopBean data, int position, int size) {
        //通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
        RequestOptions requestOptions = RequestOptions.centerCropTransform().centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontAnimate();

        Glide.with(holder.itemView)
                .load(data.getPath())
                .apply(requestOptions)
                .into(holder.imageView);
    }

}
