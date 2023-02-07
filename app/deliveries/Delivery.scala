package deliveries
import java.time._
import scala.math._
import play.api.libs.json.Json


case class Delivery (cart_value: Int = 0 ,delivery_distance: Int = 0 ,number_of_items: Int = 0, deliver_time: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC))



object Delivery {

    implicit val userImplicitReads = Json.reads[Delivery]
    implicit val userImplicitWrites = Json.writes[Delivery]

}

