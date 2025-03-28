package co.edu.uniquindio.contactos.controllers;

import co.edu.uniquindio.contactos.model.Contacto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ContactosController {
    private ObservableList<Contacto> contactos = FXCollections.observableArrayList();

    public void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
    }

    public void editarContacto(int index, Contacto nuevoContacto) {
        if (index >= 0 && index < contactos.size()) {
            contactos.set(index, nuevoContacto);
        }
    }

    public void eliminarContacto(int index) {
        if (index >= 0 && index < contactos.size()) {
            contactos.remove(index);
        }
    }

    public ObservableList<Contacto> obtenerContactos() {
        return contactos;
    }
}
