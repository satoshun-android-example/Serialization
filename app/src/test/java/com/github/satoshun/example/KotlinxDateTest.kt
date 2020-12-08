package com.github.satoshun.example

import kotlinx.datetime.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import kotlinx.serialization.modules.plus
import org.junit.Test

class KotlinxDateTest {
  val a = "2020-11-19T02:07:39.157Z"
  val date = """{"date": "$a"}"""

  @Test
  fun test() {
    val json = Json {
      serializersModule += SerializersModule {
        contextual(LocalDateTimeSerializer)
        contextual(InstantSerializer)
      }
    }
    println(json.decodeFromString<DateResponse>(date))
  }

  @Test
  fun test2() {
    val json = Json {
      serializersModule += SerializersModule {
        contextual(LocalDateTimeSerializer)
        contextual(InstantSerializer)
      }
    }

    json.decodeFromString<DateResponse2>(date).let {
      println(it)
      println(it.date.toString())
      println(it.date.toLocalDateTime(TimeZone.UTC))
    }
  }

  @Serializable
  data class DateResponse(
    @Contextual val date: LocalDateTime
  )

  @Serializable
  data class DateResponse2(
    @Contextual val date: Instant
  )

  object LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor
      get() = PrimitiveSerialDescriptor("LocalDateTimeSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDateTime {
      return decoder.decodeString().toLocalDateTime()
    }

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
//      encoder.encodeString(value.time.toString())
    }
  }

  object InstantSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor
      get() = PrimitiveSerialDescriptor("InstantSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Instant {
      return decoder.decodeString().toInstant()
    }

    override fun serialize(encoder: Encoder, value: Instant) {
      encoder.encodeString(value.toString())
    }
  }
}
