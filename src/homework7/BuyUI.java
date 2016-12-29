package homework7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyUI
    extends JFrame
    implements ActionListener {
    private Controller controller;
    private JTextField
        isbnTf = new JTextField(),
        copiesTf = new JTextField();
    private JButton
        buyBtn = new JButton("购买"),
        exitBtn = new JButton("退出");
    private ShoppingCartUI shoppingCartUI = new ShoppingCartUI();
    private Sale sale;

    public BuyUI(Controller controller) {
        this.controller = controller;
        this.sale = controller.getSale();
        sale.registerObserver(shoppingCartUI);

        setTitle("购买图书");
        setSize(800, 550);
        setLayout(null);

        int
            d = 20,
            spL = 40,
            l = 190,
            spW = 720,
            spH = 300,
            lblW = 100,
            isbnTfW = 300,
            copiesTfW = 250,
            unitLblW = 30,
            btnW = 200,
            h = 30;

        String[] colName = {
            "书名",
            "ISBN",
            "类型",
            "单价"
        };
        int bookNum = controller.getBookCatalog().getBooks().size();
        String[][] rowData = new String[bookNum][];
        for (int i = 0; i < bookNum; ++i) {
            BookSpecification bookSpecification =
                controller
                    .getBookCatalog()
                    .getBooks()
                    .get(i);
            rowData[i] = new String[]{
                bookSpecification.getTitle(),
                bookSpecification.getIsbn(),
                BookSpecification.getTypeStrs()[
                    bookSpecification.getType()
                ],
                String.valueOf(
                    bookSpecification.getPrice()
                )
            };
        }
        JTable booksTbl = new JTable(new DefaultTableModel(rowData, colName));
        JScrollPane booksSp = new JScrollPane(booksTbl);
        booksSp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        booksSp.setBounds(spL, d, spW, spH);
        add(booksSp);

        JLabel isbnLbl = new JLabel("ISBN");
        isbnLbl.setBounds(l, spH + d * 2, lblW, h);
        add(isbnLbl);
        isbnTf.setBounds(l + lblW + d, spH + d * 2, isbnTfW, h);
        add(isbnTf);

        JLabel copiesLbl = new JLabel("数量");
        copiesLbl.setBounds(l, spH + h + d * 3, lblW, h);
        add(copiesLbl);
        copiesTf.setBounds(l + lblW + d, spH + h + d * 3, copiesTfW, h);
        add(copiesTf);
        JLabel unitLbl = new JLabel("本");
        unitLbl.setBounds(l + lblW + d + copiesTfW + d, spH + h + d * 3, unitLblW, h);
        add(unitLbl);

        buyBtn.addActionListener(this);
        buyBtn.setBounds(l, spH + h * 2 + d * 4, btnW, h);
        add(buyBtn);

        exitBtn.addActionListener(this);
        exitBtn.setBounds(l + btnW + d, spH + h * 2 + d * 4, btnW, h);
        add(exitBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buyBtn) {
            try {
                String isbn = isbnTf.getText();
                int copies = Integer.parseInt(copiesTf.getText());
                controller.buyBook(isbn, copies);
            } catch (NumberFormatException x) {
                JOptionPane.showMessageDialog(
                    null,
                    "数量格式错误",
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
            shoppingCartUI.dispose();
            dispose();
        }
    }
}
