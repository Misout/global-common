package com.misout.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinTest {
	
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		
		RecursiveAction action1 = new RecursiveAction() {
			
			@Override
			protected void compute() {
				System.out.println("I am 1");
			}
		};
		
		RecursiveAction action2 = new RecursiveAction() {
			
			@Override
			protected void compute() {
				System.out.println("I am 2");
			}
		};
		
		RecursiveAction action3 = new RecursiveAction() {
			
			@Override
			protected void compute() {
				System.out.println("I am 3");
			}
		};
		
		forkJoinPool.execute(action1);
//		forkJoinPool.execute(action2);
//		forkJoinPool.execute(action3);
	}
}
