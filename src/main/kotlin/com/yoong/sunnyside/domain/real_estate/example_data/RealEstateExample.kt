package com.yoong.sunnyside.domain.real_estate.example_data

class RealEstateExample {

    companion object{
        const val CREATE_REAL_ESTATE_DATA = """
            여기 보시면 될 것 같습니다!!
            
            {
              "name": "모란역 4번 출구 빌라",
              "price": 100000,
              "description": "4층 노옵션 입니다",
              "address": "경기도 성남시",
              "options": [
                {
                  "name": "세탁기",
                  "description": ""  <- 얘는 추후에 필요시 작성
                }
              ],
              "latitude": 0.0,
              "longitude": 0.0,
              "attributes": {
                "COMPLETION_DATE": "2025-04-12T12:12:30.013",
                "HOUSE_TYPE": "VILLA",
                "GOODS_TYPE": "MONTHLY_RENT",
                "SECURITY": 2,
                "RENT": 2,
                "HOUSE_SIZE": 17.62,
                "MAINTENANCE_COST": 50000,
                "ROOM_COUNT": 1,
                "FLOOR": 4,
                "CONTRACT_PERIOD": "2028-04-12T12:12:30.013",
                "BATHROOM_COUNT": 1,
                "IS_PARKED": true,
                "IS_PAT": false,
                "IS_ELEVATOR": false
              }
            }
        """

    const val GET_REAL_ESTATE_DATA_LIST = """
         {
          "totalElements": 0,
          "totalPages": 0,
          "first": true,
          "last": true,
          "size": 0,
          "content": [
            {
              "id": 0,
              "businessId": 0,
              "name": "string",
              "address": "string",
              "price": 0,
              "attributes": {
                "COMPLETION_DATE": "2025-04-12T12:12:30.013",
                "HOUSE_TYPE": "VILLA",
                "GOODS_TYPE": "MONTHLY_RENT",
                "SECURITY": 2,
                "RENT": 2,
                "HOUSE_SIZE": 17.62,
                "MAINTENANCE_COST": 50000,
                "ROOM_COUNT": 1,
                "FLOOR": 4,
                "CONTRACT_PERIOD": "2028-04-12T12:12:30.013",
                "BATHROOM_COUNT": 1,
                "IS_PARKED": true,
                "IS_PAT": false,
                "IS_ELEVATOR": false
              }
            }
          ],
          "number": 0,
          "sort": {
            "empty": true,
            "sorted": true,
            "unsorted": true
          },
          "numberOfElements": 0,
          "pageable": {
            "offset": 0,
            "sort": {
              "empty": true,
              "sorted": true,
              "unsorted": true
            },
            "paged": true,
            "pageNumber": 0,
            "pageSize": 0,
            "unpaged": true
          },
          "empty": true
        }
    """
    }
}