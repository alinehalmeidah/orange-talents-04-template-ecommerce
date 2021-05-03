package br.com.orangetalents.mercadolivre.usuario;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public String cadastraUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest){
        Usuario novoUsuario = usuarioRequest.toModel();
        entityManager.persist(novoUsuario);

        return novoUsuario.toString();
    }


}
