package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;


@Entity
public class Usuario {
	@NaturalId
	String username;
	String correo;
	@NaturalId
	String cuilCuit;
	String contraseña;
	@Id
	@GeneratedValue
	Long id;
	
	
	public Usuario(){}
	
	public Usuario(String nombreusuario, String email, String cuilOcuit, String clave) {
		username = nombreusuario;
		correo = email;
		cuilCuit = cuilOcuit;
		contraseña = clave;
		
	}
	
	public void setUsername(String nombreusuario) {
		username = nombreusuario;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setCorreo(String email) {
		correo = email;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCuitCuil(String cc) {
		cuilCuit = cc;
	}
	public String getCuilCuit() {
		return cuilCuit;
	}
	
	public void setContraseña(String clave) {
		contraseña = clave;
	}
	public String getContraseña() {
		return contraseña;
	}
	
	public void setId(Long id) {
		this.id= id;
	}
	public Long getId() {
		return this.id;
	}
	
	
}
