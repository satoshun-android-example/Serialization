package com.github.satoshun.example

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class ArrayListTest {
  val a = """["a", "b", "c"]"""
  val b = """[1, 2, 3]"""

  @Test
  fun test() {
    val type = object : TypeToken<ArrayList<String>>() {}.type
    println(Gson().fromJson<ArrayList<String>>(a, type))
    println(Json.decodeFromString<ArrayList<String>>(a))
  }

  @Test
  fun test2() {
    val type = object : TypeToken<List<String>>() {}.type
    println(Gson().fromJson<List<String>>(a, type))
    println(Json.decodeFromString<List<String>>(a))
  }

  @Test
  fun test3() {
    val type = object : TypeToken<List<Int>>() {}.type
    println(Gson().fromJson<List<Int>>(b, type))
    println(Json.decodeFromString<List<Int>>(b))
  }
}
