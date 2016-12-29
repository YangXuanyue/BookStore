package homework7;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookUI
    extends JFrame
    implements ActionListener {
    private Controller controller;
    private JTextField
        titleTf = new JTextField(),
        isbnTf = new JTextField(),
        priceTf = new JTextField();
    private JComboBox<String> typeCb = new JComboBox<>();
    private JButton
        addBtn = new JButton("添加"),
        exitBtn = new JButton("退出");

    AddBookUI(Controller controller) {
        this.controller = controller;

        setTitle("添加图书");
        setSize(500, 300);
        setLayout(null);

        int
            l = 40,
            d = 20,
            h = 30,
            lblW = 100,
            tfW = 300,
            btnW = 200;

        JLabel titleLbl = new JLabel("书名");
        titleLbl.setBounds(l, d, lblW, h);
        add(titleLbl);
        titleTf.setBounds(l + lblW + d, d, tfW, h);
        add(titleTf);

        JLabel isbnLbl = new JLabel("ISBN");
        isbnLbl.setBounds(l, d * 2 + h, lblW, h);
        add(isbnLbl);
        isbnTf.setBounds(l + lblW + d, d * 2 + h, tfW, h);
        add(isbnTf);

        JLabel typeLbl = new JLabel("类型");
        typeLbl.setBounds(l, d * 3 + h * 2, lblW, h);
        add(typeLbl);
        for (String typeStr : BookSpecification.getTypeStrs()) {
            typeCb.addItem(typeStr);
        }
        typeCb.setBounds(l + lblW + d, d * 3 + h * 2, tfW, h);
        add(typeCb);

        JLabel priceLbl = new JLabel("单价");
        priceLbl.setBounds(l, d * 4 + h * 3, lblW, h);
        add(priceLbl);
        priceTf.setBounds(l + lblW + d, d * 4 + h * 3, tfW, h);
        add(priceTf);

        addBtn.addActionListener(this);
        addBtn.setBounds(l, d * 5 + h * 4, btnW, h);
        add(addBtn);

        exitBtn.addActionListener(this);
        exitBtn.setBounds(l + btnW + d, d * 5 + h * 4, btnW, h);
        add(exitBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            try {
                String title = titleTf.getText();
                String isbn = isbnTf.getText();
                int type = typeCb.getSelectedIndex();
                double price = Double.parseDouble(priceTf.getText());
                controller.addBook(title, isbn, type, price);
                JOptionPane.showMessageDialog(
                    null,
                    "添加成功"
                );
            } catch (NumberFormatException x) {
                JOptionPane.showMessageDialog(
                    null,
                    "单价格式错误",
                    "错误",
                    JOptionPane.ERROR_MESSAGE
                );
            } catch (Exception x) {
                JOptionPane.showMessageDialog(
                    null,
                    x.getMessage(),
                    "错误",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } else if (e.getSource() == exitBtn) {
            dispose();
        }
    }
}
