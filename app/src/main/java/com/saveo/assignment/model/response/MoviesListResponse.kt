package com.saveo.assignment.model.response

import java.io.Serializable

data class MoviesListResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Results>,
    val total_pages: Int,
    val total_results: Int
): Serializable