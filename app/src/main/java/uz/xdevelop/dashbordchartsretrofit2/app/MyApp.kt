package uz.xdevelop.dashbordchartsretrofit2.app

import android.app.Application
import uz.xdevelop.dashbordchartsretrofit2.data.source.local.storage.LocalStorage

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        LocalStorage.init(this)
    }

    companion object {
        lateinit var instance: MyApp
    }
}