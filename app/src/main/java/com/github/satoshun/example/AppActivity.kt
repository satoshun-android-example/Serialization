package com.github.satoshun.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AppActivity : AppCompatActivity(R.layout.app_act) {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val hoge = """{"name": "hoge"}"""
    val fuga = """{"name": "fuga"}"""

    val hogeJson = Json.decodeFromString<Response1>(hoge)
    println(hogeJson)
  }
}

@Serializable
class Response1(
  val name: String
)
