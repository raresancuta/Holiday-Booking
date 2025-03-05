package ubb.scs.map.vacante.Repository;

import ubb.scs.map.vacante.Domain.Entity;

import java.util.Optional;

public interface Repository<ID,E extends Entity<ID>> {
    public Optional<E> find(ID id);
}
