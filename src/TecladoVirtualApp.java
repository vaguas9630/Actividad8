import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TecladoVirtualApp extends JFrame {
    private JTextArea textArea;
    private List<String> pangramas;
    private String pangramaActual;
    private int teclasCorrectas;
    private int teclasIncorrectas;
    private List<Character> teclasDificiles;

    public TecladoVirtualApp() {
        // Inicializar variables
        pangramas = cargarPangramasDesdeArchivo();
        teclasDificiles = new ArrayList<>();
        pangramaActual = obtenerPangramaAleatorio();
        teclasCorrectas = 0;
        teclasIncorrectas = 0;

        // Configurar la interfaz gráfica
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Teclado Virtual App");
        setLayout(new BorderLayout());

        // Crear el teclado virtual
        JPanel tecladoPanel = crearTecladoPanel();
        add(tecladoPanel, BorderLayout.CENTER);

        // Crear el área de texto
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.SOUTH);

        // Configurar la ventana
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel crearTecladoPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 10));

        // Definir las teclas del teclado
        String[] teclas = {
                "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
                "A", "S", "D", "F", "G", "H", "J", "K", "L",
                "Z", "X", "C", "V", "B", "N", "M"
        };

        // Crear botones para cada tecla
        for (String tecla : teclas) {
            JButton button = new JButton(tecla);
            button.addActionListener(new TeclaActionListener());
            panel.add(button);
        }

        return panel;
    }

    private List<String> cargarPangramasDesdeArchivo() {
        // Aquí debes implementar la lógica para cargar los pangramas desde un archivo de texto.
        // Puedes usar BufferedReader o cualquier otra clase que prefieras para leer el archivo.
        // Devuelve una lista de pangramas.
        // Este es solo un ejemplo, reemplázalo con tu lógica de carga de archivos.
        return Collections.singletonList("El veloz murciélago hindú comía feliz cardillo y kiwi.");
    }

    private String obtenerPangramaAleatorio() {
        Random random = new Random();
        return pangramas.get(random.nextInt(pangramas.size()));
    }

    private class TeclaActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String teclaPresionada = source.getText();

            // Verificar si la tecla presionada es correcta
            if (pangramaActual.contains(teclaPresionada)) {
                teclasCorrectas++;
            } else {
                teclasIncorrectas++;
                if (!teclasDificiles.contains(teclaPresionada.charAt(0))) {
                    teclasDificiles.add(teclaPresionada.charAt(0));
                }
            }

            // Actualizar el área de texto con el nuevo pangrama
            textArea.setText(pangramaActual);

            // Cambiar el pangrama actual de manera aleatoria
            pangramaActual = obtenerPangramaAleatorio();

            // Mostrar estadísticas
            System.out.println("Teclas correctas: " + teclasCorrectas);
            System.out.println("Teclas incorrectas: " + teclasIncorrectas);
            System.out.println("Teclas difíciles: " + teclasDificiles);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TecladoVirtualApp::new);
    }
}
