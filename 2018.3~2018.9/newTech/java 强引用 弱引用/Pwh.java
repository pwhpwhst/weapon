//�ο���https://blog.csdn.net/baidu_22254181/article/details/82555485
import java.lang.ref.ReferenceQueue;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
public class Pwh{
	public static void main(String[] args){
		//������
	//	�����ÿ��Ժ�һ�����ö���(ReferenceQueue)����ʹ�á���������������ö����������գ�JAVA������ͻ����������ü��뵽��֮���������ö����С�
	ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
    String str = new String("abc");
    SoftReference<String> softReference = new SoftReference<>(str, referenceQueue);
	str = null;

	System.gc();
    System.out.println(softReference.get()); // abc

	Reference<? extends String> reference = referenceQueue.poll();
    System.out.println(reference); //null ���ڲ�δ�����գ����û�м������ö���

	//������
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
��������	����������ʱ��	��;	����ʱ��
ǿ����	��������	�����һ��״̬	JVMֹͣ����ʱ��ֹ
������	���ڴ治��ʱ	���󻺴�	�ڴ治��ʱ��ֹ
������	������������ʱ	���󻺴�	�������պ���ֹ
������	������������ʱ	���ٶ������������	�������պ���ֹ
*/