import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Ventana {

    private JPanel panel1;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JComboBox<Pelicula> comboBox1;
    private JButton comprarButton;
    private JTextArea txtCompras;
    private JLabel DisponiblesM;
    private JLabel DisponiblesB;
    private JLabel DisponibleX;
    private JTextField txtEntradas;
    private JLabel lblTotalRecaudado;

    private Cine cine = new Cine();

    public Ventana() {
        inicializarComboBox();
        configurarEventos();
        actualizarDisponibilidad();
        actualizarTotalRecaudado();
    }

    private void inicializarComboBox() {
        DefaultComboBoxModel<Pelicula> model = new DefaultComboBoxModel<>();
        for (Pelicula pelicula : cine.getPeliculas()) {
            model.addElement(pelicula);
        }
        comboBox1.setModel(model);
    }

    private void configurarEventos() {
        // Validación para solo números en txtEntradas
        txtEntradas.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }
        });

        // ActionListener para el botón comprar CON TODO EL CÓDIGO DENTRO
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO EL CÓDIGO DE realizarCompra() VA AQUÍ DENTRO
                try {
                    String nombre = txtNombre.getText().trim();
                    String apellido = txtApellido.getText().trim();
                    Pelicula peliculaSeleccionada = (Pelicula) comboBox1.getSelectedItem();
                    String cantidadTexto = txtEntradas.getText().trim();

                    // Validar que se ingresó cantidad
                    if (cantidadTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(panel1, "Debe ingresar la cantidad de entradas");
                        txtEntradas.requestFocus();
                        return;
                    }

                    int cantidad = Integer.parseInt(cantidadTexto);

                    // Validar rango 1-4
                    if (cantidad < 1 || cantidad > 4) {
                        JOptionPane.showMessageDialog(panel1, "La cantidad debe ser entre 1 y 4 entradas");
                        txtEntradas.requestFocus();
                        return;
                    }

                    // Resto de validaciones...
                    if (nombre.isEmpty() || apellido.isEmpty()) {
                        JOptionPane.showMessageDialog(panel1, "Debe ingresar nombre y apellido");
                        return;
                    }

                    if (peliculaSeleccionada == null) {
                        JOptionPane.showMessageDialog(panel1, "Debe seleccionar una película");
                        return;
                    }

                    // Realizar compra
                    if (cine.realizarCompra(nombre, apellido, peliculaSeleccionada.getTitulo(), cantidad)) {
                        actualizarInterfaz();
                        limpiarCampos();
                        JOptionPane.showMessageDialog(panel1, "Compra realizada exitosamente!");
                    } else {
                        JOptionPane.showMessageDialog(panel1, "No hay suficientes entradas disponibles");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel1, "La cantidad debe ser un número válido");
                    txtEntradas.requestFocus();
                }
            }
        });

        // ActionListener para ENTER en txtEntradas
        txtEntradas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprarButton.doClick(); // Simular click en el botón comprar
            }
        });
    }

    private void actualizarInterfaz() {
        actualizarCompras();
        actualizarDisponibilidad();
        actualizarTotalRecaudado();
    }

    private void actualizarCompras() {
        txtCompras.setText("");
        for (Compra compra : cine.getCompras()) {
            txtCompras.append(compra.toString() + "\n");
        }
        if (cine.getCompras().isEmpty()) {
            txtCompras.setText("No hay compras realizadas");
        }
    }

    private void actualizarDisponibilidad() {
        for (Pelicula pelicula : cine.getPeliculas()) {
            int disponibles = pelicula.getEntradasDisponibles();
            switch (pelicula.getTitulo()) {
                case "XMEN":
                    DisponibleX.setText(String.valueOf(disponibles));
                    break;
                case "MARIO":
                    DisponiblesM.setText(String.valueOf(disponibles));
                    break;
                case "BATMAN":
                    DisponiblesB.setText(String.valueOf(disponibles));
                    break;
            }
        }
    }

    private void actualizarTotalRecaudado() {
        double total = cine.getTotalGeneralRecaudado();
        if (lblTotalRecaudado != null) {
            lblTotalRecaudado.setText(String.format("$%.2f", total));
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtEntradas.setText("");
        txtNombre.requestFocus();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Cine");
        frame.setContentPane(new Ventana().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}