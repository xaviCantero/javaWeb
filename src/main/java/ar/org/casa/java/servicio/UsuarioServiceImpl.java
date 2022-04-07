package ar.org.casa.java.servicio;

import ar.org.casa.java.datos.PersonaDao;
import ar.org.casa.java.datos.UsuarioDao;
import ar.org.casa.java.domain.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UsuarioServiceImpl implements UsuarioService{
    
    @Inject
    private UsuarioDao usuarioDao;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioDao.findAllUsuario();
    }

    @Override
    public Usuario encontrarUsuarioPorId(Usuario usuario) {
        return usuarioDao.findUsuarioById(usuario);
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        usuarioDao.insertUsuario(usuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        usuarioDao.updateUsuario(usuario);
    }

    @Override
    public void elminarUsuario(Usuario usuario) {
        usuarioDao.deleteUsuario(usuario);
    }
    
    
}
