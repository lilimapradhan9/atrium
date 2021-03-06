//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.creating.collectors

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.collectors.AssertionCollector
import ch.tutteli.atrium.domain.robstoll.lib.creating.collectors._collect
import ch.tutteli.atrium.domain.robstoll.lib.creating.collectors._collectForComposition

//TODO 0.16.0 move to logic and deprecate
class AssertionCollectorImpl : AssertionCollector {

    override fun <T> collect(
        maybeSubject: Option<T>,
        assertionCreator: Expect<T>.() -> Unit
    ) = _collect(maybeSubject, assertionCreator)

    override fun <T> collectForComposition(
        maybeSubject: Option<T>,
        assertionCreator: Expect<T>.() -> Unit
    ) = _collectForComposition(maybeSubject, assertionCreator)
}
