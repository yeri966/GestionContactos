package co.edu.uniquindio.contactos.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Contacto {

    private final StringProperty nombre;
    private final StringProperty apellido;
    private final StringProperty telefono;
    private final StringProperty email;
    private final StringProperty fotoPerfil;
    private final ObjectProperty<LocalDate> fechaNacimiento; // ✅ Cambiado a ObjectProperty

    public Contacto(String nombre, String apellido, String telefono, String email, String fotoPerfil, LocalDate fechaNacimiento) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.telefono = new SimpleStringProperty(telefono);
        this.email = new SimpleStringProperty(email);
        this.fotoPerfil = new SimpleStringProperty(fotoPerfil);
        this.fechaNacimiento = new SimpleObjectProperty<>(fechaNacimiento); // ✅ Inicializado correctamente
    }

    // Getters y Setters con Property
    public StringProperty nombreProperty() { return nombre; }
    public String getNombre() { return nombre.get(); }
    public void setNombre(String nombre) { this.nombre.set(nombre); }

    public StringProperty apellidoProperty() { return apellido; }
    public String getApellido() { return apellido.get(); }
    public void setApellido(String apellido) { this.apellido.set(apellido); }

    public StringProperty telefonoProperty() { return telefono; }
    public String getTelefono() { return telefono.get(); }
    public void setTelefono(String telefono) { this.telefono.set(telefono); }

    public StringProperty emailProperty() { return email; }
    public String getEmail() { return email.get(); }
    public void setEmail(String email) { this.email.set(email); }

    public StringProperty fotoPerfilProperty() { return fotoPerfil; }
    public String getFotoPerfil() { return fotoPerfil.get(); }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil.set(fotoPerfil); }

    // ✅ Métodos corregidos para fecha de nacimiento
    public ObjectProperty<LocalDate> fechaNacimientoProperty() { return fechaNacimiento; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento.get(); }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento.set(fechaNacimiento); }
}
