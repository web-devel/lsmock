package me.webdevel.lsmock

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals

class CompletionTest : LanguageServerTestFixture() {

    @Test
    fun `test completion`() {

        openDocument("test.txt", "text")

        val result = server.textDocumentService
            .completion(completionParams("test.txt", 0, 0))
            .get()

        // Assert
        assertTrue(result.isRight)
        val items = result.right.items
        assertEquals(2, items.size)
        assertEquals("fakeItem1", items[0].label)
        assertEquals("fakeItem2", items[1].label)
    }

}