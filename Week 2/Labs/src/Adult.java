package src;
public class Adult extends Person {

    private boolean license;
    public Adult(String givenName, String familyName, String IDNumber, int birthYear, boolean license) {
        super(givenName, familyName, IDNumber, birthYear);
        this.license = license;
    }
      

    public static void main(String[] args) {
        Person a = new Adult("Paul", "Wolfgang", null, 1900, false);
        System.out.println(a.age(2030));

        // What does Child automatically inherit?
        Child w = new Child("Trung", "Dang", "33858723", 2004, "UMass");

        
    }
}