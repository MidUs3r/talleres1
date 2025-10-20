public class Pelicula {
    private String nombre;
    private int capacidad;
    private int entradasVendidas;
    private double precio;

    public Pelicula(String nombre, int capacidad, double precio) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precio = precio;
        this.entradasVendidas = 0;
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }
    public int getEntradasVendidas() { return entradasVendidas; }
    public double getPrecio() { return precio; }

    public boolean venderEntradas(int cantidad) {
        if (entradasVendidas + cantidad <= capacidad) {
            entradasVendidas += cantidad;
            return true;
        }
        return false;
    }

    public int getEntradasDisponibles() {
        return capacidad - entradasVendidas;
    }

    public double getTotalRecaudado() {
        return entradasVendidas * precio;
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }
}