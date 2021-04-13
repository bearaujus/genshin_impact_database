/* 
 * The MIT License
 *
 * Copyright 2021 Bear Au Jus - ジュースとくま.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package bean;

import controller.CharacterSB;
import controller.CodeSB;
import controller.ElementSB;
import controller.ReedemCodeHistorySB;
import controller.UserCharacterMarkSB;
import controller.UserSB;
import controller.WeaponTypeSB;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Code;
import model.Dbuser;
import model.Element;
import model.GenshinCharacter;
import model.ReedemCodeHistory;
import model.UserCharacterMark;
import model.WeaponType;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.PieChartModel;
import utill.Common;
import utill.Indexer;
import utill.ProjectInfo;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Named(value = "globalMB")
@SessionScoped
public class GlobalMB implements Serializable {

    @EJB
    CharacterSB characterSB;
    @EJB
    CodeSB codeSB;
    @EJB
    ElementSB elementSB;
    @EJB
    ReedemCodeHistorySB reedemCodeSB;
    @EJB
    UserCharacterMarkSB userCharacterMarkSB;
    @EJB
    UserSB userSB;
    @EJB
    WeaponTypeSB weaponTypeSB;

    private String indexer, confirm_password, tmp, filterBase, verifier_file, verifier_voucher;
    private Boolean isChangePassword;
    private Dbuser user;
    private Element element;
    private WeaponType weaponType;
    private GenshinCharacter character;
    private Code code;
    private UserCharacterMark userCharacterMark;
    private ReedemCodeHistory reedemCodeHistory;
    private HashMap<Integer, Boolean> filterElement, filterWeaponType;
    private List<Integer> userRedeemedCodeMapped;
    private ProjectInfo projectInfo;

    public GlobalMB() {
        projectInfo = new ProjectInfo();
    }

    public void redirect_login() {
        user = new Dbuser();
        indexer = Indexer.LOGIN;
    }

    public void redirect_register() {
        user = new Dbuser();
        confirm_password = "";
        indexer = Indexer.REGISTER;
    }

    public void redirect_profile() {
        isChangePassword = false;
        indexer = Indexer.PROFILE;
    }

    public void redirect_home() {
        indexer = userSB.getLoggedUser().getIsadmin() ? Indexer.ADMIN_HOME : Indexer.USER_HOME;
    }

    public void redirect_element() {
        element = new Element();
        indexer = userSB.getLoggedUser().getIsadmin() ? Indexer.ADMIN_ELEMENT : Indexer.USER_ELEMENT;
    }

    public void redirect_weapon_type() {
        weaponType = new WeaponType();
        indexer = userSB.getLoggedUser().getIsadmin() ? Indexer.ADMIN_WEAPON_TYPE : Indexer.USER_WEAPON_TYPE;
    }

    public void redirect_character() {
        if (userSB.getLoggedUser().getIsadmin()) {
            indexer = Indexer.ADMIN_CHARACTER;
            character = new GenshinCharacter();
            character.setNormalAttackName("-");
            character.setDescriptionNormalAttack("-");
            character.setElementalSkillName("-");
            character.setDescriptionElementalSkill("-");
            character.setElementalBurstName("-");
            character.setDescriptionElementalBurst("-");
            character.setIdElement(new Element());
            character.setIdWeaponType(new WeaponType());
        } else {
            indexer = Indexer.USER_CHARACTER;
            filterElement = elementSB.getDataFilterModel();
            filterWeaponType = weaponTypeSB.getDataFilterModel();
            filterBase = Common.FILTER_ALL;
            verifier_file = "";
            verifier_voucher = "";
        }
    }

    public void redirect_voucher_code() {
        if (userSB.getLoggedUser().getIsadmin()) {
            indexer = Indexer.ADMIN_VOUCHER_CODE;
            code = new Code();
            code.setPostedDate(Common.getCurrDate());
        } else {
            indexer = Indexer.USER_VOUCHER_CODE;
            filterBase = "reedem_code";
            reedemCodeHistory = new ReedemCodeHistory();
            userRedeemedCodeMapped = reedemCodeSB.getUserDataCodeMapped(userSB.getLoggedUser());
        }
    }

    public void register() {
        if (userSB.isUserAlreadyExist(user)) {
            redirect_register();
            addMessage(FacesMessage.SEVERITY_INFO, "Register Info", "Fail to register, username already exist");
        } else {
            user.setIsadmin(false);
            userSB.insert(user);
            redirect_login();
            addMessage(FacesMessage.SEVERITY_INFO, "Register Info", "Register success !");
        }
    }

    public void login() {
        if (userSB.Login(user)) {
            redirect_home();
            addMessage(FacesMessage.SEVERITY_INFO, "Login Info", "Login success !");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Login Info", "Username / password is not exist");
        }
    }

    public void logout() {
        userSB.logout();
        redirect_login();
        addMessage(FacesMessage.SEVERITY_INFO, "Logout Info", "Logout success !");
    }

    public void insertElement() {
        elementSB.insert(element);
        addMessage(FacesMessage.SEVERITY_INFO, "Manage Element", "Element data has been saved !");
        redirect_element();
    }

    public void updateElement() {
        elementSB.update(element);
        addMessage(FacesMessage.SEVERITY_INFO, "Manage Element", "Element data has been updated !");
        redirect_element();
    }

    public void deleteElement(Element e) {
        elementSB.delete(e);
        addMessage(FacesMessage.SEVERITY_INFO, "Manage Element", "Element data has been deleted !");
        redirect_element();
    }

    public void selectElement(Element e) {
        element = e;
    }

    public void insertWeaponType() {
        weaponTypeSB.insert(weaponType);
        addMessage(FacesMessage.SEVERITY_INFO, "Manage Weapon Type", "Weapon Type data has been saved !");
        redirect_weapon_type();
    }

    public void updateWeaponType() {
        weaponTypeSB.update(weaponType);
        addMessage(FacesMessage.SEVERITY_INFO, "Manage Weapon Type", "Weapon Type data has been updated !");
        redirect_weapon_type();
    }

    public void deleteWeaponType(WeaponType wt) {
        weaponTypeSB.delete(wt);
        addMessage(FacesMessage.SEVERITY_INFO, "Manage Weapon Type", "Weapon Type data has been deleted !");
        redirect_weapon_type();
    }

    public void selectWeaponType(WeaponType wt) {
        weaponType = wt;
    }

    public void insertCharacter() {
        characterSB.insert(character);
        addMessage(FacesMessage.SEVERITY_INFO, "Manage Character", "Character data has been saved !");
        redirect_character();
    }

    public void updateCharacter() {
        characterSB.update(character);
        addMessage(FacesMessage.SEVERITY_INFO, "Manage Character", "Character data has been updated !");
        redirect_character();
    }

    public void deleteCharacter(GenshinCharacter c) {
        characterSB.delete(c);
        addMessage(FacesMessage.SEVERITY_INFO, "Manage Character", "Character data has been deleted !");
        redirect_character();
    }

    public void selectCharacter(GenshinCharacter c) {
        character = c;
    }

    public void viewCharacterSkill(GenshinCharacter c) {
        StringBuilder sb = new StringBuilder();
        sb.append("Character : ").append(c.getName()).append("\n").append(Common.getDialogSeparator("-", 269)).append("\n\n");
        sb.append("= BASIC ATTACK =").append("\n").append(c.getNormalAttackName()).append(" | ").append(c.getDescriptionNormalAttack()).append("\n\n");
        sb.append("= ELEMENTAL SKILL =").append("\n").append(c.getElementalSkillName()).append(" | ").append(c.getDescriptionElementalSkill()).append("\n\n");
        sb.append("= ELEMENTAL BURST =").append("\n").append(c.getElementalBurstName()).append(" | ").append(c.getDescriptionElementalBurst());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Skill Details", sb.toString());
        PrimeFaces.current().dialog().showMessageDynamic(message, true);
    }

    public void viewCharacterPicture(GenshinCharacter c) {
        tmp = c.getPictureHd();
    }

    public void viewManageProfilePassword() {
        StringBuilder sb = new StringBuilder();
        sb.append("Details").append("\n").append(Common.getDialogSeparator("-", 272)).append("\n\n");
        sb.append("Secret : '").append(userSB.getLoggedUser().getPassword()).append("'\n\n").append("*Note\nDo not share this with anyone !");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Password Info", sb.toString());
        PrimeFaces.current().dialog().showMessageDynamic(message, true);
    }

    public void manageProfileChangePassword() {
        user = new Dbuser();
        confirm_password = "";
        isChangePassword = true;
    }

    public void manageConfirmChangePassword() {
        isChangePassword = false;
        Dbuser u = userSB.getLoggedUser();
        u.setPassword(user.getPassword());
        userSB.update(u);
        userSB.login(u.getUsername(), u.getPassword());
        addMessage(FacesMessage.SEVERITY_INFO, "Manage Profile", "Password has been changed !");
        redirect_profile();
    }

    public void insertCode() {
        codeSB.insert(code);
        addMessage(FacesMessage.SEVERITY_INFO, "Manage Voucher Code", "Voucher Code data has been saved !");
        redirect_voucher_code();
    }

    public void updateCode() {
        codeSB.update(code);
        addMessage(FacesMessage.SEVERITY_INFO, "Manage Voucher Code", "Voucher Code data has been updated !");
        redirect_voucher_code();
    }

    public void deleteCode(Code c) {
        codeSB.delete(c);
        addMessage(FacesMessage.SEVERITY_INFO, "Manage Voucher Code", "Voucher Code data has been deleted !");
        redirect_voucher_code();
    }

    public void selectCode(Code c) {
        code = c;
    }

    public List<String> getWeaponBannerLocations() {
        List output = Common.getArrBannerLocation(Indexer.getImageRootPath() + "banner/weapon/", 9, "jpg");
        output = Common.shuffleCollection(output);
        return output;
    }

    public List<String> getCharacterBannerLocations() {
        List output = Common.getArrBannerLocation(Indexer.getImageRootPath() + "banner/character/", 10, "jpg");
        output = Common.shuffleCollection(output);
        return output;
    }

    public List<String> getAllBannerLocations() {
        List<String> output = getCharacterBannerLocations();
        output.addAll(getWeaponBannerLocations());
        output = Common.shuffleCollection(output);
        return output;
    }

    public void showCharacterUserHomeList(GenshinCharacter c) {
        userCharacterMark = userCharacterMarkSB.getData(c, userSB.getLoggedUser());
        PrimeFaces.current().executeScript("PF('show_user_home_character_list').show()");
    }

    public void markCharacterAsOwn() {
        userCharacterMark.setIsown(true);
        userCharacterMarkSB.update(userCharacterMark);
        addMessage(FacesMessage.SEVERITY_INFO, "Character Mark Info", userCharacterMark.getIdCharacter().getName() + " has successfully marked as owned character");
    }

    public void markCharacterAsNotOwned() {
        userCharacterMark.setIsown(false);
        userCharacterMarkSB.update(userCharacterMark);
        addMessage(FacesMessage.SEVERITY_INFO, "Character Mark Info", userCharacterMark.getIdCharacter().getName() + " has successfully unmarked");
    }

    public void markCharacterAsNotOwned(GenshinCharacter c) {
        userCharacterMark = userCharacterMarkSB.getData(c, userSB.getLoggedUser());
        userCharacterMark.setIsown(false);
        userCharacterMarkSB.update(userCharacterMark);
        addMessage(FacesMessage.SEVERITY_INFO, "Character Mark Info", userCharacterMark.getIdCharacter().getName() + " has successfully unmarked");
    }

    public void selectElementFilter(Element e) {
        final Boolean x = filterElement.get(e.getId());
        if (x) {
            filterElement.replace(e.getId(), false);
        } else {
            filterElement.replace(e.getId(), true);
        }
    }

    public void selectWeaponTypeFilter(WeaponType wt) {
        final Boolean x = filterWeaponType.get(wt.getId());
        if (x) {
            filterWeaponType.replace(wt.getId(), false);
        } else {
            filterWeaponType.replace(wt.getId(), true);
        }
    }

    public List<GenshinCharacter> getAllCharacterModelFilter() {
        List<UserCharacterMark> ucm = userCharacterMarkSB.getOwnedCharacerMark(userSB.getLoggedUser());
        List<Integer> gc = new ArrayList<>();
        for (UserCharacterMark i : ucm) {
            gc.add(new GenshinCharacter(i.getIdCharacter().getId()).getId());
        }
        return characterSB.getAllDataFiltered(filterElement, filterWeaponType, filterBase, gc);
    }

    public PieChartModel getUserReportElementPieChart() {
        PieChartModel output = new PieChartModel();

        HashMap<String, Integer> dataPair = elementSB.getChartDataPair();
        for (UserCharacterMark i : userCharacterMarkSB.getOwnedCharacerMark(userSB.getLoggedUser())) {
            Element elm = i.getIdCharacter().getIdElement();
            dataPair.replace(elm.getName(), dataPair.get(elm.getName()) + 1);
        }
        for (Map.Entry<String, Integer> i : dataPair.entrySet()) {
            output.set(i.getKey(), i.getValue());
        }
        output.setTitle("Simple Pie");
        output.setLegendPosition("w");
        output.setShadow(false);
        return output;
    }

    public Integer getCharacterOwnedPercentage() {
        return Common.calculatePercentage(userCharacterMarkSB.getOwnedCharacerMark(userSB.getLoggedUser()).size(), characterSB.getAllData().size());
    }

    public Integer getWeaponOwnderPercentage() {
        return 0;
    }

    public void reedemCode(Code c) {
        if (codeSB.reedem(c)) {
            reedemCodeHistory.setIdCode(c);
            reedemCodeHistory.setIdUser(userSB.getLoggedUser());
            reedemCodeHistory.setReedemDate(Common.getCurrDate());
            reedemCodeSB.insert(reedemCodeHistory);
            redirect_voucher_code();
        }
        addMessage(FacesMessage.SEVERITY_INFO, "Code Redemption Info", "Voucher ID - " + String.valueOf(c.getId()) + " has successfully redeemed");
    }

    public Boolean isCodeReedemed(Code c) {
        return userRedeemedCodeMapped.contains(c.getId());
    }

    public StreamedContent getDownloadVoucherData(ReedemCodeHistory rch) {
        StreamedContent file;
        try {
            String oFName = rch.getIdCode().getId().toString() + "-" + rch.getId().toString() + ".txt";
            InputStream targetStream = new ByteArrayInputStream(rch.getIdCode().getCode().getBytes());
            file = new DefaultStreamedContent(targetStream, "text/plain", oFName);
        } catch (Exception e) {
            file = null;
        }
        return file;
    }

    public void voucherMD5CopySuccessNotification(Code c) {
        addMessage(FacesMessage.SEVERITY_INFO, "Info Voucher ID - " + c.getId(), " MD5 Copied to clipboard");
    }

    public void verifyMD5() {
        if (verifier_file.equals(verifier_voucher)) {
            addMessage(FacesMessage.SEVERITY_INFO, "MD5 Verifier Info", "Hash Matched");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "MD5 Verifier Info", "Hash Unmatched");
        }
        verifier_file = "";
        verifier_voucher = "";
    }

    public StreamedContent getDownloadReedemedCodeUserListData(Code c) {
        StreamedContent file;
        try {
            String oFName = "[" + Common.getCurrDate().toString() + "][VC-" + c.getCode() + "] List Reedemed User.txt";
            InputStream targetStream = new ByteArrayInputStream(reedemCodeSB.getListUserReedemedCodeData(c).getBytes());
            file = new DefaultStreamedContent(targetStream, "text/plain", oFName);
        } catch (Exception e) {
            file = null;
        }
        return file;
    }

    public String getIndexer() {
        return indexer;
    }

    public void setIndexer(String indexer) {
        this.indexer = indexer;
    }

    public Dbuser getUser() {
        return user;
    }

    public void setUser(Dbuser user) {
        this.user = user;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public GenshinCharacter getCharacter() {
        return character;
    }

    public void setCharacter(GenshinCharacter character) {
        this.character = character;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public Boolean getIsChangePassword() {
        return isChangePassword;
    }

    public void setIsChangePassword(Boolean isChangePassword) {
        this.isChangePassword = isChangePassword;
    }

    public void addMessage(FacesMessage.Severity facesMessage_Severity, String title, String detail) {
        FacesMessage message = new FacesMessage(facesMessage_Severity, title, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public UserSB getUserSB() {
        return userSB;
    }

    public void setUserSB(UserSB userSB) {
        this.userSB = userSB;
    }

    public CharacterSB getCharacterSB() {
        return characterSB;
    }

    public void setCharacterSB(CharacterSB characterSB) {
        this.characterSB = characterSB;
    }

    public CodeSB getCodeSB() {
        return codeSB;
    }

    public void setCodeSB(CodeSB codeSB) {
        this.codeSB = codeSB;
    }

    public ElementSB getElementSB() {
        return elementSB;
    }

    public void setElementSB(ElementSB elementSB) {
        this.elementSB = elementSB;
    }

    public ReedemCodeHistorySB getReedemCodeSB() {
        return reedemCodeSB;
    }

    public void setReedemCodeSB(ReedemCodeHistorySB reedemCodeSB) {
        this.reedemCodeSB = reedemCodeSB;
    }

    public UserCharacterMarkSB getUserCharacterMarkSB() {
        return userCharacterMarkSB;
    }

    public void setUserCharacterMarkSB(UserCharacterMarkSB userCharacterMarkSB) {
        this.userCharacterMarkSB = userCharacterMarkSB;
    }

    public WeaponTypeSB getWeaponTypeSB() {
        return weaponTypeSB;
    }

    public void setWeaponTypeSB(WeaponTypeSB weaponTypeSB) {
        this.weaponTypeSB = weaponTypeSB;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public UserCharacterMark getUserCharacterMark() {
        return userCharacterMark;
    }

    public void setUserCharacterMark(UserCharacterMark userCharacterMark) {
        this.userCharacterMark = userCharacterMark;
    }

    public HashMap getFilterElement() {
        return filterElement;
    }

    public void setFilterElement(HashMap filterElement) {
        this.filterElement = filterElement;
    }

    public HashMap getFilterWeaponType() {
        return filterWeaponType;
    }

    public void setFilterWeaponType(HashMap filterWeaponType) {
        this.filterWeaponType = filterWeaponType;
    }

    public String getFilterBase() {
        return filterBase;
    }

    public void setFilterBase(String filterBase) {
        this.filterBase = filterBase;
    }

    public String getVerifier_file() {
        return verifier_file;
    }

    public void setVerifier_file(String verifier_file) {
        this.verifier_file = verifier_file;
    }

    public String getVerifier_voucher() {
        return verifier_voucher;
    }

    public void setVerifier_voucher(String verifier_voucher) {
        this.verifier_voucher = verifier_voucher;
    }

    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

}
