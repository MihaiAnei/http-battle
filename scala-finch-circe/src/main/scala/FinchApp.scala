import cats.effect.IO
import com.twitter.finagle.Http
import com.twitter.finagle.stats.NullStatsReceiver
import com.twitter.finagle.tracing.NullTracer
import com.twitter.util.Await
import io.finch.circe._
import io.finch._

object FinchApp extends App with Endpoint.Module[IO] {
  val api: Endpoint[IO, List[Integer]] = post("benchmark" :: jsonBody[List[Integer]]) { numbers: List[Integer] =>
    Ok(numbers.distinct)
  }

  val service = api.toServiceAs[Application.Json]

  Await.ready(Http.server
    .withCompressionLevel(0)
    .withStatsReceiver(NullStatsReceiver)
    .withTracer(NullTracer)
    .serve(":8080", service))
}
