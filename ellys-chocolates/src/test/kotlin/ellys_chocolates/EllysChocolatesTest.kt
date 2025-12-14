package ellys_chocolates

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

/*
 * https://community.topcoder.com/stat?c=problem_statement&pm=14681
 */
class EllysChocolatesTest : StringSpec({

    val ellysChocolates = EllysChocolates()

    "Elly has 15 euros, one is sold for 1, 3 can be exchanged for a new one, she can get 22" {
        ellysChocolates.getCount(1, 3, 15) shouldBe 22
    }

    "Elly has 823172 euros, one is sold for 666, 13 can be exchanged for a new one, she can get 1337" {
        ellysChocolates.getCount(666, 13, 823172) shouldBe 1337
    }

    "Elly has 444 euros, one is sold for 666, 222 can be exchanged for a new one, she can get 0" {
        ellysChocolates.getCount(666, 222, 444) shouldBe 0
    }

    "Elly has 1337 euros, one is sold for 41, 4 can be exchanged for a new one, she can get 42" {
        ellysChocolates.getCount(41, 4, 1337) shouldBe 42
    }
})
