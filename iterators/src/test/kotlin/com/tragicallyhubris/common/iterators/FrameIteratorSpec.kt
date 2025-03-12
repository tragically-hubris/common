package com.tragicallyhubris.common.iterators

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class FrameIteratorSpec : DescribeSpec({

    describe("empty") {
        val it = emptyList<Int>().iterator().frame()

        it("hasNext is false") { it.hasNext().shouldBe(false) }
        it("next throws") { shouldThrow<NoSuchElementException> { it.next() } }
    }

    describe("single") {
        val it = listOf(1).iterator().frame()

        it("hasNext is true") { it.hasNext().shouldBe(true) }
        it("next has null, 1, null") {
            it.next().let { it ->
                it.previous.shouldBe(null)
                it.current.shouldBe(1)
                it.next.shouldBe(null)
            }
        }

        it("hasNext is false") { it.hasNext().shouldBe(false) }
        it("next throws") { shouldThrow<NoSuchElementException> { it.next() } }
    }

    describe("list") {
        val it = listOf(1, 2, 3, 4).iterator().frame()

        it("hasNext is true") { it.hasNext().shouldBe(true) }
        it("next has null, 1, 2") {
            it.next().let { it ->
                it.previous.shouldBe(null)
                it.current.shouldBe(1)
                it.next.shouldBe(2)
            }
        }

        it("hasNext is true") { it.hasNext().shouldBe(true) }
        it("next has 1, 2, 3") {
            it.next().let { it ->
                it.previous.shouldBe(1)
                it.current.shouldBe(2)
                it.next.shouldBe(3)
            }
        }

        it("hasNext is true") { it.hasNext().shouldBe(true) }
        it("next has 2, 3, 4") {
            it.next().let { it ->
                it.previous.shouldBe(2)
                it.current.shouldBe(3)
                it.next.shouldBe(4)
            }
        }

        it("hasNext is true") { it.hasNext().shouldBe(true) }
        it("next has 3, 4, null") {
            it.next().let { it ->
                it.previous.shouldBe(3)
                it.current.shouldBe(4)
                it.next.shouldBe(null)
            }
        }

        it("hasNext is false") { it.hasNext().shouldBe(false) }
        it("next throws") { shouldThrow<NoSuchElementException> { it.next() } }
    }
})
