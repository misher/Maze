package com.hrenosoft.maze

import org.scalatest.FlatSpec

/**
  * Created by misher on 11/4/15.
  */
class PointTest extends FlatSpec {
  private val pnt = Point(2, 2)
  "Point (2, 2)" should "be equal to another Point(2, 2)" in {
    assert(pnt == Point(2, 2))
  }
  it should "have Point(1, 2) left" in {
    assert(pnt.left == Point(1, 2))
  }
  it should "have Point(3, 2) right" in {
    assert(pnt.right == Point(3, 2))
  }
  it should "have Point(2, 1) above" in {
    assert(pnt.above == Point(2, 1))
  }
  it should "have Point(2, 3) below" in {
    assert(pnt.below== Point(2, 3))
  }
}
