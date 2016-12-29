package homework7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI
    extends JFrame
    implements ActionListener {
    private Controller controller = new Controller();
    private JButton
        addBookBtn = new JButton("添加图书"),
        strategiesBtn = new JButton("管理策略"),
        buyBtn = new JButton("购买图书"),
        exitBtn = new JButton("退出系统");

    MainUI() {
        setTitle("图书购物车系统");
        setSize(300,300);
        setLayout(new GridLayout(4, 1, 0, 20));

        addBookBtn.addActionListener(this);
        add(addBookBtn);
        strategiesBtn.addActionListener(this);
        add(strategiesBtn);
        buyBtn.addActionListener(this);
        add(buyBtn);
        exitBtn.addActionListener(this);
        add(exitBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBookBtn) {
            new AddBookUI(controller);
        } else if (e.getSource() == strategiesBtn) {
            new StrategiesUI(controller);
        } else if (e.getSource() == buyBtn) {
            new BuyUI(controller);
        } else if (e.getSource() == exitBtn) {
            dispose();
        }
    }
}
