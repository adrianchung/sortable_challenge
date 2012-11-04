package sortfun

import com.codahale.jerkson._

/**
 * Represents a Result, associating a product name to a list of listings
 */
case class Result(val product_name: String, val listings: List[Listing])

object Result {
  /**
   * Convert a Result to a JSON string
   */
  def toJsonString(result: Result): String = {
    Json.generate(result)
  }
}