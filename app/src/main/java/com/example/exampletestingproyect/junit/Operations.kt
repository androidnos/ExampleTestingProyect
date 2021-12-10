package com.example.exampletestingproyect.junit

class Operations {

    companion object {

        fun addition(num1: Int, num2: Int): Int {
            return num1 + num2
        }

        fun subtraction(num1: Int, num2: Int): Int {
            return num1 - num2
        }

        fun multiplication(num1: Int, num2: Int): Int {
            return num1 * num2
        }

        fun division(num1: Int, num2: Int): Any {
            if (num1 == 0 && num2 == 0){
                return "Error"
            } else if (num2 == 0){
                return "Infinito"
            }
            return num1 / num2
        }

    }
}