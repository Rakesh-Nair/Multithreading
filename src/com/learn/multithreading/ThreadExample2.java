package com.learn.multithreading;

import java.math.BigInteger;

public class ThreadExample2 {

	public static void main(String args[]) {
		
		Thread t = new Thread(new CalculationRunnable(new BigInteger("20000"), new BigInteger("100000")));
		t.start();
		t.interrupt();
	}

	private static class CalculationRunnable implements Runnable {

		private BigInteger base;
		private BigInteger power;

		private CalculationRunnable(BigInteger base, BigInteger power) {
			super();
			this.base = base;
			this.power = power;
		}

		@Override
		public void run() {
			System.out.println(base + "^" + power + " is " + compute(base, power));

		}

		private BigInteger compute(BigInteger base2, BigInteger power2) {
			BigInteger result = BigInteger.ONE;
			for (BigInteger i = BigInteger.ZERO; i.compareTo(power2) != 0; i=i.add(BigInteger.ONE)) {
				if(Thread.currentThread().isInterrupted()) {
					System.out.println("Current Thread is interrupted ");
					return BigInteger.ZERO;
				}
				result = result.multiply(base2);
			}
			return result;
		}

	}

}
