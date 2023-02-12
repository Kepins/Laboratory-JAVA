package repository;

import entity.Mage;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class RepositoryTest {

    @Test
    public void find_notExists_optionalEmpty() {
        MageRepository repository = new MageRepository();
        repository.save(new Mage("Adam", 5));
        repository.save(new Mage("Franek", 42));
        Optional<Mage> jan = repository.find("Jan");
        Optional<Mage> jacek = repository.find("Jacek");
        assertThat(jan).isEmpty();
        assertThat(jacek).isEmpty();
    }

    @Test
    public void find_exists_optionalWithMage() {
        MageRepository repository = new MageRepository();
        Mage adam = new Mage("Adam", 5);
        Mage franek = new Mage("Franek", 42);
        repository.save(adam);
        repository.save(franek);
        Optional<Mage> adamFind = repository.find("Adam");
        Optional<Mage> franekFind = repository.find("Franek");
        assertThat(adamFind).get().isEqualTo(adam);
        assertThat(franekFind).get().isEqualTo(franek);
    }

    @Test
    public void delete_notExists_IllegalArgumentException() {
        MageRepository repository = new MageRepository();
        repository.save(new Mage("Adam", 5));
        repository.save(new Mage("Franek", 42));

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                repository.delete("Michał")
        );
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                repository.delete("Magda")
        );
    }


    @Test
    public void delete_exists_doesNotThrowIllegalArgumentException() {
        MageRepository repository = new MageRepository();
        repository.save(new Mage("Adam", 5));
        repository.save(new Mage("Franek", 42));

        assertThatNoException().isThrownBy(() ->
                repository.delete("Adam"));
        assertThatNoException().isThrownBy(() ->
                repository.delete("Franek"));
    }

    @Test
    public void save_notExists_doesNotThrowIllegalArgumentException() {
        MageRepository repository = new MageRepository();
        repository.save(new Mage("Adam", 5));
        repository.save(new Mage("Franek", 42));

        assertThatNoException().isThrownBy(() ->
                repository.save(new Mage("Michał", 28))
        );
        assertThatNoException().isThrownBy(() ->
                repository.save(new Mage("Magda", 19))
        );
    }

    @Test
    public void save_exists_IllegalArgumentException() {
        MageRepository repository = new MageRepository();
        repository.save(new Mage("Adam", 5));
        repository.save(new Mage("Franek", 42));

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                repository.save(new Mage("Adam", 3))
        );
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                repository.save(new Mage("Franek", 42))
        );
    }
}
