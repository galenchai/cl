package me.galenchai.basic.lock;

/**
 * Each Class/Object has a lock monitor separately.
 * Class Lock and Object Lock effects in the different scope.
 * Actually Class Lock doesnot exists
 * @author Galen Chai
 * 2017年4月27日下午8:56:07
 *
 */
public class TestSync {
	
	public static void main(String[] args) {
		testClassLevelSync();
	}
	

	private static void testClassLevelSync() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Target.staticSyncPrint();
			}
			
		}).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				/**
				 * 1. If following method is a static synchronized method, it will be blocked until the first thread is executed; this scenario can be test
				 * by uncomment staticSyncPrint1();
				 * 2. If following method is a static method without synchronized method, it willnot be blocked
				 * 3. If following method is a non-static synchronized method, it will not be blocked
				 */
				//Target.staticSyncPrint1();
				//Target.staticPrint();
				new Target().syncPrint();
			}
			
		}).start();
	}
	

}

class Target {
	

	public static synchronized void staticSyncPrint() {
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("I am a class-level synchronized method");
	}
	
	public static synchronized void staticSyncPrint1() {
		System.out.println("I am a class-level synchronized method1");
	}
	
	public static void staticPrint() {
		System.out.println("I am a class-level none synchronized method");
	}
	
	public synchronized void syncPrint() {
		System.out.println("I am a object-level synchronized method");
	}
	
	public void nonSyncPrint() {
		System.out.println("I am a object-level none synchronized method");
	}
	
}
