package com.github.satoshun.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.reflect.full.findAnnotation

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

  @Test
  fun test3() {
    println(Json {
      isLenient = true
      coerceInputValues = true
    }.encodeToString(Response(a = A.AAA)))

    println(Json {
      isLenient = true
      coerceInputValues = true
    }.encodeToString(Response(a = A.BBB)))
  }

  @Test
  fun test4() {
    println(A.AAA::class.findAnnotation<SerialName>())
    println(A.BBB::class.findAnnotation<SerialName>())
    println(A.BBB.declaringClass.getField(A.BBB.name).getAnnotation(SerialName::class.java))
    println(A.AAA.declaringClass.getField(A.AAA.name).getAnnotation(SerialName::class.java))
    println(A::class.java.getField(A.AAA.name).getAnnotation(SerialName::class.java))
    println(A::class.java.getField(A.BBB.name).getAnnotation(SerialName::class.java))
  }

  @Serializable
  data class Response(
    val a: A = A.UNKNOWN
  )

  @Serializable
  enum class A {
    AAA,

    @SerialName("bbb")
    BBB,

    UNKNOWN
  }
}
