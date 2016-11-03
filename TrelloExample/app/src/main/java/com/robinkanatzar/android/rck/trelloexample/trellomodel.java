package com.robinkanatzar.android.rck.trelloexample;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class trellomodel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("avatarHash")
    @Expose
    private String avatarHash;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("bioData")
    @Expose
    private Object bioData;
    @SerializedName("confirmed")
    @Expose
    private Boolean confirmed;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("idPremOrgsAdmin")
    @Expose
    private Object idPremOrgsAdmin;
    @SerializedName("initials")
    @Expose
    private String initials;
    @SerializedName("memberType")
    @Expose
    private String memberType;
    @SerializedName("products")
    @Expose
    private List<Object> products = new ArrayList<Object>();
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("avatarSource")
    @Expose
    private Object avatarSource;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("gravatarHash")
    @Expose
    private Object gravatarHash;
    @SerializedName("idBoards")
    @Expose
    private List<Object> idBoards = new ArrayList<Object>();
    @SerializedName("idEnterprise")
    @Expose
    private Object idEnterprise;
    @SerializedName("idOrganizations")
    @Expose
    private List<Object> idOrganizations = new ArrayList<Object>();
    @SerializedName("loginTypes")
    @Expose
    private Object loginTypes;
    @SerializedName("oneTimeMessagesDismissed")
    @Expose
    private Object oneTimeMessagesDismissed;
    @SerializedName("prefs")
    @Expose
    private Object prefs;
    @SerializedName("trophies")
    @Expose
    private List<Object> trophies = new ArrayList<Object>();
    @SerializedName("uploadedAvatarHash")
    @Expose
    private Object uploadedAvatarHash;
    @SerializedName("premiumFeatures")
    @Expose
    private List<Object> premiumFeatures = new ArrayList<Object>();
    @SerializedName("idBoardsPinned")
    @Expose
    private Object idBoardsPinned;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The avatarHash
     */
    public String getAvatarHash() {
        return avatarHash;
    }

    /**
     *
     * @param avatarHash
     * The avatarHash
     */
    public void setAvatarHash(String avatarHash) {
        this.avatarHash = avatarHash;
    }

    /**
     *
     * @return
     * The bio
     */
    public String getBio() {
        return bio;
    }

    /**
     *
     * @param bio
     * The bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     *
     * @return
     * The bioData
     */
    public Object getBioData() {
        return bioData;
    }

    /**
     *
     * @param bioData
     * The bioData
     */
    public void setBioData(Object bioData) {
        this.bioData = bioData;
    }

    /**
     *
     * @return
     * The confirmed
     */
    public Boolean getConfirmed() {
        return confirmed;
    }

    /**
     *
     * @param confirmed
     * The confirmed
     */
    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    /**
     *
     * @return
     * The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @param fullName
     * The fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     *
     * @return
     * The idPremOrgsAdmin
     */
    public Object getIdPremOrgsAdmin() {
        return idPremOrgsAdmin;
    }

    /**
     *
     * @param idPremOrgsAdmin
     * The idPremOrgsAdmin
     */
    public void setIdPremOrgsAdmin(Object idPremOrgsAdmin) {
        this.idPremOrgsAdmin = idPremOrgsAdmin;
    }

    /**
     *
     * @return
     * The initials
     */
    public String getInitials() {
        return initials;
    }

    /**
     *
     * @param initials
     * The initials
     */
    public void setInitials(String initials) {
        this.initials = initials;
    }

    /**
     *
     * @return
     * The memberType
     */
    public String getMemberType() {
        return memberType;
    }

    /**
     *
     * @param memberType
     * The memberType
     */
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    /**
     *
     * @return
     * The products
     */
    public List<Object> getProducts() {
        return products;
    }

    /**
     *
     * @param products
     * The products
     */
    public void setProducts(List<Object> products) {
        this.products = products;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The avatarSource
     */
    public Object getAvatarSource() {
        return avatarSource;
    }

    /**
     *
     * @param avatarSource
     * The avatarSource
     */
    public void setAvatarSource(Object avatarSource) {
        this.avatarSource = avatarSource;
    }

    /**
     *
     * @return
     * The email
     */
    public Object getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(Object email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The gravatarHash
     */
    public Object getGravatarHash() {
        return gravatarHash;
    }

    /**
     *
     * @param gravatarHash
     * The gravatarHash
     */
    public void setGravatarHash(Object gravatarHash) {
        this.gravatarHash = gravatarHash;
    }

    /**
     *
     * @return
     * The idBoards
     */
    public List<Object> getIdBoards() {
        return idBoards;
    }

    /**
     *
     * @param idBoards
     * The idBoards
     */
    public void setIdBoards(List<Object> idBoards) {
        this.idBoards = idBoards;
    }

    /**
     *
     * @return
     * The idEnterprise
     */
    public Object getIdEnterprise() {
        return idEnterprise;
    }

    /**
     *
     * @param idEnterprise
     * The idEnterprise
     */
    public void setIdEnterprise(Object idEnterprise) {
        this.idEnterprise = idEnterprise;
    }

    /**
     *
     * @return
     * The idOrganizations
     */
    public List<Object> getIdOrganizations() {
        return idOrganizations;
    }

    /**
     *
     * @param idOrganizations
     * The idOrganizations
     */
    public void setIdOrganizations(List<Object> idOrganizations) {
        this.idOrganizations = idOrganizations;
    }

    /**
     *
     * @return
     * The loginTypes
     */
    public Object getLoginTypes() {
        return loginTypes;
    }

    /**
     *
     * @param loginTypes
     * The loginTypes
     */
    public void setLoginTypes(Object loginTypes) {
        this.loginTypes = loginTypes;
    }

    /**
     *
     * @return
     * The oneTimeMessagesDismissed
     */
    public Object getOneTimeMessagesDismissed() {
        return oneTimeMessagesDismissed;
    }

    /**
     *
     * @param oneTimeMessagesDismissed
     * The oneTimeMessagesDismissed
     */
    public void setOneTimeMessagesDismissed(Object oneTimeMessagesDismissed) {
        this.oneTimeMessagesDismissed = oneTimeMessagesDismissed;
    }

    /**
     *
     * @return
     * The prefs
     */
    public Object getPrefs() {
        return prefs;
    }

    /**
     *
     * @param prefs
     * The prefs
     */
    public void setPrefs(Object prefs) {
        this.prefs = prefs;
    }

    /**
     *
     * @return
     * The trophies
     */
    public List<Object> getTrophies() {
        return trophies;
    }

    /**
     *
     * @param trophies
     * The trophies
     */
    public void setTrophies(List<Object> trophies) {
        this.trophies = trophies;
    }

    /**
     *
     * @return
     * The uploadedAvatarHash
     */
    public Object getUploadedAvatarHash() {
        return uploadedAvatarHash;
    }

    /**
     *
     * @param uploadedAvatarHash
     * The uploadedAvatarHash
     */
    public void setUploadedAvatarHash(Object uploadedAvatarHash) {
        this.uploadedAvatarHash = uploadedAvatarHash;
    }

    /**
     *
     * @return
     * The premiumFeatures
     */
    public List<Object> getPremiumFeatures() {
        return premiumFeatures;
    }

    /**
     *
     * @param premiumFeatures
     * The premiumFeatures
     */
    public void setPremiumFeatures(List<Object> premiumFeatures) {
        this.premiumFeatures = premiumFeatures;
    }

    /**
     *
     * @return
     * The idBoardsPinned
     */
    public Object getIdBoardsPinned() {
        return idBoardsPinned;
    }

    /**
     *
     * @param idBoardsPinned
     * The idBoardsPinned
     */
    public void setIdBoardsPinned(Object idBoardsPinned) {
        this.idBoardsPinned = idBoardsPinned;
    }
}
