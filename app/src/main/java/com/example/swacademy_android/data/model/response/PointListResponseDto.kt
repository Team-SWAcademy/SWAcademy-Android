package com.example.swacademy_android.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class PointListResponseDto(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val content: List<Content>,
        val pageable: Pageable,
        val first: Boolean,
        val last: Boolean,
        val size: Long,
        val number: Long,
        val sort: Sort2,
        val numberOfElements: Long,
        val empty: Boolean,
    ) {
        @Serializable
        data class Content(
            val useAtParsed: String,
            val useAt: String,
            val point: Long,
            val pointTypeImageUrl: String,
            val rentalLocationName: String,
            val rentalLocationAddress: String,
            val returnLocationName: String,
            val returnLocationAddress: String,
        )

        @Serializable
        data class Pageable(
            val pageNumber: Long,
            val pageSize: Long,
            val sort: Sort,
            val offset: Long,
            val paged: Boolean,
            val unpaged: Boolean,
        ) {
            @Serializable
            data class Sort(
                val empty: Boolean,
                val unsorted: Boolean,
                val sorted: Boolean,
            )
        }

        @Serializable
        data class Sort2(
            val empty: Boolean,
            val unsorted: Boolean,
            val sorted: Boolean,
        )
    }
}