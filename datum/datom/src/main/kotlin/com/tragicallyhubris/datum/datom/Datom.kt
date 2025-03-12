package com.tragicallyhubris.datum.datom

import com.tragicallyhubris.datum.datom.Datom.Assert
import com.tragicallyhubris.datum.datom.Datom.Retract

typealias E = String
typealias A = String
typealias V = String
typealias T = Long
typealias O = Boolean

val assertion = true
val retraction = false

sealed interface Datom {
    val e: E
    val a: A
    val v: V
    val t: T
    val o: O

    val isAssert get() = o == assertion
    val isRetract get() = o == retraction

    companion object : (E, A, V, T, O) -> Datom {
        override fun invoke(e: E, a: A, v: V, t: T, o: O): Datom =
            when (o) {
                assertion -> Assert(e, a, v, t)
                else -> Retract(e, a, v, t)
            }
    }

    data class Assert(
        override val e: E,
        override val a: A,
        override val v: V,
        override val t: T,
    ) : Datom {
        override val o: O = assertion
    }

    data class Retract(
        override val e: E,
        override val a: A,
        override val v: V,
        override val t: T,
    ) : Datom {
        override val o: O = retraction
    }
}

fun assertion(e: E, a: A, v: V, t: T) = Assert(e, a, v, t)
fun retraction(e: E, a: A, v: V, t: T) = Retract(e, a, v, t)

val eComparator: Comparator<E> = comparator { e1, e2 -> e1.compareTo(e2) }
val aComparator: Comparator<A> = comparator { a1, a2 -> a1.compareTo(a2) }
val vComparator: Comparator<V> = comparator { v1, v2 -> v1.compareTo(v2) }
val tComparator: Comparator<T> = comparator { t1, t2 -> t1.compareTo(t2) }

val eavtComparator: Comparator<Datom> = comparator { d1, d2 ->
    var d = d1.e.compareTo(d2.e)
    if (d == 0) d = d1.a.compareTo(d2.a)
    if (d == 0) d = d1.v.compareTo(d2.v)
    if (d == 0) d = d1.t.compareTo(d2.t)
    d
}

val aevtComparator: Comparator<Datom> = comparator { d1, d2 ->
    var d = d1.a.compareTo(d2.a)
    if (d == 0) d = d1.e.compareTo(d2.e)
    if (d == 0) d = d1.v.compareTo(d2.v)
    if (d == 0) d = d1.t.compareTo(d2.t)
    d
}

val avetComparator: Comparator<Datom> = comparator { d1, d2 ->
    var d = d1.a.compareTo(d2.a)
    if (d == 0) d = d1.v.compareTo(d2.v)
    if (d == 0) d = d1.e.compareTo(d2.e)
    if (d == 0) d = d1.t.compareTo(d2.t)
    d
}

val vaetComparator: Comparator<Datom> = comparator { d1, d2 ->
    var d = d1.v.compareTo(d2.v)
    if (d == 0) d = d1.a.compareTo(d2.a)
    if (d == 0) d = d1.e.compareTo(d2.e)
    if (d == 0) d = d1.t.compareTo(d2.t)
    d
}

fun <I : Any> comparator(f: (I, I) -> Int) = Comparator<I> { i1, i2 -> f(i1, i2) }

fun <I : Any> comparison(f: (I) -> Int) = object : (I) -> Int {
    override fun invoke(i: I): Int =
        f(i)
}

fun <I : Any> Comparator<I>.comparisonFor(item: I) = comparison<I> { this.compare(it, item) }

val intComparator = Comparator<Int> { i1, i2 -> i1.compareTo(i2) }
fun Int.comparison(): (Int) -> Int = intComparator.comparisonFor(this)

val longComparator = Comparator<Long> { i1, i2 -> i1.compareTo(i2) }
fun Long.comparison(): (Long) -> Int = longComparator.comparisonFor(this)

val stringComparator = Comparator<String> { i1, i2 -> i1.compareTo(i2) }
fun String.comparison(): (String) -> Int = stringComparator.comparisonFor(this)

fun <T : Any> Iterator<T>.nextOrNull(): T? =
    if (hasNext()) next() else null

// e1, a1, v1, t1, assert
// e1, a1, v1, t2, retract
// e1, a1, v2, t1, assert

//fun Iterator<Datom>.headDatomIterator(): Iterator<Datom> {
//    var previous: Datom?
//    var next: Datom? = null
//    return lazyNextOrNullIterator {
//        var v: Datom? = null
//        while (true) {
//            previous = next
//            if (this@headDatomIterator.hasNext()) {
//                next = this@headDatomIterator.next()
//            } else {
//                v = null
//                break
//            }
//
//            if (next.e != previous?.e || next.a != previous?.a || next.v != previous?.v)
//                v = next
//            break
//        }
//        v
//    }
//}
//
