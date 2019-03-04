import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
    DefaultListModel contacts=new DefaultListModel();

    JList contacts2=new JList(contacts);
    JScrollPane scrollPane=new JScrollPane(contacts2);

    /*TODO: Change buttons (Save Changes and Delete contact and Save an New) change via setVisible().
        TODO: Have a are you sure window open when someone clicks away from the window while editing, does not save changes. Get the text from the Contact object.
        TODO: List of contact on the side should be scrollable when the list extends past the window
        TODO: List of contact on the side is a JScrollPane, it automatically updates to scroll.
        TODO: If an index is selected, change the buttons, seen in the first sentence.
        TODO: Contact is what all the textfields are got from. Only when save changes is clicked, does the contact object update
     */

    public RoliPokeDex() {
        super("K0904676");
        setVisible(true);
        setLayout(null);
        setSize(800,600);
        BufferedReader reader;
        try{
            reader=new BufferedReader(new FileReader("C:\\Users\\varun\\Desktop\\RoliPokeDex\\src\\ContactLoader"));
            String line=reader.readLine();
            //String firstName, String lastName, int number, String address
            while(line!=null) {
                contacts.addElement(new Contact(line.split(" ")[0],line.split(" ")[1],Integer.parseInt(line.split(" ")[2]),line.split(" ")[3]));
                line = reader.readLine();
            }
            reader.close();
        }catch (IOException r) {r.printStackTrace();}
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



    }
}
