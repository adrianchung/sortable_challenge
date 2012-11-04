package sortfun

import org.codehaus.jackson._
import org.codehaus.jackson.map.ObjectMapper
import com.codahale.jerkson._

/**
 * Represents a Listing.
 * 
 * Decided to use options here, it's possible any of these could be missing. In the sample data manufacturer is not present and would throw otherwise
 */
case class Listing(val title: Option[String], val manufacturer: Option[String], val currency: Option[String], val price: Option[String])

object Listing {
  
  /**
   * Convert a JSON string to a Listing
   */
  def fromJsonString(json: String): Listing = {
    val jsonObject = Json.parse[Map[String, String]](json)
    Listing(
      jsonObject.get("title"),
      jsonObject.get("manufacturer"),
      jsonObject.get("currency"), 
      jsonObject.get("price")
    )
  }
  
  /**
   * Convert a Listing to a JSON string
   */
  def toJsonString(listing: Listing): String = {
    Json.generate(Map("title" -> listing.title,
			          "manufacturer" -> listing.manufacturer,
			          "currency" -> listing.currency,
			          "price" -> listing.price))
  }
}