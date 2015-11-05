package com.hrenosoft.maze

/**
  * Created by misher on 11/5/15.
  */
object Point {
  def apply(pntX: Int, pntY: Int):Point = new Point(pntX, pntY)
}

class Point(val x: Int, val y: Int) {
  override def equals(toCheck: Any):Boolean = {
    if (toCheck.isInstanceOf[Point]) {
      val tchk = toCheck.asInstanceOf[Point]
      tchk.x == this.x && tchk.y == this.y
    } else {
      false
    }
  }
  lazy val left = Point(x-1, y)
  lazy val right = Point(x+1, y)
  lazy val above = Point(x, y-1)
  lazy val below = Point(x, y+1)
  override def toString():String = {s"($x, $y)"}
}