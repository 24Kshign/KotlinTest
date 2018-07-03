package cn.share.kotlintest

import android.app.ActivityManager
import android.content.Context

/**
 * Created by jack on 2018/5/25
 */

object Test {
    fun getAppRunStatus(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningTaskInfo = activityManager.getRunningTasks(1)

        if (null != runningTaskInfo && runningTaskInfo.size >= 1) {
            return context.packageName.equals((runningTaskInfo[0] as ActivityManager.RunningTaskInfo)
                    .baseActivity.packageName, ignoreCase = true)
        }
        return false
    }
}