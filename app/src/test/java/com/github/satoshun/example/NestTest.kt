package com.github.satoshun.example

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class NestTest {
  val child = """{"text": "test", "unknown": "10"}"""
  val response = """{"child": $child}"""

  @Test
  fun test() {
    val json = Json {
      ignoreUnknownKeys = true
    }
    println(json.decodeFromString<ParentResponse>(response))
  }
}

@Serializable
data class ParentResponse(
  val child: ChildResponse
)

@Serializable
data class ChildResponse(
  val text: String,
  val a: String = "empty"
)
