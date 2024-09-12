package com.learn.multithreading;

public class MyFirstThreadExample {
	public static void main(String args[]) throws InterruptedException {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Current Thread Name "+Thread.currentThread().getName());
				throw new RuntimeException("My Runtime Exception");
			}
			
		});
		
		System.out.println("Current Thread Name "+Thread.currentThread().getName());
		thread.setName("My First Thread");
		thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				// TODO Auto-generated method stub
				System.out.println("Exception Caught "+e.getMessage());
			}
		});
		thread.start();
		
	}
}
