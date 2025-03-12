package com.tragicallyhubris.common.iterators

fun <T : Any> Iterator<T>.nextOrNull(): T? = if (hasNext()) next() else null

fun <T> emptyIterator() =
    object : Iterator<T> {
        override fun hasNext() = false

        override fun next() = throw NoSuchElementException()
    }

fun <T : Any> nextOrNullIterator(nextOrNull: () -> T?) =
    object : Iterator<T> {
        private var next: T? = nextOrNull()

        override fun hasNext(): Boolean = next != null

        override fun next(): T =
            (next ?: throw NoSuchElementException())
                .also { this.next = nextOrNull() }
    }

fun <T : Any> Iterator<T>.filter(predicate: (T) -> Boolean) =
    nextOrNullIterator {
        while (true) {
            val next = this@filter.nextOrNull() ?: return@nextOrNullIterator null
            if (predicate(next)) return@nextOrNullIterator next
        }
    }

fun <T : Any> Iterator<T>.takeWhile(predicate: (T) -> Boolean) =
    nextOrNullIterator {
        this@takeWhile.nextOrNull()?.takeIf(predicate)
    }

fun <T : Any> Iterator<T>.takeUntil(predicate: (T) -> Boolean) =
    nextOrNullIterator {
        this@takeUntil.nextOrNull()?.takeUnless(predicate)
    }

fun <T : Any, R : Any> Iterator<T>.map(f: (T) -> R) =
    nextOrNullIterator {
        this@map.nextOrNull()?.let { f(it) }
    }
