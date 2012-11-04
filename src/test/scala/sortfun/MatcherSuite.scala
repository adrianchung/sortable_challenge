package sortfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MatcherSuite extends FunSuite {
  trait TestSets {
    val l1 = """{"title":"Sony DSCW310 Cyber-Shot Digital Camera  (12.1 MP, 4 x Optical Zoom, 2.7 inch LCD) - Silver","manufacturer":"Sony","currency":"GBP","price":"55.99"}"""
    val l2 = """{"title":"Sony DSCW310 Cyber-Shot Digital Camera  (12.1 MP, 4 x Optical Zoom, 2.7 inch LCD) - Silver","manufacturer":"Sony","currency":"GBP","price":"59.99"}"""
    val l3 = """{"title":"Sony DSCW310 Cyber-Shot Digital Camera  (12.1 MP, 4 x Optical Zoom, 2.7 inch LCD) - Silver","manufacturer":"Sony","currency":"GBP","price":"99.99"}"""
      
    val l4 = """{"title":"Canon PowerShot D10 12.1 MP Waterproof Digital Camera with 3x Optical Image Stabilized Zoom and 2.5-inch LCD (Blue/Silver)","manufacturer":"Canon Canada","currency":"CAD","price":"420.33"}"""

    // Purposely generic to match multiple products
    val l5 = """{"title":"Nikon","manufacturer":"Nikon","currency":"CAD","price":"3.67"}"""
    
    val p1 = """{"product_name":"Sony_Cyber-shot_DSC-W310","manufacturer":"Sony","model":"DSC-W310","family":"Cyber-shot","announced-date":"2010-01-06T19:00:00.000-05:00"}"""
    val p2 = """{"product_name":"Nikon_D5000","manufacturer":"Nikon","model":"D5000","announced-date":"2009-04-13T20:00:00.000-04:00"}"""
    val p3 = """{"product_name":"Nikon_D3000","manufacturer":"Nikon","model":"D3000","announced-date":"2009-07-29T20:00:00.000-04:00"}"""
  }
  
  test("Test 3 matches matches everything") {
	new TestSets {
	  val listings = List(Listing.fromJsonString(l1), Listing.fromJsonString(l2), Listing.fromJsonString(l3))
	  val matched = DefaultMatcher.associate(List(Product.fromJsonString(p1)), listings)
	  
	  val expectedResult = Result("Sony_Cyber-shot_DSC-W310", listings)
	  
	  assert(!matched.isEmpty)
	  assert(expectedResult == matched.head)
	}  
  }
  
  test("Test multiple listings only some match") {
    new TestSets {
	  val listings = List(Listing.fromJsonString(l1), Listing.fromJsonString(l2), Listing.fromJsonString(l3), Listing.fromJsonString(l4))
	  val matched = DefaultMatcher.associate(List(Product.fromJsonString(p1)), listings)
	  
	  val expectedListings = List(Listing.fromJsonString(l1), Listing.fromJsonString(l2), Listing.fromJsonString(l3))
	  val expectedResult = Result("Sony_Cyber-shot_DSC-W310", expectedListings)
	  
	  assert(!matched.isEmpty)
	  assert(expectedResult == matched.head)
	}
  }
  
  test("Test mulitple matches filtered") {
    new TestSets {
	  val listings = List(Listing.fromJsonString(l5))
	  val matched = DefaultMatcher.associate(List(Product.fromJsonString(p2), Product.fromJsonString(p3)), listings)
	  
	  assert(matched.isEmpty)
	}
  }
}
