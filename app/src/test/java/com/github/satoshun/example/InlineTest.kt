package com.github.satoshun.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.junit.Test

class InlineTest {
  val a = """{"a": "test"}"""

  @Test
  fun test() {
    val result = Json.decodeFromString<Response>(a)
    println(result)
    println(result.a)
  }

  @Serializable
  data class Response(
    @SerialName("a") private val _a: String
  ) {
    val a: InlineResponse get() = InlineResponse(_a)
  }
}

inline class InlineResponse(
  val value: String
)
