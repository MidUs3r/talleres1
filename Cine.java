import java.util.*;

public class Cine {
    private List<Pelicula> peliculas;
    private List<Compra> compras;
    private Set<String> cedulasRegistradas;

    public Cine() {
        peliculas = new ArrayList<>();
        compras = new ArrayList<>();
        cedulasRegistradas = new HashSet<>();
        inicializarPeliculas();
    }

    private void inicializarPeliculas() {
        peliculas.add(new Pelicula("PIRATAS", 17, 5.0));
        peliculas.add(new Pelicula("NARUTO", 17, 5.0));
        peliculas.add(new Pelicula("ANTMAN", 17, 5.0));
    }

    public boolean realizarCompra(String cedula, String nombrePelicula, int cantidad) {
        // Validar cédula única
        if (cedulasRegistradas.contains(cedula)) {
            return false;
        }

        Pelicula pelicula = buscarPelicula(nombrePelicula);
        if (pelicula != null && pelicula.venderEntradas(cantidad)) {
            Compra compra = new Compra(cedula, pelicula, cantidad);
            compras.add(compra);
            cedulasRegistradas.add(cedula);
            return true;
        }
        return false;
    }

    private Pelicula buscarPelicula(String nombre) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getNombre().equals(nombre)) {
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

    public Pelicula getPeliculaPorNombre(String nombre) {
        return buscarPelicula(nombre);
    }

    public boolean cedulaExiste(String cedula) {
        return cedulasRegistradas.contains(cedula);
    }
}
