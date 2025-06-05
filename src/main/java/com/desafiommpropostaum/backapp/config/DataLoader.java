package com.desafiommpropostaum.backapp.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.desafiommpropostaum.backapp.dtos.requests.UserRequestDTO;
import com.desafiommpropostaum.backapp.repositories.UserRepository;
import com.desafiommpropostaum.backapp.security.AuthenticationService;

@Component
public class DataLoader implements CommandLineRunner{
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public DataLoader(UserRepository userRepository, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public void run(String... args) throws Exception {
        
        if (userRepository.findByUsername("admin@lojasmm.com").isEmpty()) {
            UserRequestDTO userRequestDTO = new UserRequestDTO(
            "admin", 
            "admin@lojasmm.com", 
            "admin123", 
            "GERENTE", 
            "Tecnologia da Informação", 
            "Sou o gerente do sistema", 
            "42999999999", 
            null
            );
            
            try {
                authenticationService.registerUser(userRequestDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Já existe um usuário admin com mesmo username");
        }

        
    }
}
