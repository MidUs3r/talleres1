import java.util.ArrayList;
import java.util.List;

public class Cine {
    private List<Pelicula> peliculas;
    private List<Compra> compras;

    public Cine() {
        peliculas = new ArrayList<>();
        compras = new ArrayList<>();
        inicializarPeliculas();
    }

    private void inicializarPeliculas() {
        peliculas.add(new Pelicula("XMEN", 2.25, 23));
        peliculas.add(new Pelicula("MARIO", 3.25, 23));
        peliculas.add(new Pelicula("BATMAN", 3.75, 23));
    }

    public boolean realizarCompra(String nombre, String apellido, String tituloPelicula, int cantidad) {
        Pelicula pelicula = buscarPelicula(tituloPelicula);
        if (pelicula != null && pelicula.venderEntradas(cantidad)) {
            Compra compra = new Compra(nombre, apellido, pelicula, cantidad);
            compras.add(compra);
            return true;
        }
        return false;
    }

    private Pelicula buscarPelicula(String titulo) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getTitulo().equals(titulo)) {
                return pelicula;
            }
        }
        return null;
    }

    public List<Pelicula> getPeliculas() {
        return new ArrayList<>(peliculas);
    }

    public List<Compra> getCompras() {
        return new ArrayList<>(compras);
    }

    public double getTotalGeneralRecaudado() {
        double total = 0;
        for (Pelicula pelicula : peliculas) {
            total += pelicula.getTotalRecaudado();
        }
        return total;
    }

    // Método auxiliar para obtener una película por nombre
    public Pelicula getPeliculaPorNombre(String nombre) {
        return buscarPelicula(nombre);
    }
}