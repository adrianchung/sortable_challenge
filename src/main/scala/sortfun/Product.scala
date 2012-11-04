package sortfun

import com.codahale.jerkson._

/**
 * Represents a Product.
 * 
 * Decided to use options here, it's possible any of these could be missing. I have to assume that product_name
 * must be present though because otherwise there wouldn't be a listing
 */
case class Product(val product_name: String, val manufacturer: Option[String], val family: Option[String], 
    val model: Option[String], val announced_date: Option[String])

object Product {
  
  /**
   * Convert a JSON string to a Product. It would have been nice to use jerkson's parse to case class feature
   * but announced-date has a hyphen which is an invalid variable name
   */
  def fromJsonString(json: String): Product = {
    val jsonObject = Json.parse[Map[String, String]](json)
    Product(
      jsonObject.get("product_name").get,
      jsonObject.get("manufacturer"),
      jsonObject.get("family"), 
      jsonObject.get("model"),
      jsonObject.get("announced-date")
    )
  }
  
  /**
   * Convert a Product to a JSON string
   */
  def toJsonString(product: Product): String = {
    Json.generate(Map("product_name" -> product.product_name,
			          "manufacturer" -> product.manufacturer,
			          "family" -> product.family,
			          "model" -> product.model,
			          "announced-date" -> product.announced_date))
  }
}