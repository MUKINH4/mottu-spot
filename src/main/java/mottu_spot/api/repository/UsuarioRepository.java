package mottu_spot.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import mottu_spot.api.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    UserDetails findByUsuario(String usuario);
}
