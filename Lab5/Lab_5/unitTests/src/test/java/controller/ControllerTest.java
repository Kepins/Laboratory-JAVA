package controller;


import entity.Mage;



import org.junit.jupiter.api.Test;

import repository.MageRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ControllerTest {

    @Test
    public void find_notExists(){
        MageRepository repository = mock(MageRepository.class);
        when(repository.find("Adam")).thenReturn(Optional.empty());
        when(repository.find("Ewa")).thenReturn(Optional.empty());

        MageController controller = new MageController(repository);

        List<String> results = new ArrayList<>();
        results.add(controller.find("Adam"));
        results.add(controller.find("Ewa"));

        assertThat(results).containsExactly("not found", "not found");
    }

    @Test
    public void find_exists(){
        MageRepository repository = mock(MageRepository.class);
        Mage adam = new Mage("Adam", 3);
        Mage ewa = new Mage("Ewa", 15);
        when(repository.find("Adam")).thenReturn(Optional.of(adam));
        when(repository.find("Ewa")).thenReturn(Optional.of(ewa));

        MageController controller = new MageController(repository);

        List<String> results = new ArrayList<>();
        results.add(controller.find("Adam"));
        results.add(controller.find("Ewa"));

        assertThat(results).containsExactly(adam.toString(), ewa.toString());
    }

    @Test
    public void delete_notExists(){
        MageRepository repository = mock(MageRepository.class);
        doThrow(new IllegalArgumentException()).when(repository).delete("Adam");
        doThrow(new IllegalArgumentException()).when(repository).delete("Ewa");

        MageController controller = new MageController(repository);

        List<String> results = new ArrayList<>();
        results.add(controller.find("Adam"));
        results.add(controller.find("Ewa"));

        assertThat(results).containsExactly("not found", "not found");
    }

    @Test
    public void delete_exists(){
        MageRepository repository = mock(MageRepository.class);
        doNothing().when(repository).delete("Adam");
        doNothing().when(repository).delete("Ewa");

        MageController controller = new MageController(repository);

        List<String> results = new ArrayList<>();
        results.add(controller.delete("Adam"));
        results.add(controller.delete("Ewa"));

        assertThat(results).containsExactly("done", "done");
    }

    @Test
    public void save_notExists(){
        MageRepository repository = mock(MageRepository.class);
        Mage adam = new Mage("Adam", 3);
        Mage ewa = new Mage("Ewa", 15);
        doNothing().when(repository).save(adam);
        doNothing().when(repository).save(ewa);

        MageController controller = new MageController(repository);

        List<String> results = new ArrayList<>();
        results.add(controller.save(adam.getName(), valueOf(adam.getLevel())));
        results.add(controller.save(ewa.getName(), valueOf(ewa.getLevel())));

        assertThat(results).containsExactly("done", "done");
    }

    @Test
    public void save_exists(){
        MageRepository repository = mock(MageRepository.class);

        Mage adam = new Mage("Adam", 3);
        Mage ewa = new Mage("Ewa", 15);

        doThrow(new IllegalArgumentException()).when(repository).save(adam);
        doThrow(new IllegalArgumentException()).when(repository).save(ewa);

        MageController controller = new MageController(repository);

        List<String> results = new ArrayList<>();
        results.add(controller.save(adam.getName(), valueOf(adam.getLevel())));
        results.add(controller.save(ewa.getName(), valueOf(ewa.getLevel())));

        assertThat(results).containsExactly("bad request", "bad request");
    }
}
