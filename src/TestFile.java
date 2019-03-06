import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestFile {
    public static void main(String args[]) {
        BufferedReader reader;
        int x = 0;
        String line = "";
        StringBuffer inputBuffer = new StringBuffer();

        String awesome="Varun-kumar-5-435";

        inputBuffer.append(awesome);
        line=inputBuffer.toString();

        System.out.println("LINE BEFORE "+ line);
        line=line.replace("Varun","Anish");

        System.out.println(line);

        /*try {
            /*reader = new BufferedReader(new FileReader(file));
            //String firstName, String lastName, int number, String address
            line = reader.readLine();
            while (line != null) {
                if (x == contacts2.getSelectedIndex()) {
                    inputBuffer.append(line);
                }
                line = reader.readLine();
                x++;
            }
            String inputString = inputBuffer.toString();
            reader.close();

            //inputString = inputString.replace(contacts.get(contacts2.getSelectedIndex()).getFirstName(), firstName.getText());

        }catch(IOException r){r.printStackTrace();}*/
    }
}
