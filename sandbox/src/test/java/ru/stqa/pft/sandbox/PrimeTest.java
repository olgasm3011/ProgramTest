package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTest {
  @Test
  public void testPrime(){
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }
 /* @Test
  public void testNoPrimes(){
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE-2));
  }*/
   @Test
  public void testPrimeLong(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
   }
 /* @Test
  public void testPrimesFast(){
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE-2));
  }*/
}
