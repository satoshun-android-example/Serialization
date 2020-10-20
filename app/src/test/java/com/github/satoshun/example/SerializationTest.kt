package com.github.satoshun.example

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class SerializationTest {
  val hoge = """{"name": "hoge"}"""
  val fuga = """{"name": "fuga"}"""
  val nuga = """{"name": "NUGA"}"""
  val unknown = """{"name": "unknown"}"""

  @Test
  fun test() {
    println(Json.decodeFromString<Response1>(hoge))
    println(Json.decodeFromString<Response1>(fuga))
    println(Gson().fromJson(hoge, Response1::class.java))
    println(Gson().fromJson(fuga, Response1::class.java))
  }

  @Test
  fun test2() {
    val json = Json {
      ignoreUnknownKeys = true
      coerceInputValues = true
    }
    println(json.decodeFromString<Response2>(hoge))
    println(json.decodeFromString<Response2>(fuga))
    println(json.decodeFromString<Response2>(unknown))
  }

  @Test
  fun test3() {
    val json = Json {
      ignoreUnknownKeys = true
      coerceInputValues = true
    }
    println(json.decodeFromString<Response2>(hoge))
    println(json.decodeFromString<Response2>(fuga))
    println(json.decodeFromString<Response2>(nuga))
    println(json.decodeFromString<Response2>(unknown))
  }

  @Test
  fun test4() {
    println(Gson().fromJson(hoge, Response3::class.java))
    println(Gson().fromJson(fuga, Response3::class.java))
    println(Gson().fromJson(unknown, Response3::class.java))
  }

  @Test
  fun test5() {
    val gson = GsonBuilder()
      .registerTypeAdapterFactory(LowercaseEnumTypeAdapterFactory())
      .create()
    println(gson.fromJson(hoge, Response3::class.java))
    println(gson.fromJson(fuga, Response3::class.java))
    println(gson.fromJson(unknown, Response3::class.java))
  }
}

@Serializable
data class Response1(
  val name: String
)

@Serializable
data class Response2(
  val name: Name = Name.EMPTY
)

@Serializable
enum class Name {
  @SerialName("hoge") HOGEHGOE,
  @SerialName("fuga") FUGAFUGA,
  NUGA,
  EMPTY
}

data class Response3(
  val name: Name2?
)

enum class Name2 {
  @SerializedName("hoge") HOGEHGOE,
  @SerializedName("fuga") FUGAFUGA,
  EMPTY
}
