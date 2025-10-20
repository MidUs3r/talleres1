public class Pelicula {
    private String titulo;
    private double precioEntrada;
    private int capacidadMaxima;
    private int entradasVendidas;

    public Pelicula(String titulo, double precioEntrada, int capacidadMaxima) {
        // Validaciones simples sin excepciones
        this.titulo = (titulo == null || titulo.trim().isEmpty()) ? "Película Sin Nombre" : titulo;
        this.precioEntrada = (precioEntrada <= 0) ? 1.0 : precioEntrada;
        this.capacidadMaxima = (capacidadMaxima <= 0) ? 23 : capacidadMaxima;
        this.entradasVendidas = 0;
    }

    // Getters
    public String getTitulo() { return titulo; }
    public double getPrecioEntrada() { return precioEntrada; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public int getEntradasVendidas() { return entradasVendidas; }

    public boolean venderEntradas(int cantidad) {
        // Validación simple sin excepciones
        if (cantidad <= 0) {
            return false;
        }
        if (entradasVendidas + cantidad > capacidadMaxima) {
            return false;
        }
        entradasVendidas += cantidad;
        return true;
    }

    public int getEntradasDisponibles() {
        return capacidadMaxima - entradasVendidas;
    }

    public double getTotalRecaudado() {
        return entradasVendidas * precioEntrada;
    }

    @Override
    public String toString() {
        return titulo + " - $" + precioEntrada;
    }
}