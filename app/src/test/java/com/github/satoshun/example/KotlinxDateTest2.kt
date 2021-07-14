package com.github.satoshun.example

import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.plus
import org.junit.Test

class KotlinxDateTest2 {
  val a = "2020-11-19T02:07:39.157Z"
  val b = "2018-11-21T09:28:18.417Z"
  val date = """{"date": "$b"}"""

  @Test
  fun test() {
//    LocalDateTime.parse(a)
//    LocalDateTime.parse(b)

    val json = Json {
      serializersModule += SerializersModule {
      }
    }
    val result = json.decodeFromString<DateResponse>(date)
    println(result)
  }

//  @Serializable
//  data class DateResponse(
//    @Contextual val date: LocalDateTime
//  )

  @Serializable
  data class DateResponse(
    @Contextual val date: Instant
  )
}
