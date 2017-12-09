package com.af.aoc2017

import scala.collection.immutable.Map

object Day03 {

  object Part1 {
    def position(n: Int): (Int, Int) = {
      assert(n > 0)
      val dirOffsets = Array((1, 0), (0, -1), (-1, 0), (0, 1))
      def d(n: Int, span: Int, curr: Int, iDir:Int, acc: (Int, Int)): (Int, Int) = {
        if(n == 0)
          acc
        else {
          val (x, y) = acc
          if(curr == 0) {
            val newDir = (iDir + 1)  % 4
            val spanIncrement = if (Set(0, 2).contains(newDir)) 1 else 0
            val newSpan = span + spanIncrement
            val (ox, oy) = dirOffsets(newDir)
            d(n-1, newSpan, newSpan-1, newDir, (x + ox, y + oy))
          } else {
            val (ox, oy) = dirOffsets(iDir)
            d(n-1, span, curr - 1, iDir, (x + ox, y + oy))
          }
        }
      }
      d(n-1, 1, 1, 0, (0, 0))
    }

    def solve(n: Int) = {
      val (x, y) = position(n)
      Math.abs(x) + Math.abs(y)
    }
    val solution = solve(347991)
  }

  object Part2 {
    def positions(n: Int): Seq[(Int, Int)] = {
      assert(n > 0)
      val dirOffsets = Array((1, 0), (0, -1), (-1, 0), (0, 1))
      def d(n: Int, span: Int, curr: Int, iDir:Int, acc: List[(Int, Int)]): List[(Int, Int)] = {
        if(n == 0)
          acc
        else {
          val (x, y) = acc.head
          if(curr == 0) {
            val newDir = (iDir + 1)  % 4
            val spanIncrement = if (Set(0, 2).contains(newDir)) 1 else 0
            val newSpan = span + spanIncrement
            val (ox, oy) = dirOffsets(newDir)
            d(n-1, newSpan, newSpan-1, newDir, (x + ox, y + oy) :: acc)
          } else {
            val (ox, oy) = dirOffsets(iDir)
            d(n-1, span, curr - 1, iDir, (x + ox, y + oy) :: acc)
          }
        }
      }
      d(n-1, 1, 1, 0, List((0, 0))).reverse
    }

    def solve(n: Int): Int = {
      assert(n > 0)
      if(n == 1) return 1
      val offsets = Seq((1, 0), (1, -1), (0, -1), (-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1))
      val ps = positions(n).drop(1)
      val sums = ps
        .foldLeft(Map(((0, 0), 1))) { case (cells, (x, y)) =>
          val sum = {
            offsets.map { case (ox, oy) =>
              val neighborIndex = (x + ox, y + oy)
              if(cells.contains(neighborIndex)) {
                cells(neighborIndex)
              } else {
                0
              }
            }
          }.sum
          if (sum > n) return sum
          cells + (((x, y), sum))
        }
      sums(ps.last)
    }

    val solution = solve(347991)
  }
}
