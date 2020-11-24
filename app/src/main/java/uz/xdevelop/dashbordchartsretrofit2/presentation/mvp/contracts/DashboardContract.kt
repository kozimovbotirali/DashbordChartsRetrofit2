package uz.xdevelop.dashbordchartsretrofit2.presentation.mvp.contracts

import uz.xdevelop.dashbordchartsretrofit2.data.models.network_models.*
import uz.xdevelop.dashbordchartsretrofit2.utils.network.ResultData

interface DashboardContract {
    interface Model {
        fun getBalanceDataFromServer(block: (ResultData<List<BalanceData>?>) -> Unit)
        fun getBalanceDataFromServerAll(block: (ResultData<List<BalanceData>?>) -> Unit)
        fun getUsersDataFromServer(block: (ResultData<List<UsersData>?>) -> Unit)
        fun getUsersDataFromServerAll(block: (ResultData<List<UsersData>?>) -> Unit)
        fun getProductsDataFromServer(block: (ResultData<List<ProductsData>?>) -> Unit)
        fun getProductsDataFromServerAll(block: (ResultData<List<ProductsData>?>) -> Unit)
        fun getWorkersDataFromServer(block: (ResultData<List<WorkersData>?>) -> Unit)
        fun getWorkersDataFromServerAll(block: (ResultData<List<WorkersData>?>) -> Unit)
        fun getTasksDataFromServer(block: (ResultData<List<TasksData>?>) -> Unit)
        fun getTasksDataFromServerAll(block: (ResultData<List<TasksData>?>) -> Unit)
    }

    interface View {
        fun initDataBalance(data: List<BalanceData>)
        fun openB(data: List<BalanceData>)
        fun initDataUsers(data: List<UsersData>)
        fun openU(data: List<UsersData>)
        fun initDataTasks(data: List<TasksData>)
        fun openT(data: List<TasksData>)
        fun initDataWorkers(data: List<WorkersData>)
        fun openW(data: List<WorkersData>)
        fun initDataProducts(data: List<ProductsData>)
        fun openP(data: List<ProductsData>)
        fun initDataBalanceDaily(
            lsBalance: List<BalanceData>,
            lsWorkers: List<WorkersData>
        )

        fun onResponseFailure(t: Throwable)
        fun showMessage(str: String)
        fun showProgressDialog()
        fun hideProgressDialog()
    }

    interface Presenter {
        fun requestDataFromServer()
        fun tasksClicked()
        fun usersClicked()
        fun workersClicked()
        fun productsClicked()
        fun balanceClicked()
        fun topChartClicked()
        fun changeBottomChartData(type: Int)
    }
}
