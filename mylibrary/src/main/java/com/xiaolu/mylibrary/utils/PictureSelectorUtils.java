package com.xiaolu.mylibrary.utils;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.listener.OnResultCallbackListener;

/**
 * @author: zhaol
 * @createdOn: 2018/4/2 9:34
 * @description: 图片选择器Utils
 */
public class PictureSelectorUtils {
    /**
     * 选择图片
     *
     * @param activity     当前的Activity
     * @param type         图片类型  PictureMimeType.ofImage() or ofAll() or ofVideo() or ofAudio()
     * @param maxSelectNum 最大图片选择数量
     * @param minSelectNum 最小图片选择数量
     * @param count        每行显示个数
     * @param isSingle     是否多选   PictureConfig.SINGLE or PictureConfig.MULTIPLE
     */
    public static void getPhoto(Activity activity, int type, int maxSelectNum, int minSelectNum
            , int count, int isSingle, OnResultCallbackListener onResultCallbackListener) {
        PictureSelector.create(activity)
                .openGallery(type)//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageEngine(GlideEngine.createGlideEngine())
                .maxSelectNum(maxSelectNum)// 最大图片选择数量 int
                .minSelectNum(minSelectNum)// 最小选择数量 int
                .imageSpanCount(count)// 每行显示个数 int
                .selectionMode(isSingle)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .isCamera(true)// 是否显示拍照按钮 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .imageFormat(PictureMimeType.ofPNG())// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .isGif(false)// 是否显示gif图片 true or false
                .minimumCompressSize(100)
                .forResult(onResultCallbackListener);//结果回调onActivityResult code
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
     */
    public static void getPhoto(Fragment fragment, int type, int maxSelectNum, int minSelectNum
            , int count, int isSingle, OnResultCallbackListener onResultCallbackListener) {
        PictureSelector.create(fragment)
                .openGallery(type)//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageEngine(GlideEngine.createGlideEngine())
                .maxSelectNum(maxSelectNum)// 最大图片选择数量 int
                .minSelectNum(minSelectNum)// 最小选择数量 int
                .imageSpanCount(count)// 每行显示个数 int
                .selectionMode(isSingle)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .isCamera(true)// 是否显示拍照按钮 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .imageFormat(PictureMimeType.ofPNG())// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .isGif(false)// 是否显示gif图片 true or false
                .minimumCompressSize(100)
                .forResult(onResultCallbackListener);
    }

    /**
     * 拍照
     *
     * @param activity 当前的Activity
     * @param type     类型 PictureMimeType.ofImage() or ofAll() or ofVideo() or ofAudio()
     */
    public static void getCamera(Activity activity, int type
            , OnResultCallbackListener onResultCallbackListener) {
        PictureSelector.create(activity)
                .openCamera(type)
                .imageEngine(GlideEngine.createGlideEngine())
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .minimumCompressSize(100)
                .imageFormat(PictureMimeType.ofPNG())// 拍照保存图片格式后缀,默认jpeg
                .forResult(onResultCallbackListener);
    }

    /**
     * 拍照
     *
     * @param fragment 当前的Fragment
     * @param type     类型 PictureMimeType.ofImage() or ofAll() or ofVideo() or ofAudio()
     */
    public static void getCamera(Fragment fragment, int type
            , OnResultCallbackListener onResultCallbackListener) {
        PictureSelector.create(fragment)
                .openCamera(type)
                .imageEngine(GlideEngine.createGlideEngine())
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .minimumCompressSize(100)
                .imageFormat(PictureMimeType.ofPNG())// 拍照保存图片格式后缀,默认jpeg
                .forResult(onResultCallbackListener);
    }
}
