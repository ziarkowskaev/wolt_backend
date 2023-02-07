package controllers
import deliveries._
import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.duration._
import play.api.libs.json._
import java.time.DayOfWeek

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    val deliveries = List(Delivery())
    Ok(Json.toJson(deliveries)).as("application/json")
  }

  def delivery_fee(value: Int, distance: Int, number: Int) = Action { implicit request: Request[AnyContent] =>
    
    val del = Delivery(value, distance, number)
    val time = del.deliver_time

        var fee = 0 

            if(del.delivery_distance > 1000){
                fee +=  200 + 100*((del.delivery_distance.toFloat - 1000)/500).ceil.toInt
            }

            if( del.cart_value <= 1000){
                fee+= 1000 - del.cart_value
            }

            if(del.number_of_items >= 5){
                fee += (del.number_of_items - 4)*50
            }

            if(del.number_of_items >= 12){
                fee += 120
            }

            if(time.getDayOfWeek() == DayOfWeek.FRIDAY  && (time.getHour() >= 15 && time.getHour() <= 19) ){
              fee = (fee.toDouble * 1.2).toInt
            }
      
            if(fee >= 1500){
                fee =  1500
            }

             if(del.cart_value >= 10000){
                fee = 0 
            }

    Ok(Json.toJson(del, Fee(fee))).as("application/json")
  }

  def calculate() = Action(parse.json) { implicit request: Request[JsValue] =>
    val userResult = request.body.validate[Delivery]
    userResult.fold(
      errors => {
        BadRequest(Json.obj("status" -> "error", "message" -> JsError.toJson(errors) ))
      },
      delivery_cost => {
           Ok(Json.toJson(delivery_cost)).as("application/json")
      }
    )
  }

}
