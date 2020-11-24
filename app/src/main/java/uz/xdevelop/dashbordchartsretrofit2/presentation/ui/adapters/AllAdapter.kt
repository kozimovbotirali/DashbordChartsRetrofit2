package uz.xdevelop.dashbordchartsretrofit2.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import uz.xdevelop.dashbordchartsretrofit2.R
import uz.xdevelop.dashbordchartsretrofit2.data.models.network_models.*
import uz.xdevelop.dashbordchartsretrofit2.utils.bindItem
import uz.xdevelop.dashbordchartsretrofit2.utils.inflate

class AllAdapter(
    val list:AllTypes
) : RecyclerView.Adapter<AllAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(parent.inflate(R.layout.list_item))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    override fun getItemCount(): Int = when {
        list.listU !== null -> list.listU.size
        list.listP !== null -> list.listP.size
        list.listB !== null -> list.listB.size
        list.listT !== null -> list.listT.size
        else -> list.listW!!.size
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind() = bindItem {
            tv_name.visibility = View.VISIBLE
            tv_2.visibility = View.VISIBLE
            tv_3.visibility = View.VISIBLE

            when {
                list.listU !== null -> {
                    val d = list.listU[adapterPosition]
                    tv_name.text = "${adapterPosition + 1}. ${d.firstName} ${d.lastName}"
                    tv_2.text = d.age.toString()
                    tv_3.visibility = View.GONE
                }
                list.listP !== null -> {
                    val d = list.listP[adapterPosition]
                    tv_name.text = "${adapterPosition + 1}. " + d.modelName
                    tv_2.text = d.serial
                    tv_3.text = d.modelName
                }
                list.listT !== null -> {
                    val d = list.listT[adapterPosition]
                    tv_name.text = "${adapterPosition + 1}. " + d.value.toString()
                    tv_2.visibility = View.GONE
                    tv_3.visibility = View.GONE
                }
                list.listB !== null -> {
                    val d = list.listB[adapterPosition]
                    tv_name.text = "${adapterPosition + 1}. " + d.value.toString()
                    tv_2.visibility = View.GONE
                    tv_3.visibility = View.GONE
                }
                list.listW !== null -> {
                    val d = list.listW[adapterPosition]
                    tv_name.text = "${adapterPosition + 1}. " + d.value.toString()
                    tv_2.visibility = View.GONE
                    tv_3.visibility = View.GONE
                }
            }
        }
    }

}