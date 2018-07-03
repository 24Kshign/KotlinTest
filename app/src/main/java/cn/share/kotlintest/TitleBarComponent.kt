//package application.ui.common.title_bar
//
//import android.graphics.Color
//import android.text.TextUtils
//import android.view.Gravity
//import android.view.View
//import android.view.ViewGroup
//import android.view.ViewManager
//import android.widget.FrameLayout
//import android.widget.TextView
//import application.ui.base.res.TEXT_SIZE_16SP
//import application.ui.base.res.TEXT_SIZE_18SP
//import application.ui.common.R
//import org.jetbrains.anko.*
//import plugin.core.PluginCore
//import radiancez.android.content.res.colorRes
//import radiancez.android.widget.setLeftDrawable
//import radiancez.anko.component
//import radiancez.anko.component.RzAnkoComponent
//import radiancez.anko.dp
//import radiancez.anko.layoutParams
//import radiancez.java.util.function.Block
//
///**
// * Created by radiance on 2017/10/25.
// * TitleBarComponent
// */
//
//class TitleBarComponent : RzAnkoComponent<FrameLayout>() {
//
//    // 标题
//    lateinit var titleTextView: TextView private set
//    // 左侧按钮
//    lateinit var leftTextView: TextView private set
//    // 右侧按钮
//    lateinit var rightTextView: TextView private set
//
//    var titleText: CharSequence?
//        get() {
//            return titleTextView.text
//        }
//        set(value) {
//            titleTextView.text = value
//        }
//
//    override fun ViewManager.ankoCreateView(): FrameLayout {
//        return frameLayout {
//            backgroundColor = PluginCore.colorRes(R.color.main_light_color)
//
//            titleTextView = textView {
//                gravity = Gravity.CENTER
//                singleLine = true
//                maxWidth = 175.dp()
//                ellipsize = TextUtils.TruncateAt.END
//                textColor = Color.WHITE
//                textSize = TEXT_SIZE_18SP
//            }.lparams {
//                gravity = Gravity.CENTER
//                width = wrapContent
//                height = 45.dp()
//            }
//
//            leftTextView = textView(R.string.title_bar_back_btn) {
//                visibility = View.INVISIBLE
//                gravity = Gravity.CENTER
//                singleLine = true
//                leftPadding = 15.dp()
//                textColor = Color.WHITE
//                textSize = TEXT_SIZE_16SP
//                compoundDrawablePadding = 5.dp()
//                setLeftDrawable(R.drawable.title_bar_left_arrow)
//            }.lparams {
//                gravity = Gravity.LEFT
//                width = wrapContent
//                height = matchParent
//            }
//
//            rightTextView = textView {
//                gravity = Gravity.CENTER
//                leftPadding = 5.dp()
//                rightPadding = 15.dp()
//                singleLine = true
//                textColor = Color.WHITE
//                textSize = TEXT_SIZE_16SP
//            }.lparams {
//                gravity = Gravity.RIGHT
//                width = wrapContent
//                height = matchParent
//            }
//        }.layoutParams(matchParent, wrapContent)
//    }
//
//    // 把左侧按钮设置成返回按钮
//    fun showBackBtn() {
//        leftTextView.setOnClickListener {
//            activity.onBackPressed()
//        }
//        leftTextView.visibility = View.VISIBLE
//    }
//
//    inline fun titleTextView(init: Block<TextView>): TextView {
//        return titleTextView.apply(init)
//    }
//
//    inline fun leftTextView(init: Block<TextView>): TextView {
//        return leftTextView.apply(init)
//    }
//
//    inline fun rightTextView(init: Block<TextView>): TextView {
//        return rightTextView.apply(init)
//    }
//}
//
//////////////////////////////////////////////////////////////////////////////////////////////////////
//
//fun ViewGroup.titleBar(): TitleBarComponent {
//    return component { showBackBtn() }
//}
//
//inline fun ViewGroup.titleBar(init: Block<TitleBarComponent>): TitleBarComponent {
//    return component { showBackBtn(); init() }
//}
