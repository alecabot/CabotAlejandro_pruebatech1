# Gestión de Empleados

Este proyecto es una aplicación de consola para la gestión de empleados utilizando Java y JPA con una base de datos MySQL.

## Requisitos

- Java 17 o superior
- Maven
- MySQL

## Configuración de la Base de Datos

1. Crea una base de datos en MySQL llamada `empleado`.
2. Configura las credenciales de la base de datos en el archivo `src/main/resources/META-INF/persistence.xml`.

## Uso
Al ejecutar la aplicación, verás un menú con las siguientes opciones:  
  1. Crear Empleado
  2. Editar Empleados
  3. Eliminar Empleados
  4. Buscar por cargo
  5. Mostrar proyectos
  6. Salir
Sigue las instrucciones en pantalla para gestionar los empleados.

## Cómo Funciona

- Crear Empleado
  Selecciona la opción 1 y sigue las instrucciones para ingresar el nombre, apellido, cargo, salario y fecha de inicio del nuevo empleado.  
- Editar Empleados
  Selecciona la opción 2 para ver la lista de empleados. Ingresa el ID del empleado que deseas modificar y sigue las instrucciones para actualizar sus datos.  
- Eliminar Empleados
  Selecciona la opción 3 para ver la lista de empleados. Ingresa el ID del empleado que deseas eliminar.  
- Buscar por Cargo
  Selecciona la opción 4 y sigue las instrucciones para ingresar el cargo del empleado que deseas buscar. Se mostrará una lista de empleados que coinciden con el cargo ingresado.  
- Mostrar Empleados
  Selecciona la opción 5 para ver la lista de todos los empleados registrados.  
- Salir
  Selecciona la opción 6 para salir de la aplicación.
  
## Estructura del Proyecto
  src/main/java/org/example: Contiene las clases principales del proyecto.
  
  - Main.java: Esta clase contiene el método main que inicia la aplicación y maneja la lógica del menú principal para la gestión de empleados.  
  
  - Empleado.java: Esta clase representa la entidad Empleado con sus atributos y métodos correspondientes. Está anotada con @Entity para ser gestionada por JPA.  
  
  - ControladoraPersistencia.java: Esta clase maneja la lógica de persistencia de los empleados, incluyendo métodos para crear, modificar, eliminar y buscar empleados en la base de datos.
  
  
  src/main/resources/META-INF/persistence.xml: Configuración de JPA y la base de datos.
