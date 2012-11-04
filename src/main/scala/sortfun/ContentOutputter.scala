package sortfun

/**
 * Defines interface for outputting content
 */
trait ContentOutputter {
  /**
   * Output a list of results somewhere
   */
  def output(results: List[Result])
}

/**
 * Implementation of ContentOutputter to print results to a file. Note that if the
 * outputFile exists this file will overwrite that file
 */
class FileContentOutputter(val outputFile: String) extends ContentOutputter {
  private val _outputFile = new java.io.File(outputFile)
  
  @Override
  def output(results: List[Result]) = {
    if(_outputFile.exists) 
      _outputFile.delete
    
    val p = new java.io.PrintWriter(_outputFile)
    try { 
      results.foreach(result => p.println(Result.toJsonString(result)))
	} finally { p.close() }
  }
}