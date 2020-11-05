package com.xiaolu.mylibrarykotlin.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.xiaolu.mylibrarykotlin.utils.GlideEngine.Companion.createGlideEngine

/**
 * @author: zhaol
 * @createdOn: 2018/4/2 9:34
 * @description: 图片选择器Utils
 */
object PictureSelectorUtils {
    /**
     * 选择图片
     *
     * @param activity     当前的Activity
     * @param type         图片类型  PictureMimeType.ofImage() or ofAll() or ofVideo() or ofAudio()
     * @param maxSelectNum 最大图片选择数量
     * @param minSelectNum 最小图片选择数量
     * @param count        每行显示个数
     * @param isSingle     是否多选   PictureConfig.SINGLE or PictureConfig.MULTIPLE
     * @param isCompress   是否压缩
     * @param isEnableCrop 是否裁剪
     */
    @JvmStatic
    fun getPhoto(activity: Activity?, type: Int, maxSelectNum: Int, minSelectNum: Int
                 , count: Int, isSingle: Int, isCompress: Boolean, isEnableCrop: Boolean, onResultCallbackListener: OnResultCallbackListener<*>?) {
        PictureSelector.create(activity) //全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .openGallery(type)
                .imageEngine(createGlideEngine()) // 最大图片选择数量 int
                .maxSelectNum(maxSelectNum) // 最小选择数量 int
                .minSelectNum(minSelectNum) // 是否压缩
                .isCompress(isCompress) // 是否裁剪
                .isEnableCrop(isEnableCrop) // 每行显示个数 int
                .imageSpanCount(count) // 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .selectionMode(isSingle) // 是否显示拍照按钮 true or false
                .isCamera(true) // 裁剪框是否可拖拽 true or false
                .freeStyleCropEnabled(true) // 裁剪是否可旋转图片 true or false
                .rotateEnabled(true) // 裁剪是否可放大缩小图片 true or false
                .scaleEnabled(true) // 拍照保存图片格式后缀,默认jpeg
                .imageFormat(PictureMimeType.ofPNG()) // 图片列表点击 缩放效果 默认true
                .isZoomAnim(true) // 是否显示gif图片 true or false
                .isGif(false)
                .minimumCompressSize(100) //结果回调onActivityResult code
                .forResult(onResultCallbackListener)
    }

    /**
     * 选择图片
     *
     * @param fragment     当前的Fragment
     * @param type         图片类型  PictureMimeType.ofImage() or ofAll() or ofVideo() or ofAudio()
     * @param maxSelectNum 最大图片选择数量
     * @param minSelectNum 最小图片选择数量
     * @param count        每行显示个数
     * @param isSingle     是否多选   PictureConfig.SINGLE or PictureConfig.MULTIPLE
     * @param isCompress   是否压缩
     * @param isEnableCrop 是否裁剪
     */
    @JvmStatic
    fun getPhoto(fragment: Fragment?, type: Int, maxSelectNum: Int, minSelectNum: Int
                 , count: Int, isSingle: Int, isCompress: Boolean, isEnableCrop: Boolean, onResultCallbackListener: OnResultCallbackListener<*>?) {
        PictureSelector.create(fragment) //全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .openGallery(type)
                .imageEngine(createGlideEngine()) // 最大图片选择数量 int
                .maxSelectNum(maxSelectNum) // 最小选择数量 int
                .minSelectNum(minSelectNum) // 每行显示个数 int
                .imageSpanCount(count) // 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .selectionMode(isSingle) // 是否显示拍照按钮 true or false
                .isCamera(true) // 是否压缩
                .isCompress(isCompress) // 是否裁剪
                .isEnableCrop(isEnableCrop) // 裁剪框是否可拖拽 true or false
                .freeStyleCropEnabled(true) // 裁剪是否可旋转图片 true or false
                .rotateEnabled(true) // 裁剪是否可放大缩小图片 true or false
                .scaleEnabled(true) // 拍照保存图片格式后缀,默认jpeg
                .imageFormat(PictureMimeType.ofPNG()) // 图片列表点击 缩放效果 默认true
                .isZoomAnim(true) // 是否显示gif图片 true or false
                .isGif(false)
                .minimumCompressSize(100)
                .forResult(onResultCallbackListener)
    }

    /**
     * 拍照
     *
     * @param activity     当前的Activity
     * @param type         类型 PictureMimeType.ofImage() or ofAll() or ofVideo() or ofAudio()
     * @param isCompress   是否压缩
     * @param isEnableCrop 是否裁剪
     */
    @JvmStatic
    fun getCamera(activity: Activity?, type: Int
                  , isCompress: Boolean, isEnableCrop: Boolean, onResultCallbackListener: OnResultCallbackListener<*>?) {
        PictureSelector.create(activity)
                .openCamera(type)
                .imageEngine(createGlideEngine()) // 是否压缩
                .isCompress(isCompress) // 是否裁剪
                .isEnableCrop(isEnableCrop) // 裁剪框是否可拖拽 true or false
                .freeStyleCropEnabled(true) // 裁剪是否可旋转图片 true or false
                .rotateEnabled(true) // 裁剪是否可放大缩小图片 true or false
                .scaleEnabled(true)
                .minimumCompressSize(100) // 拍照保存图片格式后缀,默认jpeg
                .imageFormat(PictureMimeType.ofPNG())
                .forResult(onResultCallbackListener)
    }

    /**
     * 拍照
     *
     * @param fragment     当前的Fragment
     * @param type         类型 PictureMimeType.ofImage() or ofAll() or ofVideo() or ofAudio()
     * @param isCompress   是否压缩
     * @param isEnableCrop 是否裁剪
     */
    @JvmStatic
    fun getCamera(fragment: Fragment?, type: Int
                  , isCompress: Boolean, isEnableCrop: Boolean, onResultCallbackListener: OnResultCallbackListener<*>?) {
        PictureSelector.create(fragment)
                .openCamera(type)
                .imageEngine(createGlideEngine()) // 是否压缩
                .isCompress(isCompress) // 是否裁剪
                .isEnableCrop(isEnableCrop) // 裁剪框是否可拖拽 true or false
                .freeStyleCropEnabled(true) // 裁剪是否可旋转图片 true or false
                .rotateEnabled(true) // 裁剪是否可放大缩小图片 true or false
                .scaleEnabled(true)
                .minimumCompressSize(100) // 拍照保存图片格式后缀,默认jpeg
                .imageFormat(PictureMimeType.ofPNG())
                .forResult(onResultCallbackListener)
    }
}