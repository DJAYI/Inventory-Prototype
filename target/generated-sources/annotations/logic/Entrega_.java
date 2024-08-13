package logic;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logic.Equipo;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-12T22:29:30", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Entrega.class)
public class Entrega_ { 

    public static volatile SingularAttribute<Entrega, Equipo> equipoID;
    public static volatile SingularAttribute<Entrega, String> fechaDevolucion;
    public static volatile SingularAttribute<Entrega, String> fechaEntrega;
    public static volatile SingularAttribute<Entrega, String> observaciones;
    public static volatile SingularAttribute<Entrega, String> docente;
    public static volatile SingularAttribute<Entrega, Integer> id;

}