package com.aranegav.fizzbuzz

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Override only to set the Hilt annotation (mandatory to use dependency injection)
 */

@HiltAndroidApp
class App: Application()