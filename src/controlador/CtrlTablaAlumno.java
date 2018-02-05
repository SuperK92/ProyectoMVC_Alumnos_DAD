/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import datos.Alumno;
import modelo.NegAlumnos;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jjrodmar
 */

//Clase controladora de la carga del  JTable partiendo de la clase abstracta AbstractTableModel
//https://docs.oracle.com/javase/7/docs/api/javax/swing/table/AbstractTableModel.html
public class CtrlTablaAlumno extends AbstractTableModel{
  
    private final NegAlumnos _negalumnos;
    private int _ultimaFila=-1;
    private Alumno alumno;
    private static final String _columnas[] = {
        "Registro",
        "Dni",
        "Nombre",
        "Apellido1",
        "Apellido2"
    };

    public CtrlTablaAlumno(NegAlumnos negalumnos) {
        _negalumnos = negalumnos;
    }

    // Indica el número de filas que tendrá la tabla
    @Override
    public int getRowCount() {
        try {
            return _negalumnos.NumeroRegistros();
        } catch (Exception e) {
        }
        return 0;
    }

    //Indica el número de campos a mostrar en la tabla
    
    @Override
    public int getColumnCount() {
        return _columnas.length;
    }

    //Carga el valor en la fila y columna correspondiente
    //atención es +1 pq los metadatos trabajan en arry base 1 y yo espero en base 0
    //voy a comentar el que puse inicialmente y voy a poner un array para cargar los datos
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= 0) {
            try {
                
                if(rowIndex != _ultimaFila){
                    alumno = _negalumnos.getAlumno(rowIndex + 1);
                    _ultimaFila=rowIndex;
                    
                }
                               
                switch (columnIndex) {

                    case 0:
                        return alumno.getRegistro();
                    case 1:
                        return alumno.getDni();
                    case 2:
                        return alumno.getNombre();
                    case 3:
                        return alumno.getApellido1();
                    case 4:
                        return alumno.getApellido2();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return "";
    }

    //Indica el nombre de la columna según array _columnas
    @Override
    public String getColumnName(int columnIndex) {
        return _columnas[ columnIndex];
    }

    //Indica si las celldas del Jtable son editable o no. En este caso ningun campo es editable
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}