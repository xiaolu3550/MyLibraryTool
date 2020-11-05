package com.xiaolu.mylibrarykotlin.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.PointF
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.ImageViewTarget
import com.luck.picture.lib.engine.ImageEngine
import com.luck.picture.lib.listener.OnImageCompleteCallback
import com.luck.picture.lib.tools.MediaUtils
import com.luck.picture.lib.widget.longimage.ImageSource
import com.luck.picture.lib.widget.longimage.ImageViewState
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView
import com.xiaolu.mylibrarykotlin.R

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.utils
 * @ClassName: GlideEngine
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/7 14:22
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/7 14:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
class GlideEngine private constructor() : ImageEngine {
    override fun loadImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
                .load(url)
                .into(imageView)
    }

    override fun loadImage(context: Context, url: String, imageView: ImageView, longImageView: SubsamplingScaleImageView, callback: OnImageCompleteCallback) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(object : ImageViewTarget<Bitmap?>(imageView) {
                    override fun onLoadStarted(placeholder: Drawable?) {
                        super.onLoadStarted(placeholder)
                        callback?.onShowLoading()
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        super.onLoadFailed(errorDrawable)
                        callback?.onHideLoading()
                    }

                    override fun setResource(resource: Bitmap?) {
                        callback?.onHideLoading()
                        if (resource != null) {
                            val eqLongImage = MediaUtils.isLongImg(resource.width,
                                    resource.height)
                            longImageView.visibility = if (eqLongImage) View.VISIBLE else View.GONE
                            imageView.visibility = if (eqLongImage) View.GONE else View.VISIBLE
                            if (eqLongImage) {
                                // 加载长图
                                longImageView.isQuickScaleEnabled = true
                                longImageView.isZoomEnabled = true
                                longImageView.isPanEnabled = true
                                longImageView.setDoubleTapZoomDuration(100)
                                longImageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP)
                                longImageView.setDoubleTapZoomDpi(SubsamplingScaleImageView.ZOOM_FOCUS_CENTER)
                                longImageView.setImage(ImageSource.bitmap(resource),
                                        ImageViewState(0F, PointF(0F, 0F), 0))
                            } else {
                                // 普通图片
                                imageView.setImageBitmap(resource)
                            }
                        }
                    }
                })
    }

    override fun loadImage(context: Context, url: String, imageView: ImageView, longImageView: SubsamplingScaleImageView) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(object : ImageViewTarget<Bitmap?>(imageView) {
                    override fun setResource(resource: Bitmap?) {
                        if (resource != null) {
                            val eqLongImage = MediaUtils.isLongImg(resource.width,
                                    resource.height)
                            longImageView.visibility = if (eqLongImage) View.VISIBLE else View.GONE
                            imageView.visibility = if (eqLongImage) View.GONE else View.VISIBLE
                            if (eqLongImage) {
                                // 加载长图
                                longImageView.isQuickScaleEnabled = true
                                longImageView.isZoomEnabled = true
                                longImageView.isPanEnabled = true
                                longImageView.setDoubleTapZoomDuration(100)
                                longImageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP)
                                longImageView.setDoubleTapZoomDpi(SubsamplingScaleImageView.ZOOM_FOCUS_CENTER)
                                longImageView.setImage(ImageSource.bitmap(resource),
                                        ImageViewState(0F, PointF(0F, 0F), 0))
                            } else {
                                // 普通图片
                                imageView.setImageBitmap(resource)
                            }
                        }
                    }
                })
    }

    override fun loadFolderImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(RequestOptions().placeholder(R.drawable.picture_image_placeholder))
                .into(object : BitmapImageViewTarget(imageView) {
                    override fun setResource(resource: Bitmap?) {
                        val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources, resource)
                        circularBitmapDrawable.cornerRadius = 8f
                        imageView.setImageDrawable(circularBitmapDrawable)
                    }
                })
    }

    override fun loadAsGifImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
                .asGif()
                .load(url)
                .into(imageView)
    }

    override fun loadGridImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
                .load(url)
                .apply(RequestOptions().placeholder(R.drawable.picture_image_placeholder))
                .into(imageView)
    }

    companion object {
        private var instance: GlideEngine? = null
        fun createGlideEngine(): GlideEngine? {
            if (null == instance) {
                synchronized(GlideEngine::class.java) {
                    if (null == instance) {
                        instance = GlideEngine()
                    }
                }
            }
            return instance
        }
    }
}