package cn.share.kotlintest.extendfunction

import kotlinx.coroutines.experimental.Job

/**
 * Created by jack on 2018/6/8
 */
interface JobHolder {
    val job: Job
}