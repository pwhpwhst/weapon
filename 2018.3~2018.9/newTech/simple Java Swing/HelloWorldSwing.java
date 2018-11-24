import javax.swing.*;
import java.awt.event.*;

//http://www.runoob.com/w3cnote/java-swing-demo-intro.html

public class HelloWorldSwing {
    /**{
     * ��������ʾGUI�������̰߳�ȫ�Ŀ��ǣ�
     * ����������¼������߳��е��á�
     */
    private static void createAndShowGUI() {
        // ȷ��һ��Ư������۷��
		
        JFrame.setDefaultLookAndFeelDecorated(true);

        // ���������ô���
        JFrame frame = new JFrame("HelloWorldSwing");
		frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        // ��� "Hello World" ��ǩ
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);


        /* ������壬��������� HTML �� div ��ǩ
         * ���ǿ��Դ��������岢�� JFrame ��ָ��λ��
         * ��������ǿ�������ı��ֶΣ���ť�����������
         */
        JPanel panel = new JPanel();    
        // ������
        frame.add(panel);


       // ���� JLabel
        JLabel userLabel = new JLabel("User:");
        /* ������������������λ�á�
         * setBounds(x, y, width, height)
         * x �� y ָ�����Ͻǵ���λ�ã��� width �� height ָ���µĴ�С��
         */
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        /* 
         * �����ı��������û�����
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

		 // ���������ť
        JButton confirmButton = new JButton("confirm");
        confirmButton.setBounds(10, 80, 80, 25);
        panel.add(confirmButton);

		//����¼�
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // �����߼�������
                System.out.println("�������¼�");
            }
        });



        // ��ʾ����
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // ��ʾӦ�� GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}