package com.github.satoshun.example

import com.google.gson.Gson
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test

class GsonNullableTest {
  val a = """{"a": "test"}"""

  @Test
  fun test() {
    val result = Gson().fromJson(a, Response::class.java)
    println(result)
    println(result.b)
  }

  @Test
  fun test2() {
    val result: Response2 = Json {
      ignoreUnknownKeys = true
      coerceInputValues = true
      encodeDefaults = true
      isLenient = true
    }.decodeFromString(a)
    println(result)
    println(result.b)
  }

  data class Response(
    val a: String,
    val b: Boolean,
  )

  @Serializable
  data class Response2(
    val a: String,
    val b: Boolean,
  )
}
