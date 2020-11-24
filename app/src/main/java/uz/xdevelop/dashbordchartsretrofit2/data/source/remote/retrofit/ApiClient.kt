package uz.xdevelop.dashbordchartsretrofit2.data.source.remote.retrofit

import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.xdevelop.dashbordchartsretrofit2.app.MyApp

object ApiClient {
    private val logging =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val clientSimple = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(ChuckInterceptor(MyApp.instance))//for seeing responses and requests from emulator
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://c0df51ccd7f1.ngrok.io/")
        .client(clientSimple)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}