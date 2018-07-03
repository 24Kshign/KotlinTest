package cn.share.kotlintest.extendfunction

import android.view.View
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.NonCancellable
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.channels.actor

/**
 * Created by jack on 2018/6/8
 */

val View.contextJob: Job get() = (context as? JobHolder)?.job ?: NonCancellable

fun View.onClickDebounce(action: suspend () -> Unit) {

    val eventActor = actor<Unit>(contextJob + UI) {
        for (event in channel) action()
    }

    setOnClickListener { eventActor.offer(Unit) }

}