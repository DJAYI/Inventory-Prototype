package gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import logic.Controller.Controller;
import logic.Equipo;
import logic.Laboratorio;

public class LabInventoryUI {

    private final Controller controller = new Controller();
    private final Scanner in = new Scanner(System.in);

    public void start() {
        String action;
        String enter;
        do {
            clearScreen();
            showMainMenu();
            action = in.nextLine().toLowerCase().trim();
            handleAction(action);
            enter = in.nextLine().trim();
            System.out.println(enter);
        } while (!"0".equals(action));
        System.out.println("See u later dude ;)");
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void showMainMenu() {
        System.out.println("============================================================================");
        System.out.println("Bienvenido a este sistema de inventario para los laboratorios de Elyon Yireh");
        System.out.println("============================================================================");
        System.out.println("¿Qué acción desea realizar?");
        System.out.println("=====| Equipos |=====");
        System.out.println("|1|. Ingresar Equipo de Laboratorio");
        System.out.println("|2|. Editar Equipo de Laboratorio");
        System.out.println("|3|. Eliminar Equipo de Laboratorio");
        System.out.println("=====| Laboratorios |=====");
        System.out.println("|4|. Ingresar nuevo Laboratorio");
        System.out.println("|5|. Ver lista de Laboratorios");
        System.out.println("|6|. Ver lista de Equipos por Laboratorio");
        System.out.println("|7|. Editar Información de Laboratorio");
        System.out.println("=====| Acciones |=====");
        System.out.println("|A|. Crear Orden de Servicio | Mantenimiento para Equipo");
        System.out.println("|B|. Crear Acta de Baja para Equipo");
        System.out.println("|C|. Crear Acta de Entrega de Equipo");
        System.out.println("\n------------------------------------");
        System.out.println("|0|. Salir");
    }

    private void handleAction(String action) {
        switch (action) {
            case "1" -> {
                clearScreen();
                ingresarEquipo();
            }
            case "2" -> {
                clearScreen();
                editarEquipo();
            }
            case "3" -> {
                clearScreen();
                eliminarEquipo();
            }
            case "4" -> {
                clearScreen();
                ingresarLaboratorio();
            }
            case "5" -> {
                clearScreen();
                verListaLaboratorios();
            }
            case "6" -> {
                clearScreen();
                verListaEquiposPorLaboratorio();
            }
            case "7" -> {
                clearScreen();
                editarLaboratorio();
            }
            default -> {
                clearScreen();
            }
        }
    }

    private void ingresarEquipo() {
        System.out.println("Ingrese el Código del Equipo: ");
        String codigo = in.nextLine();
        System.out.println("Ingrese el Nombre del Equipo: ");
        String nombre = in.nextLine();
        System.out.println("Ingrese el Estado del Equipo: ");
        String estado = in.nextLine();
        System.out.println("Ingrese el Responsable del Equipo: ");
        String responsable = in.nextLine();
        System.out.println("_______Ingrese la Ubicación del Equipo_________");
        System.out.println("Ingrese la Torre: ");
        String ubiTorre = in.nextLine();
        System.out.println("Ingrese el Piso: ");
        String ubiPiso = in.nextLine();
        String ubicacion = "Torre " + ubiTorre + " Piso " + ubiPiso;
        System.out.println("Ingrese las Observaciones del Equipo: ");
        String observaciones = in.nextLine();
        System.out.println("Ingrese el laboratorio al que pertenece");
        int numeroLab = in.nextInt();
        in.nextLine(); // clear buffer
        Laboratorio labId = controller.FindLaboratorio(numeroLab);

        IngresarEquipo(codigo, nombre, estado, new Date(), responsable, ubicacion, observaciones, labId);
    }

    private void editarEquipo() {
        System.out.println("¿Qué equipo desea editar? (Ingrese el ID)");
        int idEquipoEditar = in.nextInt();
        in.nextLine(); // clear buffer
        Equipo equipoEditar = controller.FindEquipo(idEquipoEditar);

        if (equipoEditar != null) {
            EditarEquipo(equipoEditar.getId());
        }
    }

    private void eliminarEquipo() {
        System.out.println("¿Qué equipo desea eliminar? (Ingrese el ID)");
        int eliminarId = in.nextInt();
        in.nextLine(); // clear buffer
        Equipo equipoEliminar = controller.FindEquipo(eliminarId);

        if (equipoEliminar != null) {
            EliminarEquipo(equipoEliminar.getId());
        }
    }

    private void ingresarLaboratorio() {
        System.out.println("Ingrese el código del laboratorio: ");
        String codigoLab = in.nextLine();
        System.out.println("Ingrese el nombre del laboratorio: ");
        String nombreLab = in.nextLine();

        IngresarLaboratorio(codigoLab, nombreLab);
    }

    private void verListaLaboratorios() {
        VerListaLaboratorios();
    }

    private void verListaEquiposPorLaboratorio() {
        System.out.println("¿Los equipos de qué laboratorios desea ver? (Ingrese el ID)");
        int idLaboratorio = in.nextInt();
        in.nextLine(); // clear buffer
        VerListaEquiposPorLaboratorio(idLaboratorio);
    }

    private void editarLaboratorio() {
        System.out.println("¿Qué laboratorio desea editar? (Ingrese el ID)");
        int idLaboratorioEditar = in.nextInt();
        in.nextLine(); // clear buffer
        Laboratorio lab = controller.FindLaboratorio(idLaboratorioEditar);

        if (lab != null) {
            EditarLaboratorio(lab.getId());
        }
    }

    private void IngresarEquipo(String codigo, String nombre, String estado, Date fechaAdquisicion,
            String responsable, String ubicacion, String observaciones, Laboratorio lab) {
        try {
            Equipo newEquipo = new Equipo(Integer.SIZE, codigo, nombre, estado, fechaAdquisicion, responsable,
                    ubicacion, observaciones, lab);
            controller.CreateEquipo(newEquipo);
            System.out.println("Equipo creado correctamente!");
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Ha habido un problema al crear el equipo");
        }
    }

    private void EditarEquipo(int id) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\n\n");
            System.out.println("------------------------------");
            System.out.println("¿Qué desea editar del equipo?");
            System.out.println("|1|. Codigo");
            System.out.println("|2|. Nombre");
            System.out.println("|3|. Estado");
            System.out.println("|4|. Responsable");
            System.out.println("|5|. Ubicacion");
            System.out.println("|6|. Observaciones");
            System.out.println("|7|. Laboratorio");
            System.out.println("\n");
            System.out.println("|0|. Salir");

            try {
                Equipo EquipoEditar = controller.FindEquipo(id);
                String EditarOptions = sc.nextLine();
                switch (EditarOptions) {
                    case "1":
                        System.out.println("Ingrese nuevo Codigo:");
                        String codigo = sc.nextLine();
                        EquipoEditar.setCódigo(codigo);
                        controller.EditEquipo(EquipoEditar);
                        break;

                    case "2":
                        System.out.println("Ingrese nuevo Nombre:");
                        String nombre = sc.nextLine();
                        EquipoEditar.setNombre(nombre);
                        controller.EditEquipo(EquipoEditar);
                        break;
                    case "3":
                        System.out.println("Ingrese nuevo Estado:");
                        String estado = sc.nextLine();
                        EquipoEditar.setEstado(estado);
                        controller.EditEquipo(EquipoEditar);
                        break;
                    case "4":
                        System.out.println("Ingrese nuevo Responsable: ");
                        String responsable = sc.nextLine();
                        EquipoEditar.setResponsable(responsable);
                        controller.EditEquipo(EquipoEditar);
                        break;
                    case "5":
                        System.out.println("\n\n-------------------------------");
                        System.out.println("=========_Ingrese la nueva ubicacion del equipo__==========");
                        System.out.println("Ingrese el Numero de la torre: ");
                        String NuevaTorre = sc.nextLine();

                        System.out.println("Ingrese el Numero del piso: ");
                        String NuevoPiso = sc.nextLine();

                        String ubicacion = "Torre " + NuevaTorre + " Piso " + NuevoPiso;

                        EquipoEditar.setUbicacion(ubicacion);
                        controller.EditEquipo(EquipoEditar);
                        break;
                    case "6":
                        System.out.println("Ingrese nueva Observacion: ");
                        String observaciones = sc.nextLine();
                        EquipoEditar.setObservaciones(observaciones);
                        controller.EditEquipo(EquipoEditar);
                    case "7":
                        System.out.println("\n\n-------------------------------");
                        System.out.println("Ingrese el Id del laboratorio que desea asignarle al equipo:");
                        int IdNuevoLab = sc.nextInt();

                        Laboratorio lab = controller.FindLaboratorio(IdNuevoLab);

                        EquipoEditar.setLaboratorioID(lab);
                        controller.EditEquipo(EquipoEditar);
                        break;
                    case "0":
                        System.out.println("Tenga buen dia! ;)");
                        break;
                    default:
                        throw new AssertionError();
                }

                System.out.println("Equipo editado correctamente!");
            } catch (Exception e) {
                System.out.println("Error: " + e);
                System.out.println("El Equipo no se ha podido encontrorar");
            }
        } catch (AssertionError e) {
            System.out.println(e);
        }
    }

    private void EliminarEquipo(int id) {
        try {
            controller.DeleteEquipo(id);
            System.out.println("Equipo Eliminado correctamente!");
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("El equipo no se ha podido eliminar");
        }
    }

    private void IngresarLaboratorio(String codigo, String nombre) {
        try {
            Laboratorio newLaboratorio = new Laboratorio(Integer.SIZE, codigo, nombre);
            controller.CreateLaboratorio(newLaboratorio);
            System.out.println("Laboratorio Creado exitosamente");
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("No se ha podido crear el laboratorio");
        }

    }

    private void VerListaLaboratorios() {

        ArrayList<Laboratorio> ListLabs = controller.FindAllLaboratorios();
        System.out.println("===== | Lista de Laboratorios | =====");

        for (Laboratorio Lab : ListLabs) {
            System.out.println("Laboratorio encontrado: " + Lab.toString());
        }
    }

    private void VerListaEquiposPorLaboratorio(int id) {

        Laboratorio lab = controller.FindLaboratorio(id);
        ArrayList<Equipo> EquiposLab = lab.getEquipoID();
        System.out.println("Equipos del laboratorio " + lab.getCódigo());
        for (Equipo equipo : EquiposLab) {
            System.out.println("Equipo: " + equipo.toString());
        }
    }

    private void EditarLaboratorio(int id) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\n\n");
            System.out.println("------------------------------");
            System.out.println("¿Qué desea editar del laboratorio?");
            System.out.println("|1|. Codigo");
            System.out.println("|2|. Nombre");
            System.out.println("\n");
            System.out.println("|0|. Salir");

            try {
                Laboratorio LabEditar = controller.FindLaboratorio(id);
                String EditarOptions = sc.nextLine();

                switch (EditarOptions) {
                    case "1" -> {
                        System.out.println("Ingrese nuevo Codigo:");
                        String NewCodigo = sc.nextLine();
                        LabEditar.setCódigo(NewCodigo);
                        controller.EditLaboratorio(LabEditar);
                    }

                    case "2" -> {
                        System.out.println("Ingrese nuevo Nombre:");
                        String NewNombre = sc.nextLine();
                        LabEditar.setNombre(NewNombre);
                        controller.EditLaboratorio(LabEditar);
                    }
                    default -> {
                        System.out.println("No se ha podido editar el Laboratorio");
                    }
                }

                System.out.println("Laboratorio editado con exito!");
            } catch (Exception e) {
                System.out.println("No se pudo editar el laboratorio");
            }
        } catch (AssertionError e) {
            System.out.println(e);
        }
    }
}
