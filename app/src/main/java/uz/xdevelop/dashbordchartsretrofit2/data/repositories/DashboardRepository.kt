package uz.xdevelop.dashbordchartsretrofit2.data.repositories

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.xdevelop.dashbordchartsretrofit2.data.models.ResponseData
import uz.xdevelop.dashbordchartsretrofit2.data.models.network_models.*
import uz.xdevelop.dashbordchartsretrofit2.data.source.remote.api.ChartsApi
import uz.xdevelop.dashbordchartsretrofit2.presentation.mvp.contracts.DashboardContract
import uz.xdevelop.dashbordchartsretrofit2.utils.network.ResultData

class DashboardRepository(private val chartsApi: ChartsApi) : DashboardContract.Model {

    override fun getBalanceDataFromServer(block: (ResultData<List<BalanceData>?>) -> Unit) {
        chartsApi.getAllBalance().enqueue(object : Callback<ResponseData<List<BalanceData>>> {
            override fun onResponse(
                call: Call<ResponseData<List<BalanceData>>>,
                response: Response<ResponseData<List<BalanceData>>>
            ) {
                val res = response.body()
                when {
//                    res == null -> block(ResultData.resource(R.string.empty_body))
                    res == null -> block(ResultData.message("Returns empty body. Please connect with call center!"))
                    res.status == "ERROR" -> {
                        block(ResultData.message(res.message))
                    }
                    res.status == "OK" -> {
                        block(ResultData.data(res.data))
                    }
                }
                if (response.code() >= 500) {
                    block(ResultData.failure(Throwable("Returns empty body. Please connect with call center!")))
                }
            }

            override fun onFailure(call: Call<ResponseData<List<BalanceData>>>, t: Throwable) {
                block(ResultData.failure(t))
            }
        })
    }

    override fun getBalanceDataFromServerAll(block: (ResultData<List<BalanceData>?>) -> Unit) {
        chartsApi.getAllBalanceAll().enqueue(object : Callback<ResponseData<List<BalanceData>>> {
            override fun onResponse(
                call: Call<ResponseData<List<BalanceData>>>,
                response: Response<ResponseData<List<BalanceData>>>
            ) {
                val res = response.body()
                when {
//                    res == null -> block(ResultData.resource(R.string.empty_body))
                    res == null -> block(ResultData.message("Returns empty body. Please connect with call center!"))
                    res.status == "ERROR" -> {
                        block(ResultData.message(res.message))
                    }
                    res.status == "OK" -> {
                        block(ResultData.data(res.data))
                    }
                }
                if (response.code() >= 500) {
                    block(ResultData.failure(Throwable("Returns empty body. Please connect with call center!")))
                }
            }

            override fun onFailure(call: Call<ResponseData<List<BalanceData>>>, t: Throwable) {
                block(ResultData.failure(t))
            }
        })
    }

    override fun getUsersDataFromServer(block: (ResultData<List<UsersData>?>) -> Unit) {
        chartsApi.getAllUsers().enqueue(object : Callback<ResponseData<List<UsersData>>> {
            override fun onResponse(
                call: Call<ResponseData<List<UsersData>>>,
                response: Response<ResponseData<List<UsersData>>>
            ) {
                val res = response.body()
                when {
//                    res == null -> block(ResultData.resource(R.string.empty_body))
                    res == null -> block(ResultData.message("Returns empty body. Please connect with call center!"))
                    res.status == "ERROR" -> {
                        block(ResultData.message(res.message))
                    }
                    res.status == "OK" -> {
                        block(ResultData.data(res.data))
                    }
                }
                if (response.code() >= 500) {
                    block(ResultData.failure(Throwable("Returns empty body. Please connect with call center!")))
                }
            }

            override fun onFailure(call: Call<ResponseData<List<UsersData>>>, t: Throwable) {
                block(ResultData.failure(t))
            }
        })
    }

    override fun getUsersDataFromServerAll(block: (ResultData<List<UsersData>?>) -> Unit) {
        chartsApi.getAllUsersAll().enqueue(object : Callback<ResponseData<List<UsersData>>> {
            override fun onResponse(
                call: Call<ResponseData<List<UsersData>>>,
                response: Response<ResponseData<List<UsersData>>>
            ) {
                val res = response.body()
                when {
//                    res == null -> block(ResultData.resource(R.string.empty_body))
                    res == null -> block(ResultData.message("Returns empty body. Please connect with call center!"))
                    res.status == "ERROR" -> {
                        block(ResultData.message(res.message))
                    }
                    res.status == "OK" -> {
                        block(ResultData.data(res.data))
                    }
                }
                if (response.code() >= 500) {
                    block(ResultData.failure(Throwable("Returns empty body. Please connect with call center!")))
                }
            }

            override fun onFailure(call: Call<ResponseData<List<UsersData>>>, t: Throwable) {
                block(ResultData.failure(t))
            }
        })
    }

    override fun getProductsDataFromServer(block: (ResultData<List<ProductsData>?>) -> Unit) {
        chartsApi.getAllProducts().enqueue(object : Callback<ResponseData<List<ProductsData>>> {
            override fun onResponse(
                call: Call<ResponseData<List<ProductsData>>>,
                response: Response<ResponseData<List<ProductsData>>>
            ) {
                val res = response.body()
                when {
//                    res == null -> block(ResultData.resource(R.string.empty_body))
                    res == null -> block(ResultData.message("Returns empty body. Please connect with call center!"))
                    res.status == "ERROR" -> {
                        block(ResultData.message(res.message))
                    }
                    res.status == "OK" -> {
                        block(ResultData.data(res.data))
                    }
                }
                if (response.code() >= 500) {
                    block(ResultData.failure(Throwable("Returns empty body. Please connect with call center!")))
                }
            }

            override fun onFailure(call: Call<ResponseData<List<ProductsData>>>, t: Throwable) {
                block(ResultData.failure(t))
            }
        })
    }

    override fun getProductsDataFromServerAll(block: (ResultData<List<ProductsData>?>) -> Unit) {
        chartsApi.getAllProductsAll().enqueue(object : Callback<ResponseData<List<ProductsData>>> {
            override fun onResponse(
                call: Call<ResponseData<List<ProductsData>>>,
                response: Response<ResponseData<List<ProductsData>>>
            ) {
                val res = response.body()
                when {
//                    res == null -> block(ResultData.resource(R.string.empty_body))
                    res == null -> block(ResultData.message("Returns empty body. Please connect with call center!"))
                    res.status == "ERROR" -> {
                        block(ResultData.message(res.message))
                    }
                    res.status == "OK" -> {
                        block(ResultData.data(res.data))
                    }
                }
                if (response.code() >= 500) {
                    block(ResultData.failure(Throwable("Returns empty body. Please connect with call center!")))
                }
            }

            override fun onFailure(call: Call<ResponseData<List<ProductsData>>>, t: Throwable) {
                block(ResultData.failure(t))
            }
        })
    }

    override fun getWorkersDataFromServer(block: (ResultData<List<WorkersData>?>) -> Unit) {
        chartsApi.getAllWorkers().enqueue(object : Callback<ResponseData<List<WorkersData>>> {
            override fun onResponse(
                call: Call<ResponseData<List<WorkersData>>>,
                response: Response<ResponseData<List<WorkersData>>>
            ) {
                val res = response.body()
                when {
//                    res == null -> block(ResultData.resource(R.string.empty_body))
                    res == null -> block(ResultData.message("Returns empty body. Please connect with call center!"))
                    res.status == "ERROR" -> {
                        block(ResultData.message(res.message))
                    }
                    res.status == "OK" -> {
                        block(ResultData.data(res.data))
                    }
                }
                if (response.code() >= 500) {
                    block(ResultData.failure(Throwable("Returns empty body. Please connect with call center!")))
                }
            }

            override fun onFailure(call: Call<ResponseData<List<WorkersData>>>, t: Throwable) {
                block(ResultData.failure(t))
            }
        })
    }

    override fun getWorkersDataFromServerAll(block: (ResultData<List<WorkersData>?>) -> Unit) {
        chartsApi.getAllWorkersAll().enqueue(object : Callback<ResponseData<List<WorkersData>>> {
            override fun onResponse(
                call: Call<ResponseData<List<WorkersData>>>,
                response: Response<ResponseData<List<WorkersData>>>
            ) {
                val res = response.body()
                when {
//                    res == null -> block(ResultData.resource(R.string.empty_body))
                    res == null -> block(ResultData.message("Returns empty body. Please connect with call center!"))
                    res.status == "ERROR" -> {
                        block(ResultData.message(res.message))
                    }
                    res.status == "OK" -> {
                        block(ResultData.data(res.data))
                    }
                }
                if (response.code() >= 500) {
                    block(ResultData.failure(Throwable("Returns empty body. Please connect with call center!")))
                }
            }

            override fun onFailure(call: Call<ResponseData<List<WorkersData>>>, t: Throwable) {
                block(ResultData.failure(t))
            }
        })
    }

    override fun getTasksDataFromServer(block: (ResultData<List<TasksData>?>) -> Unit) {
        chartsApi.getAllTasks().enqueue(object : Callback<ResponseData<List<TasksData>>> {
            override fun onResponse(
                call: Call<ResponseData<List<TasksData>>>,
                response: Response<ResponseData<List<TasksData>>>
            ) {
                val res = response.body()
                when {
//                    res == null -> block(ResultData.resource(R.string.empty_body))
                    res == null -> block(ResultData.message("Returns empty body. Please connect with call center!"))
                    res.status == "ERROR" -> {
                        block(ResultData.message(res.message))
                    }
                    res.status == "OK" -> {
                        block(ResultData.data(res.data))
                    }
                }
                if (response.code() >= 500) {
                    block(ResultData.failure(Throwable("Returns empty body. Please connect with call center!")))
                }
            }

            override fun onFailure(call: Call<ResponseData<List<TasksData>>>, t: Throwable) {
                block(ResultData.failure(t))
            }
        })
    }

    override fun getTasksDataFromServerAll(block: (ResultData<List<TasksData>?>) -> Unit) {
        chartsApi.getAllTasksAll().enqueue(object : Callback<ResponseData<List<TasksData>>> {
            override fun onResponse(
                call: Call<ResponseData<List<TasksData>>>,
                response: Response<ResponseData<List<TasksData>>>
            ) {
                val res = response.body()
                when {
//                    res == null -> block(ResultData.resource(R.string.empty_body))
                    res == null -> block(ResultData.message("Returns empty body. Please connect with call center!"))
                    res.status == "ERROR" -> {
                        block(ResultData.message(res.message))
                    }
                    res.status == "OK" -> {
                        block(ResultData.data(res.data))
                    }
                }
                if (response.code() >= 500) {
                    block(ResultData.failure(Throwable("Returns empty body. Please connect with call center!")))
                }
            }

            override fun onFailure(call: Call<ResponseData<List<TasksData>>>, t: Throwable) {
                block(ResultData.failure(t))
            }
        })
    }

}
