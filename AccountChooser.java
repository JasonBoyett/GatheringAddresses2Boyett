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
    private JCheckBox boxes[];
    private Account accounts[];
    private JTextArea target;

    public AccountChooser(Account accounts[], JTextArea target) {
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
            panel.add(boxes[i]);
        }

        create.addActionListener(e -> this.setTarget());
        this.pack();
    }

    public Account[] getSelected() {
        ArrayList<Account> selected = new ArrayList();

        for (int i = 0; i < this.boxes.length; i++) {
            if (boxes[i].isSelected()) {
                selected.add(this.accounts[i]);
            }
        }

        return selected.toArray(new Account[selected.size()]);
    }

    private void setTarget(){
        String result = "";
        for(Account account : this.getSelected()){
            result += account.getInfoString();
        }
        this.target.setText(result);
        this.target.update(getGraphics());
        this.dispose();
    }
}