package src;
class Person {

    private String givenName;
    private String familyName;
    private String IDNumber;
    private int birthYear;

    private static final int votingAge = 18;
    private static final int seniorAge = 65;

    public Person(String givenName, String familyName, String IDNumber, int birthYear) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.IDNumber = IDNumber;
        this.birthYear = birthYear;
    }

    public int age(int currentYear) {
        return currentYear - birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    // Implement:

    // Must be at least voting age to vote
    public boolean canVote(int currentYear) {
        return this.age(currentYear) >= votingAge;
    }
    // Must be at least seniorAge to vote
    public boolean isSenior(int currentYear)  {
        return this.age(currentYear) >= seniorAge;
    }

    // Create a new method canVoteNotSenior
    // parameter: int currentYear
    // Returns: True if Can vote but is not a senior citizen; false otherwise
    public boolean canVoteNotSenior(int currentYear){
        return canVote(currentYear) && !isSenior(currentYear);
    }    

    public static void main(String[] args) {
        Person author2 = new Person("Paul", "Wolfgang", null, 1900);

        System.out.println(author2.age(2023));

        System.out.println(author2.canVote(2023));
        
    }
}