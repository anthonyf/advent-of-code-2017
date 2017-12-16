import com.af.aoc2017.Day07.InputItem
import com.af.aoc2017._
import org.scalatest.{FlatSpec, Matchers}

class Tests extends FlatSpec with Matchers {
  "Solutions" should "be correct" in {
    Day01.Part1.solve("1122") should equal(3)
    Day01.Part1.solve("1111") should equal(4)
    Day01.Part1.solve("1234") should equal(0)
    Day01.Part1.solve("91212129") should equal(9)
    Day01.Part1.solution should equal(1069)

    Day01.Part2.solve("1212") should equal(6)
    Day01.Part2.solve("1221") should equal(0)
    Day01.Part2.solve("123425") should equal(4)
    Day01.Part2.solve("123123") should equal(12)
    Day01.Part2.solve("12131415") should equal(4)
    Day01.Part2.solution should equal(1268)

    Day02.Part1.solve("5 1 9 5\n7 5 3\n2 4 6 8") should equal (18)
    Day02.Part1.solution should equal (51833)

    Day02.Part2.solve("5 9 2 8\n9 4 7 3\n3 8 6 5") should equal (9)
    Day02.Part2.solution should equal (288)

    Day03.Part1.position(1) should equal((0, 0))
    Day03.Part1.position(2) should equal((1, 0))
    Day03.Part1.position(3) should equal((1, -1))
    Day03.Part1.position(4) should equal((0, -1))
    Day03.Part1.position(5) should equal((-1, -1))
    Day03.Part1.position(6) should equal((-1, 0))
    Day03.Part1.position(7) should equal((-1, 1))

    Day03.Part1.solve(1) should equal(0)
    Day03.Part1.solve(12) should equal(3)
    Day03.Part1.solve(23) should equal(2)
    Day03.Part1.solve(1024) should equal(31)

    Day03.Part1.solution should equal(480)

    Day03.Part2.positions(1) should equal (Seq((0, 0)))
    Day03.Part2.positions(2) should equal (Seq((0, 0), (1, 0)))
    Day03.Part2.positions(3) should equal (Seq((0, 0), (1, 0), (1, -1)))
    Day03.Part2.positions(10) should equal (Seq((0,0), (1,0), (1,-1), (0,-1), (-1,-1), (-1,0), (-1,1), (0,1), (1,1), (2,1)))

    Day03.Part2.solve(1) should equal (1)
    Day03.Part2.solve(2) should equal (1)
    Day03.Part2.solve(3) should equal (2)
    Day03.Part2.solve(7) should equal (10)

    Day03.Part2.solution should equal (349975)

    Day07.loadInput(Day07.input)(0) should equal (InputItem("mqdjo", 83, Seq()))
    Day07.loadInput(Day07.input)(1) should equal (InputItem("jzgxy", 15, Seq("usdayz", "zvbru")))

    Day07.Part1.solution should equal ("dtacyn")

    Day07.Part2.weight("ugml", Day07.Part2.makeTree(Day07.loadInput(Day07.testData))) should equal (251)

    Day07.Part2.solve(Day07.testData) should equal (60)

    Day07.Part2.solution should equal(521)

    Day08.Part1.solve(Day08.testInput) should equal (1)

    Day08.Part1.solution should equal (5075)

    Day08.Part2.solve(Day08.testInput) should equal (10)

    Day08.Part2.solution should equal (7310)
  }
}
