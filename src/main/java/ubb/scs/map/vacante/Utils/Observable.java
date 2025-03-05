package ubb.scs.map.vacante.Utils;

public interface Observable{
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}