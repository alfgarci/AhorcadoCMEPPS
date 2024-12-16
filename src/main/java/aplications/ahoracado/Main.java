/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package aplications.ahoracado;

/**
 *
 * @author alfgarci
 */
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JuegoAhorcado juego = new JuegoAhorcado();

            String palabraSecreta = JOptionPane.showInputDialog("Jugador 1, introduce una palabra secreta:").toUpperCase();
            juego.iniciarPartida(palabraSecreta);
        });
    }
}

