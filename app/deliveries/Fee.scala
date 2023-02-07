package deliveries
import play.api.libs.json.Json

case class Fee(delivery_fee:Int = 0)

object Fee {

    implicit val userImplicitReads = Json.reads[Fee]
    implicit val userImplicitWrites = Json.writes[Fee]

}