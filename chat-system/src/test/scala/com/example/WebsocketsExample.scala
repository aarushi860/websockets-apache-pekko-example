package com.example

import org.apache.pekko.NotUsed
import org.apache.pekko.actor.testkit.typed.scaladsl.ActorTestKit
import org.apache.pekko.http.scaladsl.model.ws.{BinaryMessage, Message, TextMessage}
import org.apache.pekko.http.scaladsl.server.Directives
import org.apache.pekko.http.scaladsl.server.Directives.path
import org.apache.pekko.http.scaladsl.testkit.{ScalatestRouteTest, WSProbe}
import org.apache.pekko.http.scaladsl.testkit.WSTestRequestBuilding.WS
import org.apache.pekko.stream.scaladsl.{Flow, Sink, Source}
import org.apache.pekko.util.ByteString
import org.scalatest.wordspec.AnyWordSpecLike

import scala.concurrent.duration.*

class WebsocketsExample extends  AnyWordSpecLike with ScalatestRouteTest {

  "greeter service" in {

    def greeter: Flow[Message, Message, Any] = Flow[Message].mapConcat {
      case tm: TextMessage => TextMessage(Source.single("Hello ") ++ tm.textStream ++ Source.single("!")) :: Nil
      case bm: BinaryMessage => {
        bm.dataStream.runWith(Sink.ignore)
        Nil
      }
    }

    val wbRoute = path("greeter") {
      Directives.handleWebSocketMessages(greeter)
    }

    val wsClient: WSProbe = WSProbe()
    WS("/greeter", wsClient.flow) ~> wbRoute ~>
      check {
        wsClient.sendMessage("Peter")
        wsClient.expectMessage("Hello Peter!")

        wsClient.sendMessage(BinaryMessage(ByteString("abc")))
        wsClient.expectNoMessage(100.millis)

      }
  }
}
