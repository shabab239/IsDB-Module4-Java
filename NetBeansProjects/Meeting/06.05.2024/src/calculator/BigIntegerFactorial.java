/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator;

import java.math.BigInteger;

public class BigIntegerFactorial {

    int number;

    public BigIntegerFactorial() {

    }

    public BigIntegerFactorial(int number) {
        this.number = number;
    }

    public BigInteger getFactorial() {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= number; i++) {
            factorial = factorial.multiply(new BigInteger(i + ""));
        }
        return factorial;

    }

    public String checkPrime(BigInteger factorial) {
        String messege = "";
        int count = 0;
        for (BigInteger i = BigInteger.ONE; i.compareTo(factorial) <= 0; i = i.add(BigInteger.ONE)) {
            if (factorial.mod(i).equals(0)) {
                count++;
            }
        }
        if (count == 2) {
            messege = "It is prime number.";
        } else {
            messege = "It is not prime number.";
        }

        return messege;
    }

}
