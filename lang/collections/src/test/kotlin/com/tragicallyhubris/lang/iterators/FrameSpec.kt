package com.tragicallyhubris.lang.iterators

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class FrameSpec : DescribeSpec({

    describe("frame") {
        val frame = Frame<Int>()

        it("hasPrevious is false") { frame.hasPrevious.shouldBe(false) }
        it("hasCurrent is false") { frame.hasCurrent.shouldBe(false) }
        it("hasNext is false") { frame.hasNext.shouldBe(false) }

        it("has null previous") { frame.previous.shouldBeNull() }
        it("has null current") { frame.current.shouldBeNull() }
        it("has null next") { frame.next.shouldBeNull() }

        frame.onNext(1)

        it("has null previous") { frame.previous.shouldBeNull() }
        it("has null current") { frame.current.shouldBeNull() }
        it("has 1 next") { frame.next.shouldBe(1) }

        frame.onNext(2)

        it("has null previous") { frame.previous.shouldBeNull() }
        it("has 1 current") { frame.current.shouldBe(1) }
        it("has 2 next") { frame.next.shouldBe(2) }

        frame.onNext(3)

        it("has 1 previous") { frame.previous.shouldBe(1) }
        it("has 2 current") { frame.current.shouldBe(2) }
        it("has 3 next") { frame.next.shouldBe(3) }

        it("hasPrevious is true") { frame.hasPrevious.shouldBe(true) }
        it("hasCurrent is true") { frame.hasCurrent.shouldBe(true) }
        it("hasNext is true") { frame.hasNext.shouldBe(true) }

        frame.onNext(4)

        it("has 2 previous") { frame.previous.shouldBe(2) }
        it("has 3 current") { frame.current.shouldBe(3) }
        it("has 4 next") { frame.next.shouldBe(4) }
    }
})
