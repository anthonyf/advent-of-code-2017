import com.af.aoc2017.{Day01, Day02}
import org.scalatest.{FlatSpec, Matchers}

class Tests extends FlatSpec with Matchers {
  "Solutions" should "be correct" in {
    Day01.part1.solve("1122") should equal(3)
    Day01.part1.solve("1111") should equal(4)
    Day01.part1.solve("1234") should equal(0)
    Day01.part1.solve("91212129") should equal(9)
    Day01.part1.solution should equal(1069)

    Day01.part2.solve("1212") should equal(6)
    Day01.part2.solve("1221") should equal(0)
    Day01.part2.solve("123425") should equal(4)
    Day01.part2.solve("123123") should equal(12)
    Day01.part2.solve("12131415") should equal(4)
    Day01.part2.solution should equal(1268)

    Day02.part1.solve("5 1 9 5\n7 5 3\n2 4 6 8") should equal (18)
    Day02.part1.solution should equal (51833)
  }
}
