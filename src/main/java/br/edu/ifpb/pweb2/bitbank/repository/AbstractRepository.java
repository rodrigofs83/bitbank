package br.edu.ifpb.pweb2.bitbank.repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.edu.ifpb.pweb2.bitbank.model.Entidade;

public abstract class AbstractRepository<T extends Entidade> implements Repository<T,Integer>{
    protected  Map<Integer,T> repositorio = new HashMap<Integer, T>();
    @Override
    public T findById(Integer id){
        return repositorio.get(id);
    }
    @Override
    public  List<T> findAll(){
        return repositorio.values().stream().collect(Collectors.toList());
    }
    @Override
    public  T save(T object){
        if(object.getId()==null){
            try {
                object.setId(getMaxId());
            } catch (NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        repositorio.put(object.getId(),object);
        return object;
    }
    @Override
    public void delete(T object){
        repositorio.remove(object.getId(),object);
    }
    @Override
    public void deleteById(Integer id){
        repositorio.remove(id);
    }
    public Integer getMaxId() throws NoSuchFieldException{
        List<T> objects = findAll();
        if(objects == null || objects.isEmpty())
            return 1;
        T maxId = objects
                .stream()
                .max(Comparator.comparing(T::getId))
                .orElseThrow(NoSuchFieldException::new);
        return maxId.getId() == null ? 1 : maxId.getId() +  1;
     }
}
