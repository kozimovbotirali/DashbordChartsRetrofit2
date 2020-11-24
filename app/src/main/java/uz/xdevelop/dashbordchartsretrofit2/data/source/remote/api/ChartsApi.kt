package uz.xdevelop.dashbordchartsretrofit2.data.source.remote.api

import retrofit2.Call
import retrofit2.http.*
import uz.xdevelop.dashbordchartsretrofit2.data.models.ResponseData
import uz.xdevelop.dashbordchartsretrofit2.data.models.network_models.*

interface ChartsApi {
    /**
     * 1. Get all balance
     * */
    @GET("analytic/balance")
    fun getAllBalance(): Call<ResponseData<List<BalanceData>>>

    /**
     * 1. Get all balance
     * */
    @GET("analytic/balance/all")
    fun getAllBalanceAll(): Call<ResponseData<List<BalanceData>>>

    /**
     * 1. Get all tasks
     * */
    @GET("analytic/tasks")
    fun getAllTasks(): Call<ResponseData<List<TasksData>>>

    /**
     * 1. Get all tasks
     * */
    @GET("analytic/tasks/all")
    fun getAllTasksAll(): Call<ResponseData<List<TasksData>>>

    /**
     * 1. Get all workers
     * */
    @GET("analytic/workers")
    fun getAllWorkers(): Call<ResponseData<List<WorkersData>>>

    /**
     * 1. Get all workers
     * */
    @GET("analytic/workers/all")
    fun getAllWorkersAll(): Call<ResponseData<List<WorkersData>>>

    /**
     * 1. Get all users
     * */
    @GET("analytic/users")
    fun getAllUsers(): Call<ResponseData<List<UsersData>>>

    /**
     * 1. Get all users
     * */
    @GET("analytic/users/all")
    fun getAllUsersAll(): Call<ResponseData<List<UsersData>>>

    /**
     * 1. Get all products
     * */
    @GET("analytic/products")
    fun getAllProducts(): Call<ResponseData<List<ProductsData>>>

    /**
     * 1. Get all products
     * */
    @GET("analytic/products/all")
    fun getAllProductsAll(): Call<ResponseData<List<ProductsData>>>
}