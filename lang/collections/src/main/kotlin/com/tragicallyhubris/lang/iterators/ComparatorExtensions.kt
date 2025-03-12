package com.tragicallyhubris.lang.iterators

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlin.time.Duration

fun <I : Any> comparator(f: (I, I) -> Int) = Comparator<I> { i1, i2 -> f(i1, i2) }

fun <I : Any> comparison(f: (I) -> Int) =
    object : (I) -> Int {
        override fun invoke(i: I): Int = f(i)
    }

fun <I : Any> Comparator<I>.comparisonFor(item: I) = comparison<I> { this.compare(it, item) }

val booleanComparator = Comparator<Boolean> { i1, i2 -> i1.compareTo(i2) }

fun Boolean.comparison(): (Boolean) -> Int = booleanComparator.comparisonFor(this)

val intComparator = Comparator<Int> { i1, i2 -> i1.compareTo(i2) }

fun Int.comparison(): (Int) -> Int = intComparator.comparisonFor(this)

val longComparator = Comparator<Long> { i1, i2 -> i1.compareTo(i2) }

fun Long.comparison(): (Long) -> Int = longComparator.comparisonFor(this)

val floatComparator = Comparator<Float> { i1, i2 -> i1.compareTo(i2) }

fun Float.comparison(): (Float) -> Int = floatComparator.comparisonFor(this)

val doubleComparator = Comparator<Double> { i1, i2 -> i1.compareTo(i2) }

fun Double.comparison(): (Double) -> Int = doubleComparator.comparisonFor(this)

val stringComparator = Comparator<String> { i1, i2 -> i1.compareTo(i2) }

fun String.comparison(): (String) -> Int = stringComparator.comparisonFor(this)

val instantComparator = Comparator<Instant> { i1, i2 -> i1.compareTo(i2) }

fun Instant.comparison(): (Instant) -> Int = instantComparator.comparisonFor(this)

val durationComparator = Comparator<Duration> { i1, i2 -> i1.compareTo(i2) }

fun Duration.comparison(): (Duration) -> Int = durationComparator.comparisonFor(this)

val localDateComparator = Comparator<LocalDate> { i1, i2 -> i1.compareTo(i2) }

fun LocalDate.comparison(): (LocalDate) -> Int = localDateComparator.comparisonFor(this)

val localTimeComparator = Comparator<LocalTime> { i1, i2 -> i1.compareTo(i2) }

fun LocalTime.comparison(): (LocalTime) -> Int = localTimeComparator.comparisonFor(this)

val localDateTimeComparator = Comparator<LocalDateTime> { i1, i2 -> i1.compareTo(i2) }

fun LocalDateTime.comparison(): (LocalDateTime) -> Int = localDateTimeComparator.comparisonFor(this)
