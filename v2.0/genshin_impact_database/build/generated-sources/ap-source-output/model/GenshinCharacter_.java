package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Element;
import model.UserCharacterMark;
import model.WeaponType;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-13T07:15:43")
@StaticMetamodel(GenshinCharacter.class)
public class GenshinCharacter_ { 

    public static volatile SingularAttribute<GenshinCharacter, WeaponType> idWeaponType;
    public static volatile CollectionAttribute<GenshinCharacter, UserCharacterMark> userCharacterMarkCollection;
    public static volatile SingularAttribute<GenshinCharacter, Element> idElement;
    public static volatile SingularAttribute<GenshinCharacter, String> description;
    public static volatile SingularAttribute<GenshinCharacter, String> descriptionElementalBurst;
    public static volatile SingularAttribute<GenshinCharacter, String> elementalSkillName;
    public static volatile SingularAttribute<GenshinCharacter, String> descriptionElementalSkill;
    public static volatile SingularAttribute<GenshinCharacter, String> picture;
    public static volatile SingularAttribute<GenshinCharacter, String> descriptionNormalAttack;
    public static volatile SingularAttribute<GenshinCharacter, String> normalAttackName;
    public static volatile SingularAttribute<GenshinCharacter, String> pictureHd;
    public static volatile SingularAttribute<GenshinCharacter, String> name;
    public static volatile SingularAttribute<GenshinCharacter, Integer> id;
    public static volatile SingularAttribute<GenshinCharacter, String> elementalBurstName;

}