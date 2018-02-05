/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos;

/**
 *
 * @author jj
 */
public class Alumno {

    
    private int    _registro;
    private String _dni;
    private String _nombre;
    private String _apellido1;
    private String _apellido2;
    
    public Alumno() {
        
        _registro=-1;
    }
    public Alumno(String dni, int registro, String nombre, String apellido1, String apellido2){
        _dni=dni;
        _registro=registro;
        _nombre=nombre;
        _apellido1=apellido1;
        _apellido2=apellido2;
    }
            

    
    public int getRegistro() {
        return _registro;
    }

    public void setRegistro(int registro) {
        this._registro = registro;
    }

    public String getDni() {
        return _dni;
    }

    public void setDni(String dni) {
        this._dni = dni;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        this._nombre = nombre;
    }

    public String getApellido1() {
        return _apellido1;
    }

    public void setApellido1(String apellido1) {
        this._apellido1 = apellido1;
    }

    public String getApellido2() {
        return _apellido2;
    }

    public void setApellido2(String apellido2) {
        this._apellido2 = apellido2;
    }
    
    
}
