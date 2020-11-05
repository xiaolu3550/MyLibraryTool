package com.xiaolu.mylibrarykotlin.load.callback

import android.R
import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.load.callback
 * @ClassName: HintCallBack
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:11
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
class HintCallBack(builder: Builder) : BaseCallBack() {
    private val title: String?
    private val subTitle: String?
    private val imgResId: Int
    private val titleStyleRes: Int
    private val subTitleStyleRes: Int
    override fun onCreateView(): Int {
        return 0
    }

    override fun onBuildView(context: Context?): View? {
        return LinearLayout(context)
    }

    override fun onViewCreate(context: Context?, view: View?) {
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.gravity = Gravity.CENTER
        val ll = view as LinearLayout?
        ll!!.orientation = LinearLayout.VERTICAL
        ll.gravity = Gravity.CENTER
        if (imgResId != -1) {
            val ivImage = ImageView(context)
            ivImage.setBackgroundResource(imgResId)
            ll.addView(ivImage, lp)
        }
        if (!TextUtils.isEmpty(title)) {
            val tvTitle = TextView(context)
            tvTitle.text = title
            if (titleStyleRes == -1) {
                tvTitle.setTextAppearance(context, R.style.TextAppearance_Large)
            } else {
                tvTitle.setTextAppearance(context, titleStyleRes)
            }
            ll.addView(tvTitle, lp)
        }
        if (!TextUtils.isEmpty(subTitle)) {
            val tvSubtitle = TextView(context)
            tvSubtitle.text = subTitle
            if (subTitleStyleRes == -1) {
                tvSubtitle.setTextAppearance(context, R.style.TextAppearance_Small)
            } else {
                tvSubtitle.setTextAppearance(context, subTitleStyleRes)
            }
            ll.addView(tvSubtitle, lp)
        }
    }

    class Builder {
        var title: String? = null
        var subTitle: String? = null
        var imgResId = -1
        var subTitleStyleRes = -1
        var titleStyleRes = -1
        fun setHintImg(@DrawableRes imgResId: Int): Builder {
            this.imgResId = imgResId
            return this
        }

        fun setTitle(title: String?): Builder {
            return setTitle(title, -1)
        }

        fun setTitle(title: String?, @StyleRes titleStyleRes: Int): Builder {
            this.title = title
            this.titleStyleRes = titleStyleRes
            return this
        }

        fun setSubTitle(subTitle: String?): Builder {
            return setSubTitle(subTitle, -1)
        }

        fun setSubTitle(subTitle: String?, @StyleRes subTitleStyleRes: Int): Builder {
            this.subTitle = subTitle
            this.subTitleStyleRes = subTitleStyleRes
            return this
        }

        fun build(): HintCallBack {
            return HintCallBack(this)
        }
    }

    init {
        title = builder.title
        subTitle = builder.subTitle
        imgResId = builder.imgResId
        subTitleStyleRes = builder.subTitleStyleRes
        titleStyleRes = builder.titleStyleRes
    }
}