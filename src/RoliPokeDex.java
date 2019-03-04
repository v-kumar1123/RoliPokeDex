import javax.swing.*;
import java.awt.*;

public class RoliPokeDex extends JFrame {
    JTextField firstName=new JTextField();
    JTextField lastName=new JTextField();
    JTextField phoneNumber=new JTextField();
    JTextField address=new JTextField();
    JLabel firstNameLabel=new JLabel("First Name: ");
    JLabel lastNameLabel=new JLabel("Last Name: ");
    JLabel phoneNumberLabel=new JLabel("Phone Number: ");
    JLabel addressLabel=new JLabel("Address: ");
    Font font = new Font("Monotype Corsiva",Font.BOLD,24);

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
        firstNameLabel.setFont(font);
        firstNameLabel.setBounds(5,20,150,30);
        lastNameLabel.setBounds(5,60,150,30);
        lastNameLabel.setFont(font);

        add(firstNameLabel);
        add(lastNameLabel);

    }
}
