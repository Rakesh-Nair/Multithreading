package com.learn.multithreading;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Factorial {
	public static void main(String args[]) throws InterruptedException {
		List<Long> numbers = Arrays.asList(1000000L, 3322L, 23232L, 2L, 432L, 9338L, 32343L);
		
		List<Thread> threads  = new ArrayList<>();
		for(Long number : numbers) {
			threads.add(new FactorialCalc(number));
		}
		
		for (Thread thread : threads) {
			thread.setDaemon(true);
			thread.start();
		}
		
		for (Thread thread : threads) {
			thread.join(5000);
		}
		
		for(int i=0 ; i<numbers.size(); i++) {
			FactorialCalc f = (FactorialCalc) threads.get(i);
			if(f.isCompleted) {
				System.out.println("The factorial of "+numbers.get(i)+ " is "+f.getResult());
			}
			else {
				System.out.println("Calculation is still in progress for input "+numbers.get(i));
			}
		}
		
	}
	
	public static class FactorialCalc extends Thread{

		private long inputNumber;
		private BigInteger result = BigInteger.ZERO;
		private boolean isCompleted = false;
		
		
		
		public FactorialCalc(long inputNumber) {
			super();
			this.inputNumber = inputNumber;
		}

		@Override
		public void run() {
			this.result = factorial(inputNumber);
		}

		private BigInteger factorial(long inputNumber) {
			BigInteger tempResult = BigInteger.ONE;
			for(long i=inputNumber; i>0; i--) {
				tempResult = tempResult.multiply(new BigInteger(Long.toString(inputNumber)));
			}
			this.setCompleted(true);
			return tempResult;
		}

		public long getInputNumber() {
			return inputNumber;
		}

		public void setInputNumber(long inputNumber) {
			this.inputNumber = inputNumber;
		}

		public boolean isCompleted() {
			return isCompleted;
		}

		public void setCompleted(boolean isCompleted) {
			this.isCompleted = isCompleted;
		}

		public BigInteger getResult() {
			return result;
		}
		
		
		
	}
}
