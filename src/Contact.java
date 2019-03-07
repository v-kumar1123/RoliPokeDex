public class Contact implements Comparable {
    String firstName="",lastName="",address="";
    Long number;
    public Contact(String firstName, String lastName, Long number, String address){
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

    public void setNumber(Long number) {
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

    public Long getNumber() {
        return number;
    }

    public String toString() {
        return ""+lastName+", "+firstName;
    }

    public int compareTo(Object o) {
        Contact k= (Contact)o;

        if(((Contact) o).getLastName().compareToIgnoreCase(getLastName())!=0) {
            return ((Contact) o).getLastName().compareToIgnoreCase(getLastName());
        }
        else {
            return ((Contact) o).getFirstName().compareToIgnoreCase(getFirstName());
        }
    }
}
