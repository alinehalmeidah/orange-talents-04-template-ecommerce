package br.com.orangetalents.mercadolivre.usuario;

import br.com.orangetalents.mercadolivre.utils.validation.UniqueValue;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "login", message = "E-mail deve ser Único")
    private String login;

    @NotBlank
    @Length(min = 6)
    private String senha;

    @Deprecated
    public UsuarioRequest() {
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel(){
        return new Usuario(this.login, new HashSenha(senha));
    }
}