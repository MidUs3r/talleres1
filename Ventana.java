import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Ventana {

    private JPanel panel1;
    private JComboBox<String> comboPeliculas;
    private JTextField txtCedula;
    private JTextField txtCantidad;
    private JButton btnComprar;
    private JTextArea txtCompras;
        private JLabel lblPiratas;
        private JLabel lblNaruto;
        private JLabel lblAntman;

        private Cine cineManager = new Cine();

        public Ventana() {
            inicializarComponentes();
            configurarEventos();
            actualizarTotales();
        }

        private void inicializarComponentes() {
            // Configurar ComboBox solo con nombres de películas
            comboPeliculas.addItem("PIRATAS");
            comboPeliculas.addItem("NARUTO");
            comboPeliculas.addItem("ANTMAN");
        }

        private void configurarEventos() {
            // Validación para solo números en cédula y cantidad
            txtCedula.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                        e.consume();
                    }
                }
            });

            txtCantidad.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                        e.consume();
                    }
                }
            });

            // ActionListener para el botón comprar
            btnComprar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    realizarCompra();
                }
            });
        }

        private void realizarCompra() {
            try {
                String cedula = txtCedula.getText().trim();
                String peliculaSeleccionada = (String) comboPeliculas.getSelectedItem();
                String cantidadTexto = txtCantidad.getText().trim();

                // Validar cédula
                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(panel1, "Debe ingresar la cédula");
                    txtCedula.requestFocus();
                    return;
                }

                // Validar cantidad
                if (cantidadTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(panel1, "Debe ingresar la cantidad de entradas (1-5)");
                    txtCantidad.requestFocus();
                    return;
                }

                int cantidad = Integer.parseInt(cantidadTexto);

                // Validar rango 1-5
                if (cantidad < 1 || cantidad > 5) {
                    JOptionPane.showMessageDialog(panel1, "La cantidad debe ser entre 1 y 5 entradas");
                    txtCantidad.requestFocus();
                    return;
                }

                // Realizar compra
                if (cineManager.realizarCompra(cedula, peliculaSeleccionada, cantidad)) {
                    actualizarInterfaz();
                    limpiarCampos();
                    JOptionPane.showMessageDialog(panel1, "Compra realizada exitosamente!");
                } else {
                    if (cineManager.cedulaExiste(cedula)) {
                        JOptionPane.showMessageDialog(panel1, "Error: La cédula ya tiene una compra registrada");
                    } else {
                        JOptionPane.showMessageDialog(panel1, "No hay suficientes entradas disponibles");
                    }
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(panel1, "La cantidad debe ser un número válido");
                txtCantidad.requestFocus();
            }
        }

        private void actualizarInterfaz() {
            actualizarCompras();
            actualizarTotales();
        }

        private void actualizarCompras() {
            txtCompras.setText("");

            // Mostrar todas las compras en el formato solicitado
            for (Compra compra : cineManager.getCompras()) {
                txtCompras.append(compra.toString() + "\n");
            }

            if (cineManager.getCompras().isEmpty()) {
                txtCompras.setText("No hay compras realizadas");
            }
        }

        private void actualizarTotales() {
            // Actualizar solo los totales en dinero para cada película
            for (Pelicula pelicula : cineManager.getPeliculas()) {
                double totalRecaudado = pelicula.getTotalRecaudado();

                switch (pelicula.getNombre()) {
                    case "PIRATAS":
                        lblPiratas.setText(String.format("$%.2f", totalRecaudado));
                        break;
                    case "NARUTO":
                        lblNaruto.setText(String.format("$%.2f", totalRecaudado));
                        break;
                    case "ANTMAN":
                        lblAntman.setText(String.format("$%.2f", totalRecaudado));
                        break;
                }
            }
        }

        private void limpiarCampos() {
            txtCedula.setText("");
            txtCantidad.setText("");
            txtCedula.requestFocus();
        }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    }

