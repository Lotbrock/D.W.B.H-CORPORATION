package co.edu.sena.horarios.model.controller.dao;


import java.util.Collection;

public interface InterefaceDao<T> {

    public void insert(T entity);

    public void update(T entity);

    public void remove(T entity);


    public T find(Object id);

    public Collection<T> findAll();
}
