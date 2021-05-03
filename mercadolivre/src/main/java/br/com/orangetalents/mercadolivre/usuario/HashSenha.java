package br.com.orangetalents.mercadolivre.usuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

public class HashSenha {

    private String senha;

    public HashSenha(@NotBlank @Length(min =6) String senha){
        this.senha = senha;
    }
    public String conversao(){
        return new BCryptPasswordEncoder().encode(senha);

    }
}
