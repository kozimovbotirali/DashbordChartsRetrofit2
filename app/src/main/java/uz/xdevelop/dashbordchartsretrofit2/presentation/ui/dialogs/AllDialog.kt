package uz.xdevelop.dashbordchartsretrofit2.presentation.ui.dialogs

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.dialog_all.view.*
import uz.xdevelop.dashbordchartsretrofit2.R
import uz.xdevelop.dashbordchartsretrofit2.data.models.network_models.AllTypes
import uz.xdevelop.dashbordchartsretrofit2.presentation.ui.adapters.AllAdapter

class AllDialog(context: Context, type: Int, listAll: AllTypes) : AlertDialog(context) {
    private val view = LayoutInflater.from(context).inflate(R.layout.dialog_all, null, false)

    init {
        setView(view)

        val adapter = AllAdapter(list = listAll)
        view.list.adapter = adapter
        view.list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        setButton(BUTTON_NEGATIVE, "Cancel") { _, _ -> dismiss() }
        when (type) {
            1 -> {
                val ls = listAll.listB
                var max = ls?.get(0)?.value
                var min = ls?.get(0)?.value
                var s = 0
                ls?.forEach {
                    if (it.value > max!!)
                        max = it.value
                    if (it.value < min!!)
                        min = it.value

                    s += it.value
                }

                val mid = s / ls?.size!!

                view.tv_max.text = max.toString()
                view.tv_min.text = min.toString()
                view.tv_mid.text = mid.toString()
            }
            2 -> {
                val ls = listAll.listU

                var max = ls?.get(0)?.age
                var min = ls?.get(0)?.age
                var s = 0
                ls?.forEach {
                    if (it.age > max!!)
                        max = it.age
                    if (it.age < min!!)
                        min = it.age

                    s += it.age
                }

                val mid = s / ls?.size!!

                view.tv_max.text = max.toString()
                view.tv_min.text = min.toString()
                view.tv_mid.text = mid.toString()
            }
            3 -> {
                val ls = listAll.listT

                var max = ls?.get(0)?.value
                var min = ls?.get(0)?.value
                var s = 0
                ls?.forEach {
                    if (it.value > max!!)
                        max = it.value
                    if (it.value < min!!)
                        min = it.value

                    s += it.value
                }

                val mid = s / ls?.size!!

                view.tv_max.text = max.toString()
                view.tv_min.text = min.toString()
                view.tv_mid.text = mid.toString()
            }
            4 -> {
                val ls = listAll.listW

                var max = ls?.get(0)?.value
                var min = ls?.get(0)?.value
                var s = 0
                ls?.forEach {
                    if (it.value > max!!)
                        max = it.value
                    if (it.value < min!!)
                        min = it.value

                    s += it.value
                }

                val mid = s / ls?.size!!

                view.tv_max.text = max.toString()
                view.tv_min.text = min.toString()
                view.tv_mid.text = mid.toString()
            }
            5 -> {
                view.layoutTop.visibility = View.GONE
            }
        }
    }
}