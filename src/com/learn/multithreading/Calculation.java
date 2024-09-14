package com.learn.multithreading;
import java.math.BigInteger;

public class Calculation {
	
	public static void main(String args[]) {
		ComplexCalculation c = new ComplexCalculation();
		try {
			BigInteger result = c.calculateResult(BigInteger.TWO, BigInteger.TWO, BigInteger.TWO, BigInteger.TWO);
			System.out.println("Result is "+result);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


	private static class ComplexCalculation {
	    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
	        BigInteger result = BigInteger.ZERO;
	        /*
	            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
	            Where each calculation in (..) is calculated on a different thread
	        */
	        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
	        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);
	        thread1.start();
	        thread1.join();
	        thread2.start();
	        thread2.join();
	        System.out.println(thread1.getResult()+" "+thread2.getResult());
	        result = result.add(thread1.getResult());
	        result = result.add(thread2.getResult());
	        return result;
	    }

	    private static class PowerCalculatingThread extends Thread {
	        private BigInteger result = BigInteger.ONE;
	        private BigInteger base;
	        private BigInteger power;
	    
	        public PowerCalculatingThread(BigInteger base, BigInteger power) {
	            this.base = base;
	            this.power = power;
	        }
	    
	        @Override
	        public void run() {
	           /*
	           Implement the calculation of result = base ^ power
	           */
	                   for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i=i.add(BigInteger.ONE)) {
	                	   //System.out.println("ya"+i+" "+this.getName());   
	                	   //System.out.println(result+" "+ base+" "+this.getName());
	                	   result = result.multiply(base);
	            //System.out.println(result+" "+ this.getName());
	        }  
	                   System.out.println("Result is "+result);
	        }
	    
	        public BigInteger getResult() { return result; }
	    }
	}

}
