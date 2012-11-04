package sortfun

import com.codahale.jerkson._

object scratch {
  val m1 = Map[String, String]()                  //> m1  : scala.collection.immutable.Map[String,String] = Map()
  val a = Map[String, List[(Int, Int)]]("hi" -> List( (1,2), (3,4) ))
                                                  //> a  : scala.collection.immutable.Map[String,List[(Int, Int)]] = Map(hi -> Lis
                                                  //| t((1,2), (3,4)))
  a.map(f => f._2).flatten.map(_._2)              //> res0: scala.collection.immutable.Iterable[Int] = List(2, 4)

  val listing = """{"title":"LED Flash Macro Ring Light (48 X LED) with 6 Adapter Rings for For Canon/Sony/Nikon/Sigma Lenses","manufacturer":"Neewer Electronics Accessories","currency":"CAD","price":"35.99"}"""
                                                  //> listing  : java.lang.String = {"title":"LED Flash Macro Ring Light (48 X LED
                                                  //| ) with 6 Adapter Rings for For Canon/Sony/Nikon/Sigma Lenses","manufacturer"
                                                  //| :"Neewer Electronics Accessories","currency":"CAD","price":"35.99"}
  
  val listingMap = Json.parse[Map[String, String]](listing)
                                                  //> listingMap  : Map[String,String] = Map(price -> 35.99, currency -> CAD, titl
                                                  //| e -> LED Flash Macro Ring Light (48 X LED) with 6 Adapter Rings for For Cano
                                                  //| n/Sony/Nikon/Sigma Lenses, manufacturer -> Neewer Electronics Accessories)
                                                  
  Json.parse[Listing](listing)                    //> res1: sortfun.Listing = Listing(Some(LED Flash Macro Ring Light (48 X LED) w
                                                  //| ith 6 Adapter Rings for For Canon/Sony/Nikon/Sigma Lenses),Some(Neewer Elect
                                                  //| ronics Accessories),Some(CAD),Some(35.99))
                                                  
  Json.generate(listingMap)                       //> res2: String = {"price":"35.99","currency":"CAD","title":"LED Flash Macro Ri
                                                  //| ng Light (48 X LED) with 6 Adapter Rings for For Canon/Sony/Nikon/Sigma Lens
                                                  //| es","manufacturer":"Neewer Electronics Accessories"}
  
  val product = """{"product_name":"Sony_Cyber-shot_DSC-W310","manufacturer":"Sony","model":"DSC-W310","family":"Cyber-shot","announced-date":"2010-01-06T19:00:00.000-05:00"}"""
                                                  //> product  : java.lang.String = {"product_name":"Sony_Cyber-shot_DSC-W310","ma
                                                  //| nufacturer":"Sony","model":"DSC-W310","family":"Cyber-shot","announced-date"
                                                  //| :"2010-01-06T19:00:00.000-05:00"}

  val productMap = Json.parse[Map[String, String]](product)
                                                  //> productMap  : Map[String,String] = Map(announced-date -> 2010-01-06T19:00:00
                                                  //| .000-05:00, model -> DSC-W310, product_name -> Sony_Cyber-shot_DSC-W310, man
                                                  //| ufacturer -> Sony, family -> Cyber-shot)
  
 
  Json.generate(productMap)                       //> res3: String = {"announced-date":"2010-01-06T19:00:00.000-05:00","model":"DS
                                                  //| C-W310","product_name":"Sony_Cyber-shot_DSC-W310","manufacturer":"Sony","fam
                                                  //| ily":"Cyber-shot"}
                                                  
  val productsF = new java.io.File("/Users/adrian.chung/dev/scala_coursera/sortable_challenge/products.txt")
                                                  //> productsF  : java.io.File = /Users/adrian.chung/dev/scala_coursera/sortable
                                                  //| _challenge/products.txt
  
  val listingsF = new java.io.File("/Users/adrian.chung/dev/scala_coursera/sortable_challenge/products.txt")
                                                  //> listingsF  : java.io.File = /Users/adrian.chung/dev/scala_coursera/sortable
                                                  //| _challenge/products.txt
  
  val cp = new FileContentProvider(listingsF.getPath, productsF.getPath)
                                                  //> cp  : sortfun.FileContentProvider = sortfun.FileContentProvider@4cdada24
	val listings = cp.retrieveListings        //> listings  : List[sortfun.Listing] = List(Listing(None,Some(Sony),None,None)
                                                  //| , Listing(None,Some(Samsung),None,None), Listing(None,Some(Nikon),None,None
                                                  //| ), Listing(None,Some(Samsung),None,None), Listing(None,Some(Fujifilm),None,
                                                  //| None), Listing(None,Some(Casio),None,None), Listing(None,Some(Canon),None,N
                                                  //| one), Listing(None,Some(Leica),None,None), Listing(None,Some(Fujifilm),None
                                                  //| ,None), Listing(None,Some(Sony),None,None), Listing(None,Some(Fujifilm),Non
                                                  //| e,None), Listing(None,Some(Olympus),None,None), Listing(None,Some(Canon),No
                                                  //| ne,None), Listing(None,Some(Canon),None,None), Listing(None,Some(Casio),Non
                                                  //| e,None), Listing(None,Some(Olympus),None,None), Listing(None,Some(Kodak),No
                                                  //| ne,None), Listing(None,Some(Toshiba),None,None), Listing(None,Some(Sony),No
                                                  //| ne,None), Listing(None,Some(Casio),None,None), Listing(None,Some(Sony),None
                                                  //| ,None), Listing(None,Some(Canon),None,None), Listing(None,Some(Fujifilm),No
                                                  //| ne,None), Listing(None,
                                                  //| Output exceeds cutoff limit.
	val products = cp.retrieveProducts        //> products  : List[sortfun.Product] = List(Product(Sony_Cyber-shot_DSC-W310,S
                                                  //| ome(Sony),Some(Cyber-shot),Some(DSC-W310),Some(2010-01-06T19:00:00.000-05:0
                                                  //| 0)), Product(Samsung_TL240,Some(Samsung),None,Some(TL240),Some(2010-01-05T1
                                                  //| 9:00:00.000-05:00)), Product(Nikon-s6100,Some(Nikon),Some(Coolpix),Some(S61
                                                  //| 00),Some(2011-02-08T19:00:00.000-05:00)), Product(Samsung_TL220,Some(Samsun
                                                  //| g),None,Some(TL220),Some(2009-08-12T20:00:00.000-04:00)), Product(Fujifilm-
                                                  //| T205,Some(Fujifilm),Some(FinePix),Some(T205),Some(2011-02-15T19:00:00.000-0
                                                  //| 5:00)), Product(Casio_QV-5000SX,Some(Casio),None,Some(QV-5000SX),Some(1998-
                                                  //| 04-19T20:00:00.000-04:00)), Product(Canon_Digital_IXUS_130_IS,Some(Canon),S
                                                  //| ome(Digital IXUS),Some(130 IS),Some(2010-02-07T19:00:00.000-05:00)), Produc
                                                  //| t(Leica_Digilux,Some(Leica),None,Some(Digilux),Some(1998-09-15T20:00:00.000
                                                  //| -04:00)), Product(Fujifilm_FinePix_1500,Some(Fujifilm),Some(FinePix),Some(1
                                                  //| 500),Some(1999-05-17T20
                                                  //| Output exceeds cutoff limit.
  
  val test = """{"title":"LED Flash Macro Ring Light (48 X LED) with 6 Adapter Rings for For Canon/Sony/Nikon/Sigma Lenses","manufacturer":"Neewer Electronics Accessories","currency":"CAD","price":"35.99"}"""
                                                  //> test  : java.lang.String = {"title":"LED Flash Macro Ring Light (48 X LED) 
                                                  //| with 6 Adapter Rings for For Canon/Sony/Nikon/Sigma Lenses","manufacturer":
                                                  //| "Neewer Electronics Accessories","currency":"CAD","price":"35.99"}
  Listing.fromJsonString(test)                    //> res4: sortfun.Listing = Listing(Some(LED Flash Macro Ring Light (48 X LED) 
                                                  //| with 6 Adapter Rings for For Canon/Sony/Nikon/Sigma Lenses),Some(Neewer Ele
                                                  //| ctronics Accessories),Some(CAD),Some(35.99))
                                                  
  val result1 = new Result("my_product_name",
	    List(new Listing(Option[String]("a"), Option[String]("b"), Option[String]("c"), Option[String]("d")),
	        new Listing(Option[String]("e"), Option[String]("f"), Option[String]("g"), Option[String]("h"))))
                                                  //> result1  : sortfun.Result = Result(my_product_name,List(Listing(Some(a),Som
                                                  //| e(b),Some(c),Some(d)), Listing(Some(e),Some(f),Some(g),Some(h))))
	
	val result2 = new Result("my_product_name2",
	    List(new Listing(Option[String]("a1"), Option[String]("b"), Option[String]("c"), Option[String]("d")),
	        new Listing(Option[String]("e1"), Option[String]("f"), Option[String]("g"), Option[String]("h"))))
                                                  //> result2  : sortfun.Result = Result(my_product_name2,List(Listing(Some(a1),S
                                                  //| ome(b),Some(c),Some(d)), Listing(Some(e1),Some(f),Some(g),Some(h))))
	
	val all = List(result1, result2)          //> all  : List[sortfun.Result] = List(Result(my_product_name,List(Listing(Some
                                                  //| (a),Some(b),Some(c),Some(d)), Listing(Some(e),Some(f),Some(g),Some(h)))), R
                                                  //| esult(my_product_name2,List(Listing(Some(a1),Some(b),Some(c),Some(d)), List
                                                  //| ing(Some(e1),Some(f),Some(g),Some(h)))))
	
	val outputter = new FileContentOutputter("results.txt")
                                                  //> outputter  : sortfun.FileContentOutputter = sortfun.FileContentOutputter@2b
                                                  //| be2893
	//outputter.output(all)
	
	// TEST A MATCH
	val l1 = """{"title":"Sony DSCW310 Cyber-Shot Digital Camera  (12.1 MP, 4 x Optical Zoom, 2.7 inch LCD) - Silver","manufacturer":"Sony","currency":"GBP","price":"55.99"}"""
                                                  //> l1  : java.lang.String = {"title":"Sony DSCW310 Cyber-Shot Digital Camera  
                                                  //| (12.1 MP, 4 x Optical Zoom, 2.7 inch LCD) - Silver","manufacturer":"Sony","
                                                  //| currency":"GBP","price":"55.99"}
  val l2 = """{"title":"Sony DSCW310 Cyber-Shot Digital Camera  (12.1 MP, 4 x Optical Zoom, 2.7 inch LCD) - Silver","manufacturer":"Sony","currency":"GBP","price":"59.99"}"""
                                                  //> l2  : java.lang.String = {"title":"Sony DSCW310 Cyber-Shot Digital Camera  
                                                  //| (12.1 MP, 4 x Optical Zoom, 2.7 inch LCD) - Silver","manufacturer":"Sony","
                                                  //| currency":"GBP","price":"59.99"}
  val l3 = """{"title":"Sony DSCW310 Cyber-Shot Digital Camera  (12.1 MP, 4 x Optical Zoom, 2.7 inch LCD) - Silver","manufacturer":"Sony","currency":"GBP","price":"99.99"}"""
                                                  //> l3  : java.lang.String = {"title":"Sony DSCW310 Cyber-Shot Digital Camera  
                                                  //| (12.1 MP, 4 x Optical Zoom, 2.7 inch LCD) - Silver","manufacturer":"Sony","
                                                  //| currency":"GBP","price":"99.99"}
                                                  
  val p1 = """{"product_name":"Sony_Cyber-shot_DSC-W310","manufacturer":"Sony","model":"DSC-W310","family":"Cyber-shot","announced-date":"2010-01-06T19:00:00.000-05:00"}"""
                                                  //> p1  : java.lang.String = {"product_name":"Sony_Cyber-shot_DSC-W310","manufa
                                                  //| cturer":"Sony","model":"DSC-W310","family":"Cyber-shot","announced-date":"2
                                                  //| 010-01-06T19:00:00.000-05:00"}
  
	DefaultMatcher.associate(List(Product.fromJsonString(p1)), List(Listing.fromJsonString(l1), Listing.fromJsonString(l2), Listing.fromJsonString(l3)))
                                                  //> res5: List[sortfun.Result] = List(Result(Sony_Cyber-shot_DSC-W310,List(List
                                                  //| ing(Some(Sony DSCW310 Cyber-Shot Digital Camera  (12.1 MP, 4 x Optical Zoom
                                                  //| , 2.7 inch LCD) - Silver),Some(Sony),Some(GBP),Some(55.99)), Listing(Some(S
                                                  //| ony DSCW310 Cyber-Shot Digital Camera  (12.1 MP, 4 x Optical Zoom, 2.7 inch
                                                  //|  LCD) - Silver),Some(Sony),Some(GBP),Some(59.99)), Listing(Some(Sony DSCW31
                                                  //| 0 Cyber-Shot Digital Camera  (12.1 MP, 4 x Optical Zoom, 2.7 inch LCD) - Si
                                                  //| lver),Some(Sony),Some(GBP),Some(99.99)))))
}