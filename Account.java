/*
 * Jason Boyett - jaboye2448
 * CIT 4423 01
 * Oct 16, 2022
 * mac OS 12
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Account implements Serializable{

    String id = "";
    String name = "";
    String address = "";
    String state = "";
    String city = "";
    String zipCode = "";
    String phone = "";
    String timeToContact = "";
    String comment = "";
    private static final String ASK_FOR_COMMENT = "Do you want to add a comment to this account?"; 
    LocalDateTime timeCreated;
    int balance = 0;//saved as an integer value of cents

    public Account(String constructor){//object constructor


        parseConstructorString(constructor,this);//this function parses the constructor string into the data fields of the constructed object
        String nameIDAndComment = "Account ID : " + this.id + "\n" +"Name: "+ this.name + "\n" + ASK_FOR_COMMENT;
        this.timeCreated = LocalDateTime.now();//shows the time the object was created
        this.comment = JOptionPane.showInputDialog(null, nameIDAndComment);//adds a comment to the account
        if(this.comment.equals(null) || this.comment.equals("")){//if the user doesn't add a comment the comment will be "no comment added"
            this.comment = "No comment added.";
        }
        serializeAccount();//account object is serialized

    }

    public String getInfoString(){//returns the attributes of the object in a string

        String info = "Id: " + this.id + "\n";
        info += "Name: " + this.name + "\n";
        info += "Address: " + this.address + "\n";
        info += "Phone: " + this.phone + "\n";
        info += "State: " + this.state + "\n";
        info += "City: " + this.city + "\n";
        info += "Zip: " + this.zipCode + "\n";
        info += "Best time to contact: " + this.timeToContact + "\n";
        info += String.format("Balance: $%,.2f \n", (((double)this.balance)/100));
        info += "Comment: " + this.comment + "\n";
        info += "Last update: " + this.timeCreated + "\n";

        return info;
    }

   

    public void parseConstructorString(String constructor, Account account){
        String[] attributes = new String[9];
        int startIndex = 0;
        String attributeDelimiter = "$";
        StringTokenizer tokenizer = new StringTokenizer(constructor, attributeDelimiter);

        for(int i = 0; i < attributes.length ; i++) {
            attributes[i] = tokenizer.nextToken();
        }

        account.id = attributes[0];
        account.name = attributes[1];
        account.address = attributes[2];
        account.state = attributes[3];
        account.city = attributes[4];
        account.zipCode = attributes[5];
        account.phone = attributes[6];
        account.timeToContact = attributes[7];
        account.balance = Integer.parseInt(attributes[8]);

    }

    private void serializeAccount(){//stores the account in a persistent file
        try {

            FileOutputStream fileOut = new FileOutputStream("stored_accounts.cer");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(this);
            objOut.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public String getName(){
        return this.name;
    }

}