package com.tragicallyhubris.lang.iterators

class Frame<T : Any>(
    previous: T? = null,
    current: T? = null,
    next: T? = null,
) {
    var previous: T? = previous
        private set

    var current: T? = current
        private set

    var next: T? = next
        private set

    fun onNext(t: T?): Frame<T> =
        also {
            previous = current
            current = next
            next = t
        }

    val hasPrevious get() = previous != null
    val hasCurrent get() = current != null
    val hasNext get() = next != null
}

fun <T : Any> Iterator<T>.frame() = frameIterator(this)

fun <T : Any> frameIterator(iterator: Iterator<T>) =
    object : Iterator<Frame<T>> {
        private val frame: Frame<T> by lazy {
            Frame(next = iterator.nextOrNull())
        }

        override fun hasNext(): Boolean = frame.hasNext

        override fun next(): Frame<T> =
            frame.takeIf { it.hasNext }
                ?.onNext(iterator.nextOrNull())
                ?: throw NoSuchElementException()
    }
