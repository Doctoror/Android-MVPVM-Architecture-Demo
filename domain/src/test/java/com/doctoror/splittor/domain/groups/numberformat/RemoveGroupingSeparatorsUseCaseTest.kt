package com.doctoror.splittor.domain.groups.numberformat

import com.doctoror.splittor.domain.numberformat.RemoveGroupingSeparatorsUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Locale

class RemoveGroupingSeparatorsUseCaseTest {

    private val underTest = RemoveGroupingSeparatorsUseCase(Locale.US)

    @Test
    fun returnsUnchangedValueWhenNoSeparators() {
        assertEquals("1000", underTest("1000"))
    }

    @Test
    fun returnsValueWithoutGroupingSeparators() {
        assertEquals("1000.99", underTest("1,000.99"))
    }

    @Test
    fun returnsValueWithoutGroupingSeparatorsWhenHasMultiple() {
        assertEquals("1000992.99", underTest("1,000,992.99"))
    }
}
