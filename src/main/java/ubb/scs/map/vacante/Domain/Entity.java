package ubb.scs.map.vacante.Domain;

public class Entity<ID> {
    private ID id;
    public Entity(ID id) {
        this.id=id;
    }
    public ID getId() {
        return id;
    }
}
