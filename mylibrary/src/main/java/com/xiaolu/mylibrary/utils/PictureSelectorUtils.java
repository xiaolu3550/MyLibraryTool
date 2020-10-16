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
     * @param isCompress   是否压缩
     * @param isEnableCrop 是否裁剪
     */
    public static void getPhoto(Activity activity, int type, int maxSelectNum, int minSelectNum
            , int count, int isSingle, boolean isCompress, boolean isEnableCrop, OnResultCallbackListener onResultCallbackListener) {
        PictureSelector.create(activity)
                //全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .openGallery(type)
                .imageEngine(GlideEngine.createGlideEngine())
                // 最大图片选择数量 int
                .maxSelectNum(maxSelectNum)
                // 最小选择数量 int
                .minSelectNum(minSelectNum)
                // 是否压缩
                .isCompress(isCompress)
                // 是否裁剪
                .isEnableCrop(isEnableCrop)
                // 每行显示个数 int
                .imageSpanCount(count)
                // 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .selectionMode(isSingle)
                // 是否显示拍照按钮 true or false
                .isCamera(true)
                // 裁剪框是否可拖拽 true or false
                .freeStyleCropEnabled(true)
                // 裁剪是否可旋转图片 true or false
                .rotateEnabled(true)
                // 裁剪是否可放大缩小图片 true or false
                .scaleEnabled(true)
                // 拍照保存图片格式后缀,默认jpeg
                .imageFormat(PictureMimeType.ofPNG())
                // 图片列表点击 缩放效果 默认true
                .isZoomAnim(true)
                // 是否显示gif图片 true or false
                .isGif(false)
                .minimumCompressSize(100)
                //结果回调onActivityResult code
                .forResult(onResultCallbackListener);
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
    public static void getPhoto(Fragment fragment, int type, int maxSelectNum, int minSelectNum
            , int count, int isSingle, boolean isCompress, boolean isEnableCrop, OnResultCallbackListener onResultCallbackListener) {
        PictureSelector.create(fragment)
                //全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .openGallery(type)
                .imageEngine(GlideEngine.createGlideEngine())
                // 最大图片选择数量 int
                .maxSelectNum(maxSelectNum)
                // 最小选择数量 int
                .minSelectNum(minSelectNum)
                // 每行显示个数 int
                .imageSpanCount(count)
                // 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .selectionMode(isSingle)
                // 是否显示拍照按钮 true or false
                .isCamera(true)
                // 是否压缩
                .isCompress(isCompress)
                // 是否裁剪
                .isEnableCrop(isEnableCrop)
                // 裁剪框是否可拖拽 true or false
                .freeStyleCropEnabled(true)
                // 裁剪是否可旋转图片 true or false
                .rotateEnabled(true)
                // 裁剪是否可放大缩小图片 true or false
                .scaleEnabled(true)
                // 拍照保存图片格式后缀,默认jpeg
                .imageFormat(PictureMimeType.ofPNG())
                // 图片列表点击 缩放效果 默认true
                .isZoomAnim(true)
                // 是否显示gif图片 true or false
                .isGif(false)
                .minimumCompressSize(100)
                .forResult(onResultCallbackListener);
    }

    /**
     * 拍照
     *
     * @param activity     当前的Activity
     * @param type         类型 PictureMimeType.ofImage() or ofAll() or ofVideo() or ofAudio()
     * @param isCompress   是否压缩
     * @param isEnableCrop 是否裁剪
     */
    public static void getCamera(Activity activity, int type
            , boolean isCompress, boolean isEnableCrop, OnResultCallbackListener onResultCallbackListener) {
        PictureSelector.create(activity)
                .openCamera(type)
                .imageEngine(GlideEngine.createGlideEngine())
                // 是否压缩
                .isCompress(isCompress)
                // 是否裁剪
                .isEnableCrop(isEnableCrop)
                // 裁剪框是否可拖拽 true or false
                .freeStyleCropEnabled(true)
                // 裁剪是否可旋转图片 true or false
                .rotateEnabled(true)
                // 裁剪是否可放大缩小图片 true or false
                .scaleEnabled(true)
                .minimumCompressSize(100)
                // 拍照保存图片格式后缀,默认jpeg
                .imageFormat(PictureMimeType.ofPNG())
                .forResult(onResultCallbackListener);
    }

    /**
     * 拍照
     *
     * @param fragment     当前的Fragment
     * @param type         类型 PictureMimeType.ofImage() or ofAll() or ofVideo() or ofAudio()
     * @param isCompress   是否压缩
     * @param isEnableCrop 是否裁剪
     */
    public static void getCamera(Fragment fragment, int type
            , boolean isCompress, boolean isEnableCrop, OnResultCallbackListener onResultCallbackListener) {
        PictureSelector.create(fragment)
                .openCamera(type)
                .imageEngine(GlideEngine.createGlideEngine())
                // 是否压缩
                .isCompress(isCompress)
                // 是否裁剪
                .isEnableCrop(isEnableCrop)
                // 裁剪框是否可拖拽 true or false
                .freeStyleCropEnabled(true)
                // 裁剪是否可旋转图片 true or false
                .rotateEnabled(true)
                // 裁剪是否可放大缩小图片 true or false
                .scaleEnabled(true)
                .minimumCompressSize(100)
                // 拍照保存图片格式后缀,默认jpeg
                .imageFormat(PictureMimeType.ofPNG())
                .forResult(onResultCallbackListener);
    }
}
