package logic;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logic.Equipo;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-12T22:29:30", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Laboratorio.class)
public class Laboratorio_ { 

    public static volatile ListAttribute<Laboratorio, Equipo> equipoID;
    public static volatile SingularAttribute<Laboratorio, String> código;
    public static volatile SingularAttribute<Laboratorio, Integer> id;
    public static volatile SingularAttribute<Laboratorio, String> nombre;

}