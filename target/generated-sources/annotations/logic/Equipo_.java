package logic;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logic.Laboratorio;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-12T22:29:30", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Equipo.class)
public class Equipo_ { 

    public static volatile SingularAttribute<Equipo, Date> fechaAdquisicion;
    public static volatile SingularAttribute<Equipo, String> código;
    public static volatile SingularAttribute<Equipo, String> estado;
    public static volatile SingularAttribute<Equipo, String> ubicacion;
    public static volatile SingularAttribute<Equipo, String> responsable;
    public static volatile SingularAttribute<Equipo, String> observaciones;
    public static volatile SingularAttribute<Equipo, Integer> id;
    public static volatile SingularAttribute<Equipo, String> nombre;
    public static volatile SingularAttribute<Equipo, Laboratorio> laboratorioID;

}