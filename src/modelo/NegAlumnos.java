/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conexion.ClaseDatos;
import datos.Alumno;
import java.sql.ResultSet;

/**
 *
 * @author jj
 */
public class NegAlumnos {

    private final ClaseDatos _cldatos;
    private ResultSet _rs;
    private String _buscar;
    public static final int COLUMN_ALUMNO_REGISTRO = 0;
    public static final int COLUMN_ALUMNO_DNI = 1;
    public static final int COLUMN_ALUMNO_NOMBRE = 2;
    public static final int COLUMN_ALUMNO_APELLIDO1 = 3;
    public static final int COLUMN_ALUMNO_APELLIDO2 = 4;

    public NegAlumnos(ClaseDatos cldatos) {
        _cldatos = cldatos;
        _rs = null;
        _buscar = "";
    }

    public int NumeroRegistros() throws Exception {
        int fila = -1;
        if (_rs.last()) {
            fila = _rs.getRow();
        }
        return fila;
    }

    public void Actualizar() throws Exception {
        String sql;
        if (_buscar.isEmpty()) {

            sql = "SELECT * FROM alumnos";

        } else {

            sql = "select * from alumnos where "
                    + "dni like '%" + _buscar + "%'"
                    + "or nombre like '%" + _buscar + "%'"
                    + "or apellido1 like '%" + _buscar + "%'"
                    + "or apellido2 like '%" + _buscar + "%'";

        }
        _cldatos.Ejecutar_Consulta(sql);
        _rs = _cldatos.getRs();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    // Poner en el futuro para solucionar lo de inyección de código
    public int Modificar(Alumno alumno) throws Exception {
      
        //Arregla la funcion
        int id = _cldatos.Ejecutar_Actualizacion("UPDATE alumnos SET "
                + "dni = '" + alumno.getDni()+ "', "
                + "nombre = '" + alumno.getNombre() + "', " 
                + "apellido1 = '" + alumno.getApellido1() + "', " 
                + "apellido2 = '" + alumno.getApellido2() + "' "
                + "WHERE registro = " + alumno.getRegistro() + ";"
        );
        //alumno.setRegistro(id);
        //if (id > 0) {
            Actualizar();
        
        //}
        return id;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////// 

    // Poner en el futuro para solucionar lo de inyección de código
    public int Altas(Alumno alumno) throws Exception {
        int id = _cldatos.Ejecutar_Insercion("INSERT INTO alumnos VALUES( "
                + "null, "
                + "'" + alumno.getDni() + "', "
                + "'" + alumno.getNombre() + "', "
                + "'" + alumno.getApellido1() + "', "
                + "'" + alumno.getApellido2() + "' )");
        alumno.setRegistro(id);
        if (id > 0) {
            Actualizar();
        }
        return id;
    }

    // Poner en el futuro para solucionar lo de inyección de código, aunque aquí no es necesario, el campo es entero
    public int Borrar(Alumno alumno) throws Exception {
        int fila = _cldatos.Ejecutar_Actualizacion("DELETE FROM alumnos "
                + "WHERE registro = " + alumno.getRegistro());
        if (fila > 0) {
            Actualizar();
        }
        return fila;
    }

    public ResultSet getRs() {
        return _rs;
    }

    //los metadatos empiezan en base 1 por eso +1
    public Alumno getAlumno(int row) throws Exception {
        Alumno alumno = null;
        if (_rs.absolute(row)) {

            alumno = new Alumno();
// +1 a cada columna
            int registro = _rs.getInt(COLUMN_ALUMNO_REGISTRO + 1);
            String dni = _rs.getString(COLUMN_ALUMNO_DNI + 1);
            String nombre = _rs.getString(COLUMN_ALUMNO_NOMBRE + 1);
            String apellido1 = _rs.getString(COLUMN_ALUMNO_APELLIDO1 + 1);
            String apellido2 = _rs.getString(COLUMN_ALUMNO_APELLIDO2 + 1);

            alumno.setRegistro(registro);
            alumno.setDni(dni);
            alumno.setNombre(nombre);
            alumno.setApellido1(apellido1);
            alumno.setApellido2(apellido2);

        }
        return alumno;
    }

    public String getBuscar() {
        return _buscar;
    }

    public void setBuscar(String buscar) throws Exception {
        _buscar = buscar;
        Actualizar();


    }

    
}
