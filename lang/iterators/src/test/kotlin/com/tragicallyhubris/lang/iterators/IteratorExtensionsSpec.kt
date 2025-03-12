package com.tragicallyhubris.lang.iterators

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class IteratorExtensionsSpec : DescribeSpec({

    describe("nextOrNull") {
        describe("empty") {
            val it = emptyIterator<Int>()
            it("nextOrNull is null") { it.nextOrNull().shouldBe(null) }
        }

        describe("list") {
            val it = listOf(1, 2, 3).iterator()
            it("nextOrNull is 1") { it.nextOrNull().shouldBe(1) }
            it("nextOrNull is 2") { it.nextOrNull().shouldBe(2) }
            it("nextOrNull is 3") { it.nextOrNull().shouldBe(3) }
            it("nextOrNull is null") { it.nextOrNull().shouldBe(null) }
        }
    }

    describe("filter") {
        describe("empty") {
            val it = emptyIterator<Int>().filter { it % 2 == 0 }
            it("nextOrNull is null") { it.nextOrNull().shouldBe(null) }
        }

        describe("list") {
            val it = listOf(1, 2, 3, 4).iterator().filter { it % 2 == 0 }
            it("nextOrNull is 2") { it.nextOrNull().shouldBe(2) }
            it("nextOrNull is 4") { it.nextOrNull().shouldBe(4) }
            it("nextOrNull is null") { it.nextOrNull().shouldBe(null) }
        }
    }

    describe("takeWhile") {
        describe("empty") {
            val it = emptyIterator<Int>().takeWhile { it <= 2 }
            it("nextOrNull is null") { it.nextOrNull().shouldBe(null) }
        }

        describe("list") {
            val it = listOf(1, 2, 3, 4).iterator().takeWhile { it <= 2 }
            it("nextOrNull is 1") { it.nextOrNull().shouldBe(1) }
            it("nextOrNull is 2") { it.nextOrNull().shouldBe(2) }
            it("nextOrNull is null") { it.nextOrNull().shouldBe(null) }
        }
    }

    describe("takeUntil") {
        describe("empty") {
            val it = emptyIterator<Int>().takeUntil { it > 2 }
            it("nextOrNull is null") { it.nextOrNull().shouldBe(null) }
        }

        describe("list") {
            val it = listOf(1, 2, 3, 4).iterator().takeUntil { it > 2 }
            it("nextOrNull is 1") { it.nextOrNull().shouldBe(1) }
            it("nextOrNull is 2") { it.nextOrNull().shouldBe(2) }
            it("nextOrNull is null") { it.nextOrNull().shouldBe(null) }
        }
    }

    describe("map") {
        describe("empty") {
            val it = emptyIterator<Int>().map { it * 2 }
            it("nextOrNull is null") { it.nextOrNull().shouldBe(null) }
        }

        describe("list") {
            val it = listOf(1, 2, 3, 4).iterator().map { it * 2 }
            it("nextOrNull is 2") { it.nextOrNull().shouldBe(2) }
            it("nextOrNull is 4") { it.nextOrNull().shouldBe(4) }
            it("nextOrNull is 6") { it.nextOrNull().shouldBe(6) }
            it("nextOrNull is 8") { it.nextOrNull().shouldBe(8) }
            it("nextOrNull is null") { it.nextOrNull().shouldBe(null) }
        }
    }
})
