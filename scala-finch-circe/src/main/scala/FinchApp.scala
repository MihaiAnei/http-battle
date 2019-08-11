import cats.effect.IO
import com.twitter.finagle.Http
import com.twitter.finagle.stats.NullStatsReceiver
import com.twitter.finagle.tracing.NullTracer
import com.twitter.util.Await
import io.finch._
import io.finch.circe._

import scala.util.Random

object FinchApp extends App with Endpoint.Module[IO] {
  val random: Random = new Random()

  val api: Endpoint[IO, Int] = get("benchmark") {
    Ok(random.nextInt(999))
  }

  val service = api.toServiceAs[Application.Json]

  Await.ready(Http.server
    .withCompressionLevel(0)
    .withStatsReceiver(NullStatsReceiver)
    .withTracer(NullTracer)
    .serve(":8080", service))
}
