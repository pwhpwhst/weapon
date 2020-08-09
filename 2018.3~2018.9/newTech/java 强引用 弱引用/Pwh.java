//参考：https://blog.csdn.net/baidu_22254181/article/details/82555485
import java.lang.ref.ReferenceQueue;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
public class Pwh{
	public static void main(String[] args){
		//软引用
	//	软引用可以和一个引用队列(ReferenceQueue)联合使用。如果软引用所引用对象被垃圾回收，JAVA虚拟机就会把这个软引用加入到与之关联的引用队列中。
	ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
    String str = new String("abc");
    SoftReference<String> softReference = new SoftReference<>(str, referenceQueue);
	str = null;

	System.gc();
    System.out.println(softReference.get()); // abc

	Reference<? extends String> reference = referenceQueue.poll();
    System.out.println(reference); //null 由于并未被回收，因此没有加入引用队列

	//弱引用
	str = new String("abc2");
    WeakReference<String> weakReference = new WeakReference<>(str, referenceQueue);
	System.out.println(weakReference.get()); // abc2
    str = null;

	System.gc();
    System.out.println(weakReference.get()); // null
	reference = referenceQueue.poll();
    System.out.println(reference);// java.lang.ref.WeakReference@15db9742
	}
}

/**
引用类型	被垃圾回收时间	用途	生存时间
强引用	从来不会	对象的一般状态	JVM停止运行时终止
软引用	当内存不足时	对象缓存	内存不足时终止
弱引用	正常垃圾回收时	对象缓存	垃圾回收后终止
虚引用	正常垃圾回收时	跟踪对象的垃圾回收	垃圾回收后终止
*/