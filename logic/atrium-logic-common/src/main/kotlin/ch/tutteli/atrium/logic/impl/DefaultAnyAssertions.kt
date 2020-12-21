package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.ExplanatoryGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.*
import kotlin.reflect.KClass

class DefaultAnyAssertions : AnyAssertions {
    override fun <T> toBe(container: AssertionContainer<T>, expected: T): Assertion =
        container.createDescriptiveAssertion(TO_BE, expected) { it == expected }

    override fun <T> notToBe(container: AssertionContainer<T>, expected: T): Assertion =
        container.createDescriptiveAssertion(NOT_TO_BE, expected) { it != expected }

    override fun <T> isSameAs(container: AssertionContainer<T>, expected: T): Assertion =
        container.createDescriptiveAssertion(IS_SAME, expected) { it === expected }

    override fun <T> isNotSameAs(container: AssertionContainer<T>, expected: T): Assertion =
        container.createDescriptiveAssertion(IS_NOT_SAME, expected) { it !== expected }

    override fun <T> toBeNull(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(TO_BE, Text.NULL) { it == null }

    override fun <T> because(
        container: AssertionContainer<T>,
        reason: String,
        assertionCreator: Expect<T>.() -> Unit
    ): Assertion {
        return assertionBuilder.invisibleGroup.withAssertions(
            assertionCollector.collect(container.maybeSubject, assertionCreator),
            ExplanatoryGroup
                .GroupTypeOption
                .create()
                .withInformationType
                .withExplanatoryAssertion(TranslatableWithArgs(BECAUSE, reason)).build()
        ).build()
    }

    override fun <T : Any> toBeNullIfNullGivenElse(
        container: AssertionContainer<T?>,
        type: KClass<T>,
        assertionCreatorOrNull: (Expect<T>.() -> Unit)?
    ): Assertion =
        if (assertionCreatorOrNull == null) container.toBeNull()
        else notToBeNull(container, type, assertionCreatorOrNull)

    private fun <T : Any> notToBeNull(
        container: AssertionContainer<T?>,
        type: KClass<T>,
        assertionCreator: Expect<T>.() -> Unit
    ) = container.notToBeNullButOfType(type).collect(assertionCreator)


    override fun <T : Any> notToBeNullButOfType(
        container: AssertionContainer<T?>,
        subType: KClass<T>
    ): SubjectChangerBuilder.ExecutionStep<T?, T> = container.isA(subType)

    override fun <T, TSub : Any> isA(
        container: AssertionContainer<T>,
        subType: KClass<TSub>
    ): SubjectChangerBuilder.ExecutionStep<T, TSub> =
        container.changeSubject.reportBuilder()
            .downCastTo(subType)
            .build()

    override fun <T> isNotIn(container: AssertionContainer<T>, expected: Iterable<T>): Assertion {
        val assertions = expected.map { value ->
            assertionBuilder.representationOnly
                .withTest(container) { it != value }
                .withRepresentation(value)
                .build()
        }
        return assertionBuilder.list
            .withDescriptionAndEmptyRepresentation(IS_NONE_OF)
            .withAssertions(assertions)
            .build()
    }

}
