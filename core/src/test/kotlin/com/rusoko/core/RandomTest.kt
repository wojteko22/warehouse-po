package com.rusoko.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

class RandomTest {

    @Test
    fun `number with one digit should have minimum three leading zeros`() {
        repeat(100) {
            val result = Random().nextNumber(1)
            assertThat(result).startsWith("000")
        }
    }

    @Test
    fun `number with one digit should be a number less than 10`() {
        repeat(100) {
            val result = Random().nextNumber(1)
            val int = result.toInt()
            assertThat(int).isLessThan(10)
        }
    }

    @Test
    fun `number with one digit should have four chars`() {
        repeat(100) {
            val result = Random().nextNumber(1)
            val length = result.length
            assertThat(length).isEqualTo(4)
        }
    }

    @Test
    fun `number with two digits should have minimum two leading zeros`() {
        repeat(100) {
            val result = Random().nextNumber(2)
            assertThat(result).startsWith("00")
        }
    }

    @Test
    fun `number with two digits should be a number less than 100`() {
        repeat(100) {
            val result = Random().nextNumber(2)
            val int = result.toInt()
            assertThat(int).isLessThan(100)
        }
    }

    @Test
    fun `number with three digits should have minimum one leading zero`() {
        repeat(100) {
            val result = Random().nextNumber(3)
            assertThat(result).startsWith("0")
        }
    }

    @Test
    fun `number with five digits should be a number less than 100000`() {
        repeat(100) {
            val result = Random().nextNumber(5)
            val int = result.toInt()
            assertThat(int).isLessThan(100000)
        }
    }

    @Test
    fun `number with six digits should have between four and six chars`() {
        repeat(100) {
            val result = Random().nextNumber(6)
            val length = result.length
            assertThat(length).isBetween(4, 6)
        }
    }
}