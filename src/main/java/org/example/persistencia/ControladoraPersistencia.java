package org.example.persistencia;

import org.example.logica.Empleado;
import org.example.persistencia.exceptions.NonexistentEntityException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {


    EmpleadoJpaController empleJPA = new EmpleadoJpaController();

    public void crearEmpleado(Empleado pers) {
        empleJPA.create(pers);
        System.out.println("proyecto creado correctamente");
    }

    public void borrarEmpleado(Long id) {
        try {
            empleJPA.destroy(id);
            System.out.println("empleado con id: " + id + " borrado correctamente");
        } catch (NonexistentEntityException ex) {
//            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("el empleado con id " + id +  " no existe");

        }
    }

    public List<Empleado> traerEmpleado () {
        return empleJPA.findEmpleadoEntities();
    }

    public List<Empleado> traerEmpleadosPorTipo (String cargo) {
        List<Empleado> empleados = empleJPA.findEmpleadoEntities();
        //este metodo elimina los proyectos que no coinciden con el tipo especificado
        empleados.removeIf(empleado -> !empleado.getCargo().equals(cargo));
        return empleados;
    }

    public void modificarEmpleado (Empleado pers) {

        try {
            empleJPA.edit(pers);
            System.out.println("empleado con id: " + pers.getId() + " modificado correctamente");
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer contarEmpleados(){
        return empleJPA.getEmpleadoCount();
    }
}

