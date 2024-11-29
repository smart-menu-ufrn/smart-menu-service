package br.edu.ufrn.smartmenu.users.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ufrn.smartmenu.users.enums.Role;
import br.edu.ufrn.smartmenu.users.models.User;
import br.edu.ufrn.smartmenu.users.repositories.UserRepository;

@Component
public class UsersLoader implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {

            User rary = new User("rary@gmail.com", "rary", Role.ADMINISTRATOR);
            User rafael = new User("rafael@gmail.com", "rafael", Role.ADMINISTRATOR);
            User lucas = new User("lucas@gmail.com", "lucas", Role.ADMINISTRATOR);

            User manager = new User("manager@gmail.com", "manager", Role.MANAGER);
            User waiter = new User("waiter@gmail.com", "waiter", Role.WAITER);

            userRepository.saveAll(List.of(rary, rafael, lucas, manager, waiter));
        }
    }

}
