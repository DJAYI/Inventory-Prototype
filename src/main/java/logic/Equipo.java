/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author danil
 */
@Entity
@Table(name = "equipo")
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e"),
    @NamedQuery(name = "Equipo.findById", query = "SELECT e FROM Equipo e WHERE e.id = :id")})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "C\u00f3digo")
    private String código;
    @Basic(optional = false)
    @Lob
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Lob
    @Column(name = "Estado")
    private String estado;
    @Basic(optional = false)
    
    @Column(name = "FechaAdquisicion")
    @Temporal(TemporalType.DATE)
    private Date fechaAdquisicion;
    @Lob
    @Column(name = "Responsable")
    private String responsable;
    
    @Lob
    @Column(name = "Ubicacion")
    private String ubicacion;
    
    @Lob
    @Column(name = "Observaciones")
    private String observaciones;
    @JoinColumn(name = "LaboratorioID", referencedColumnName = "ID")
    @ManyToOne
    private Laboratorio laboratorioID;
    

    public Equipo() {
    }

    public Equipo(Integer id) {
        this.id = id;
    }

    public Equipo(Integer id, String código, String nombre, String estado, Date fechaAdquisicion, String responsable, String ubicacion, String observaciones, Laboratorio laboratorioID) {
        this.id = id;
        this.código = código;
        this.nombre = nombre;
        this.estado = estado;
        this.fechaAdquisicion = fechaAdquisicion;
        this.responsable = responsable;
        this.ubicacion = ubicacion;
        this.observaciones = observaciones;
        this.laboratorioID = laboratorioID;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCódigo() {
        return código;
    }

    public void setCódigo(String código) {
        this.código = código;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Laboratorio getLaboratorioID() {
        return laboratorioID;
    }

    public void setLaboratorioID(Laboratorio laboratorioID) {
        this.laboratorioID = laboratorioID;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Equipo{" + "id=" + id + ", c\u00f3digo=" + código + ", nombre=" + nombre + ", estado=" + estado + ", fechaAdquisicion=" + fechaAdquisicion + ", responsable=" + responsable + ", observaciones=" + observaciones + ", laboratorioID=" + laboratorioID +'}';
    }

    
}
