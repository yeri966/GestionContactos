package co.edu.uniquindio.contactos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String fechaNacimiento;
    private String fotoPerfil;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{7,15}$");

    public boolean validarEmail() {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean validarTelefono() {
        return PHONE_PATTERN.matcher(telefono).matches();
    }
}
