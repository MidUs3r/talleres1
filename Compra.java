public class Compra {
    private String cedula;
    private Pelicula pelicula;
    private int cantidad;
    private double totalPago;

    public Compra(String cedula, Pelicula pelicula, int cantidad) {
        this.cedula = cedula;
        this.pelicula = pelicula;
        this.cantidad = cantidad;
        this.totalPago = cantidad * pelicula.getPrecio();
    }

    // Getters
    public String getCedula() { return cedula; }
    public Pelicula getPelicula() { return pelicula; }
    public int getCantidad() { return cantidad; }
    public double getTotalPago() { return totalPago; }

    @Override
    public String toString() {
        return String.format("Película: %s | Cédula: %s | Entradas: %d | Total: $%.2f",
                pelicula.getNombre(), cedula, cantidad, totalPago);
    }
}