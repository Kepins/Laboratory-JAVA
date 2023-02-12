package repository;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Simple in-memory repository for storing entity classes. It uses {@link Set} implementation for storing data, that is
 * the same elements can not repeat.
 *
 * @param <E> type of the entity stored in repository
 */
public abstract class InMemoryRepository<E extends Comparable<E>> {

    private boolean sorted;
    private boolean alternative;
    private Comparator<E> comparator;

    /**
     * Underlying set containing data.
     */
    private Set<E> set;

    /**
     * Default constructor. Uses {@link HashSet} for storing data.
     */
    public InMemoryRepository() {
        set = new HashSet<>();
        this.sorted = false;
        this.alternative = false;
        this.comparator = null;
    }

    /**
     * Uses {@link TreeSet} for sorted or {@link HashSet} for not sorted.
     *
     * @param sorted true if underlying structure should be automatically sorted
     */
    public InMemoryRepository(boolean sorted) {
        set = sorted ? new TreeSet<>() : new HashSet<>();
        this.sorted = sorted;
        this.alternative = false;
        this.comparator = null;
    }

    /**
     * Uses {@link TreeSet} and provided comparator.
     *
     * @param comparator comparator for underlying set
     */
    public InMemoryRepository(Comparator<E> comparator) {
        set = new TreeSet<>(comparator);
        this.sorted = true;
        this.alternative = true;
        this.comparator = comparator;
    }

    public boolean isSorted() {
        return sorted;
    }

    public boolean isAlternative() {
        return alternative;
    }

    public Comparator<E> getComparator() {
        return comparator;
    }

    /**
     * Creates new collection in order not to expose the original underlying data set.
     *
     * @return list containing all entries
     */
    public List<E> findAll() {
        return new ArrayList<>(set);
    }

    /**
     * Remove single element. In this simple implementation default methods for comparing that is selecting object to be
     * removed are used. For example for {@link java.util.HashSet} methods {@link E#hashCode()} and
     * {@link E#equals(Object)} are used whereas for {@link java.util.TreeSet} method
     * {@link Comparable#compareTo(Object)} is used. If external comparator was provided, the
     * {@link java.util.Comparator#compare(Object, Object)} is used.
     *
     * @param entity element to be removed
     */
    public void delete(E entity) {
        set.remove(entity);
    }

    /**
     * Adds single element. In this simple implementation default methods for comparing that is checking if element is
     * new are used. For example for {@link java.util.HashSet} methods {@link E#hashCode()} and {@link E#equals(Object)}
     * are used whereas for {@link java.util.TreeSet} method {@link Comparable#compareTo(Object)} is used. If external
     * comparator was provided, the {@link java.util.Comparator#compare(Object, Object)} is used.
     *
     * @param entity element to be removed
     */
    public void add(E entity) {
        set.add(entity);
    }

}
