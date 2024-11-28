import java.util.ArrayList;

class ListaSimple {
    private Node cabeza;

    private static class Node {
        YoungAvenger vengador;
        Node siguiente;

        Node(YoungAvenger vengador) {
            this.vengador = vengador;
        }
    }

    public void agregarJovenVengador(YoungAvenger vengador) {
        Node nuevoNodo = new Node(vengador);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
    }

    // Buscar por código
    public YoungAvenger buscarPorCodigo(int codigo) {
        Node actual = cabeza;
        while (actual != null) {
            if (actual.vengador.getCodigo() == codigo) {
                return actual.vengador;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    // Convertir la lista a un ArrayList
    public ArrayList<YoungAvenger> aLista() {
        ArrayList<YoungAvenger> lista = new ArrayList<>();
        Node actual = cabeza;
        while (actual != null) {
            lista.add(actual.vengador);
            actual = actual.siguiente;
        }
        return lista;
    }

    // Filtrar por poder especial
    public ArrayList<YoungAvenger> filtrarPorPoder(String poder) {
        ArrayList<YoungAvenger> listaFiltrada = new ArrayList<>();
        Node actual = cabeza;

        while (actual != null) {
            if (!actual.vengador.getPoderEspecial().equals(poder)) {
                listaFiltrada.add(actual.vengador);
            }
            actual = actual.siguiente;
        }

        return listaFiltrada;
    }



    public int contarMisionesRecursivo(String mision, Node nodo) {
        if (nodo == null) {
            return 0;
        }


        System.out.println("Misión actual: '" + nodo.vengador.getMisionActiva() + "' comparando con: '" + mision + "'");

        boolean coincide = nodo.vengador.getMisionActiva().trim().equalsIgnoreCase(mision.trim());

        return (coincide ? 1 : 0) + contarMisionesRecursivo(mision, nodo.siguiente);
    }

    public String contarMisiones() {
        String[] misiones = {"Rescate de Civiles", "Contención de Amenazas", "Investigación",
                "Apoyo Estratégico", "Recuperación de Artefactos"};
        StringBuilder resultado = new StringBuilder();
        for (String mision : misiones) {
            int cuenta = contarMisionesRecursivo(mision, cabeza);
            resultado.append(mision).append(": ").append(cuenta).append("\n");
        }
        return resultado.toString();
    }

    public void ordenarBurbuja() {
        if (cabeza == null || cabeza.siguiente == null) {
            return; // Si la lista está vacía o tiene un solo elemento no hace falta ordenar
        }

        boolean swapped;
        do {
            swapped = false;
            Node actual = cabeza;

            while (actual.siguiente != null) {
                // Comparar los niveles de habilidad
                if (actual.vengador.getNivelHabilidad() > actual.siguiente.vengador.getNivelHabilidad()) {
                    // Intercambiar los valores de los vengadores
                    YoungAvenger temp = actual.vengador;
                    actual.vengador = actual.siguiente.vengador;
                    actual.siguiente.vengador = temp;

                    swapped = true;
                }
                actual = actual.siguiente;
            }
        } while (swapped);
    }

}