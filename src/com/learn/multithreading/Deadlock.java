package com.learn.multithreading;

import java.util.Random;

public class Deadlock {
	public static void main(String args[]) {
		
		Intersection intersection = new Intersection();
		
		TrainA trainA = new TrainA(intersection);
		TrainB trainB = new TrainB(intersection);
		
		Thread t1 = new Thread(trainA);
		Thread t2 = new Thread(trainB);
		
		t1.start();
		t2.start();
		
	}
	private static class TrainA implements Runnable{
		Intersection intersection;
		
		Random random = new Random();
		
		public TrainA(Intersection intersection) {
			this.intersection = intersection;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				long sleepingTime = random.nextInt(5);
				try {
					Thread.sleep(sleepingTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				intersection.takeRoadA();
			}
			
		}
		
	}
	
	private static class TrainB implements Runnable{
		Intersection intersection;
		
		Random random = new Random();
		
		public TrainB(Intersection intersection) {
			this.intersection = intersection;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				long sleepingTime = random.nextInt(5);
				try {
					Thread.sleep(sleepingTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				intersection.takeRoadB();
			}
			
		}
		
	}
	
	private static class Intersection{
		private Object roadA = new Object();
		private Object roadB = new Object();
		
		public void takeRoadA() {
			synchronized(roadA) {
				System.out.println("Road A is locked by thread "+Thread.currentThread().getName());
				
				synchronized(roadB) {
					System.out.println("Train is passing through Road A");
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
			
		}
		
		public void takeRoadB() {
			synchronized(roadB) {
				System.out.println("Road B is locked by thread "+Thread.currentThread().getName());
				
				synchronized(roadA) {
					System.out.println("Train is passing through Road B");
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}


