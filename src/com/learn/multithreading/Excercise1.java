package com.learn.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Excercise1 {
	public static final int MAX_PASSWORD = 9999;

	public static void main(String args[]) {
		Random random = new Random();
		
		int nextInt = random.nextInt(MAX_PASSWORD);
		System.out.println("Pshhh the password is "+nextInt);
		Vault vault = new Vault(nextInt);
		
		List<Thread> threads = new ArrayList<>();
		
		threads.add(new AscendingHackerThread(vault));
		threads.add(new DescendingHackerThread(vault));
		threads.add(new PoliceThread());
		
		for(Thread thread : threads) {
			thread.start();
		}
	}

	private static class Vault {

		private int password;

		public Vault(int password) {
			this.password = password;
		}

		public boolean isCorrectPassword(int password) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return this.password == password;
		}

	}

	private static abstract class HackerThread extends Thread {

		protected Vault vault;

		public HackerThread(Vault vault) {
			this.vault = vault;
			this.setName(this.getClass().getSimpleName());
			this.setPriority(MAX_PRIORITY);
		}

		@Override
		public void start() {
			System.out.println("Thread " + Thread.currentThread().getName() + " has started");
			super.start();
		}
	}

	private static class AscendingHackerThread extends HackerThread {

		public AscendingHackerThread(Vault vault) {
			super(vault);

		}

		@Override
		public void run() {
			System.out.println("Running Ascending Hacker Thread");
			for (int i = 0; i <= MAX_PASSWORD; i++) {
				System.out.println("Ascending Thread iteration "+i);
				if (vault.isCorrectPassword(i)) {
					System.out.println(this.getName()+" has guessed the correct password "+i);
					System.exit(0);
				}
			}
		}

	}
	
	private static class DescendingHackerThread extends HackerThread{

		public DescendingHackerThread(Vault vault) {
			super(vault);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			System.out.println("Running Descending Hacker Thread");
			for(int i=MAX_PASSWORD; i>=0; i--) {
				System.out.println("Descending Thread iteration "+i);
				if(vault.isCorrectPassword(i)) {
					System.out.println(this.getName()+" has guessed the correct password "+i);
					System.exit(0);
				}
			}
		}
	}

	private static class PoliceThread extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 10; i > 0; i--) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(i);
			}
			System.out.println("Game over for you Hackers");
			System.exit(0);
		}
	}
}