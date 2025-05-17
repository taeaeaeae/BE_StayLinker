package com.yoong.sunnyside.domain.real_estate.enum_class

enum class RealEstateAttributeData(val dataType: DataType) {
    COMPLETION_DATE(DataType.DATE),
    HOUSE_TYPE(DataType.STRING),
    GOODS_TYPE(DataType.STRING),
    SECURITY(DataType.INT),
    RENT(DataType.INT),
    HOUSE_SIZE(DataType.DOUBLE),
    MAINTENANCE_COST(DataType.INT),
    ROOM_COUNT(DataType.INT),
    FLOOR(DataType.INT),
    CONTRACT_PERIOD(DataType.DATE),
    BATHROOM_COUNT(DataType.INT),
    IS_PARKED(DataType.BOOLEAN),
    IS_PAT(DataType.BOOLEAN),
    IS_ELEVATOR(DataType.BOOLEAN),
}