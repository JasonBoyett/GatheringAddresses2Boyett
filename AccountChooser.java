/*
 * Jason Boyett - jaboye2448
 * CIT 4423 01
 * Nov 5, 2022
 * mac OS 12
 */
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.FlowLayout;

public class AccountChooser extends JFrame {
    private JPanel panel = new JPanel(new FlowLayout());
    private JButton create = new JButton("Create");
    private JCheckBox[] boxes;
    private Account[] accounts;
    private JTextArea target;

    public AccountChooser(Account[] accounts, JTextArea target) {
        this.target = target;
        this.setVisible(true);
        this.accounts = accounts;
        this.setAlwaysOnTop(true);
        panel.setPreferredSize(new Dimension(200, 200));
        this.add(panel);
        this.boxes = new JCheckBox[accounts.length];
        panel.add(create);
        for (int i = 0; i < accounts.length; i++) {
            this.boxes[i] = new JCheckBox(this.accounts[i].getName());
            if(!accounts[i].getComment().equals(Account.NO_COMMENT)){
                this.boxes[i].setText(this.boxes[i].getText() + ": " + accounts[i]);
            }
            panel.add(boxes[i]);
        }

        create.addActionListener(e -> this.setTarget());
        this.setSize(this.target.getParent().getSize());
    }

    public Account[] getSelected() {
        ArrayList<Account> selected = new ArrayList<>();

        for (int i = 0; i < this.boxes.length; i++) {
            if (boxes[i].isSelected()) {
                selected.add(this.accounts[i]);
            }
        }

        return selected.toArray(new Account[selected.size()]);
    }

    private void setTarget() {
        StringBuilder bld = new StringBuilder();
        for (Account account : this.getSelected()) {
            bld.append(account.getInfoString() + "\n");
        }
        this.target.setText(bld.toString());
        this.target.update(getGraphics());
        this.dispose();
    }
}