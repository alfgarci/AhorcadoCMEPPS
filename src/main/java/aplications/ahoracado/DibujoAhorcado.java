/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplications.ahoracado;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author alfgarci
 */
public class DibujoAhorcado extends JPanel {
    private int errores;

    public DibujoAhorcado() {
        this.errores = 0;
    }

    public void setErrores(int errores) {
        this.errores = errores;
        repaint();
    }

    public void reiniciar() {
        this.errores = 0;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        //Base
        g.drawLine(50, 250, 150, 250); //Base
        g.drawLine(100, 250, 100, 50); //Poste vertical
        g.drawLine(100, 50, 200, 50);  //Poste horizontal
        g.drawLine(200, 50, 200, 80);  //Cuerda

        
        if (errores > 0) g.drawOval(175, 80, 50, 50); //Cabeza
        if (errores > 1) g.drawLine(200, 130, 200, 200); //Cuerpo
        if (errores > 2) g.drawLine(200, 150, 170, 180); //Brazo izquierdo
        if (errores > 3) g.drawLine(200, 150, 230, 180); //Brazo derecho
        if (errores > 4) g.drawLine(200, 200, 170, 230); //Pierna izquierda
        if (errores > 5) g.drawLine(200, 200, 230, 230); //Pierna derecha
    }
}
