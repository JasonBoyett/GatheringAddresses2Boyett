import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FileButton extends JButton {
    UI parentUI;
    JTextArea text = new JTextArea();
    JScrollPane scroll = new JScrollPane(text);

    public FileButton(UI parent) {
        super("Select File");
        this.parentUI = parent;
        this.addActionListener(e -> press());
        this.parentUI = parent;
        this.text.setEditable(false);
    }

    public void press() {
        try {
            JFileChooser chooser = new JFileChooser();
            int found = chooser.showOpenDialog(parentUI);

            if (found == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                Account accounts[] = constructAccounts(getStringFromFile(filename));
                AccountChooser accountChooser = new AccountChooser(accounts, this.parentUI.getTextArea());
                Thread.sleep(5);
                parentUI.getExit().setEnabled(true);
            } else {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("file not found");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            parentUI.getPanel().repaint();
        }

    }

    private Account[] constructAccounts(String constructionString) {// creates account objects from the given
                                                                    // string and stores them in an array that is
                                                                    // then returned

        String objectDelimiter = "%";
        StringTokenizer tokenizer = new StringTokenizer(constructionString, objectDelimiter);
        Account[] myAccounts = new Account[tokenizer.countTokens()];// sets the size of the array based on the number of tokens
        try {
            for (int i = 0; i < myAccounts.length; i++) {// breaks up the constructionString into constructors for individual accounts
                myAccounts[i] = new Account(tokenizer.nextToken());
            }
            return myAccounts;// returns the array
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private String getStringFromFile(String localFileName) throws Exception {// reads in a file and returns the
                                                                             // contents as a string
        try {
            String result = "";
            File file = new File(localFileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                result += scanner.nextLine();
            }
            scanner.close();

            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}