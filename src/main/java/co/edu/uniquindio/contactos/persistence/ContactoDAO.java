package co.edu.uniquindio.contactos.persistence;

import co.edu.uniquindio.contactos.model.Contacto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactoDAO {
    private static final ObservableList<Contacto> contactos = FXCollections.observableArrayList();

    public static ObservableList<Contacto> getContactos() {
        return contactos;
    }

    public static void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
    }

    public static void eliminarContacto(Contacto contacto) {
        contactos.remove(contacto);
    }
}
