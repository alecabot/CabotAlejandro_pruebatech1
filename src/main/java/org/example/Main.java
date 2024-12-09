package org.example;

import org.example.logica.Empleado;
import org.example.persistencia.ControladoraPersistencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in); // Scanner para leer la entrada del usuario
    private static final ControladoraPersistencia controlPersi = new ControladoraPersistencia(); // Controladora de persistencia para manejar la lógica de negocio
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Formateador de fechas

    public static void main(String[] args) {
        String opcion;

        // Bucle principal del menú
        do {
            System.out.println("******************************");
            System.out.println("*    Gestión de Empleados    *");
            System.out.println("******************************");
            System.out.println("* 1. Crear Empleado          *");
            System.out.println("* 2. Editar Empleados        *");
            System.out.println("* 3. Eliminar Empleados      *");
            System.out.println("* 4. Buscar por tipo         *");
            System.out.println("* 5. Mostrar proyectos       *");
            System.out.println("* 6. Salir                   *");
            System.out.println("******************************");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine(); // Leer la opción del usuario

            switch (opcion) {
                case "1":
                    grabarEmpleado(null); // Crear un nuevo proyecto
                    break;
                case "2":
                    mostrarTodosLosEmpleados(); // Mostrar todos los proyectos
                    System.out.println("introduzca el id del empleado a modificar: ");
                    Long id = leerEntrada(Long.class); // Leer el ID del proyecto a modificar
                    grabarEmpleado(id); // Modificar el proyecto con el ID especificado
                    break;
                case "3":
                    eliminarEmpleado(); // Eliminar un proyecto
                    break;
                case "4":
                    buscarPorTipo(); // Buscar proyectos por tipo
                    break;
                case "5":
                    mostrarTodosLosEmpleados(); // Mostrar todos los proyectos
                    break;
                case "6":
                    System.out.println("Saliendo... "); // Salir del programa
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo."); // Opción no válida
                    break;
            }
        } while (!opcion.equals("6")); // Repetir hasta que el usuario elija salir
    }

    /*
     Metodo genérico para leer la entrada del usuario, esto hace que pueda meter cualquier parametro del tipo que sea,
     Introduzco el Tipo de dato que quiero leer y me hace un cast si el valor no coincide me salta una excepcion al cath
     me devuelve un tipo de dato generico en el return dependiendo el tipo de dato
    */
    private static <U> U leerEntrada(Class<U> tipo) {
        while (true) {
            try {
                U entrada = null;
                if (tipo == Integer.class) {
                    entrada = tipo.cast(scanner.nextInt());
                    scanner.nextLine(); // Limpiar el buffer
                } else if (tipo == Long.class) {
                    entrada = tipo.cast(scanner.nextLong());
                    scanner.nextLine(); // Limpiar el buffer
                } else if (tipo == Double.class) {
                    entrada = tipo.cast(scanner.nextDouble());
                    scanner.nextLine(); // Limpiar el buffer
                } else if (tipo == LocalDate.class) {
                    String input = scanner.nextLine();
                    entrada = tipo.cast(LocalDate.parse(input, formatter));
                } else if (tipo == String.class) {
                    String input = scanner.nextLine();
                    if (input.matches("[a-zA-Z ]+")) {
                        entrada = tipo.cast(input);
                    } else {
                        System.out.println("Entrada no válida. Se esperaba caracteres. Intente de nuevo.");
                        continue;
                    }
                }
                if (entrada != null) {
                    return entrada;
                } else {
                    System.out.println("La entrada no puede ser nula. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. formato numerico incorrecto. Intente de nuevo.");
                scanner.next(); // Limpiar entrada inválida
            } catch (DateTimeParseException e) {
                System.out.println("Fecha no válida. Intente de nuevo.");
            } catch (Exception e) {
                System.out.println("Entrada no válida. Intente de nuevo.");
                scanner.next(); // Limpiar entrada inválida
            }
        }
    }

    // Método para eliminar un proyecto
    private static void eliminarEmpleado() {
        mostrarTodosLosEmpleados(); // Mostrar todos los proyectos
        System.out.print("Introduzca el id del empleado a eliminar: ");
        Long idEmpleado = leerEntrada(Long.class); // Leer el ID del proyecto a eliminar
        controlPersi.borrarEmpleado(idEmpleado); // Eliminar el proyecto con el ID especificado

    }

    // Método para crear o modificar un proyecto
    private static void grabarEmpleado(Long id) {
        System.out.print("Ingrese nombre: ");
        String nombre = leerEntrada(String.class); // Leer el nombre del proyecto

        System.out.print("Ingrese apellido: ");
        String apellido = leerEntrada(String.class); // Leer el apellido del proyecto

        System.out.print("Ingrese cargo: ");
        String cargo = leerEntrada(String.class); // Leer el nombre del proyecto

        System.out.print("Ingrese salario: ");
        Double salario = leerEntrada(Double.class); // Leer el nombre del proyecto

        System.out.print("Ingrese fecha de inicio (dd-MM-yyyy): ");
        LocalDate fechaInicio = leerEntrada(LocalDate.class); // Leer la fecha de inicio del proyecto

        if (id == null) {
            controlPersi.crearEmpleado(new Empleado(null, nombre, apellido, cargo, salario, fechaInicio)); // Crear un nuevo proyecto
        } else {
            controlPersi.modificarEmpleado(new Empleado(id, nombre, apellido, cargo, salario, fechaInicio)); // Modificar un proyecto existente
        }
    }

    // Método para buscar proyectos por tipo
    private static void buscarPorTipo() {
        mostrarTodosLosEmpleados(); // Mostrar todos los proyectos
        System.out.print("Ingrese el cargo del empleado: ");
        String cargo = leerEntrada(String.class); // Leer el tipo de proyecto
        List<Empleado> tipoEmpleados = controlPersi.traerEmpleadosPorTipo(cargo); // Obtener proyectos por tipo
        // si me devuelve un array con contenido me lo imprime
        if (!tipoEmpleados.isEmpty()) {
            mostrarRegistros(tipoEmpleados);
        } else {
            System.out.println("no se encontraron empleados para este cargo");
        }
    }

    // Método para mostrar todos los proyectos
    private static void mostrarTodosLosEmpleados() {
        if (controlPersi.contarEmpleados() != 0) {
            List<Empleado> empleados = controlPersi.traerEmpleado(); // Obtener todos los proyectos
            mostrarRegistros(empleados);
        }else {
            System.out.println("no se encontraron empleados");
        }

    }

    private static void mostrarRegistros(List<Empleado> empleados) {
        System.out.println();
        System.out.printf("%-5s %-15s %-20s %-15s %-12s %-10s%n", "ID", "Nombre", "Apellido", "Cargo", "Salario", "Fecha Inicio");
        System.out.println("--------------------------------------------------------------------------------------");
        for (Empleado empleado : empleados) {
            System.out.printf("%-5d %-15s %-20s %-15s %-12.2f %-10s%n",
                    empleado.getId(),
                    empleado.getNombre(),
                    empleado.getApellido(),
                    empleado.getCargo(),
                    empleado.getSalario(),
                    empleado.getFechaInicio().format(formatter));
        }
        System.out.println();
    }
}