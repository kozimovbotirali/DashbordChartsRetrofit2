package uz.xdevelop.dashbordchartsretrofit2.data.models.network_models

import java.io.Serializable

data class AllTypes(
    val listB: List<BalanceData>? = null,
    val listU: List<UsersData>? = null,
    val listT: List<TasksData>? = null,
    val listW: List<WorkersData>? = null,
    val listP: List<ProductsData>? = null
) : Serializable