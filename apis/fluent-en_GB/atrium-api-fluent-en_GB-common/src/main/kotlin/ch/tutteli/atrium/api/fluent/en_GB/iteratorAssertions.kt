package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.hasNext
import ch.tutteli.atrium.logic.hasNotNext

/**
 * Expects that the subject of the assertion (an [Iterator]) has at least one element.
 *
 * @return an [Expect] for the current subject of the assertion.
 *
 * @since 0.13.0
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.IteratorAssertionSamples.hasNext
 */
fun <E, T : Iterator<E>> Expect<T>.hasNext(): Expect<T> =
    _logicAppend { hasNext() }

/**
 * Expects that the subject of the assertion (an [Iterator]) does not have a next element.
 *
 * @return an [Expect] for the current subject of the assertion.
 *
 * @since 0.13.0
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.IteratorAssertionSamples.hasNoNext
 */
fun <E, T : Iterator<E>> Expect<T>.hasNotNext(): Expect<T> =
    _logicAppend { hasNotNext() }
