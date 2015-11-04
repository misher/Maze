package com.hrenosoft.maze

/**
  * Created by misher on 11/4/15.
  */
class Maze(private val roadPoints: Array[Point], targetPoint: Point) {
  def isRoadPoint(pnt: Point) = roadPoints.contains(pnt)
  def isTargetPoint(pnt: Point) = targetPoint == pnt

  lazy val topLeft = new Point(
    x = roadPoints.minBy{_.x}.x,
    y = roadPoints.minBy{_.y}.y
  )

  lazy val bottomRight = new Point(
    x = roadPoints.maxBy{_.x}.x,
    y = roadPoints.maxBy{_.y}.y
  )

  override def toString():String = {
    val rows = for(y <- topLeft.y to bottomRight.y)
      yield {
        for (x <- topLeft.x to bottomRight.x)
          yield {
            val pnt: Point = Point(x, y)
            if (isTargetPoint(pnt)) {
              '$'
            } else if (isRoadPoint(pnt)) {
              'X'
            } else {
              ' '
            }
          }
      }.mkString("")
    rows.mkString("\n")
  }

}

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
