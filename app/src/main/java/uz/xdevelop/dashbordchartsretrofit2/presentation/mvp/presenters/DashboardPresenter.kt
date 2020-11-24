package uz.xdevelop.dashbordchartsretrofit2.presentation.mvp.presenters

import uz.xdevelop.dashbordchartsretrofit2.presentation.mvp.contracts.DashboardContract
import uz.xdevelop.dashbordchartsretrofit2.utils.ThreadsHelper

class DashboardPresenter(
    private val model: DashboardContract.Model,
    private val view: DashboardContract.View
) : DashboardContract.Presenter, ThreadsHelper() {

    override fun requestDataFromServer() {
        view.showProgressDialog()
        model.getBalanceDataFromServer { balance ->
            runOnWorkerThread {
                balance.onData { lsB ->
                    runOnUIThread {
                        view.initDataBalance(lsB!!)

                        model.getWorkersDataFromServer { workers ->
                            runOnWorkerThread {
                                workers.onData { lsW ->
                                    runOnUIThread {
                                        view.initDataWorkers(lsW!!)
                                        view.initDataBalanceDaily(lsB, lsW)
                                    }
                                }
                            }

                            workers.onFailure {
                                onFailure(it)
                            }
                            workers.onMessage { view.showMessage(it) }
                            view.hideProgressDialog()

                        }
                    }
                }
            }

            balance.onFailure {
                onFailure(it)
            }
            balance.onMessage { view.showMessage(it) }
            view.hideProgressDialog()
        }

        model.getTasksDataFromServer { tasks ->
            runOnWorkerThread {
                tasks.onData {
                    runOnUIThread {
                        view.initDataTasks(it!!)
                    }
                }
            }

            tasks.onFailure {
                onFailure(it)
            }
            tasks.onMessage { view.showMessage(it) }
            view.hideProgressDialog()
        }
    }

    override fun tasksClicked() {
        view.showProgressDialog()
        model.getTasksDataFromServerAll { tasks ->
            runOnWorkerThread {
                tasks.onData {
                    runOnUIThread {
                        view.openT(it!!)
                    }
                }
            }

            tasks.onFailure {
                onFailure(it)
            }
            tasks.onMessage { view.showMessage(it) }
            view.hideProgressDialog()
        }
    }

    override fun usersClicked() {
        view.showProgressDialog()
        model.getUsersDataFromServerAll { tasks ->
            runOnWorkerThread {
                tasks.onData {
                    runOnUIThread {
                        view.openU(it!!)
                    }
                }
            }

            tasks.onFailure {
                onFailure(it)
            }
            tasks.onMessage { view.showMessage(it) }
            view.hideProgressDialog()
        }
    }

    override fun workersClicked() {
        view.showProgressDialog()
        model.getWorkersDataFromServerAll { tasks ->
            runOnWorkerThread {
                tasks.onData {
                    runOnUIThread {
                        view.openW(it!!)
                    }
                }
            }

            tasks.onFailure {
                onFailure(it)
            }
            tasks.onMessage { view.showMessage(it) }
            view.hideProgressDialog()
        }
    }

    override fun productsClicked() {
        view.showProgressDialog()
        model.getProductsDataFromServerAll { tasks ->
            runOnWorkerThread {
                tasks.onData {
                    runOnUIThread {
                        view.openP(it!!)
                    }
                }
            }

            tasks.onFailure {
                onFailure(it)
            }
            tasks.onMessage { view.showMessage(it) }
            view.hideProgressDialog()
        }
    }

    override fun balanceClicked() {
        view.showProgressDialog()
        model.getBalanceDataFromServerAll { tasks ->
            runOnWorkerThread {
                tasks.onData {
                    runOnUIThread {
                        view.openB(it!!)
                    }
                }
            }

            tasks.onFailure {
                onFailure(it)
            }
            tasks.onMessage { view.showMessage(it) }
            view.hideProgressDialog()
        }
    }

    override fun topChartClicked() {

    }

    override fun changeBottomChartData(type: Int) {
        view.showProgressDialog()
        if (type == 0) {
            model.getUsersDataFromServer {
                runOnWorkerThread {
                    it.onData {
                        runOnUIThread {
                            view.initDataUsers(it!!)
                        }
                    }
                }

                runOnUIThread {
                    it.onFailure {
                        onFailure(it)
                    }
                    it.onMessage { view.showMessage(it) }
                    view.hideProgressDialog()
                }
            }
        } else {
            model.getProductsDataFromServer {
                runOnWorkerThread {
                    it.onData {
                        runOnUIThread {
                            view.initDataProducts(it!!)
                        }
                    }
                }

                runOnUIThread {
                    it.onFailure {
                        onFailure(it)
                    }
                    it.onMessage { view.showMessage(it) }
                    view.hideProgressDialog()
                }
            }
        }
    }

    private fun onFailure(t: Throwable) {
        view.onResponseFailure(t)
        view.hideProgressDialog()
    }
}
