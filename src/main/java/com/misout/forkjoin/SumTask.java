package com.misout.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 计算从起始值到结束值得连续数的累加结果，利用Fork/Join框架
 * @author Misout
 * @date 2018-01-13 16:06:44
 */
public class SumTask extends RecursiveTask<Long> {

	private static final long serialVersionUID = 4828818665955149519L;
	
	/** 每个任务最多允许计算的数字个数阈值，超过这个阈值，任务进行拆分 */
	private static final long THRESHOLD = 1000L;
	
	/** 起始值 */
	private Long startNumber;
	
	/** 结束值 */
	private Long endNumber;
	
	public SumTask(Long startNumber, Long endNumber) {
		this.startNumber = startNumber;
		this.endNumber = endNumber;
	}

	/**
	 * 累加数的个数超过阈值1000个，拆分成2个子任务执行。子任务继续作拆分。计算完，合并结果。
	 */
	@Override
	protected Long compute() {
		if(startNumber > endNumber) {
			System.out.println("start number should be smaller than end number");
			return 0L;
		}
		if(endNumber - startNumber < THRESHOLD) {
			return this.getCount(startNumber, endNumber);
		} else {
			Long mid = (startNumber + endNumber) / 2;
			RecursiveTask<Long> subTask1 = new SumTask(startNumber, mid);
			RecursiveTask<Long> subTask2 = new SumTask(mid + 1, endNumber);
			subTask1.fork();
			subTask2.fork();
			
			return subTask1.join() + subTask2.join();
		}
	}
	
	/**
	 * 普通累加执行方法
	 * @param start 起始数
	 * @param end 结束数
	 * @return 累加和
	 */
	protected Long getCount(Long start, Long end) {
		Long sum = 0L;
		for(long i = start; i <= end; i++) {
			sum += i;
		}
		
		return sum;
	}

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Long start = 5L;
		Long end = 3463434L;
		SumTask task = new SumTask(start, end);
		
		Long startTime = System.currentTimeMillis();
		Long sum = forkJoinPool.invoke(task);
		Long endTime = System.currentTimeMillis();
		System.out.println("fork/join : sum = " + sum + ", cost time = " + (endTime - startTime) + "ms");
		
		startTime = System.currentTimeMillis();
		Long sum2 = task.getCount(start, end);
		endTime = System.currentTimeMillis();
		System.out.println("normal : sum = " + sum2 + ", cost time = " + (endTime - startTime) + "ms");
	}
}
