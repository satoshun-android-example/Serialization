package com.github.satoshun.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class EnumTest {
  val a = """{"a": "AAA"}"""

  @Test
  fun test() {
    println(Json.decodeFromString<Response>(a))
  }

  @Serializable
  data class Response(
    val a: A
  )

  enum class A {
    AAA,
    BBB
  }
}
