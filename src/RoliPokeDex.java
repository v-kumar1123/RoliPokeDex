import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;

public class RoliPokeDex extends JFrame {
    ArrayList<JButton> buttons=new ArrayList<JButton>();
    JTextField firstName=new JTextField();
    JTextField lastName=new JTextField();
    int oldIndex=-1;
    JTextField phoneNumber=new JTextField();
    JButton save=new JButton("Save");
    JButton neww=new JButton("New");
    JButton saveChanges=new JButton("Save Changes");
    JButton delete=new JButton("Delete Contact");
    Contact oldValue=null;
    int yeetIndex=0;
    JTextField address=new JTextField();
    JLabel firstNameLabel=new JLabel("First Name: ");
    JLabel errorLabel=new JLabel("PUT IN A VALID FIRST OR LAST NAME");
    JLabel lastNameLabel=new JLabel("Last Name: ");
    JLabel phoneNumberLabel=new JLabel("Phone Number: ");
    JLabel addressLabel=new JLabel("Address: ");
    Font font = new Font("Monotype Corsiva",Font.BOLD,24);
    //ArrayList<Contact> contacts=new ArrayList<Contact>();
    ArrayList<Contact> contacts=new ArrayList<Contact>();
    JList contacts2=new JList(contacts.toArray());
    JScrollPane scrollPane=new JScrollPane(contacts2);
    JButton clear=new JButton("Clear");
    File file = new File ("C:\\Users\\varun\\Desktop\\RoliPokeDex\\ContactLoader");
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(800,600);
        BufferedReader reader;
        for(JButton b: buttons)  {
            b.addActionListener((e) ->process(e));
        }
        errorLabel.setBounds(0,200,550,400);
        errorLabel.setFont(font);
        errorLabel.setVisible(false);
        int x=0;
        clear.setBounds(0,500,230,50);
        Long banana=new Long(-1);
        String s="";
        try{
            reader=new BufferedReader(new FileReader(file));
            String line=reader.readLine();
            //String firstName, String lastName, int number, String address
            while(line!=null) {
                if(!phoneNumber.getText().equals("")) {
                    banana=Long.parseLong(line.split("-")[2]);
                }
                if(!address.getText().equals("")) {
                    s=line.split("-")[3];
                }
                contacts.add(new Contact(line.split("-")[0],line.split("-")[1],banana,s));
                contacts2.setListData(contacts.toArray());
                line = reader.readLine();
            }
            reader.close();
        }catch (IOException r) {r.printStackTrace();}

        add(clear);
        add(errorLabel);
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
            yeetIndex=contacts2.getSelectedIndex();
            if (oldIndex != -1) {
                oldValue = contacts.get(oldIndex);
                //if (!oldValue.getFirstName().equals(firstName.getText()) || !oldValue.getLastName().equals(lastName.getText()) || !(oldValue.getNumber() + "").equals(phoneNumber.getText()) || !oldValue.getAddress().equals(address.getText())&&oldIndex!=yeetIndex&&saveChanges.isVisible()) {
                  if(oldIndex!=yeetIndex&&yeetIndex!=-1) {
                      System.out.println("\t\t\t\t\toldindex 1= yeetindex");
                      if((!oldValue.getFirstName().equals(firstName.getText()) || !oldValue.getLastName().equals(lastName.getText()) || (oldValue.getNumber())!=-1 || !oldValue.getAddress().equals(address.getText()))&&saveChanges.isVisible()) {
                          if(!firstName.getText().equals("")&&!lastName.getText().equals("")) {
                              System.out.println(oldValue.getLastName());
                              System.out.println("JAI HOOO");
                              JOptionPane jOptionPane = new JOptionPane("Confirm Changes");
                          /*jOptionPane.setVisible(true);
                          add(jOptionPane);*/
                              Object[] op = {"Yes", "No"};
                              int result = JOptionPane.showOptionDialog(jOptionPane, "Would you like to change away? All unsaved changes will be lost.", "Confirm Changeaway", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, op, op[1]);
                              jOptionPane.setBounds(0,0,200,200);
                              jOptionPane.setVisible(true);
                              if (JOptionPane.YES_OPTION == result) {
                                  firstName.setText(contacts.get(yeetIndex).getFirstName());

                                  lastName.setText(contacts.get(yeetIndex).getLastName());

                                  address.setText(contacts.get(yeetIndex).getAddress());

                                  if (contacts.get(yeetIndex).getNumber() == -1) {
                                      System.out.println("HELLO AGAIN BANANA");
                                      phoneNumber.setText("");
                                  } else {
                                      phoneNumber.setText(contacts.get(yeetIndex).getNumber() + "");
                                  }
                              } else if (JOptionPane.NO_OPTION == result) {
                                  yeetIndex = oldIndex;
                                  return;
                              }
                              return;
                          }
                      }

                    //POPUP BOX

                    /*if(oldValue.getNumber()==-1||oldValue.getAddress().equals("")) {
                        System.out.println("HEHEHEHEHHEE");


                        firstName.setText(contacts.get(contacts2.getSelectedIndex()).getFirstName());

                        lastName.setText(contacts.get(contacts2.getSelectedIndex()).getLastName());
                        if (oldValue.getNumber() != -1) {
                            System.out.println("AYO WASiod fjo;asd");
                            phoneNumber.setText(contacts.get(contacts2.getSelectedIndex()).getNumber() + "");
                        }
                        if (!oldValue.getAddress().equals("")) {
                            address.setText(contacts.get(contacts2.getSelectedIndex()).getAddress());
                        }

                    }*/
                        firstName.setText(contacts.get(contacts2.getSelectedIndex()).getFirstName());

                        lastName.setText(contacts.get(contacts2.getSelectedIndex()).getLastName());

                        address.setText(contacts.get(contacts2.getSelectedIndex()).getAddress());

                        if (contacts.get(contacts2.getSelectedIndex()).getNumber() == -1) {
                            System.out.println("HELLO AGAIN BANANA");
                            phoneNumber.setText("");
                        } else {
                            phoneNumber.setText(contacts.get(contacts2.getSelectedIndex()).getNumber() + "");
                        }
                } else{
                    System.out.println("NANI????");
                    firstName.setText(contacts.get(contacts2.getSelectedIndex()).getFirstName());

                    lastName.setText(contacts.get(contacts2.getSelectedIndex()).getLastName());

                    address.setText(contacts.get(contacts2.getSelectedIndex()).getAddress());

                    if (contacts.get(contacts2.getSelectedIndex()).getNumber() == -1) {
                        phoneNumber.setText("");
                    } else {
                        phoneNumber.setText(contacts.get(contacts2.getSelectedIndex()).getNumber() + "");
                    }
                }
            }
            oldIndex=yeetIndex;

        }
        if(e.getActionCommand().equals(saveChanges.getText())) {
            firstName.setText(firstName.getText());
            lastName.setText(lastName.getText());
            address.setText(address.getText());
            phoneNumber.setText(phoneNumber.getText());

            BufferedReader reader;
            int x = 0;
            String line;
            StringBuffer inputBuffer = new StringBuffer();

            /*try {
                reader = new BufferedReader(new FileReader(file));
                //String firstName, String lastName, int number, String address
                //line = reader.readLine();
                while ((line = reader.readLine()) != null) {
                    System.out.println("OUVE PASWED");

                    if (x == contacts2.getSelectedIndex()) {
                        System.out.println("AYYO WASSUP");
                        inputBuffer.append(line);
                        System.out.println(inputBuffer);
                        break;
                    }

                    System.out.println("\t\t\t\tREADER LINE"+line);
                    x++;
                }
                String inputString = inputBuffer.toString();

                System.out.println("INPUT STRING B4" + inputString);

                inputString = inputString.replace(contacts.get(contacts2.getSelectedIndex()).getFirstName(), firstName.getText());

                inputString = inputString.replace(contacts.get(contacts2.getSelectedIndex()).getLastName(), lastName.getText());

                inputString = inputString.replace(contacts.get(contacts2.getSelectedIndex()).getAddress(), address.getText());

                inputString = inputString.replace(contacts.get(contacts2.getSelectedIndex()).getNumber() + "", phoneNumber.getText());

                System.out.println("THIS IS INPUT STRING" + inputString);

                FileOutputStream fileOut = new FileOutputStream(file);
                fileOut.write(inputString.getBytes());
            } catch (IOException r) {
                r.printStackTrace();
            }catch (Exception t) {
                t.printStackTrace();
            }*/
            /*try{
                reader=new BufferedReader(new FileReader(file));
                line=reader.readLine();
                //String firstName, String lastName, int number, String address
                while(line!=null) {
                    contacts.add(new Contact(line.split("-")[0],line.split("-")[1],Integer.parseInt(line.split("-")[2]),line.split("-")[3]));
                    contacts2.setListData(contacts.toArray());
                    line = reader.readLine();
                }
            }catch (IOException r) {r.printStackTrace();}*/


            contacts.get(yeetIndex).setFirstName(firstName.getText());

            contacts.get(yeetIndex).setAddress(address.getText());

            contacts.get(yeetIndex).setLastName(lastName.getText());

            if (!phoneNumber.getText().equals("")) {
                contacts.get(yeetIndex).setNumber(Long.parseLong(phoneNumber.getText()));
            }
            else {
                contacts.get(yeetIndex).setNumber(new Long(-1));
            }
            /*try {
                new PrintWriter(file).close();
            }catch (IOException w) {w.printStackTrace();}
            for(Contact c:contacts) {
                try {
                    PrintWriter p = new PrintWriter(file);
                    System.out.println("HEYYYYYYYOOOOOOOO");
                    p.println(c.firstName+" -"+c.lastName+" -"+c.number+" -"+c.address);
                } catch (IOException w) {
                    w.printStackTrace();
                }
            }*/
            firstName.setText("");

            lastName.setText("");

            address.setText("");

            phoneNumber.setText("");

            saveChanges.setVisible(false);
            delete.setVisible(false);
            save.setVisible(true);
            neww.setVisible(true);

            contacts2.clearSelection();
            //TODO 3/5/19 : CHANGE FILE TO SAVE CHANGES
        }

        if(e.getActionCommand().equals(delete.getText())) {
            contacts.remove(yeetIndex);
            contacts2.setListData(contacts.toArray());

            firstName.setText("");

            lastName.setText("");

            address.setText("");


            phoneNumber.setText("");



            saveChanges.setVisible(false);
            delete.setVisible(false);
            save.setVisible(true);
            neww.setVisible(true);
        }
        if(e.getActionCommand().equals(save.getText())) {
            //TODO: write to file, make new object and add to Contact ArrayList, as long as it has a first and last name.
            if(!firstName.getText().equals("")&&!lastName.getText().equals("")) {
                firstNameLabel.setForeground(Color.BLACK);
                lastNameLabel.setForeground(Color.BLACK);
                System.out.println("HELLO");
                try {
                    PrintWriter writer = new PrintWriter(file);
                    writer.print(firstName.getText()+" -"+lastName.getText()+" -"+phoneNumber.getText()+" -"+address.getText()+"\n");
                    writer.close();
                } catch (FileNotFoundException u) {
                    u.printStackTrace();
                }
                errorLabel.setVisible(false);
                Collections.sort(contacts);
                contacts2.setListData(contacts.toArray());
            }
            else {
                if(firstName.getText().equals("")) {
                    firstNameLabel.setForeground(Color.RED);
                }
                if(lastName.getText().equals("")) {
                    lastNameLabel.setForeground(Color.RED);
                }
                errorLabel.setVisible(true);
                Collections.sort(contacts);
                contacts2.setListData(contacts.toArray());
                contacts2.clearSelection();
                Collections.sort(contacts);
                contacts2.setListData(contacts.toArray());
                contacts2.clearSelection();
                return;
            }

            BufferedReader reader;
            Long banana=new Long(-1);
            String apple="";
            try{
                reader=new BufferedReader(new FileReader(file));
                String line=reader.readLine();
                //String firstName, String lastName, int number, String address
                while(line!=null) {
                    if(!phoneNumber.getText().equals("")) {
                        banana=Long.parseLong(phoneNumber.getText());
                    }
                    if(!address.getText().equals("")) {
                        apple=line.split("-")[3];
                    }
                    contacts.add(new Contact(line.split("-")[0],line.split("-")[1],banana,apple));
                    contacts2.setListData(contacts.toArray());
                    line = reader.readLine();
                }
                reader.close();
            }catch (IOException r) {r.printStackTrace();}



            contacts2.clearSelection();


            firstName.setText("");

            lastName.setText("");

            address.setText("");


            phoneNumber.setText("");
        }
        if(e.getActionCommand().equals(neww.getText())) {
            System.out.println("HEYYYWASSUP");

            contacts2.clearSelection();

            firstName.setText("");

            lastName.setText("");

            address.setText("");

            phoneNumber.setText("");

            saveChanges.setVisible(false);
            delete.setVisible(false);
            save.setVisible(true);
            neww.setVisible(true);

        }
        if(e.getActionCommand().equals(clear.getText())) {

            saveChanges.setVisible(false);
            delete.setVisible(false);
            save.setVisible(true);
            neww.setVisible(true);


            contacts2.clearSelection();

            firstName.setText("");

            lastName.setText("");

            address.setText("");

            phoneNumber.setText("");

            contacts.clear();
            contacts2.setListData(contacts.toArray());
        }
        Collections.sort(contacts,Collections.reverseOrder());
        contacts2.setListData(contacts.toArray());
        contacts2.clearSelection();
    }
}
