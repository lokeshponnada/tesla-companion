package com.lokesh.teslacore.data

data class VehicleInfo(
    val api_version: Int,
    val backseat_token: Any,
    val backseat_token_updated_at: Any,
    val calendar_enabled: Boolean,
    val color: Any,
    val display_name: String,
    val id: Long,
    val id_s: String,
    val in_service: Boolean,
    val option_codes: String,
    val state: String,
    val tokens: List<String>,
    val vehicle_id: Int,
    val vin: String
)