package com.github.satoshun.example

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class EnumTest {
  val a = """{"a": "AAA"}"""
  val c = """{"a": "CCC"}"""

  @Test
  fun test() {
    println(Json.decodeFromString<Response>(a))
  }

  @Test
  fun test2() {
    println(Json {
      isLenient = true
      coerceInputValues = true
    }.decodeFromString<Response>(c))
  }

  @Serializable
  data class Response(
    val a: A = A.UNKNOWN
  )

  enum class A {
    AAA,
    BBB,
    UNKNOWN
  }
}
