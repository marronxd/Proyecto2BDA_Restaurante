/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Controladores.CoordinadorFrames;
import com.toedter.calendar.JDateChooser;
import dtos.ComandaDTO;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author luiscarlosbeltran
 */
public class PnlBuscadorComandas extends javax.swing.JPanel {

    private CoordinadorFrames coordinadorF;

    public PnlBuscadorComandas(CoordinadorFrames coordinadorF) {
        this.coordinadorF = coordinadorF;
        initComponents();
        tablaComandas.setDefaultEditor(Object.class, null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createTitledBorder(
                null,
                "Búsqueda de comandas por rango de fechas",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Roboto", 1, 12)
        ));

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.JLabel lblInicio = new javax.swing.JLabel("Fecha inicio:");
        javax.swing.JLabel lblFin = new javax.swing.JLabel("Fecha fin:");

        fechaInicio = new JDateChooser();
        fechaFin = new JDateChooser();

        btnBuscar = new javax.swing.JButton("Buscar");

        jScrollPane1 = new javax.swing.JScrollPane();

        tablaComandas = new javax.swing.JTable();

        tablaComandas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Fecha", "Mesa", "Estado", "Cliente"
                }
        ) {
            Class[] types = new Class[]{
                Long.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });

        jScrollPane1.setViewportView(tablaComandas);

        add(lblInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));
        add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 150, -1));

        add(lblFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));
        add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 150, -1));

        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 100, -1));

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 860, 450));

        btnBuscar.addActionListener(e -> buscarComandas());
    }


    private void buscarComandas() {
        try {
            Date inicioDate = fechaInicio.getDate();
            Date finDate = fechaFin.getDate();

            if (inicioDate == null || finDate == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "Selecciona ambas fechas");
                return;
            }

            LocalDateTime inicio = inicioDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            LocalDateTime fin = finDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            List<ComandaDTO> lista = coordinadorF.buscarComandasPorFecha(inicio, fin);

            llenarTabla(lista);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void llenarTabla(List<ComandaDTO> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tablaComandas.getModel();
        modelo.setRowCount(0);

        if (lista != null) {
            for (ComandaDTO c : lista) {
                modelo.addRow(new Object[]{
                    c.getId(),
                    c.getFechaHoraCreacion(),
                    c.getIdMesa(),
                    c.getEstado(),
                    c.getIdCliente()
                });
            }
        }
    }

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaComandas;
    private javax.swing.JButton btnBuscar;
    private JDateChooser fechaInicio;
    private JDateChooser fechaFin;
}
