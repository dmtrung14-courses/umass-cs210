package src;
public class Child extends Person{
    private String school;
    public Child(String givenName, String familyName, String IDNumber, int birthYear, String school){
        super(givenName, familyName, IDNumber, birthYear);
        this.school = school;
    }
    public boolean isTeen(int currentYear){
        return this.age(currentYear) >= 13 && !this.canVote(currentYear);
    }
    public String getSchool(){
        return this.school;
    }
    public boolean isSameSchool(Child child){
        return this.school.equals(child.getSchool());
    }
    public static void main(String[] args) {
        Child a = new Child("Trung", "Dang", "33858723", 2004, "UMass");
        Child b = new Child("Trung2", "Dang", "12345678", 2004, "UMass");
        Child c = new Child("Trung3", "Dang", "12345678", 2004, "Harvard");

        System.out.println(a.getSchool());
        System.out.println(a.isSameSchool(b));
        System.out.println(a.isSameSchool(c));
        System.out.println(a.isTeen(2023));
        

    }
}