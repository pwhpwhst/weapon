import javax.swing.*;
import java.awt.event.*;

//http://www.runoob.com/w3cnote/java-swing-demo-intro.html

public class HelloWorldSwing {
    /**{
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    private static void createAndShowGUI() {
        // 确保一个漂亮的外观风格
		
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
		frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        // 添加 "Hello World" 标签
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);


        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();    
        // 添加面板
        frame.add(panel);


       // 创建 JLabel
        JLabel userLabel = new JLabel("User:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        /* 
         * 创建文本域用于用户输入
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

		 // 创建点击按钮
        JButton confirmButton = new JButton("confirm");
        confirmButton.setBounds(10, 80, 80, 25);
        panel.add(confirmButton);

		//点击事件
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 进行逻辑处理即可
                System.out.println("触发了事件");
            }
        });



        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // 显示应用 GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}