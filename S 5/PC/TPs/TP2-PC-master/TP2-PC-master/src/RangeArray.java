import  java.util.Iterator;
public class RangeArray<T> implements Iterable<T> {
     //Les element de tableux
   // En va essai de crée un tableus d'array
    private int indexMin, indexMax;
    private T[] array;


    public RangeArray(int indexMin, int indexMax)
    {
        this.indexMin = indexMin;
        this.indexMax = indexMax;
        this.array = (T[]) new Object[indexMax-indexMin+1];
    }

    public int getIndexMin() {
        return indexMin;
    }

    public int getIndexMax() {
        return indexMax;
    }

    public void set(int index, T value) {
       this.array[index-this.indexMin] = value;

    }
    public T get(int index) {
        return this.array[index-this.indexMin];
    }

    public int rangeSize()
    {
        return this.array.length;
    }
    @Override
    public Iterator<T> iterator(){
        return new RangeArrayIterator();
    }

    public class RangeArrayIterator implements Iterator<T> {
        int position = indexMin - 1;

        @Override
        public boolean hasNext() {
            return  position < indexMax;

        }
        @Override
        public T next() {
            position++;
            return  get(position);
        }
        @Override
        public void remove(){

        }
    }
}

/*
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexis Couvreur on 27/09/2016.


/**
 * La classe <code>RangeArray</code> permet d'instancier
 * un tableau de type T avec un début pas forcément égal à 0, et
 * une fin > à début.
 * @param <T>

public class RangeArray<T> implements Iterable<T> {

    /**
     * Les éléments du tableau
     *

    private Object[] elements;

    /**
     * L'index minimum et maximum
     *
    private int indexMin, indexMax;

    /**
     * Construit un tableau de taille <code>indexMax</code> - <code>indexMin</code> + 1
     * @param indexMin L'index de début du tableau
     * @param indexMax L'index de fin du tableau
     *
    public RangeArray(int indexMin, int indexMax){
        this.indexMin = indexMin;
        this.indexMax = indexMax;
        int size = indexMax - indexMin + 1;
        elements = new Object[size];
    }

    /**
     * Permet d'obtenir l'index de début
     * @return index minimum du tableau
     *
    public int getIndexMin() {
        return indexMin;
    }

    /**
     * Permet d'obtenir l'index de fin
     * @return index maximum du tableau
     *
    public int getIndexMax() {
        return indexMax;
    }

    /**
     * Permet d'obtenir la valeur du tableau à l'index <code>index</code>
     * @param index l'indice de la valeur requêtée dans le tableau
     * @return la valeur du tableau à l'indice <code>index</code>
     *
    public T get(int index) {
        assert userIndexIsValid(index);
        return (T) elements[internalIndex(index)];
    }

    /**
     * Permet d'obtenir la taille du tableau
     * @return La capacité du tableau
     *
    public int rangeSize() {
        return this.indexMax - this.indexMin + 1;
    }

    /**
     * Permet d'atribuer un valeur <code>value</code> à la case du tableau
     * à l'indice <code>index</code>
     * Si l'indice n'est pas bon, on ne fait rien
     * @param userIndex l'index de l'utilisateur
     *                  (compris entre <code>indexMin</code> et <code>indexMax</code>)
     * @param value la valeur à attribuer à la case ciblée
     *
    public void set(int userIndex, T value) {
        assert userIndexIsValid(userIndex);
        int internalIndex = internalIndex(userIndex);
        elements[internalIndex] = value;
    }

    /**
     *
     * @param userIndex
     * @return
     *
    public boolean userIndexIsValid(int userIndex){
        return indexMin <= userIndex && userIndex <= indexMax;
    }

    /**
     * Permet de donner l'index réel du tableau par rapport à l'index de l'utilisateur
     * @param userIndex l'index compris entre <code>indexMin</code> et <code>indexMax</code>
     * @return l'index de la case réel
     *
    private int internalIndex(int userIndex){
        return indexMax - userIndex;
    }


    /**
     *
     * @return
     *
    public Iterator<T> iterator() {

        Iterator<T> iElements = (Iterator<T>) Arrays.asList(elements).iterator();
        return iElements;
    }
}*/