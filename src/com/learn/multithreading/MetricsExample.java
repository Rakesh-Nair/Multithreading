package com.learn.multithreading;

import java.util.Random;

public class MetricsExample {
	
	public static void main(String args[]) {
		
		Metrics metrics = new Metrics();
		
		BusinessLogic logic1 = new BusinessLogic(metrics);
		BusinessLogic logic2 = new BusinessLogic(metrics);
		MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);
		
		logic1.start();
		logic2.start();
		metricsPrinter.start();
		
	}
	
	private static class Metrics{
		private long count = 0;
		
		private volatile double average = 0.0;
		
		public synchronized void addSample(long sample) {
			double currentSum = average*count;
			count++;
			average = (currentSum + sample)/count;
		}

		public double getAverage() {
			return average;
		}		
		
	}
	
	private static class BusinessLogic extends Thread{
		private Metrics metrics;
		private Random random = new Random();

		public BusinessLogic(Metrics metrics) {
			this.metrics = metrics;
		}

		@Override
		public void run() {
			
			while(true) {
				long startTime = System.currentTimeMillis();
				
				try {
					Thread.sleep(random.nextInt(10));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				long endTime = System.currentTimeMillis();
				metrics.addSample(endTime - startTime);
			}

		}
		
		
	}
	
	private static class MetricsPrinter extends Thread {
		private Metrics metrics;

		public MetricsPrinter(Metrics metrics) {
			this.metrics = metrics;
		}

		@Override
		public void run() {
			
			while(true) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				double currentAverage = metrics.getAverage();
				System.out.println("Current Average is "+ currentAverage);
			}

		}
		
		
	}

}
