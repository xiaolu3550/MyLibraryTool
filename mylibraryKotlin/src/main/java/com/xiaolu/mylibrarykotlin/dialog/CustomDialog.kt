package com.xiaolu.mylibrarykotlin.dialog

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tamsiree.rxkit.RxImageTool.dip2px

class CustomDialog(builder: Builder) : DialogFragment() {
    private var mContext: Context? = null
    private val height: Int
    private val width: Int
    private val cancelTouchout: Boolean
    private var mView: View? = null
    private var resStyle = -1
    private val gravity: Int
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setStyle(STYLE_NORMAL, resStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.setCanceledOnTouchOutside(cancelTouchout)
        val win = dialog!!.window
        val lp = win!!.attributes
        lp.gravity = gravity
        lp.height = height
        lp.width = width
        win.attributes = lp
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    init {
        mContext = builder.context
        height = builder.height
        width = builder.width
        cancelTouchout = builder.cancelTouchout
        mView = builder.view
        resStyle = builder.resStyle
        gravity = builder.gravity
    }

    class Builder(var context: Context?, resView: Int) {
        var height = 0
        var width = 0
        var cancelTouchout = false
        var view: View? = LayoutInflater.from(context).inflate(resView, null)
        var resStyle = -1
        var gravity = Gravity.CENTER

        /*public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }*/
        fun heightPx(height: Int): Builder {
            this.height = height
            return this
        }

        fun widthPx(width: Int): Builder {
            this.width = width
            return this
        }

        fun heightDp(height: Int): Builder {
            this.height = dip2px(height.toFloat())
            return this
        }

        fun widthDp(width: Int): Builder {
            this.width = dip2px(width.toFloat())
            return this
        }

        fun heightDimenRes(dimenRes: Int): Builder {
            height = context?.resources?.getDimensionPixelOffset(dimenRes)!!
            return this
        }

        fun widthDimenRes(dimenRes: Int): Builder {
            width = context?.resources?.getDimensionPixelOffset(dimenRes)!!
            return this
        }

        fun style(resStyle: Int): Builder {
            this.resStyle = resStyle
            return this
        }

        fun gravity(gravity: Int): Builder {
            this.gravity = gravity
            return this
        }

        fun cancelTouchOut(`val`: Boolean): Builder {
            cancelTouchout = `val`
            return this
        }

        fun addViewOnclick(viewRes: Int, listener: View.OnClickListener?): Builder {
            view?.findViewById<View>(viewRes)?.setOnClickListener(listener)
            return this
        }

        fun <T : View?> getView(viewRes: Int): T? {
            return view?.findViewById<T>(viewRes)
        }

        fun build(): CustomDialog {
            return CustomDialog(this)
        }

    }


}