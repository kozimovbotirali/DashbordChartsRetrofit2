package uz.xdevelop.dashbordchartsretrofit2.presentation.ui.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.Utils
import retrofit2.HttpException
import uz.xdevelop.dashbordchartsretrofit2.R
import uz.xdevelop.dashbordchartsretrofit2.data.models.network_models.*
import uz.xdevelop.dashbordchartsretrofit2.data.repositories.DashboardRepository
import uz.xdevelop.dashbordchartsretrofit2.data.source.remote.api.ChartsApi
import uz.xdevelop.dashbordchartsretrofit2.data.source.remote.retrofit.ApiClient
import uz.xdevelop.dashbordchartsretrofit2.databinding.FragmentMainBinding
import uz.xdevelop.dashbordchartsretrofit2.presentation.mvp.contracts.DashboardContract
import uz.xdevelop.dashbordchartsretrofit2.presentation.mvp.presenters.DashboardPresenter
import uz.xdevelop.dashbordchartsretrofit2.presentation.ui.dialogs.AllDialog
import uz.xdevelop.dashbordchartsretrofit2.utils.MyMarkerView

@SuppressLint("SetTextI18n")
class MainFragment : BaseFragment<FragmentMainBinding>(), DashboardContract.View {
    private lateinit var presenter: DashboardContract.Presenter
    private val api = ApiClient.retrofit.create(ChartsApi::class.java)

    override fun getLayoutId(): Int = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadViews()

        binding.lineChartBalance.setOnClickListener {
            presenter.balanceClicked()
        }

        binding.lineChartGrouped.setTouchEnabled(false)
        binding.lineChartGrouped.setOnClickListener {
            if (binding.spinner.selectedItemPosition == 0)
                presenter.usersClicked()
            else
                presenter.productsClicked()
        }

        binding.lineChartWorkers.setOnClickListener {
            presenter.workersClicked()
        }

        binding.lineChartTasks.setOnClickListener {
            presenter.tasksClicked()
        }
    }

    override fun initDataBalance(data: List<BalanceData>) {
        setDataBalance(data)

        binding.lineChartBalance.animateXY(2000, 2000)
        // get the legend (only possible after setting data)
        val l: Legend = binding.lineChartBalance.legend

        // draw legend entries as lines
        l.form = LegendForm.LINE
    }

    override fun openB(data: List<BalanceData>) {
        val dialog = AllDialog(context!!, 1, AllTypes(listB = data))
        dialog.show()
    }

    override fun initDataUsers(data: List<UsersData>) {
        setDataUsers(data)

        binding.lineChartGrouped.animateY(1400, Easing.EaseInOutQuad)

        // chart.spin(2000, 0, 360);
        val l: Legend = binding.lineChartGrouped.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false
    }

    override fun openU(data: List<UsersData>) {
        val dialog = AllDialog(context!!, 2, AllTypes(listU = data))
        dialog.show()
    }

    override fun initDataTasks(data: List<TasksData>) {
        setDataTasks(data)

        binding.lineChartTasks.animateXY(2000, 2000)
        // get the legend (only possible after setting data)
        val l: Legend = binding.lineChartTasks.legend

        // draw legend entries as lines
        l.form = LegendForm.LINE
    }

    override fun openT(data: List<TasksData>) {
        val dialog = AllDialog(context!!, 3, AllTypes(listT = data))
        dialog.show()
    }

    override fun initDataWorkers(data: List<WorkersData>) {
        setDataWorkers(data)

        binding.lineChartWorkers.animateXY(2000, 2000)
        // get the legend (only possible after setting data)
        val l: Legend = binding.lineChartWorkers.legend

        // draw legend entries as lines
        l.form = LegendForm.LINE
    }

    override fun openW(data: List<WorkersData>) {
        val dialog = AllDialog(context!!, 4, AllTypes(listW = data))
        dialog.show()
    }

    override fun initDataProducts(data: List<ProductsData>) {
        setDataProducts(data)

        binding.lineChartGrouped.animateY(1400, Easing.EaseInOutQuad)

        // chart.spin(2000, 0, 360);
        val l: Legend = binding.lineChartGrouped.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false
    }

    override fun openP(data: List<ProductsData>) {
        val dialog = AllDialog(context!!, 5, AllTypes(listP = data))
        dialog.show()
    }

    override fun initDataBalanceDaily(
        lsBalance: List<BalanceData>,
        lsWorkers: List<WorkersData>
    ) {
        setDataBalanceDaily(lsBalance, lsWorkers)

        binding.lineChartBalanceDaily.animateXY(2000, 2000)
        // get the legend (only possible after setting data)
        val l: Legend = binding.lineChartBalance.legend

        // draw legend entries as lines
        l.form = LegendForm.LINE
    }

    private val dialog by lazy {
        AlertDialog.Builder(context)
            .setTitle("Error!")
            .setIcon(R.drawable.ic_baseline_error_24)
            .setPositiveButton("OK") { _, _ -> }
            .create()
    }

    override fun onResponseFailure(t: Throwable) {
        if (!dialog.isShowing) {
            val msg = when (t) {
                is HttpException -> "Internetga ulanishda xatolik!"
                else -> "Aniqlanmagan xatolik. Iltimos qayta urinib ko'ring."
            }
            dialog.setMessage(msg)
            dialog.show()
        }
    }

    private val dialogMsg by lazy {
        AlertDialog.Builder(context)
            .setTitle("Info!")
            .setPositiveButton("OK") { _, _ -> }
            .create()
    }

    override fun showMessage(str: String) {
        if (!dialogMsg.isShowing) {
            dialogMsg.setMessage(str)
            dialogMsg.show()
        }
    }

    override fun showProgressDialog() = showProgress()

    override fun hideProgressDialog() = hideProgress()

    private fun loadViews() {
        loadLineChartBalance()
        loadLineChartTasks()
        loadLineChartWorkers()
        loadLineChartUsers()
        loadLineChartBalanceDaily()
//        loadCharts()

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val desc = Description()

                if (p2 == 0) {
                    desc.text = "Grouped by ages!"
                }
                if (p2 == 1) {
                    desc.text = "Grouped by manufacture!"
                }
                presenter.changeBottomChartData(p2)

                binding.lineChartGrouped.apply {
                    description = desc
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        presenter = DashboardPresenter(DashboardRepository(api), this)
        presenter.requestDataFromServer()
    }

    private fun loadLineChartBalanceDaily() {
        binding.lineChartBalanceDaily.apply {
            val desc = Description()
            desc.text = "Balance"
            description = desc

            // enable touch gestures
            setTouchEnabled(true)

            // create marker to display box when values are selected
            val mv = MyMarkerView(context, R.layout.custom_marker_view)

            // Set the marker to the chart
            mv.chartView = this
            marker = mv

            // enable scaling and dragging
            isDragEnabled = true
            setScaleEnabled(true)

            // force pinch zoom along both axis
            setPinchZoom(true)

            var xAxis: XAxis
            run {   // // X-Axis Style // //
                xAxis = getXAxis()
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                // vertical grid lines
                xAxis.enableGridDashedLine(10f, 10f, 0f)
            }

            var yAxis: YAxis
            run {   // // Y-Axis Style // //
                yAxis = axisLeft

                // disable dual axis (only use LEFT axis)
                axisRight.isEnabled = false

                // horizontal grid lines
                yAxis.enableGridDashedLine(10f, 10f, 0f)
            }
        }
    }

    private fun setDataBalanceDaily(
        lsBalance: List<BalanceData>,
        lsWorkers: List<WorkersData>
    ) {
        val list: ArrayList<Entry> = arrayListOf()
        lsBalance.forEachIndexed { index, balanceData ->
            list.add(
                Entry(
                    (index).toFloat(),
                    (balanceData.value / lsWorkers[index].value).toFloat()
                )
            )
        }

        val dataSet = LineDataSet(list, "Balance($)")
        dataSet.apply {
            // black lines and points
            color = Color.BLACK
            setCircleColor(Color.BLACK)

            // draw dashed line
            enableDashedLine(10f, 5f, 0f)

            // line thickness and point size
            lineWidth = 1f
            circleRadius = 3f

            // draw points as solid circles
            setDrawCircleHole(false)

            // customize legend entry
            formLineWidth = 1f
            formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            formSize = 15f

            // text size of values
            valueTextSize = 9f

            // draw selection line as dashed
            enableDashedHighlightLine(10f, 5f, 0f)

            // set the filled area
            setDrawFilled(true)
            fillFormatter = IFillFormatter { _, _ ->
                binding.lineChartBalanceDaily.axisLeft.axisMinimum
            }
            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                val drawable = ContextCompat.getDrawable(context!!, R.drawable.color1)
                fillDrawable = drawable
            } else {
                fillColor = Color.BLACK
            }

            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        }

        val dataSets = java.util.ArrayList<ILineDataSet>()
        dataSets.add(dataSet) // add the data sets

        // create a data object with the data sets
        val data = LineData(dataSets)

        // set data
        binding.lineChartBalanceDaily.data = data
//        binding.lineChartBalanceDaily.invalidate()
    }

    private fun loadLineChartUsers() {
        binding.lineChartGrouped.apply {
            setUsePercentValues(true)
            val desc = Description()
            desc.text = "Grouped by ages!"
            description = desc
            setExtraOffsets(5f, 10f, 5f, 5f)

            dragDecelerationFrictionCoef = 0.95f

            centerText = generateCenterSpannableText()

            setExtraOffsets(20f, 0f, 20f, 0f)

            val mv = MyMarkerView(context, R.layout.custom_marker_view)

            // Set the marker to the chart
            mv.chartView = this
            marker = mv

            isDrawHoleEnabled = true
            setHoleColor(Color.WHITE)

            setTransparentCircleColor(Color.WHITE)
            setTransparentCircleAlpha(110)

            holeRadius = 58f
            transparentCircleRadius = 61f

            setDrawCenterText(true)

            rotationAngle = 0f
            // enable rotation of the chart by touch
            isRotationEnabled = true
            isHighlightPerTapEnabled = true
        }
    }

    private fun setDataUsers(ls: List<UsersData>) {
        val entries = ArrayList<PieEntry>()

        val grouped = ls.groupBy { it.age }

        grouped.forEach { (i, list) ->
            entries.add(
                PieEntry(
                    list.size.toFloat(),
                    i.toString()
                )
            )
        }

        Log.d("TTT", grouped.toString())

        val dataSet = PieDataSet(entries, "Results")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors = java.util.ArrayList<Int>()

        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)


        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors

        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f

        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        binding.lineChartGrouped.data = data
        binding.lineChartGrouped.data.dataSet.color

        // undo all highlights
        binding.lineChartGrouped.highlightValues(null)

        binding.lineChartGrouped.invalidate()
    }

    private fun setDataProducts(ls: List<ProductsData>) {
        val entries = ArrayList<PieEntry>()

        val grouped = ls.groupBy { it.manufacturer }

        grouped.forEach { (i, list) ->
            entries.add(
                PieEntry(
                    list.size.toFloat(),
                    i
                )
            )
        }

        Log.d("TTT", grouped.toString())

        val dataSet = PieDataSet(entries, "Results")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors = java.util.ArrayList<Int>()

        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)


        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors

        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f

        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        binding.lineChartGrouped.data = data
        binding.lineChartGrouped.data.dataSet.color

        // undo all highlights
        binding.lineChartGrouped.highlightValues(null)

        binding.lineChartGrouped.invalidate()
    }

    private fun loadLineChartBalance() {
        binding.lineChartBalance.apply {
            val desc = Description()
            desc.text = "Info about balances!"
            description = desc

            // enable touch gestures
            setTouchEnabled(true)

            // create marker to display box when values are selected
            val mv = MyMarkerView(context, R.layout.custom_marker_view)

            // Set the marker to the chart
            mv.chartView = this
            marker = mv

            // enable scaling and dragging
            isDragEnabled = true
            setScaleEnabled(true)

            // force pinch zoom along both axis
            setPinchZoom(true)

            var xAxis: XAxis
            run {   // // X-Axis Style // //
                xAxis = getXAxis()
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                // vertical grid lines
                xAxis.enableGridDashedLine(10f, 10f, 0f)
            }

            var yAxis: YAxis
            run {   // // Y-Axis Style // //
                yAxis = axisLeft

                // disable dual axis (only use LEFT axis)
                axisRight.isEnabled = false

                // horizontal grid lines
                yAxis.enableGridDashedLine(10f, 10f, 0f)
            }
        }
    }

    private fun setDataBalance(ls: List<BalanceData>) {
        val list: ArrayList<Entry> = arrayListOf()
        var maxB = ls[0].value
        ls.forEachIndexed { index, balanceData ->
            list.add(
                Entry(
                    (index).toFloat(),
                    balanceData.value.toFloat()
                )
            )
            if (maxB < balanceData.value)
                maxB = balanceData.value
        }

        val dataSet = LineDataSet(list, "Balance($)")
        dataSet.apply {
            // black lines and points
            color = Color.BLACK
            setCircleColor(Color.BLACK)

            // draw dashed line
            enableDashedLine(10f, 5f, 0f)

            // line thickness and point size
            lineWidth = 1f
            circleRadius = 3f

            // draw points as solid circles
            setDrawCircleHole(false)

            // customize legend entry
            formLineWidth = 1f
            formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            formSize = 15f

            // text size of values
            valueTextSize = 9f

            // draw selection line as dashed
            enableDashedHighlightLine(10f, 5f, 0f)

            // set the filled area
            setDrawFilled(true)
            fillFormatter = IFillFormatter { _, _ ->
                binding.lineChartBalance.axisLeft.axisMinimum
            }
            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                val drawable = ContextCompat.getDrawable(context!!, R.drawable.color5)
                fillDrawable = drawable
            } else {
                fillColor = Color.BLACK
            }

            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        }

        val dataSets = java.util.ArrayList<ILineDataSet>()
        dataSets.add(dataSet) // add the data sets

        // create a data object with the data sets
        val data = LineData(dataSets)

        // set data
        binding.lineChartBalance.data = data
//        binding.lineChartBalance.invalidate()

        val maxBalance = LimitLine(maxB.toFloat(), "")
        binding.maxBalance.text = "$maxB - max"
        maxBalance.lineWidth = 2f
        maxBalance.yOffset = 6f
        maxBalance.enableDashedLine(10f, 10f, 0f)
        maxBalance.labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
        maxBalance.textSize = 10f
        binding.lineChartBalance.axisLeft.setDrawLimitLinesBehindData(true)
        binding.lineChartBalance.axisLeft.addLimitLine(maxBalance)
    }

    private fun loadLineChartTasks() {
        binding.lineChartTasks.apply {
            val desc = Description()
            desc.text = "Info about Tasks!"
            description = desc

            // enable touch gestures
            setTouchEnabled(true)

            // create marker to display box when values are selected

            // create marker to display box when values are selected
            val mv = MyMarkerView(context, R.layout.custom_marker_view)

            // Set the marker to the chart
            mv.chartView = this
            marker = mv

            // enable scaling and dragging
            isDragEnabled = true
            setScaleEnabled(true)

            // force pinch zoom along both axis
            setPinchZoom(true)

            var xAxis: XAxis
            run {   // // X-Axis Style // //
                xAxis = getXAxis()
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                // vertical grid lines
                xAxis.enableGridDashedLine(10f, 10f, 0f)
            }

            var yAxis: YAxis
            run {   // // Y-Axis Style // //
                yAxis = axisLeft

                // disable dual axis (only use LEFT axis)
                axisRight.isEnabled = false

                // horizontal grid lines
                yAxis.enableGridDashedLine(10f, 10f, 0f)
            }
        }
    }

    private fun setDataTasks(ls: List<TasksData>) {
        val list: ArrayList<Entry> = arrayListOf()
        var maxB = ls[0].value
        ls.forEachIndexed { index, balanceData ->
            list.add(
                Entry(
                    (index).toFloat(),
                    balanceData.value.toFloat()
                )
            )
            if (maxB < balanceData.value)
                maxB = balanceData.value
        }

        val dataSet = LineDataSet(list, "Tasks")
        dataSet.apply {
            // black lines and points
            color = Color.BLACK
            setCircleColor(Color.BLACK)

            // draw dashed line
            enableDashedLine(10f, 5f, 0f)

            // line thickness and point size
            lineWidth = 1f
            circleRadius = 3f

            // draw points as solid circles
            setDrawCircleHole(false)

            // customize legend entry
            formLineWidth = 1f
            formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            formSize = 15f

            // text size of values
            valueTextSize = 9f

            // draw selection line as dashed
            enableDashedHighlightLine(10f, 5f, 0f)

            // set the filled area
            setDrawFilled(true)
            fillFormatter = IFillFormatter { _, _ ->
                binding.lineChartTasks.axisLeft.axisMinimum
            }
            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                val drawable = ContextCompat.getDrawable(context!!, R.drawable.color4)
                fillDrawable = drawable
            } else {
                fillColor = Color.BLACK
            }

            mode = LineDataSet.Mode.STEPPED
        }

        val dataSets = java.util.ArrayList<ILineDataSet>()
        dataSets.add(dataSet) // add the data sets

        // create a data object with the data sets
        val data = LineData(dataSets)

        // set data
        binding.lineChartTasks.data = data
//        binding.lineChartTasks.invalidate()

        val maxBalance = LimitLine(maxB.toFloat(), "")
        binding.maxTask.text = "$maxB - max"
        maxBalance.lineWidth = 2f
        maxBalance.yOffset = 6f
        maxBalance.enableDashedLine(10f, 10f, 0f)
        maxBalance.labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
        maxBalance.textSize = 10f
        binding.lineChartTasks.axisLeft.setDrawLimitLinesBehindData(true)
        binding.lineChartTasks.axisLeft.addLimitLine(maxBalance)
    }

    private fun loadLineChartWorkers() {
        binding.lineChartWorkers.apply {
            val desc = Description()
            desc.text = "Info about Workers!"
            description = desc

            // enable touch gestures
            setTouchEnabled(true)

            // create marker to display box when values are selected

            // create marker to display box when values are selected
            val mv = MyMarkerView(context, R.layout.custom_marker_view)

            // Set the marker to the chart
            mv.chartView = this
            marker = mv

            // enable scaling and dragging
            isDragEnabled = true
            setScaleEnabled(true)

            // force pinch zoom along both axis
            setPinchZoom(true)

            var xAxis: XAxis
            run {   // // X-Axis Style // //
                xAxis = getXAxis()
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                // vertical grid lines
                xAxis.enableGridDashedLine(10f, 10f, 0f)
            }

            var yAxis: YAxis
            run {   // // Y-Axis Style // //
                yAxis = axisLeft

                // disable dual axis (only use LEFT axis)
                axisRight.isEnabled = false

                // horizontal grid lines
                yAxis.enableGridDashedLine(10f, 10f, 0f)
            }
        }
    }

    private fun setDataWorkers(ls: List<WorkersData>) {
        val list: ArrayList<Entry> = arrayListOf()
        var maxB = ls[0].value
        ls.forEachIndexed { index, balanceData ->
            list.add(
                Entry(
                    (index).toFloat(),
                    balanceData.value.toFloat()
                )
            )
            if (maxB < balanceData.value)
                maxB = balanceData.value
        }

        val dataSet = LineDataSet(list, "Workers")
        dataSet.apply {
            // black lines and points
            color = Color.BLACK
            setCircleColor(Color.BLACK)

            // draw dashed line
            enableDashedLine(10f, 5f, 0f)

            // line thickness and point size
            lineWidth = 1f
            circleRadius = 3f

            // draw points as solid circles
            setDrawCircleHole(false)

            // customize legend entry
            formLineWidth = 1f
            formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            formSize = 15f

            // text size of values
            valueTextSize = 9f

            // draw selection line as dashed
            enableDashedHighlightLine(10f, 5f, 0f)

            // set the filled area
            setDrawFilled(true)
            fillFormatter = IFillFormatter { _, _ ->
                binding.lineChartWorkers.axisLeft.axisMinimum
            }
            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                val drawable = ContextCompat.getDrawable(context!!, R.drawable.color2)
                fillDrawable = drawable
            } else {
                fillColor = Color.BLACK
            }

            mode = LineDataSet.Mode.LINEAR
        }

        val dataSets = java.util.ArrayList<ILineDataSet>()
        dataSets.add(dataSet) // add the data sets

        // create a data object with the data sets
        val data = LineData(dataSets)

        // set data
        binding.lineChartWorkers.data = data
//        binding.lineChartWorkers.invalidate()

        val maxBalance = LimitLine(maxB.toFloat(), "")
        binding.maxWorkers.text = "$maxB - max"
        maxBalance.lineWidth = 2f
        maxBalance.yOffset = 6f
        maxBalance.enableDashedLine(10f, 10f, 0f)
        maxBalance.labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
        maxBalance.textSize = 10f
        binding.lineChartWorkers.axisLeft.setDrawLimitLinesBehindData(true)
        binding.lineChartWorkers.axisLeft.addLimitLine(maxBalance)
    }

    private fun generateCenterSpannableText(): SpannableString? {
        val s = SpannableString("PieChart\nby Botirali Kozimov")
        s.setSpan(RelativeSizeSpan(1.5f), 0, 8, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 8, s.length - 17, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 8, s.length - 17, 0)
        s.setSpan(RelativeSizeSpan(.65f), 8, s.length - 17, 0)
        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 16, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 16, s.length, 0)
        return s
    }
}
