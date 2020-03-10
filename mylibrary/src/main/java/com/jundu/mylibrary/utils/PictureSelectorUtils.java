package com.jundu.mylibrary.utils;

import android.app.Activity;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;

import androidx.fragment.app.Fragment;

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
     * @param requestCode  回调的requestCode
     */
    public static void getPhoto(Activity activity, boolean isCut, int type, int maxSelectNum, int minSelectNum, int count, int isSingle, int requestCode) {
        PictureSelector.create(activity)
                .openGallery(type)//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(maxSelectNum)// 最大图片选择数量 int
                .minSelectNum(minSelectNum)// 最小选择数量 int
                .imageSpanCount(count)// 每行显示个数 int
                .selectionMode(isSingle)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .enableCrop(isCut)// 是否裁剪 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                //.compressSavePath( "/health_nx")
                //.setOutputCameraPath("/health_nx")
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .compress(true)// 是否压缩 true or false
                .isGif(false)// 是否显示gif图片 true or false
                .minimumCompressSize(100)
                //.glideOverride(688,323)
                .forResult(requestCode);//结果回调onActivityResult code
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
     * @param requestCode  回调的requestCode
     */
    public static void getPhoto(Fragment fragment, boolean isCut, int type, int maxSelectNum, int minSelectNum, int count, int isSingle, int requestCode) {
        PictureSelector.create(fragment)
                .openGallery(type)//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(maxSelectNum)// 最大图片选择数量 int
                .minSelectNum(minSelectNum)// 最小选择数量 int
                .imageSpanCount(count)// 每行显示个数 int
                .selectionMode(isSingle)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .enableCrop(isCut)// 是否裁剪 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                //.compressSavePath( "/health_nx")
                //.setOutputCameraPath("/health_nx")
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .compress(true)// 是否压缩 true or false
                .isGif(false)// 是否显示gif图片 true or false
                .minimumCompressSize(100)
                //.glideOverride(688,323)
                .forResult(requestCode);//结果回调onActivityResult code
    }

    /**
     * 拍照
     *
     * @param activity    当前的Activity
     * @param type        类型 PictureMimeType.ofImage() or ofAll() or ofVideo() or ofAudio()
     * @param requestCode 回调的requestCode
     */
    public static void getCamera(Activity activity, boolean isCut, int type, int requestCode) {
        PictureSelector.create(activity)
                .openCamera(type)
                .enableCrop(isCut)// 是否裁剪 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .compress(true)// 是否压缩 true or false
                .minimumCompressSize(100)
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .forResult(requestCode);
    }

    /**
     * 拍照
     *
     * @param fragment    当前的Fragment
     * @param type        类型 PictureMimeType.ofImage() or ofAll() or ofVideo() or ofAudio()
     * @param requestCode 回调的requestCode
     */
    public static void getCamera(Fragment fragment, boolean isCut, int type, int requestCode) {
        PictureSelector.create(fragment)
                .openCamera(type)
                .enableCrop(isCut)// 是否裁剪 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .compress(true)// 是否压缩 true or false
                .minimumCompressSize(100)
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .forResult(requestCode);
    }
}
