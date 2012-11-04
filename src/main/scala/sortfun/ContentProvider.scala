package sortfun

/**
 * Defines interface for getting data
 */
trait ContentProvider {
  /**
   * Retrieve listings
   */
  def retrieveListings(): List[Listing]
  
  /**
   * Retrieve products
   */
  def retrieveProducts(): List[Product]
}

/**
 * Provider that reads listings and products from specified files
 */
class FileContentProvider(val listingFile: String, val productFile: String) extends ContentProvider {
  
  private val _listing = new java.io.File(listingFile)
  private val _product = new java.io.File(productFile)
  
  require(_listing.exists)
  require(_product.exists)
  
  @Override
  def retrieveListings(): List[Listing] = {
    readFromFile(_listing)(Listing.fromJsonString)
  }
  
  @Override
  def retrieveProducts(): List[Product] = {
    readFromFile(_product)(Product.fromJsonString)
  }
  
  private def readFromFile[T](file: java.io.File)(converter: String => T): List[T] = {
    val source = io.Source.fromFile(file)
    val lines = source.getLines.toList.map(line => converter(line))
    source.close
    lines
  }
}