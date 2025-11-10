package com.maan.proj.math;

import java.math.*;
import java.util.*;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Common file for all projects.
  * <li> Purpose : To generate random number using Math functions.
  * <blockquote>This class extends java.math.Random class.</blockquote>
  * </ul>
  */

public class RandomFunction extends Random
{
	/**
	  * Get the random number with in maximum given number
	  * @param n maximum limit go generate integer
	  * @return a age as int.
	  * @Exception Exception.
	  */

	public int nextInt(int n)
	{
		 // check for n is a positive number
		 if (n<=0)
			throw new IllegalArgumentException("n must be positive");

	     if ((n & -n) == n)  // i.e., n is a power of 2
		     return (int)((n * (long)next(31)) >> 31);

		int bits, val;

		// generate random number with in the limit of n.
		do
		{
			bits = next(31);
			val = bits % n;
		} while(bits - val + (n-1) < 0);

		return val;
	}
}
