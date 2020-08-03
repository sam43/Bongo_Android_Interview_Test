package com.sam43.bongovideoplayer.anagram

import org.junit.Assert.*
import org.junit.Test

class AnagramTest {

    private val firstArgumentArray = arrayOf("eat", "bleat", "time", "pop", "no", "university")
    private val secondArgumentArray = arrayOf("tar", "table", "mike", "opo", "on", "universe")

    @Test
    fun isAnagramTestCase1() {
        //assign
        val firstArgument = firstArgumentArray[0]
        val secondArgument = secondArgumentArray[0]

        //act
        val result = isAnagram(firstArgument, secondArgument)

        //assert
        assertEquals(false, result)
    }

    @Test
    fun isAnagramTestCase2() {
        //assign
        val firstArgument = firstArgumentArray[1]
        val secondArgument = secondArgumentArray[1]

        //act
        val result = isAnagram(firstArgument, secondArgument)

        //assert
        assertEquals(true, result)
    }

    @Test
    fun isAnagramTestCase3() {
        //assign
        val firstArgument = firstArgumentArray[2]
        val secondArgument = secondArgumentArray[2]

        //act
        val result = isAnagram(firstArgument, secondArgument)

        //assert
        assertEquals(false, result)
    }

    @Test
    fun isAnagramTestCase4() {
        //assign
        val firstArgument = firstArgumentArray[3]
        val secondArgument = secondArgumentArray[3]

        //act
        val result = isAnagram(firstArgument, secondArgument)

        //assert
        assertEquals(false, result)
    }

    @Test
    fun isAnagramTestCase5() {
        //assign
        val firstArgument = firstArgumentArray[4]
        val secondArgument = secondArgumentArray[4]

        //act
        val result = isAnagram(firstArgument, secondArgument)

        //assert
        assertEquals(true, result)
    }

    @Test
    fun isAnagramTestCase6() {
        //assign
        val firstArgument = firstArgumentArray[5]
        val secondArgument = secondArgumentArray[5]

        //act
        val result = isAnagram(firstArgument, secondArgument)

        //assert
        assertEquals(false, result)
    }
}




