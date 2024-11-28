public class NodoDoble {
    public int dato;
    public NodoDoble anterior;
    public NodoDoble siguiente;

    public NodoDoble(int dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }
}