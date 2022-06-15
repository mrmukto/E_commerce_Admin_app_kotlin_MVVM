package com.mrmukto.ecommerceadmin.models

import com.mrmukto.ecommerceadmin.R

data class DashboardItem(
    val icon :Int,
    val tittle: String,
    val type : DashboardItemType
)
enum class DashboardItemType{
    ADD_PRODUCT, VIEW_PRODUCT,ORDER,CATEGORY, REPORT,USER, SETTINGS
}
val dashboardItemList = listOf<DashboardItem>(
    DashboardItem(icon = R.drawable.ic_baseline_add_24, tittle = "Add Product", type =DashboardItemType.ADD_PRODUCT ),
    DashboardItem(icon = R.drawable.ic_baseline_list_24, tittle = "All Product", type = DashboardItemType.VIEW_PRODUCT),
    DashboardItem(icon = R.drawable.ic_baseline_attach_money_24, tittle = "Order", type = DashboardItemType.ORDER),
    DashboardItem(icon = R.drawable.ic_baseline_category_24, tittle = "Category",type=DashboardItemType.CATEGORY),
    DashboardItem(icon = R.drawable.ic_baseline_bar_chart_24, tittle = "Report", type = DashboardItemType.REPORT),
    DashboardItem(icon = R.drawable.ic_baseline_supervised_user_circle_24, tittle = "Manager Users",type = DashboardItemType.USER),
    DashboardItem(icon = R.drawable.ic_baseline_settings_24, tittle = "Settings", type = DashboardItemType.SETTINGS)
)
