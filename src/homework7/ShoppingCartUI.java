package homework7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ShoppingCartUI
    extends JFrame
    implements Observer {

    ShoppingCartUI() {
    }

    @Override
    public void update(Sale sale) {
        setTitle("购物车");
        setSize(500, 500);
        setLayout(null);

        int
            d = 20,
            spL = 40,
            spW = 420,
            spH = 300,
            w = 200,
            h = 30;

        String[] colNames = {
            "书名",
            "数量"
        };
        int itemNum = sale.getItems().size();
        String[][] rowData = new String[itemNum][];
        for (int i = 0; i < itemNum; ++i) {
            SaleLineItem item = sale.getItems().get(i);
            rowData[i] = new String[]{
                item.getBookTitle(),
                String.valueOf(
                    item.getCopies()
                )
            };
        }
        JTable booksTable = new JTable(new DefaultTableModel(rowData, colNames));
        JScrollPane scrollPane = new JScrollPane(booksTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(spL, d, spW, spH);
        add(scrollPane);

        JLabel totalLbl = new JLabel(
            String.format("总价：%.2f元", sale.getTotal())
        );
        totalLbl.setBounds(spL + w + d, spH + d * 2, w, h);
        add(totalLbl);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
