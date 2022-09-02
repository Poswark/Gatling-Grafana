import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class demostore extends Simulation {
    val domain = "http://demostore.gatling.io/"
    val httpProtocol = http
        .baseUrl(domain)
    
    val demostore = exec(http("demostore")
                        .post("demostore.gatling.io")
                        //.headers(HttpHeadersValues.commonHeaders)
                        //.body(StringBody( """ {"message": "demostore"} """.stripMargin)).asJSON
                        .check(status.is(200)))

    // val commonHeaders = Map(
    //             "code" -> "fb573145-13ef-4bee-b1e6-129749632cf7",
    //             "name" -> "test",
    //             "data" -> [{"value": "Connection: keep-alive", "type": "string"}],

    val scn = scenario("demostore")
        .exec(http("Home")
			.get("/"))
		.pause(2)

setUp(
  scn.inject(
  constantUsersPerSec(10).during(5),
  constantUsersPerSec(10).during(10), 
  constantUsersPerSec(10).during(15), 
  constantUsersPerSec(10).during(20), 
  constantUsersPerSec(10).during(25),
  constantUsersPerSec(10).during(30), 
  constantUsersPerSec(10).during(35), 
  constantUsersPerSec(10).during(40), 

  ).protocols(httpProtocol)
)

}


