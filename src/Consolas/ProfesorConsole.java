package Consolas;

import java.util.Scanner;

import actividades.Actividad;
import learningPath.LearningPath;
import user.Profesor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfesorConsole {
    
	private static Map<String, Profesor> profesores = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatos();
        
        System.out.println("== Sistema de Profesores ==");
        System.out.print("Ingrese su login: ");
        String login = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        Profesor profesor = profesores.get(login);
        if (profesor != null && profesor.login(login, password)) {
            System.out.println("Autenticación exitosa. Bienvenido, Profesor " + profesor.getNombre());
            mostrarMenuProfesor(profesor);
        } else {
            System.out.println("Credenciales incorrectas.");
        }
        
        guardarDatos();
    }

    private static void mostrarMenuProfesor(Profesor profesor) {
        int opcion;
        do {
            System.out.println("\n== Menú Profesor ==");
            System.out.println("1. Registrar una actividad");
            System.out.println("2. Crear un Learning Path");
            System.out.println("3. Ver mis actividades");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    registrarActividad(profesor);
                    break;
                case 2:
                    crearLearningPath(profesor);
                    break;
                case 3:
                    verActividades(profesor);
                    break;
                case 4:
                    profesor.logout();
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (profesor.isLoggedIn());
    }

    private static void registrarActividad(Profesor profesor) {
        
    	System.out.print("Ingrese el titulo de la actividad: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Ingrese la descripcion de la actividad: ");
        String descripcion = scanner.nextLine();
        
        System.out.print("Ingrese los objetivos de la actividad (separados por comas): ");
        String objetivosInput = scanner.nextLine();
        List<String> objetivos = new ArrayList<>();
        for (String objetivo : objetivosInput.split(",")) {
            objetivos.add(objetivo.trim());
        }
        
        System.out.print("Ingrese el nivel de dificultad de la actividad: ");
        String dificultad = scanner.nextLine();
        
        System.out.print("Ingrese la duracion en minutos de la actividad: ");
        int duracion = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Ingrese la fecha limite de la actividad (formato: dd/MM/yyyy): ");
        String fechaInput = scanner.nextLine();
        Date fechaLimite = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            fechaLimite = sdf.parse(fechaInput); 
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto. Utilizando fecha actual.");
            fechaLimite = new Date(); 
        }

        Actividad actividad = new Actividad(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, profesor); 
        profesor.registrarActividad(actividad);
        System.out.println("Actividad registrada exitosamente.");
    }

    private static void crearLearningPath(Profesor profesor) {
    	
    	System.out.print("Ingrese el titulo del learning path: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Ingrese la descripcion del learning path: ");
        String descripcion = scanner.nextLine();
        
        System.out.print("Ingrese los objetivos del learning path (separados por comas): ");
        String objetivosInput = scanner.nextLine();
        List<String> objetivos = new ArrayList<>();
        for (String objetivo : objetivosInput.split(",")) {
            objetivos.add(objetivo.trim());
        }
        
        System.out.print("Ingrese el nivel de dificultad de la actividad: ");
        String dificultad = scanner.nextLine();
        
        
        
        
        LearningPath lp = new LearningPath(titulo, descripcion, objetivos, dificultad, profesor, null, null); 
        profesor.registrarLearningPath(lp);
        System.out.println("Learning Path creado exitosamente.");
    }

    private static void verActividades(Profesor profesor) {
        System.out.println("== Mis Actividades ==");
        profesor.getMapaRecursosPropios().forEach((id, recurso) -> System.out.println("Recurso: " + id));
        profesor.getMapaTareasPropias().forEach((id, tarea) -> System.out.println("Tarea: " + id));
        profesor.getMapaExamenesPropios().forEach((id, examen) -> System.out.println("Examen: " + id));
        profesor.getMapaQuicesPropios().forEach((id, quiz) -> System.out.println("Quiz: " + id));
        profesor.getMapaEncuestasPropias().forEach((id, encuesta) -> System.out.println("Encuesta: " + id));
        
    }

    // Métodos para cargar y guardar datos en archivos (simulación)
    private static void cargarDatos() {
        // Aquí se podría implementar la carga de datos de un archivo
    }

    private static void guardarDatos() {
        // Aquí se podría implementar la persistencia de datos en un archivo
    }
}