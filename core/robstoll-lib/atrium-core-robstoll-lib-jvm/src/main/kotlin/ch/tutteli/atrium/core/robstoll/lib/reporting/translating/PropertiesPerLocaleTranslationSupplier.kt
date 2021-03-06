@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslationSupplier

/**
 * Represents a [TranslationSupplier] which is based on properties-files which are structured per [Locale].
 *
 * For instance, the translations for `ch.tutteli.atrium.DescriptionAnyAssertion` and the [Locale] `de_CH` are
 * stored in a properties file named `Atrium_de_CH.properties` in the folder `/ch/tutteli/atrium/reporting/translating`.
 *
 * For instance, the translations for the [Locale] `de_CH` are stored in a properties file named .
 *
 * An entry in such a file would look like the following:
 * `ch.tutteli.atrium.DescriptionAnyAssertion-TO_BE = a translation for TO_BE`
 */
class PropertiesPerLocaleTranslationSupplier : PropertiesBasedTranslationSupplier<Locale>() {

    override fun get(translatable: Translatable, locale: Locale): String? {
        val fileName = getFileNameFor(this::class.java.`package`.name + ".Atrium", locale)
        val translations = getOrLoadProperties(locale, fileName, keyCreator = { it })
        return translations[translatable.id]
    }
}
