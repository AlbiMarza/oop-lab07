package it.unibo.inner.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.Iterator;
import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    
    private final List<T> elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] elements, final Predicate<T> filter){
        this.elements = List.of(elements);
        this.filter = filter;
    }

    public void setIterationPolicy(final Predicate<T> filter){
        this.filter = filter;
    }

    public InnerIterator iterator(){
        return new InnerIterator();
    }

    public class InnerIterator implements Iterator<T>{
        private int current = 0;
        
        @Override 
        public boolean hasNext(){
            return this.current < elements.size();
        }

        public T next(){
            if (hasNext()){
                return elements.get(current++);
            }
            throw new NoSuchElementException();
        }

    }

}
