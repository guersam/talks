import shapeless._

case class Address(
                    line1: String,
                    line2: String,
                    postalCode: String
                  )

case class Conference(
                       name: String,
                       year: Int,
                       address: Address
                     )

val scalanight2018 =
  Conference(
    "ScalaNight",
    2018,
    Address(
      "서울시 서대문구 연희로2길 62",
      "한빛미디어",
      "03785"
    ))

trait Show[A] {
  def show(x: A): String
}

object Show {

  def apply[A](implicit ev: Show[A]): Show[A] = ev

  def instance[A](f: A => String): Show[A] =
    new Show[A] { def show(x: A): String = f(x) }

  // primitive instances
  implicit val showForInt:    Show[Int]    = instance(_.toString)
  implicit val showForString: Show[String] = instance(_.toString)

  // generic derivations
}

Show[Int].show(1)
//Show[Int :: String :: HNil].show(42 :: "goyang2" :: HNil)
//Show[Conference].show(scalanight2018)

