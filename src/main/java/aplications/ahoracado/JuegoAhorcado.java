/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplications.ahoracado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alfgarci
 */
public class JuegoAhorcado {
    private JFrame frame;
    private DibujoAhorcado panelDibujo;
    private JTextField inputLetra;
    private JLabel labelPalabra;
    private JLabel labelMensaje;
    private JLabel labelPuntuacion;

    private String palabra;
    private char[] progreso;
    private int errores;
    private final int MAX_ERRORES = 6;
    private int puntuacionJugador1 = 0;
    private int puntuacionJugador2 = 0;
    private boolean turnoJugador1 = true; // Alternar entre jugadores

    public JuegoAhorcado() {
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        frame = new JFrame("Ahorcado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 500);

        panelDibujo = new DibujoAhorcado();
        panelDibujo.setPreferredSize(new Dimension(300, 300));

        inputLetra = new JTextField(1);
        JButton botonEnviar = new JButton("Enviar");
        labelPalabra = new JLabel("", SwingConstants.CENTER);
        labelMensaje = new JLabel("", SwingConstants.CENTER);
        labelPuntuacion = new JLabel("  Jugador 1: 0 | Jugador 2: 0", SwingConstants.CENTER);

        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manejarIntento();
            }
        });

        JPanel panelInferior = new JPanel();
        panelInferior.add(new JLabel("Letra:"));
        panelInferior.add(inputLetra);
        panelInferior.add(botonEnviar);

        frame.setLayout(new BorderLayout());
        frame.add(panelDibujo, BorderLayout.CENTER);
        frame.add(labelPalabra, BorderLayout.NORTH);
        frame.add(panelInferior, BorderLayout.SOUTH);
        frame.add(labelMensaje, BorderLayout.EAST);
        frame.add(labelPuntuacion, BorderLayout.WEST);

        frame.setVisible(true);
    }

    public void iniciarPartida(String palabraSecreta) {
        this.palabra = palabraSecreta.toUpperCase();
        this.progreso = new char[palabra.length()];
        this.errores = 0;
        panelDibujo.reiniciar();
        labelMensaje.setText("");

        for (int i = 0; i < progreso.length; i++) {
            progreso[i] = '_';
        }

        actualizarVista();
    }

    private void manejarIntento() {
        String letraTexto = inputLetra.getText().toUpperCase();
        if (letraTexto.length() != 1) {
            labelMensaje.setText("Introduce solo una letra.");
            return;
        }

        char letra = letraTexto.charAt(0);
        boolean acierto = false;

        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra) {
                progreso[i] = letra;
                acierto = true;
            }
        }

        if (!acierto) {
            errores++;
            panelDibujo.setErrores(errores);
        }

        if (errores >= MAX_ERRORES) {
            labelMensaje.setText("¡Has perdido! La palabra era: " + palabra);
            if (turnoJugador1) puntuacionJugador2++;
            else puntuacionJugador1++;
            siguienteTurno();
        } else if (String.valueOf(progreso).equals(palabra)) {
            labelMensaje.setText("¡Has ganado esta ronda!");
            if (turnoJugador1) puntuacionJugador1++;
            else puntuacionJugador2++;
            siguienteTurno();
        }

        actualizarVista();
    }

    private void siguienteTurno() {
        turnoJugador1 = !turnoJugador1;
        if (puntuacionJugador1 == 5 || puntuacionJugador2 == 5) {
            labelMensaje.setText("¡Juego terminado! Ganador: " +
                    (puntuacionJugador1 == 5 ? "Jugador 1" : "Jugador 2"));
            inputLetra.setEnabled(false);
        } else {
            String palabraSecreta = JOptionPane.showInputDialog(
                    (turnoJugador1 ? "  Jugador 1" : "Jugador 2") +
                            ", introduce una nueva palabra:").toUpperCase();
            iniciarPartida(palabraSecreta);
        }
    }

    private void actualizarVista() {
        labelPalabra.setHorizontalAlignment(SwingConstants.CENTER); // Centrar horizontalmente
        labelPalabra.setText(String.valueOf(progreso));
        inputLetra.setText("");
    }
}

