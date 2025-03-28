package co.edu.uniquindio.contactos.controllers;

import co.edu.uniquindio.contactos.model.Contacto;
import co.edu.uniquindio.contactos.utils.Validador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;//verificacion git//


public class ContactosViewController {
    @FXML private TableView<Contacto> tablaContactos;
    @FXML private TableColumn<Contacto, String> colNombre, colApellido, colTelefono, colEmail;
    @FXML private TextField txtNombre, txtApellido, txtTelefono, txtEmail;
    @FXML private Button btnAgregar, btnEditar, btnEliminar, btnSeleccionarFoto;

    private ObservableList<Contacto> contactos = FXCollections.observableArrayList();
    private String rutaFoto = "";

    @FXML
    public void initialize() {
        tablaContactos.setItems(contactos);
    }

    @FXML
    private void agregarContacto() {
        if (validarCampos()) {
            Contacto nuevo = new Contacto(txtNombre.getText(), txtApellido.getText(), txtTelefono.getText(), txtEmail.getText(), "01/01/2000", rutaFoto);
            contactos.add(nuevo);
            limpiarCampos();
        }
    }

    @FXML
    private void editarContacto() {
        Contacto seleccionado = tablaContactos.getSelectionModel().getSelectedItem();
        if (seleccionado != null && validarCampos()) {
            seleccionado.setNombre(txtNombre.getText());
            seleccionado.setApellido(txtApellido.getText());
            seleccionado.setTelefono(txtTelefono.getText());
            seleccionado.setEmail(txtEmail.getText());
            seleccionado.setFotoPerfil(rutaFoto);
            tablaContactos.refresh();
            limpiarCampos();
        }
    }

    @FXML
    private void eliminarContacto() {
        Contacto seleccionado = tablaContactos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            contactos.remove(seleccionado);
        }
    }

    @FXML
    private void seleccionarFoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg"));
        File archivo = fileChooser.showOpenDialog(null);
        if (archivo != null) {
            rutaFoto = archivo.getAbsolutePath();
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios");
            return false;
        }
        if (!Validador.validarEmail(txtEmail.getText())) {
            mostrarAlerta("Error", "Correo electrónico no válido");
            return false;
        }
        if (!Validador.validarTelefono(txtTelefono.getText())) {
            mostrarAlerta("Error", "Número de teléfono no válido");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtTelefono.clear();
        txtEmail.clear();
        rutaFoto = "";
    }
}
