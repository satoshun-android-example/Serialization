//@file:UseSerializers(DateSerializer::class)

package com.github.satoshun.example

import kotlinx.serialization.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.plus
import org.junit.Test
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateTest {
  val time = "2020-09-08T09:34:14.836Z"

  val date = """{"date": "$time"}"""

  @Test
  fun test() {
    println(Json.decodeFromString<DateResponse>(date))
  }

  @Test
  fun test2() {
    val json = Json {
      serializersModule += SerializersModule {
        contextual(Date::class, DateSerializer)
      }
    }
    println(json.decodeFromString<DateResponse2>(date))
  }

  @Serializable
  data class DateResponse(
    @Serializable(with = DateSerializer::class) val date: Date
  )

  @Serializable
  data class DateResponse2(
    @Contextual val date: Date
  )

  @Serializer(forClass = Date::class)
  object DateSerializer : KSerializer<Date> {
    private val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    override fun deserialize(decoder: Decoder): Date {
      val date = decoder.decodeString()
      return df.parse(date)
    }

    override fun serialize(encoder: Encoder, value: Date) {
      encoder.encodeString(value.time.toString())
    }
  }
}
