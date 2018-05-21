package cn.share.kotlintest.recyclerview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.*

/**
 * Created by jack on 2018/5/21
 */
class BaseCommonViewHolder : RecyclerView.ViewHolder {

    constructor(itemView: View) : super(itemView) {
    }

    fun getContext(): Context {
        return itemView.context
    }

    fun setText(view: View, char: CharSequence): BaseCommonViewHolder {
        (view as TextView).text = char
        return this
    }

    fun setTextColor(view: View, textColor: Int): BaseCommonViewHolder {
        (view as TextView).setTextColor(textColor)
        return this
    }

    fun setTextSize(view: View, textSize: Int): BaseCommonViewHolder {
        (view as TextView).setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize.toFloat())
        return this
    }

    fun setTextColorRes(view: View, textColorRes: Int): BaseCommonViewHolder {
        (view as TextView).setTextColor(ContextCompat.getColor(getContext(), textColorRes))
        return this
    }

    fun setImageResource(view: View, resId: Int): BaseCommonViewHolder {
        (view as ImageView).setImageResource(resId)
        return this
    }

    fun setImageBitmap(view: View, bitmap: Bitmap): BaseCommonViewHolder {
        (view as ImageView).setImageBitmap(bitmap)
        return this
    }

    fun setImageDrawable(view: View, drawable: Drawable): BaseCommonViewHolder {
        (view as ImageView).setImageDrawable(drawable)
        return this
    }

    fun setImagePath(view: View, commonImageLoader: CommonImageLoader): BaseCommonViewHolder {
        //将第三方加载图片框架与之分离（解耦）
        commonImageLoader.loadImageView((view as ImageView), commonImageLoader.imagePath)
        return this
    }

    fun setBackgroundColor(view: View, color: Int): BaseCommonViewHolder {
        view.setBackgroundColor(color)
        return this
    }

    fun setBackgroundRes(view: View, backgroundRes: Int): BaseCommonViewHolder {
        view.setBackgroundResource(backgroundRes)
        return this
    }

    fun setAlpha(view: View, value: Float): BaseCommonViewHolder {
        val alpha = AlphaAnimation(value, value)
        alpha.duration = 0
        alpha.fillAfter = true
        view.startAnimation(alpha)
        return this
    }

    fun setVisible(view: View, visible: Boolean): BaseCommonViewHolder {
        view.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }


    fun setProgress(view: View, progress: Int): BaseCommonViewHolder {
        (view as ProgressBar).progress = progress
        return this
    }

    fun setProgress(view: View, progress: Int, max: Int): BaseCommonViewHolder {
        (view as ProgressBar).max = max
        view.progress = progress
        return this
    }

    fun setMax(view: View, max: Int): BaseCommonViewHolder {
        (view as ProgressBar).max = max
        return this
    }

    fun setRating(view: View, rating: Float): BaseCommonViewHolder {
        (view as RatingBar).rating = rating
        return this
    }

    fun setRating(view: View, rating: Float, max: Int): BaseCommonViewHolder {
        (view as RatingBar).max = max
        view.rating = rating
        return this
    }

    fun setTag(view: View, tag: Any): BaseCommonViewHolder {
        view.tag = tag
        return this
    }

    fun setTag(view: View, key: Int, tag: Any): BaseCommonViewHolder {
        view.setTag(key, tag)
        return this
    }

    fun setChecked(view: View, checked: Boolean): BaseCommonViewHolder {
        (view as CheckBox).isChecked = checked
        return this
    }


    ///////////////////////////////////////////////////////////////////////View的事件/////////////////////////////////////////////////////////////////////

    fun setOnClickListener(view: View, listener: View.OnClickListener): BaseCommonViewHolder {
        view.setOnClickListener(listener)
        return this
    }

    fun setOnTouchListener(view: View, listener: View.OnTouchListener): BaseCommonViewHolder {
        view.setOnTouchListener(listener)
        return this
    }

    fun setOnLongClickListener(view: View, listener: View.OnLongClickListener): BaseCommonViewHolder {
        view.setOnLongClickListener(listener)
        return this
    }

    abstract class CommonImageLoader(val imagePath: String) {

        abstract fun loadImageView(imageView: ImageView, imagePath: String)
    }

}