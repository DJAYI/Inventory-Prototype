/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.Controller;

import java.util.ArrayList;
import logic.Entrega;
import logic.Equipo;
import logic.Laboratorio;
import persistence.Controller.ControllerPersist;

/**
 *
 * @author danil
 */
public class Controller {
    
    //-----------------------------------------------------------
    //=======================EQUIPOS========================
    //-----------------------------------------------------------
    
    ControllerPersist cntrlPersistence = new ControllerPersist();
    public void CreateEquipo(Equipo equipo){
        cntrlPersistence.CreateEquipo(equipo);
    }
    
    public void DeleteEquipo(int Id){
        cntrlPersistence.DeleteEquipo(Id);
    }
    
    public void EditEquipo(Equipo equipo){
        cntrlPersistence.EditEquipo(equipo);
    }
    
    public Equipo FindEquipo(int Id){
        return cntrlPersistence.FindEquipo(Id);
    }
    
    public ArrayList<Equipo> FindAllEquipos(){
        return cntrlPersistence.FindAllEquipos();
    }
    
    
    //-----------------------------------------------------------
    //=======================ENTREGA========================
    //-----------------------------------------------------------
    
    public void CreateEntrega(Entrega entrega){
        cntrlPersistence.CreateEntrega(entrega);
    }
    
    public void DeleteEntrega(int Id){
        cntrlPersistence.DeleteEntrega(Id);
    }
    
    public void EditEntrega(Entrega entrega){
        cntrlPersistence.EditEntrega(entrega);
    }
    
    public Entrega FindEntrega(int Id){
        return cntrlPersistence.FindEntrega(Id);
    }
    
    public ArrayList<Entrega> FindAllEntregas(){
        return cntrlPersistence.FindAllEntrega();
    }
    
    
    //-----------------------------------------------------------
    //=======================Laboratorios========================
    //-----------------------------------------------------------
    
    public void CreateLaboratorio(Laboratorio laboratorio){
        cntrlPersistence.CreateLaboratorio(laboratorio);
    }
    
    public void DeleteLaboratorio(int Id){
        cntrlPersistence.DeleteLaboratorio(Id);
    }
    
    public void EditLaboratorio(Laboratorio laboratorio){
        cntrlPersistence.EditLaboratorio(laboratorio);
    }
    
    public Laboratorio FindLaboratorio(int Id){
        return cntrlPersistence.FindLaboratorio(Id);
    }
    
    public ArrayList<Laboratorio> FindAllLaboratorios(){
        return cntrlPersistence.FindAllLaboratorios();
    }
    
    
}
