import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class YoungAvengerGUI {
    private JPanel pGeneral;
    private JTabbedPane tabbedPane1;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JComboBox<String> Poderespecial;
    private JComboBox<String> NIvelHabilidad;
    private JComboBox<String> MisionActiva;
    private JTable tabla;
    private JButton agregarButton;
    private JTextField txtBuscarCodigo;
    private JButton buscarButton;
    private JTextArea ResultadoBusqueda;
    private JLabel Ingreso;
    private JComboBox<String> Poderes;
    private JButton filtrarButton;
    private JTable tableFiltrado;
    private JLabel Escoger;
    private JButton mostrarMisionButton;
    private JTextArea ResultadoMision;

    private ListaSimple listaVengadores = new ListaSimple();

    public YoungAvengerGUI() {

        //Agregar un Vengador
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(txtCodigo.getText());
                    String nombre = txtNombre.getText();
                    String poderEspecial = (String) Poderespecial.getSelectedItem();
                    int nivelHabilidad = Integer.parseInt((String) NIvelHabilidad.getSelectedItem());
                    String misionActiva = (String) MisionActiva.getSelectedItem();


                    if (listaVengadores.buscarPorCodigo(codigo) != null) {
                        JOptionPane.showMessageDialog(null, "El código ya existe. No se puede registrar.");
                    } else {

                        YoungAvenger vengador = new YoungAvenger(codigo, nombre, poderEspecial, nivelHabilidad, misionActiva);
                        listaVengadores.agregarJovenVengador(vengador);


                        System.out.println("Vengador agregado con misión: " + misionActiva);


                        actualizarTabla();
                        JOptionPane.showMessageDialog(null, "Vengador agregado con éxito.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa datos válidos.");
                }
            }
        });


        //Buscar un Vengador por código
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(txtBuscarCodigo.getText());
                    YoungAvenger vengador = listaVengadores.buscarPorCodigo(codigo);

                    if (vengador != null) {
                        ResultadoBusqueda.setText(vengador.toString());
                    } else {
                        ResultadoBusqueda.setText("Vengador no encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa un código válido.");
                }
            }
        });

        //Filtrar por poder especial y ordenar
        // Filtrar por poder especial y ordenar
        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String poderSeleccionado = (String) Poderes.getSelectedItem();
                ArrayList<YoungAvenger> listaFiltrada = listaVengadores.filtrarPorPoder(poderSeleccionado);

                // Ordenar la lista filtrada usando burbuja
                listaVengadores.ordenarBurbuja();

                // Actualizar la tabla con la lista ordenada
                actualizarTablaFiltrada(listaFiltrada);
            }
        });


        //Mostrar las misiones
        mostrarMisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultadoMisiones = listaVengadores.contarMisiones();

                System.out.println("Resultado misiones:\n" + resultadoMisiones);

                ResultadoMision.setText(resultadoMisiones);
            }
        });
    }

    // Actualizar la tabla principal con todos los vengadores
    private void actualizarTabla() {
        ArrayList<YoungAvenger> lista = listaVengadores.aLista();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Código");
        model.addColumn("Nombre");
        model.addColumn("Poder");
        model.addColumn("Habilidad");
        model.addColumn("Misión");

        for (YoungAvenger v : lista) {
            model.addRow(new Object[]{v.getCodigo(), v.getNombre(), v.getPoderEspecial(), v.getNivelHabilidad(), v.getMisionActiva()});
        }
        tabla.setModel(model);
    }

    // Actualizar la tabla filtrada
    private void actualizarTablaFiltrada(ArrayList<YoungAvenger> listaFiltrada) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Código");
        model.addColumn("Nombre");
        model.addColumn("Poder");
        model.addColumn("Habilidad");
        model.addColumn("Misión");

        for (YoungAvenger v : listaFiltrada) {
            model.addRow(new Object[]{v.getCodigo(), v.getNombre(), v.getPoderEspecial(), v.getNivelHabilidad(), v.getMisionActiva()});
        }
        tableFiltrado.setModel(model);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Vengadores");
        frame.setContentPane(new YoungAvengerGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}