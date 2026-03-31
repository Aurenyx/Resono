package com.mrgogu.resono

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
 This annotation initializes Hilt in the entire application.
 Without this, Hilt will NOT work anywhere in the app.
 */
@HiltAndroidApp
class ResonoApp : Application()