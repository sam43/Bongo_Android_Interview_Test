package com.sam43.bongovideoplayer.anagram

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




