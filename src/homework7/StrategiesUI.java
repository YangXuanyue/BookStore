package homework7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class StrategiesUI
    extends JFrame
    implements ActionListener {
    private Controller controller;
    private int
        d = 20,
        spL = 40,
        spW = 920,
        spH = 150,
        l = 190,
        lblW = 220,
        tfW = 360,
        btnW = 215,
        h = 30;
    private int strategyNum;
    private JTable strategiesTbl;
    private JScrollPane
        strategiesSp = null;
    private JPanel
        strategyCkbsPnl = null;
    private int[] idxToBookType;
    private JTextField
        idTf = new JTextField(),
        nameTf = new JTextField(),
        discountTf = new JTextField();
    JComboBox<String>
        typeCb = new JComboBox<>(),
        bookTypeCb = new JComboBox<>();
    ArrayList<JCheckBox> strategyCkbs = new ArrayList<>();
    private JButton
        addBtn = new JButton("添加"),
        delBtn = new JButton("删除"),
        updBtn = new JButton("更新"),
        exitBtn = new JButton("退出");

    private void update() {
        String[] colNames = {
            "编号",
            "名称",
            "类型",
            "适用图书类型",
            "折扣百分比/每本优惠额度"
        };
        strategyNum = controller.getStrategyCatalog().getStrategies().size();
        idxToBookType = new int[strategyNum];
        String[][] rowData = new String[strategyNum][];
        int idx = 0;
        if (strategyCkbsPnl != null) {
            strategyCkbsPnl.removeAll();
        } else {
            strategyCkbsPnl = new JPanel();
            strategyCkbsPnl.setLayout(new GridLayout(5, 1));
            strategyCkbsPnl.setBounds(l + lblW + d, spH + h * 5 + d * 7, tfW, spH);
            getContentPane().add(strategyCkbsPnl);
        }
        strategyCkbs.clear();
        for (Map.Entry<Integer, IPricingStrategy> entry : controller.getStrategyCatalog().getStrategies().entrySet()) {
            IPricingStrategy strategy = entry.getValue();
            rowData[idx] = new String[]{
                strategy.getId(),
                strategy.getName(),
                IPricingStrategy.typeStrs[
                    strategy.getType()
                ],
                BookSpecification.getTypeStrs()[
                    strategy.getBookType()
                ],
                strategy.getType() == IPricingStrategy.COMPOSITE?
                    strategy.getSimpleStrategyIds()
                    : String.valueOf(strategy.getDiscount())
            };
            strategyCkbs.add(
                new JCheckBox(strategy.getId())
            );
            idxToBookType[idx++] = strategy.getBookType();
        }
        strategiesTbl = new JTable(new DefaultTableModel(rowData, colNames));
        strategiesTbl.setRowSelectionAllowed(true);
        strategiesTbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (strategiesSp != null) {
            remove(strategiesSp);
        }
        strategiesSp = new JScrollPane(strategiesTbl);
        strategiesSp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        strategiesSp.setBounds(spL, d, spW, spH);
        add(strategiesSp);

        JLabel strategyLbl = new JLabel("策略");
        strategyLbl.setBounds(l, spH + h * 5 + d * 7, lblW, h);
        add(strategyLbl);
        System.out.println(strategyNum);
        for (int i = 0; i < strategyNum; ++i) {
            strategyCkbsPnl.add(strategyCkbs.get(i));
        }
        strategyCkbsPnl.revalidate();
    }

    public StrategiesUI(Controller controller) {
        this.controller = controller;

        setTitle("管理策略");
        setSize(1000, 700);
        setLayout(null);

        //strategiesSp in update()

        JLabel idLbl = new JLabel("编号");
        idLbl.setBounds(l, spH + d * 2, lblW, h);
        add(idLbl);
        idTf.setBounds(l + lblW + d, spH + d * 2, tfW, h);
        add(idTf);

        JLabel nameLbl = new JLabel("名称");
        nameLbl.setBounds(l, spH + h + d * 3, lblW, h);
        add(nameLbl);
        nameTf.setBounds(l + lblW + d, spH + h + d * 3, tfW, h);
        add(nameTf);

        JLabel typeLbl = new JLabel("类型");
        typeLbl.setBounds(l, spH + h * 2 + d * 4, lblW, h);
        add(typeLbl);
        for (String typeStr : IPricingStrategy.typeStrs) {
            typeCb.addItem(typeStr);
        }
        typeCb.setBounds(l + lblW + d, spH + h * 2 + d * 4, tfW, h);
        add(typeCb);

        JLabel bookTypeLbl = new JLabel("适用图书类型");
        bookTypeLbl.setBounds(l, spH + h * 3 + d * 5, lblW, h);
        add(bookTypeLbl);
        for (String bookTypeStr : BookSpecification.getTypeStrs()) {
            bookTypeCb.addItem(bookTypeStr);
        }
        bookTypeCb.setBounds(l + lblW + d, spH + h * 3 + d * 5, tfW, h);
        add(bookTypeCb);

        JLabel discountLbl = new JLabel("折扣百分比/每本优惠额度");
        discountLbl.setBounds(l, spH + h * 4 + d * 6, lblW, h);
        add(discountLbl);
        discountTf.setBounds(l + lblW + d, spH + h * 4 + d * 6, tfW, h);
        add(discountTf);

        //strategyCkbs in update()

        addBtn.addActionListener(this);
        addBtn.setBounds(spL, spH * 2 + h * 5 + d * 8, btnW, h);
        add(addBtn);

        delBtn.addActionListener(this);
        delBtn.setBounds(spL + btnW + d, spH * 2 + h * 5 + d * 8, btnW, h);
        add(delBtn);

        updBtn.addActionListener(this);
        updBtn.setBounds(spL + btnW * 2 + d * 2, spH * 2 + h * 5 + d * 8, btnW, h);
        add(updBtn);

        exitBtn.addActionListener(this);
        exitBtn.setBounds(spL + btnW * 3 + d * 3, spH * 2 + h * 5 + d * 8, btnW, h);
        add(exitBtn);

        update();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitBtn) {
            dispose();
        } else {
            int idx = strategiesTbl.getSelectedRow();
            if (e.getSource() == delBtn) {
                if (idx == -1) {
                    JOptionPane.showMessageDialog(
                        null,
                        "未选定待删除策略",
                        "错误",
                        JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    controller.deleteStrategy(idxToBookType[idx]);
                }
            } else {
                try {
                    String id = idTf.getText();
                    String name = nameTf.getText();
                    int type = typeCb.getSelectedIndex();
                    int bookType = bookTypeCb.getSelectedIndex();
                    ArrayList<Integer> bookTypeOfStrategies = new ArrayList<>();
                    for (int i = 0; i < strategyNum; ++i) {
                        if (strategyCkbs.get(i).isSelected()) {
                            bookTypeOfStrategies.add(idxToBookType[i]);
                        }
                    }
                    if (e.getSource() == addBtn) {
                        if (type == IPricingStrategy.COMPOSITE) {
                            if (bookTypeOfStrategies.size() == 0) {
                                JOptionPane.showMessageDialog(
                                    null,
                                    "未勾选待组合策略",
                                    "错误",
                                    JOptionPane.ERROR_MESSAGE
                                );
                            } else {
                                controller.addCompositeStrategy(
                                    id, name, bookType, bookTypeOfStrategies
                                );
                            }
                        } else {
                            double discount = Double.parseDouble(discountTf.getText());
                            controller.addSimpleStrategy(
                                id, name, type, bookType, discount
                            );
                        }
                    } else if (e.getSource() == updBtn) {
                        if (idx == -1) {
                            JOptionPane.showMessageDialog(
                                null,
                                "未选定待更新策略",
                                "错误",
                                JOptionPane.ERROR_MESSAGE
                            );
                        } else if (bookType != idxToBookType[idx]) {
                            JOptionPane.showMessageDialog(
                                null,
                                "不可更新适用图书类型，请新类型对应的策略更新",
                                "错误",
                                JOptionPane.ERROR_MESSAGE
                            );
                        } else {
                            if (type == IPricingStrategy.COMPOSITE) {
                                if (bookTypeOfStrategies.size() == 0) {
                                    JOptionPane.showMessageDialog(
                                        null,
                                        "未勾选待组合策略",
                                        "错误",
                                        JOptionPane.ERROR_MESSAGE
                                    );
                                } else {
                                    controller.updateStrategy(
                                        bookType, id, name, type, 0., bookTypeOfStrategies
                                    );
                                }
                            } else {
                                double discount = Double.parseDouble(discountTf.getText());
                                controller.updateStrategy(
                                    bookType, id, name, type, discount, null
                                );
                            }
                        }
                    }
                } catch (NumberFormatException x) {
                    JOptionPane.showMessageDialog(
                        null,
                        "折扣百分比/每本优惠额度格式错误",
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
            }
            update();
        }
    }
}
