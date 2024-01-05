/*
* Created by rahmatsyalim on 2024/01/05
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            // timber logger logger init
            Timber.plant(
                object : Timber.DebugTree() {
                    override fun log(
                        priority: Int,
                        tag: String?,
                        message: String,
                        t: Throwable?
                    ) {
                        super.log(
                            priority = priority,
                            tag = "[Thread-${Thread.currentThread().name}] $tag",
                            message = message,
                            t = t
                        )
                    }
                }
            )
        }
    }
}