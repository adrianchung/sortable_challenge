package sortfun

/**
 * Interface for how a matcher should return  matches
 */
trait Matcher {
  def associate(products: List[Product], listings: List[Listing]): List[Result]
}

object DefaultMatcher extends Matcher {
  /**
   * Associate a list of listings to a list of products. Each listing can match at most one product.
   * This algorithm considers a listing successfully matched to a product if all of the key words in 
   * the product appear in the listing. 
   */
  def associate(products: List[Product], listings: List[Listing]): List[Result] = {
    def stringifyL(listing: Listing): String =
      listing.currency.getOrElse("") + " " + listing.manufacturer.getOrElse("") + " " + 
      listing.price.getOrElse("") + " " + listing.title.getOrElse("")
    
    def stringifyP(product: Product): String =
      // announced-date is intentionally left out
      product.product_name + " " + product.manufacturer.getOrElse("") + " " + 
      product.family.getOrElse("") + " " + product.model.getOrElse("")
    
    // Split by space, hyphen, underscore, only interested in lower case, non-empty matches
    def tokenize(string: String): List[String] =
      string.split(" |-|_").toList.map(_.toLowerCase).filter(!_.isEmpty) 
    
    // Calculate how much product tokens match the listing. Return true only if everything matches
    def evaluate(listingTokens: List[String], productTokens: List[String]): Boolean =
      productTokens.forall(p => listingTokens.exists(s => s.contains(p)))
      
    val productToListing = for { // List[ (String, Listing) ]
      product <- products
      listing <- listings
      if evaluate(tokenize(stringifyL(listing)), tokenize(stringifyP(product)))
    } yield (product.product_name, listing)
    
    // Need to remove duplicate listing entries
    val filteredProductToListing = productToListing.filter(pair => productToListing.count(p => pair._2 == p._2) == 1)
    
    // Group the products by product name
    val groupedProducts = filteredProductToListing.groupBy(_._1) // Map[String, List[String, Listing]]

    // Flatten the lists so that we can create a proper result
    (for {
      product <- groupedProducts
    } yield {
      val y = groupedProducts(product._1).map(_._2).toList
      Result(product._1, y)
    }).toList
  }
}