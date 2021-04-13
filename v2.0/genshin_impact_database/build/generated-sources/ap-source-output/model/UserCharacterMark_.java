package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Dbuser;
import model.GenshinCharacter;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-13T07:15:43")
@StaticMetamodel(UserCharacterMark.class)
public class UserCharacterMark_ { 

    public static volatile SingularAttribute<UserCharacterMark, Dbuser> idUser;
    public static volatile SingularAttribute<UserCharacterMark, Short> isown;
    public static volatile SingularAttribute<UserCharacterMark, GenshinCharacter> idCharacter;
    public static volatile SingularAttribute<UserCharacterMark, Integer> id;

}