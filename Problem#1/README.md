# Solution to Q#1: Anagram Checker

Program code here: https://github.com/sam43/Bongo_Android_Interview_Test/blob/master/Problem%231/Anagram.kt

Unit Test code here: https://github.com/sam43/Bongo_Android_Interview_Test/blob/master/Problem%231/AnagramTest.kt

## Problem explanation:

An anagram of a string is another string that contains same characters, only the order of characters can be different. For example, ‘bleat’ and ‘table’ are anagrams of each other but ‘eat’ and ‘tar’ are not. We need to write a function to check whether two given strings are anagram of each other or not. 

### Anagram.kt

    import java.util.*

    fun main() {
        val result = isAnagram("bleat", "table")

        if (result) {
            println("These two strings are anagram")
        } else {
            println("These two strings are not anagram")
        }
    }

    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) {
            return false
        }
        val str1 = s.toCharArray()
        val str2 = t.toCharArray()
        Arrays.sort(str1)
        Arrays.sort(str2)
        return str1.contentEquals(str2)
    }
    
    
Here, the 'isAnagram(source: String, target: String)' method is responsible for checking anagram taking 2 strings as params by returning a Boolean value (true/false). We can do it in different ways. Like, we can use sorting and check if they are same after sorting, we can count the characters and check if both strings have same count of characters or not etc. I used the first one to solve this and it costs time complexity of O(nlogn).


### Unit Test for isAnagram() method: AnagramTest.kt

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


Above unit test class illustrates that our 'isAnagram()' method is working fine. The test have been executed with two different set of close string list and after checking the result with the 'assertEquals(...,...)' method, verifies the method is working properly.   
   
