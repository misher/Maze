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


