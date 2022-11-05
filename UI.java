import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.DimensionUIResource;

import java.awt.GridLayout;

public class UI extends JFrame {
    private JTextArea text = new JTextArea("please select a file to be read from");
    private FileButton fileButton = new FileButton(this);
    private GridLayout grid = new GridLayout(3, 1, 10, 10);
    private JPanel panel = new JPanel(grid);
    private JButton exit = new JButton("Exit");
    private JFileChooser chooser = new JFileChooser();

    public UI() {
        this.text.setEditable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(panel);
        panel.add(fileButton);
        panel.setPreferredSize(new DimensionUIResource(500, 500));
        JScrollPane scroll = new JScrollPane(this.text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);
        exit.setEnabled(false);
        exit.addActionListener(e -> System.exit(0));
        panel.add(exit);
        this.pack();
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public JTextArea getTextArea() {
        return this.text;
    }

    public JButton getExit(){
        return this.exit;
    }
}