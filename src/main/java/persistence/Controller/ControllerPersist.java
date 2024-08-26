/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.Entrega;
import logic.Equipo;
import logic.Laboratorio;
import persistence.BajaJpaController;
import persistence.EntregaJpaController;
import persistence.EquipoJpaController;
import persistence.LaboratorioJpaController;
import persistence.MantenimientoJpaController;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author danil
 */
public class ControllerPersist {
    BajaJpaController BajaJPA = new BajaJpaController();
    EntregaJpaController EntregaJPA = new EntregaJpaController();
    EquipoJpaController EquipoJPA = new EquipoJpaController();
    LaboratorioJpaController LaboratorioJPA = new LaboratorioJpaController();
    MantenimientoJpaController MantenimientoJPA = new MantenimientoJpaController();

    // -----------------------------------------------------------
    // =======================EQUIPOS========================
    // -----------------------------------------------------------

    public void CreateEquipo(Equipo equipo) {
        EquipoJPA.create(equipo);
    }

    public void DeleteEquipo(int Id) {
        try {
            EquipoJPA.destroy(Id);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void EditEquipo(Equipo equipo) {
        try {
            EquipoJPA.edit(equipo);
        } catch (Exception ex) {
            Logger.getLogger(ControllerPersist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Equipo FindEquipo(int Id) {
        return EquipoJPA.findEquipo(Id);
    }

    public ArrayList<Equipo> FindAllEquipos() {
        List<Equipo> Equipos = EquipoJPA.findEquipoEntities();
        ArrayList<Equipo> ListadoEquipos = new ArrayList<Equipo>(Equipos);

        return ListadoEquipos;
    }

    // -----------------------------------------------------------
    // =======================LABORATORIOS========================
    // -----------------------------------------------------------

    public void CreateLaboratorio(Laboratorio laboratorio) {
        LaboratorioJPA.create(laboratorio);
    }

    public void DeleteLaboratorio(int Id) {
        try {
            LaboratorioJPA.destroy(Id);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void EditLaboratorio(Laboratorio laboratorio) {
        try {
            LaboratorioJPA.edit(laboratorio);
        } catch (Exception ex) {
            Logger.getLogger(ControllerPersist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Laboratorio FindLaboratorio(int Id) {
        return LaboratorioJPA.findLaboratorio(Id);
    }

    public ArrayList<Laboratorio> FindAllLaboratorios() {
        List<Laboratorio> Laboratorios = LaboratorioJPA.findLaboratorioEntities();
        ArrayList<Laboratorio> ListadoLaboratorios = new ArrayList<Laboratorio>(Laboratorios);

        return ListadoLaboratorios;
    }

    // -----------------------------------------------------------
    // =======================Entrega========================
    // -----------------------------------------------------------

    public void CreateEntrega(Entrega entrega) {
        EntregaJPA.create(entrega);
    }

    public void DeleteEntrega(int Id) {
        try {
            EntregaJPA.destroy(Id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControllerPersist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EditEntrega(Entrega entrega) {
        try {
            EntregaJPA.edit(entrega);
        } catch (Exception ex) {
            Logger.getLogger(ControllerPersist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Entrega FindEntrega(int Id) {
        return EntregaJPA.findEntrega(Id);
    }

    public ArrayList<Entrega> FindAllEntrega() {
        List<Entrega> Entregas = EntregaJPA.findEntregaEntities();
        ArrayList<Entrega> listEntregas = new ArrayList<>(Entregas);
        return listEntregas;
    }

}
