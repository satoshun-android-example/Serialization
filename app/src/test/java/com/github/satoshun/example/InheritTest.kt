package com.github.satoshun.example

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class InheritTest {
  val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
  }

  val b = """{"b": "ab"}"""
  val ab = """{"a": 100, "b": "ab"}"""

  @Test
  fun test() {
    val type = object : TypeToken<AB>() {}.type
    println(Gson().fromJson<AB>(ab, type))
    println(json.decodeFromString<AB>(ab))
  }

  @Test
  fun test2() {
    val type = object : TypeToken<AB>() {}.type
    println(Gson().fromJson<AB>(b, type))
    println(json.decodeFromString<AB>(b))
  }

  @Serializable
  data class AB(
    val b: String
  ) : A()

  @Serializable
  open class A(
    val a: Int = 0
  )
}
