package br.insper.turismo.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UsuarioService implements UserDetailsService {

    private HashMap<String, CreateUserDTO> usuarios = new HashMap<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CreateUserDTO create(CreateUserDTO userDTO) {

        userDTO.setSenha(passwordEncoder.encode(userDTO.getSenha()));

        usuarios.put(userDTO.getEmail(), userDTO);
        return userDTO;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CreateUserDTO userDTO = usuarios.get(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return User
                .builder()
                .username(userDTO.getEmail())
                .password(userDTO.getSenha())
                .build();

    }

}
