package org.example.logica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String cargo;
    private Double salario;
    private LocalDate fechaInicio;


    // Constructor por defecto
    public Empleado() {}

    // Constructor con parámetros


    public Empleado(Long id, String nombre, String apellido, String cargo, Double salario, LocalDate fechaInicio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaInicio = fechaInicio;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-15s %-20s %-15s %-12.2f %-10s",
                id,
                nombre,
                apellido,
                cargo,
                salario,
                fechaInicio.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }
}
