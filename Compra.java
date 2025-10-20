public class Compra {
    private String nombreCliente;
    private String apellidoCliente;
    private Pelicula pelicula;
    private int cantidadEntradas;
    private double totalPago;

    public Compra(String nombre, String apellido, Pelicula pelicula, int cantidad) {
        this.nombreCliente = nombre;
        this.apellidoCliente = apellido;
        this.pelicula = pelicula;
        this.cantidadEntradas = cantidad;
        this.totalPago = cantidad * pelicula.getPrecioEntrada();
    }

    public String toString() {
        return String.format("Película: %s | Entradas: %d | Cliente: %s %s | Total: $%.2f",
                pelicula.getTitulo(), cantidadEntradas, nombreCliente, apellidoCliente, totalPago);
    }

    // Getters
    public Pelicula getPelicula() { return pelicula; }
    public int getCantidadEntradas() { return cantidadEntradas; }
    public double getTotalPago() { return totalPago; }
}
