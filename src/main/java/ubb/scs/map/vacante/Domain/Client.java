package ubb.scs.map.vacante.Domain;

public class Client extends Entity<Long>{
    private String name;
    private int fidelityGrade;
    private int age;
    private Hobby hobby;
    public Client(Long id, String name, int fidelityGrade, int age, Hobby hobby) {
        super(id);
        this.name = name;
        this.fidelityGrade = fidelityGrade;
        this.age = age;
        this.hobby = hobby;
    }
    public String getName() {
        return name;
    }
    public int getFidelityGrade(){
        return fidelityGrade;
    }
    public int getAge(){
        return age;
    }
    public Hobby getHobby(){
        return hobby;
    }
}
