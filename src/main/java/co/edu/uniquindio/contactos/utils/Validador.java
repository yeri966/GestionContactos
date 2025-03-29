package co.edu.uniquindio.contactos.utils;

import java.util.regex.Pattern;

public class Validador {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{7,10}$");

    public static boolean validarEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean validarTelefono(String telefono) {
        return PHONE_PATTERN.matcher(telefono).matches();
    }
}






