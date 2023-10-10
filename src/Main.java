import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
 
public class Main {
 
  public static void main(String[] args) {
     
    JFrame frame = new JFrame("ウィンドウのタイトル");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 200);
    frame.setLocationRelativeTo(null);
     
    // コンポーネントの配置方法を設定
    frame.setLayout(new FlowLayout());
     
    // ボタン1を作成
    JButton button1 = new JButton("ボタン1");
    button1.addActionListener(e -> {
      System.out.println("ボタン1が押された");
    });
     
    // ボタン2を作成
    JButton button2 = new JButton("これはボタン2");
    button2.addActionListener(e -> {
      System.out.println("ボタン2が押された");
      button2.setVisible(false);
      JButton button2n = new JButton("");
      frame.add(button2n);
    });
     
    // 各ボタンをウィンドウに追加
    frame.add(button1);
    frame.add(button2);
     
    frame.setVisible(true);
  }
}