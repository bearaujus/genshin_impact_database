package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.ReedemCodeHistory;
import model.UserCharacterMark;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-13T07:15:43")
@StaticMetamodel(Dbuser.class)
public class Dbuser_ { 

    public static volatile SingularAttribute<Dbuser, String> password;
    public static volatile CollectionAttribute<Dbuser, UserCharacterMark> userCharacterMarkCollection;
    public static volatile CollectionAttribute<Dbuser, ReedemCodeHistory> reedemCodeHistoryCollection;
    public static volatile SingularAttribute<Dbuser, Short> isadmin;
    public static volatile SingularAttribute<Dbuser, Integer> id;
    public static volatile SingularAttribute<Dbuser, String> username;

}