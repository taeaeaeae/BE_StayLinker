package com.yoong.sunnyside.common.type_class

import java.time.LocalDateTime

sealed interface Cursor {

    data class LongCursor(val value: Long): Cursor
    data class LocalDateTimeCursor(val value: LocalDateTime): Cursor
    data class NullCursor(val value: String?): Cursor
}