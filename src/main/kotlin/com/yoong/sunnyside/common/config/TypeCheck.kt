package com.yoong.sunnyside.common.config

import com.yoong.sunnyside.common.exception.CustomIllegalArgumentException
import com.yoong.sunnyside.domain.real_estate.enum_class.DataType

class TypeCheck {

    //name 컬럼 가져 오기
    companion object {
        fun realEstateAttribute(argument: String): DataType{
            return when(argument){
                "address", "house_type", "goods_type" -> DataType.STRING
                "security", "rent", "maintenance_cost", "room_count", "floor", "bathroom_count" -> DataType.INT
                "completion_date", "contract_period" -> DataType.DATE
                "is_parked", "is_pat", "is_elevator" -> DataType.BOOLEAN
                else -> throw CustomIllegalArgumentException("Unexpected argument: $argument")
            }
        }
    }

}