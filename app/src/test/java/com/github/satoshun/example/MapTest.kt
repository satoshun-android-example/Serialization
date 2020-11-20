package com.github.satoshun.example

import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.*
import org.junit.Test

class MapTest {
  val json1 = """{"a": "b", "c": "d"}"""
  val json2 = """{"a": "b", "c": 1, "b": true, "d": 1.1}"""

  @Test
  fun test() {
    println(Json.decodeFromString<Map<String, String>>(json1))

    val result = Json.decodeFromString<Map<String, String>>(json1)
    println(Json.encodeToString(result))
  }

  @Test
  fun test2() {
    val json = Json {
      isLenient = true

      serializersModule += SerializersModule {
        contextual(AnySerializer)
      }
    }

//    println(json.decodeFromString<Map<String, Any>>(json1))
    println(json.decodeFromString<Map<String, Any>>(json2))
  }

  @Test
  fun test3() {
    val json = Json {
      serializersModule += SerializersModule {
        contextual(Int.serializer())
        contextual(Long.serializer())
        contextual(Boolean.serializer())
        contextual(String.serializer())
        contextual(Double.serializer())
      }
    }

    println(json.decodeFromString<Map<String, @Contextual Any>>(json1))
    println(json.decodeFromString<Map<String, @Contextual Any>>(json2))
  }
}

object AnySerializer : KSerializer<Any> {
  override val descriptor: SerialDescriptor =
    PrimitiveSerialDescriptor("AnySerializer", PrimitiveKind.STRING)


  override fun deserialize(decoder: Decoder): Any {
    return "a"
  }

  override fun serialize(encoder: Encoder, value: Any) {
    TODO()
  }
}
