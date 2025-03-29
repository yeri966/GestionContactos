package co.edu.uniquindio.contactos.controllers;

import co.edu.uniquindio.contactos.model.Contacto;
import co.edu.uniquindio.contactos.utils.Validador;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class ContactosViewController {
    @FXML private TableView<Contacto> tablaContactos;
    @FXML private TableColumn<Contacto, String> colNombre;
    @FXML private TableColumn<Contacto, String> colApellido;
    @FXML private TableColumn<Contacto, String> colTelefono;
    @FXML private TableColumn<Contacto, String> colEmail;
    @FXML private TableColumn<Contacto, String> colFechaNacimiento;
    @FXML private TableColumn<Contacto, ImageView> colFoto;

    @FXML private TextField txtNombre, txtApellido, txtTelefono, txtEmail;
    @FXML private DatePicker dpFechaNacimiento;
    @FXML private ComboBox<String> cmbFiltroBusqueda;
    @FXML public ImageView imgVistaPrevia;

    private String rutaFoto;
    private final ObservableList<Contacto> listaContactos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colApellido.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        colTelefono.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colFoto.setCellValueFactory(cellData -> {
            String ruta = cellData.getValue().getFotoPerfil();
            ImageView imageView = new ImageView();

            if (ruta != null && !ruta.isEmpty()) {
                Image imagen = new Image(ruta, 50, 50, true, true); // Tamaño de imagen
                imageView.setImage(imagen);
            }

            return new SimpleObjectProperty<>(imageView);

        });

        colFechaNacimiento.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getFechaNacimiento() != null ?
                                cellData.getValue().getFechaNacimiento().toString() : "No registrada"
                )
        );
        tablaContactos.setItems(listaContactos);

        // Inicializar ComboBox
        cmbFiltroBusqueda.setItems(FXCollections.observableArrayList("Nombre", "Teléfono"));
    }

    @FXML
    private void seleccionarFoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg"));
        File archivo = fileChooser.showOpenDialog(null);

        if (archivo != null) {
            rutaFoto = archivo.toURI().toString();

            if (imgVistaPrevia != null) {  // 👈 Evitar error si es null
                imgVistaPrevia.setImage(new Image(rutaFoto));
            } else {
                System.out.println("Error: imgVistaPrevia es null");
            }
        }
    }

    @FXML
    private void agregarContacto() {
        if (!validarCampos()) {
            mostrarAlerta("Error", "Todos los campos deben estar diligenciados correctamente");
            return;
        }

        Contacto nuevo = new Contacto(
                txtNombre.getText(),
                txtApellido.getText(),
                txtTelefono.getText(),
                txtEmail.getText(),
                rutaFoto,
                dpFechaNacimiento.getValue()
        );

        listaContactos.add(nuevo);
        tablaContactos.refresh();
        limpiarCampos();
    }

    @FXML
    private void editarContacto() {
        Contacto seleccionado = tablaContactos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            if (validarCampos()) {
                seleccionado.setNombre(txtNombre.getText());
                seleccionado.setApellido(txtApellido.getText());
                seleccionado.setTelefono(txtTelefono.getText());
                seleccionado.setEmail(txtEmail.getText());
                seleccionado.setFechaNacimiento(dpFechaNacimiento.getValue());
                if (rutaFoto != null) seleccionado.setFotoPerfil(rutaFoto);
                tablaContactos.refresh();
                limpiarCampos();
            }
        } else {
            mostrarAlerta("Error", "Seleccione un contacto para editar.");
        }
    }

    @FXML
    private void eliminarContacto() {
        Contacto seleccionado = tablaContactos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaContactos.remove(seleccionado);
            tablaContactos.refresh();
        } else {
            mostrarAlerta("Error", "Seleccione un contacto para eliminar.");
        }
    }

    @FXML
    private void buscarContacto() {
        String criterio = txtNombre.getText().trim();
        if (criterio.isEmpty()) {
            mostrarAlerta("Error", "Ingrese un nombre para buscar.");
            return;
        }

        for (Contacto c : listaContactos) {
            if (c.getNombre().equalsIgnoreCase(criterio)) {
                mostrarAlerta("Contacto Encontrado",
                        "Nombre: " + c.getNombre() + "\n" +
                                "Apellido: " + c.getApellido() + "\n" +
                                "Teléfono: " + c.getTelefono() + "\n" +
                                "Email: " + c.getEmail() + "\n" +
                                "Fecha de Nacimiento: " + (c.getFechaNacimiento() != null ? c.getFechaNacimiento() : "No registrada")
                );
                return;
            }
        }

        mostrarAlerta("No encontrado", "No se encontró un contacto con ese nombre.");
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtTelefono.clear();
        txtEmail.clear();
        dpFechaNacimiento.setValue(null);
        imgVistaPrevia.setImage(null);
        rutaFoto = null;
    }

    private boolean validarCampos() {
        String telefonoRegex = "\\d{10,}"; // 📌 Verifica que el número tenga al menos 10 dígitos

        if (txtNombre.getText().isEmpty() || txtTelefono.getText().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos deben estar diligenciados.");
            return false;
        }

        if (!txtTelefono.getText().matches(telefonoRegex)) {
            mostrarAlerta("Error", "El número de teléfono debe contener al menos 10 dígitos.");
            return false;
        }

        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    }
