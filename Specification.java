package com.johnjiangtw0804;

// in this case, T is file type
// choose to no the abstract class because of no sharing states

// The Specifier pattern is a design pattern used to separate concerns in a system where the details of a specification or requirement are encapsulated in separate "specifier" objects,
// which can then be combined, reused, or modified independently of the system that uses them. The Specifier pattern helps in decoupling the specification logic from the rest of the application.
public interface Specification <T> {
    boolean isSatisfiedBy(T item);
    default Specification<T> and(Specification<T> other) {
        // return a implementation of Specification<T>
        return (item) -> {return this.isSatisfiedBy(item) && other.isSatisfiedBy(item);};
    }
    default Specification<T> or (Specification<T> other) {
        return (item) -> {return this.isSatisfiedBy(item) || other.isSatisfiedBy(item);};
    }
    default Specification<T> not(Specification<T> other) {
        return (item) -> {return !this.isSatisfiedBy(item);};
    }
}