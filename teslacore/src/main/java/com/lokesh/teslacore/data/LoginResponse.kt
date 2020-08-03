package com.lokesh.teslacore.data

data class LoginResponse(
    val access_token: String?,
    val created_at: Int?,
    val expires_in: Int?,
    val refresh_token: String?,
    val token_type: String?
)