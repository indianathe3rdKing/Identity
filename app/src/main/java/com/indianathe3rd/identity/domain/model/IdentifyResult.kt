package com.indianathe3rd.identity.domain.model

data class IdentifyResult(
    val primaryIdentity: String,
    val primaryCategory: AppCategory,
    val percentage: Float,
    val secondaryIdentities: List<String> = emptyList()
)