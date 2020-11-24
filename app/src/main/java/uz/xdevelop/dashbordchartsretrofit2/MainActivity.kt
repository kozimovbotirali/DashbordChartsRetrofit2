package uz.xdevelop.dashbordchartsretrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.xdevelop.dashbordchartsretrofit2.presentation.ui.fragments.MainFragment
import uz.xdevelop.dashbordchartsretrofit2.utils.navigate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigate(MainFragment())
    }
}