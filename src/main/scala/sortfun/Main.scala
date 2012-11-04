package sortfun

object Main {
  def main(args: Array[String]) = {
	val cp = new FileContentProvider("listings.txt", "products.txt")
	val co = new FileContentOutputter("results.txt")
	
	val start = System.currentTimeMillis
	co.output(DefaultMatcher.associate(cp.retrieveProducts, cp.retrieveListings))	
	val end = System.currentTimeMillis
	
	println("Done. Execution took: " + (end - start) / 1000 + "s")
  }
}	