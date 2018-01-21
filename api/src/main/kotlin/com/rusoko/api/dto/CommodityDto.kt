package com.rusoko.api.dto

data class CommodityDto(
        val id: Int,
        val commodityCode: String,
        val commodityName: String,
        val measure: String,
        val producer: String
)