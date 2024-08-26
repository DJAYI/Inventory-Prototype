package com.elyon_yireh.lab_inventory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import logic.Controller.Controller;
import logic.Equipo;
import logic.Laboratorio;

public class Lab_inventory {

    public static void main(String[] args) {
        Controller controller = new Controller();
        Scanner in = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("============================================================================");
        System.out.println("Bienvenido a este sistema de inventario para los laboratorios de Elyon Yireh");
        System.out.println("============================================================================");

        System.out.println("¿Que accion desea realizar?");
        System.out.println("=====| Equipos |=====");

        System.out.println("|1|. Ingresar Equipo de Laboratorio");
        System.out.println("|2|. Editar Equipo de Laboratorio");
        System.out.println("|3|. Eliminar Equipo de Laboratorio");

        System.out.println("=====| Laboratorios |=====");
        System.out.println("|4|. Ingresar nuevo Laboratorio");
        System.out.println("|6|. Ver lista de Equipos por Laboratorio");
        System.out.println("|7|. Editar Informacion de Laboratorio");

        System.out.println("=====| Acciones |=====");
        System.out.println("|A|. Crear Orden de Servicio | Mantenimiento para Equipo");
        System.out.println("|B|. Crear Acta de Baja para Equipo");
        System.out.println("|C|. Crear Acta de Entrega de Equipo");

        System.out.println("\n------------------------------------");

        System.out.println("|0|. Salir");

        String Action = in.nextLine();

        switch (Action) {

            case "1" -> {
                System.out.println("Ingrese el Codigo del Equipo: ");
                String Codigo = in.nextLine();
                System.out.println("Ingrese el Nombre del Equipo: ");
                String Nombre = in.nextLine();
                System.out.println("Ingrese el Estado del Equipo: ");
                String Estado = in.nextLine();
                System.out.println("Ingrese el Responsable del Equipo: ");
                String Responsable = in.nextLine();
                System.out.println("_______Ingrese la Ubicacion del Equipo_________");
                System.out.println("Ingrese la Torre: ");
                String Ubi_Torre = in.nextLine();
                System.out.println("Ingrese el piso: ");
                String Ubi_Piso = in.nextLine();
                String Ubicacion = "Torre " + Ubi_Torre + " " + "Piso " + Ubi_Piso;
                System.out.println("Ingrese las Observaciones del Equipo: ");
                String Observaciones = in.nextLine();
                System.out.println("Ingrese el laboratorio al que pertenece");
                int NumeroLab = in.nextInt();
                Laboratorio LabId = controller.FindLaboratorio(NumeroLab);

                IngresarEquipo(Codigo, Nombre, Estado, new Date(), Responsable, Ubicacion, Observaciones, LabId);
            }

            case "2" -> {
                System.out.println("¿Que equipo desea editar? (Ingrese el ID))");
                int IdEquipoEditar = in.nextInt();
                Equipo EquipoEditar = controller.FindEquipo(IdEquipoEditar);

                if (EquipoEditar != null) {
                    EditarEquipo(EquipoEditar.getId());
                }
            }
            case "3" -> {
                System.out.println("¿Que equipo desea eliminar? (Ingrese el ID)");
                int EliminarId = in.nextInt();
                Equipo EquipoEliminar = controller.FindEquipo(EliminarId);

                if (EquipoEliminar != null) {
                    EliminarEquipo(EquipoEliminar.getId());
                }
            }
            case "4" -> {
                System.out.println("Ingrese el código del laboratorio: ");
                String codigoLab = in.nextLine();
                System.out.println("Ingrese el nombre del laboratorio: ");
                String nombreLab = in.nextLine();

                IngresarLaboratorio(codigoLab, nombreLab);
            }

            case "5" -> VerListaLaboratorios();

            case "6" -> {
                System.out.println("¿Los equipos de que laboratorios desea realizar? (Ingrese el ID)");
                int IdLaboratorio = in.nextInt();
                VerListaEquiposPorLaboratorio(IdLaboratorio);
            }

            case "7" -> {
                System.out.println("¿Que laboratorio desea editar? (Ingrese el ID))");
                int IdLaboratorioEditar = in.nextInt();
                Laboratorio Lab = controller.FindLaboratorio(IdLaboratorioEditar);

                if (Lab != null) {
                    EditarLaboratorio(Lab.getId());
                }
            }

            case "0" -> System.out.println("See u later dude ;)");
            default -> throw new AssertionError();
        }

    }

    // EQUIPOS
    public static void IngresarEquipo(String codigo, String nombre, String estado, Date fechaAdquisicion,
            String responsable, String ubicacion, String observaciones, Laboratorio lab) {
        Controller controller = new Controller();

        try {
            Equipo NewEquipo = new Equipo(Integer.SIZE, codigo, nombre, estado, fechaAdquisicion, responsable,
                    ubicacion, observaciones, lab);
            controller.CreateEquipo(NewEquipo);

            System.out.println("Equipo creado correctamente!");
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Ha habido un problema al crear el equipo");
        }
    }

    public static void EditarEquipo(int Id) {
        Controller controller = new Controller();
        Scanner sc = new Scanner(System.in);

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
            Equipo EquipoEditar = controller.FindEquipo(Id);
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
    }

    public static void EliminarEquipo(int Id) {
        Controller controller = new Controller();
        try {
            controller.DeleteEquipo(Id);
            System.out.println("Equipo Eliminado correctamente!");
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("El equipo no se ha podido eliminar");
        }
    }

    // LABORATORIOS
    public static void IngresarLaboratorio(String codigo, String nombre) {
        Controller controller = new Controller();
        try {
            Laboratorio newLaboratorio = new Laboratorio(Integer.SIZE, codigo, nombre);
            controller.CreateLaboratorio(newLaboratorio);
            System.out.println("Laboratorio Creado exitosamente");
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("No se ha podido crear el laboratorio");
        }

    }

    public static void VerListaLaboratorios() {
        Controller controller = new Controller();

        ArrayList<Laboratorio> ListLabs = controller.FindAllLaboratorios();
        System.out.println("===== | Lista de Laboratorios | =====");

        for (Laboratorio Lab : ListLabs) {
            System.out.println("Laboratorio encontrado: " + Lab.toString());
        }
    }

    public static void VerListaEquiposPorLaboratorio(int Id) {
        // VER EQUIPOS DE UN LABORATORIO EN ESPECIFICO

        Controller controller = new Controller();

        Laboratorio lab = controller.FindLaboratorio(Id);
        ArrayList<Equipo> EquiposLab = lab.getEquipoID();
        System.out.println("Equipos del laboratorio " + lab.getCódigo());
        for (Equipo equipo : EquiposLab) {
            System.out.println("Equipo: " + equipo.toString());
        }
    }

    public static void EditarLaboratorio(int Id) {
        Controller controller = new Controller();
        Scanner sc = new Scanner(System.in);

        System.out.println("\n\n");
        System.out.println("------------------------------");
        System.out.println("¿Qué desea editar del laboratorio?");
        System.out.println("|1|. Codigo");
        System.out.println("|2|. Nombre");
        System.out.println("\n");
        System.out.println("|0|. Salir");

        try {
            Laboratorio LabEditar = controller.FindLaboratorio(Id);
            String EditarOptions = sc.nextLine();

            switch (EditarOptions) {
                case "1":
                    System.out.println("Ingrese nuevo Codigo:");
                    String NewCodigo = sc.nextLine();
                    LabEditar.setCódigo(NewCodigo);
                    controller.EditLaboratorio(LabEditar);
                    break;

                case "2":
                    System.out.println("Ingrese nuevo Nombre:");
                    String NewNombre = sc.nextLine();
                    LabEditar.setNombre(NewNombre);
                    controller.EditLaboratorio(LabEditar);
                    break;
                default:
                    throw new AssertionError();
            }

            System.out.println("Laboratorio editado con exito!");
        } catch (Exception e) {
            System.out.println("No se pudo editar el laboratorio");
        }

    }
}
