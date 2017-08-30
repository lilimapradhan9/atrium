package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionNumberAssertion.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

fun <T> _isLessThan(plant: IAssertionPlant<T>, expected: T): IAssertion where T : Number, T : Comparable<T>
    = BasicAssertion(IS_LESS_THAN, expected, { plant.subject < expected })

fun <T> _isLessOrEquals(plant: IAssertionPlant<T>, expected: T): IAssertion where T : kotlin.Number, T : kotlin.Comparable<T>
    = BasicAssertion(IS_LESS_OR_EQUALS, expected, { plant.subject <= expected })

fun <T> _isGreaterThan(plant: IAssertionPlant<T>, expected: T): IAssertion where T : kotlin.Number, T : kotlin.Comparable<T>
    = BasicAssertion(IS_GREATER_THAN, expected, { plant.subject > expected })

fun <T> _isGreaterOrEquals(plant: IAssertionPlant<T>, expected: T): IAssertion where T : kotlin.Number, T : kotlin.Comparable<T>
    = BasicAssertion(IS_GREATER_OR_EQUALS, expected, { plant.subject >= expected })