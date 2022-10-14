package com.apiservicepessoa.application.core.exception

import com.sun.xml.internal.ws.developer.Serialization
import java.time.LocalDate

@Serialization
class MsgExceptionResponse (
   var timestamp: LocalDate,
   var status: Int,
   var message: String? = "",
   var details: String? = ""
)