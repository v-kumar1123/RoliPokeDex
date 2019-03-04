public class Contact {
    String firstName="",lastName="",address="";
    int number;
    public Contact(String firstName, String lastName, int number, String address) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.number=number;
        this.address=address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getNumber() {
        return number;
    }

    public String toString() {
        return ""+lastName+", "+firstName;
    }
}
