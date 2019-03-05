import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class RoliPokeDex extends JFrame {
    ArrayList<JButton> buttons=new ArrayList<JButton>();
    JTextField firstName=new JTextField();
    JTextField lastName=new JTextField();
    JTextField phoneNumber=new JTextField();
    JButton save=new JButton("Save");
    JButton neww=new JButton("New");
    JButton saveChanges=new JButton("Save Changes");
    JButton delete=new JButton("Delete Contact");

    JTextField address=new JTextField();
    JLabel firstNameLabel=new JLabel("First Name: ");
    JLabel lastNameLabel=new JLabel("Last Name: ");
    JLabel phoneNumberLabel=new JLabel("Phone Number: ");
    JLabel addressLabel=new JLabel("Address: ");
    Font font = new Font("Monotype Corsiva",Font.BOLD,24);
    //ArrayList<Contact> contacts=new ArrayList<Contact>();
    ArrayList<Contact> contacts=new ArrayList<Contact>();
    JList contacts2=new JList(contacts.toArray());
    JScrollPane scrollPane=new JScrollPane(contacts2);
    JButton clear=new JButton("Clear");
    File file = new File ("C:\\Users\\OTHSCS097\\Desktop\\RoliPokeDex\\src\\ContactLoader");
    boolean editing=false;

    /*TODO: Change buttons (Save Changes and Delete contact and Save an New) change via setVisible().
        TODO: Have a are you sure window open when someone clicks away from the window while editing, does not save changes. Get the text from the Contact object.
        TODO: List of contact on the side should be scrollable when the list extends past the window
        TODO: List of contact on the side is a JScrollPane, it automatically updates to scroll.
        TODO: If an index is selected, change the buttons, seen in the first sentence.
        TODO: Contact is what all the textfields are got from. Only when save changes is clicked, does the contact object update
     */

    public RoliPokeDex() {
        super("K0904676");
        buttons.add(save);
        buttons.add(saveChanges);
        buttons.add(neww);
        buttons.add(delete);
        buttons.add(clear);
        setVisible(true);
        setLayout(null);
        setSize(800,600);
        BufferedReader reader;
        for(JButton b: buttons)  {
            b.addActionListener((e) ->process(e));
        }
        int x=0;
        clear.setBounds(0,500,230,50);
        try{
            reader=new BufferedReader(new FileReader(file));
            String line=reader.readLine();
            //String firstName, String lastName, int number, String address
            while(line!=null) {
                contacts.add(new Contact(line.split(" ")[0],line.split(" ")[1],Integer.parseInt(line.split(" ")[2]),line.split(" ")[3]));
                contacts2.setListData(contacts.toArray());
                line = reader.readLine();
            }
            reader.close();
        }catch (IOException r) {r.printStackTrace();}

        add(clear);

        //contacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
        scrollPane.setBounds(450,0,300,550);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        //scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        firstNameLabel.setFont(font);
        firstNameLabel.setBounds(5,20,150,30);
        lastNameLabel.setBounds(5,60,150,30);
        lastNameLabel.setFont(font);
        lastName.setFont(font);
        lastName.setBounds(175,60,250,30);
        phoneNumberLabel.setBounds(5,100,180,30);
        phoneNumberLabel.setFont(font);
        phoneNumber.setBounds(175,100,250,30);
        addressLabel.setBounds(5,140,150,30);
        address.setBounds(175,140,250,30);
        phoneNumber.setFont(font);
        addressLabel.setFont(font);
        address.setFont(font);
        firstName.setFont(font);
        firstName.setBounds(175,20,250,30);
        add(firstNameLabel);
        add(lastNameLabel);
        add(firstName);
        add(lastName);
        add(phoneNumber);
        add(phoneNumberLabel);
        add(address);
        add(addressLabel);
        add(scrollPane);
        add(saveChanges);
        add(save);
        add(neww);
        add(delete);
        if(editing) {
            save.setVisible(false);
            neww.setVisible(false);
            saveChanges.setVisible(true);
            delete.setVisible(true);
        }
        else{
            save.setVisible(true);
            neww.setVisible(true);
            saveChanges.setVisible(false);
            delete.setVisible(false);

        }

        saveChanges.setBounds(0,215,150,50);

        save.setBounds(0,215,150,50);

        delete.setBounds(0,285,150,50);

        neww.setBounds(0,285,150,50);


        contacts2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                process(new ActionEvent(e.getSource(),e.getID(),""));
            }
        });


    }
    public void process(ActionEvent e) {
        PrintWriter outputStream;
        try {
            outputStream = new PrintWriter(file);
        }catch (FileNotFoundException u){u.printStackTrace();}
        if(contacts2.getSelectedIndex()>=0) {
            System.out.println("HEYYO");
            editing=true;
        }
        if(editing) {
            save.setVisible(false);
            neww.setVisible(false);
            saveChanges.setVisible(true);
            delete.setVisible(true);
        }
        else{
            save.setVisible(true);
            neww.setVisible(true);
            saveChanges.setVisible(false);
            delete.setVisible(false);

        }
        if(e.getActionCommand().equals("")) {
            firstName.setText(contacts.get(contacts2.getSelectedIndex()).getFirstName());

            lastName.setText(contacts.get(contacts2.getSelectedIndex()).getLastName());

            address.setText(contacts.get(contacts2.getSelectedIndex()).getAddress());

            phoneNumber.setText(contacts.get(contacts2.getSelectedIndex()).getNumber()+"");

        }
        if(e.getActionCommand().equals(saveChanges.getText())) {
            firstName.setText(firstName.getText());
            lastName.setText(lastName.getText());
            address.setText(address.getText());
            phoneNumber.setText(phoneNumber.getText());

            //TODO 3/5/19 : CHANGE FILE TO SAVE CHANGES
        }
        if(e.getActionCommand().equals(save.getText())) {
            //TODO: write to file, make new object and add to Contact ArrayList, as long as it has a first and last name.
            if(!firstName.getText().equals("")&&!lastName.getText().equals("")) {
                System.out.println("HELLO");
                try {
                    PrintWriter writer = new PrintWriter(file);
                    writer.println(firstName.getText()+" "+lastName.getText()+" "+phoneNumber.getText()+" "+address.getText());
                    writer.close();
                } catch (FileNotFoundException u) {
                    u.printStackTrace();
                }
            }
            else {
                if(firstName.getText().equals("")) {
                    firstNameLabel.setForeground(Color.RED);
                }
                if(lastName.getText().equals("")) {
                    lastNameLabel.setForeground(Color.RED);
                }
            }

            BufferedReader reader;
            try{
                reader=new BufferedReader(new FileReader("C:\\Users\\othscs097\\Desktop\\RoliPokeDex\\src\\ContactLoader"));
                String line=reader.readLine();
                //String firstName, String lastName, int number, String address
                while(line!=null) {
                    contacts.add(new Contact(line.split(" ")[0],line.split(" ")[1],Integer.parseInt(line.split(" ")[2]),line.split(" ")[3]));
                    contacts2.setListData(contacts.toArray());
                    line = reader.readLine();
                }
                reader.close();
            }catch (IOException r) {r.printStackTrace();}
        }
        if(e.getActionCommand().equals(clear.getText())) {
            contacts.clear();
            contacts2.setListData(contacts.toArray());
        }
    }
}
