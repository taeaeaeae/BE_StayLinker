package com.yoong.sunnyside.common.exception

class ModelNotFoundException(
    msg: String
): RuntimeException("$msg is not found")