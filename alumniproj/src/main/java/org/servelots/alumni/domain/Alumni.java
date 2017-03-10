package org.servelots.alumni.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Alumni.
 */
@Entity
@Table(name = "alumni")
@Document(indexName = "alumni")
public class Alumni implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "cornell_alumni_id_number", nullable = false)
    private String cornellAlumniIDNumber;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "college_or_school")
    private String collegeOrSchool;

    @Column(name = "major")
    private String major;

    @Column(name = "place")
    private String place;

    @Column(name = "country")
    private String country;

    @Column(name = "living_city")
    private String livingCity;

    @Column(name = "where_live_now")
    private String whereLiveNow;

    @Column(name = "current_profile_link")
    private String currentProfileLink;

    @Column(name = "life_bullet_1")
    private String life_Bullet_1;

    @Column(name = "life_bullet_2")
    private String life_Bullet_2;

    @Column(name = "life_bullet_3")
    private String life_Bullet_3;

    @Column(name = "life_bullet_4")
    private String life_Bullet_4;

    @Column(name = "life_bullet_5")
    private String life_Bullet_5;

    @Column(name = "life_bullet_6")
    private String life_Bullet_6;

    @Column(name = "life_bullet_7")
    private String life_Bullet_7;

    @Column(name = "life_bullet_8")
    private String life_Bullet_8;

    @Column(name = "life_bullet_9")
    private String life_Bullet_9;

    @Column(name = "life_bullet_10")
    private String life_Bullet_10;

    @Column(name = "after_grad_bullet_1")
    private String after_Grad_Bullet_1;

    @Column(name = "after_grad_bullet_2")
    private String after_Grad_Bullet_2;

    @Column(name = "after_grad_bullet_3")
    private String after_Grad_Bullet_3;

    @Column(name = "after_grad_bullet_4")
    private String after_Grad_Bullet_4;

    @Column(name = "after_grad_bullet_5")
    private String after_Grad_Bullet_5;

    @Column(name = "after_grad_bullet_6")
    private String after_Grad_Bullet_6;

    @Column(name = "after_grad_bullet_7")
    private String after_Grad_Bullet_7;

    @Column(name = "after_grad_bullet_8")
    private String after_Grad_Bullet_8;

    @Column(name = "after_grad_bullet_9")
    private String after_Grad_Bullet_9;

    @Column(name = "after_grad_bullet_10")
    private String after_Grad_Bullet_10;

    @Column(name = "primary_email")
    private String primaryEmail;

    @Column(name = "alternate_email")
    private String alternateEmail;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "secondary_address")
    private String secondaryAddress;

    @Column(name = "telephone_mobile")
    private String telephoneMobile;

    @Column(name = "telephone_landline")
    private String telephoneLandline;

    @Column(name = "telephone_other")
    private String telephoneOther;

    @Column(name = "personal_web_page_1")
    private String personalWebPage1;

    @Column(name = "personal_web_page_2")
    private String personalWebPage2;

    @Column(name = "blog_1")
    private String blog1;

    @Column(name = "blog_2")
    private String blog2;

    @Column(name = "facebook_link")
    private String facebookLink;

    @Column(name = "twitter")
    private String twitter;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "you_tube")
    private String youTube;

    @Column(name = "vimeo")
    private String vimeo;

    @Column(name = "bandcamp")
    private String bandcamp;

    @Column(name = "other_social_media")
    private String otherSocialMedia;

    @Column(name = "other_url")
    private String otherUrl;

    @Column(name = "other_social_media_2")
    private String otherSocialMedia2;

    @Column(name = "other_url_2")
    private String otherURL2;

    @Column(name = "job")
    private String job;

    @Column(name = "company")
    private String company;

    @Column(name = "company_url")
    private String companyURL;

    @Column(name = "work_address")
    private String workAddress;

    @Column(name = "work_email")
    private String workEmail;

    @Column(name = "work_telephone")
    private String workTelephone;

    @Column(name = "upload_cvurl")
    private String uploadCVURL;

    @Column(name = "maiden_name")
    private String maidenName;

    @OneToOne
    @JoinColumn(unique = true)
    private User alumniuser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCornellAlumniIDNumber() {
        return cornellAlumniIDNumber;
    }

    public Alumni cornellAlumniIDNumber(String cornellAlumniIDNumber) {
        this.cornellAlumniIDNumber = cornellAlumniIDNumber;
        return this;
    }

    public void setCornellAlumniIDNumber(String cornellAlumniIDNumber) {
        this.cornellAlumniIDNumber = cornellAlumniIDNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public Alumni firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Alumni middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Alumni lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCollegeOrSchool() {
        return collegeOrSchool;
    }

    public Alumni collegeOrSchool(String collegeOrSchool) {
        this.collegeOrSchool = collegeOrSchool;
        return this;
    }

    public void setCollegeOrSchool(String collegeOrSchool) {
        this.collegeOrSchool = collegeOrSchool;
    }

    public String getMajor() {
        return major;
    }

    public Alumni major(String major) {
        this.major = major;
        return this;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPlace() {
        return place;
    }

    public Alumni place(String place) {
        this.place = place;
        return this;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCountry() {
        return country;
    }

    public Alumni country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLivingCity() {
        return livingCity;
    }

    public Alumni livingCity(String livingCity) {
        this.livingCity = livingCity;
        return this;
    }

    public void setLivingCity(String livingCity) {
        this.livingCity = livingCity;
    }

    public String getWhereLiveNow() {
        return whereLiveNow;
    }

    public Alumni whereLiveNow(String whereLiveNow) {
        this.whereLiveNow = whereLiveNow;
        return this;
    }

    public void setWhereLiveNow(String whereLiveNow) {
        this.whereLiveNow = whereLiveNow;
    }

    public String getCurrentProfileLink() {
        return currentProfileLink;
    }

    public Alumni currentProfileLink(String currentProfileLink) {
        this.currentProfileLink = currentProfileLink;
        return this;
    }

    public void setCurrentProfileLink(String currentProfileLink) {
        this.currentProfileLink = currentProfileLink;
    }

    public String getLife_Bullet_1() {
        return life_Bullet_1;
    }

    public Alumni life_Bullet_1(String life_Bullet_1) {
        this.life_Bullet_1 = life_Bullet_1;
        return this;
    }

    public void setLife_Bullet_1(String life_Bullet_1) {
        this.life_Bullet_1 = life_Bullet_1;
    }

    public String getLife_Bullet_2() {
        return life_Bullet_2;
    }

    public Alumni life_Bullet_2(String life_Bullet_2) {
        this.life_Bullet_2 = life_Bullet_2;
        return this;
    }

    public void setLife_Bullet_2(String life_Bullet_2) {
        this.life_Bullet_2 = life_Bullet_2;
    }

    public String getLife_Bullet_3() {
        return life_Bullet_3;
    }

    public Alumni life_Bullet_3(String life_Bullet_3) {
        this.life_Bullet_3 = life_Bullet_3;
        return this;
    }

    public void setLife_Bullet_3(String life_Bullet_3) {
        this.life_Bullet_3 = life_Bullet_3;
    }

    public String getLife_Bullet_4() {
        return life_Bullet_4;
    }

    public Alumni life_Bullet_4(String life_Bullet_4) {
        this.life_Bullet_4 = life_Bullet_4;
        return this;
    }

    public void setLife_Bullet_4(String life_Bullet_4) {
        this.life_Bullet_4 = life_Bullet_4;
    }

    public String getLife_Bullet_5() {
        return life_Bullet_5;
    }

    public Alumni life_Bullet_5(String life_Bullet_5) {
        this.life_Bullet_5 = life_Bullet_5;
        return this;
    }

    public void setLife_Bullet_5(String life_Bullet_5) {
        this.life_Bullet_5 = life_Bullet_5;
    }

    public String getLife_Bullet_6() {
        return life_Bullet_6;
    }

    public Alumni life_Bullet_6(String life_Bullet_6) {
        this.life_Bullet_6 = life_Bullet_6;
        return this;
    }

    public void setLife_Bullet_6(String life_Bullet_6) {
        this.life_Bullet_6 = life_Bullet_6;
    }

    public String getLife_Bullet_7() {
        return life_Bullet_7;
    }

    public Alumni life_Bullet_7(String life_Bullet_7) {
        this.life_Bullet_7 = life_Bullet_7;
        return this;
    }

    public void setLife_Bullet_7(String life_Bullet_7) {
        this.life_Bullet_7 = life_Bullet_7;
    }

    public String getLife_Bullet_8() {
        return life_Bullet_8;
    }

    public Alumni life_Bullet_8(String life_Bullet_8) {
        this.life_Bullet_8 = life_Bullet_8;
        return this;
    }

    public void setLife_Bullet_8(String life_Bullet_8) {
        this.life_Bullet_8 = life_Bullet_8;
    }

    public String getLife_Bullet_9() {
        return life_Bullet_9;
    }

    public Alumni life_Bullet_9(String life_Bullet_9) {
        this.life_Bullet_9 = life_Bullet_9;
        return this;
    }

    public void setLife_Bullet_9(String life_Bullet_9) {
        this.life_Bullet_9 = life_Bullet_9;
    }

    public String getLife_Bullet_10() {
        return life_Bullet_10;
    }

    public Alumni life_Bullet_10(String life_Bullet_10) {
        this.life_Bullet_10 = life_Bullet_10;
        return this;
    }

    public void setLife_Bullet_10(String life_Bullet_10) {
        this.life_Bullet_10 = life_Bullet_10;
    }

    public String getAfter_Grad_Bullet_1() {
        return after_Grad_Bullet_1;
    }

    public Alumni after_Grad_Bullet_1(String after_Grad_Bullet_1) {
        this.after_Grad_Bullet_1 = after_Grad_Bullet_1;
        return this;
    }

    public void setAfter_Grad_Bullet_1(String after_Grad_Bullet_1) {
        this.after_Grad_Bullet_1 = after_Grad_Bullet_1;
    }

    public String getAfter_Grad_Bullet_2() {
        return after_Grad_Bullet_2;
    }

    public Alumni after_Grad_Bullet_2(String after_Grad_Bullet_2) {
        this.after_Grad_Bullet_2 = after_Grad_Bullet_2;
        return this;
    }

    public void setAfter_Grad_Bullet_2(String after_Grad_Bullet_2) {
        this.after_Grad_Bullet_2 = after_Grad_Bullet_2;
    }

    public String getAfter_Grad_Bullet_3() {
        return after_Grad_Bullet_3;
    }

    public Alumni after_Grad_Bullet_3(String after_Grad_Bullet_3) {
        this.after_Grad_Bullet_3 = after_Grad_Bullet_3;
        return this;
    }

    public void setAfter_Grad_Bullet_3(String after_Grad_Bullet_3) {
        this.after_Grad_Bullet_3 = after_Grad_Bullet_3;
    }

    public String getAfter_Grad_Bullet_4() {
        return after_Grad_Bullet_4;
    }

    public Alumni after_Grad_Bullet_4(String after_Grad_Bullet_4) {
        this.after_Grad_Bullet_4 = after_Grad_Bullet_4;
        return this;
    }

    public void setAfter_Grad_Bullet_4(String after_Grad_Bullet_4) {
        this.after_Grad_Bullet_4 = after_Grad_Bullet_4;
    }

    public String getAfter_Grad_Bullet_5() {
        return after_Grad_Bullet_5;
    }

    public Alumni after_Grad_Bullet_5(String after_Grad_Bullet_5) {
        this.after_Grad_Bullet_5 = after_Grad_Bullet_5;
        return this;
    }

    public void setAfter_Grad_Bullet_5(String after_Grad_Bullet_5) {
        this.after_Grad_Bullet_5 = after_Grad_Bullet_5;
    }

    public String getAfter_Grad_Bullet_6() {
        return after_Grad_Bullet_6;
    }

    public Alumni after_Grad_Bullet_6(String after_Grad_Bullet_6) {
        this.after_Grad_Bullet_6 = after_Grad_Bullet_6;
        return this;
    }

    public void setAfter_Grad_Bullet_6(String after_Grad_Bullet_6) {
        this.after_Grad_Bullet_6 = after_Grad_Bullet_6;
    }

    public String getAfter_Grad_Bullet_7() {
        return after_Grad_Bullet_7;
    }

    public Alumni after_Grad_Bullet_7(String after_Grad_Bullet_7) {
        this.after_Grad_Bullet_7 = after_Grad_Bullet_7;
        return this;
    }

    public void setAfter_Grad_Bullet_7(String after_Grad_Bullet_7) {
        this.after_Grad_Bullet_7 = after_Grad_Bullet_7;
    }

    public String getAfter_Grad_Bullet_8() {
        return after_Grad_Bullet_8;
    }

    public Alumni after_Grad_Bullet_8(String after_Grad_Bullet_8) {
        this.after_Grad_Bullet_8 = after_Grad_Bullet_8;
        return this;
    }

    public void setAfter_Grad_Bullet_8(String after_Grad_Bullet_8) {
        this.after_Grad_Bullet_8 = after_Grad_Bullet_8;
    }

    public String getAfter_Grad_Bullet_9() {
        return after_Grad_Bullet_9;
    }

    public Alumni after_Grad_Bullet_9(String after_Grad_Bullet_9) {
        this.after_Grad_Bullet_9 = after_Grad_Bullet_9;
        return this;
    }

    public void setAfter_Grad_Bullet_9(String after_Grad_Bullet_9) {
        this.after_Grad_Bullet_9 = after_Grad_Bullet_9;
    }

    public String getAfter_Grad_Bullet_10() {
        return after_Grad_Bullet_10;
    }

    public Alumni after_Grad_Bullet_10(String after_Grad_Bullet_10) {
        this.after_Grad_Bullet_10 = after_Grad_Bullet_10;
        return this;
    }

    public void setAfter_Grad_Bullet_10(String after_Grad_Bullet_10) {
        this.after_Grad_Bullet_10 = after_Grad_Bullet_10;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public Alumni primaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
        return this;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public Alumni alternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
        return this;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public Alumni homeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getSecondaryAddress() {
        return secondaryAddress;
    }

    public Alumni secondaryAddress(String secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
        return this;
    }

    public void setSecondaryAddress(String secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }

    public String getTelephoneMobile() {
        return telephoneMobile;
    }

    public Alumni telephoneMobile(String telephoneMobile) {
        this.telephoneMobile = telephoneMobile;
        return this;
    }

    public void setTelephoneMobile(String telephoneMobile) {
        this.telephoneMobile = telephoneMobile;
    }

    public String getTelephoneLandline() {
        return telephoneLandline;
    }

    public Alumni telephoneLandline(String telephoneLandline) {
        this.telephoneLandline = telephoneLandline;
        return this;
    }

    public void setTelephoneLandline(String telephoneLandline) {
        this.telephoneLandline = telephoneLandline;
    }

    public String getTelephoneOther() {
        return telephoneOther;
    }

    public Alumni telephoneOther(String telephoneOther) {
        this.telephoneOther = telephoneOther;
        return this;
    }

    public void setTelephoneOther(String telephoneOther) {
        this.telephoneOther = telephoneOther;
    }

    public String getPersonalWebPage1() {
        return personalWebPage1;
    }

    public Alumni personalWebPage1(String personalWebPage1) {
        this.personalWebPage1 = personalWebPage1;
        return this;
    }

    public void setPersonalWebPage1(String personalWebPage1) {
        this.personalWebPage1 = personalWebPage1;
    }

    public String getPersonalWebPage2() {
        return personalWebPage2;
    }

    public Alumni personalWebPage2(String personalWebPage2) {
        this.personalWebPage2 = personalWebPage2;
        return this;
    }

    public void setPersonalWebPage2(String personalWebPage2) {
        this.personalWebPage2 = personalWebPage2;
    }

    public String getBlog1() {
        return blog1;
    }

    public Alumni blog1(String blog1) {
        this.blog1 = blog1;
        return this;
    }

    public void setBlog1(String blog1) {
        this.blog1 = blog1;
    }

    public String getBlog2() {
        return blog2;
    }

    public Alumni blog2(String blog2) {
        this.blog2 = blog2;
        return this;
    }

    public void setBlog2(String blog2) {
        this.blog2 = blog2;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public Alumni facebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
        return this;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getTwitter() {
        return twitter;
    }

    public Alumni twitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public Alumni instagram(String instagram) {
        this.instagram = instagram;
        return this;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getYouTube() {
        return youTube;
    }

    public Alumni youTube(String youTube) {
        this.youTube = youTube;
        return this;
    }

    public void setYouTube(String youTube) {
        this.youTube = youTube;
    }

    public String getVimeo() {
        return vimeo;
    }

    public Alumni vimeo(String vimeo) {
        this.vimeo = vimeo;
        return this;
    }

    public void setVimeo(String vimeo) {
        this.vimeo = vimeo;
    }

    public String getBandcamp() {
        return bandcamp;
    }

    public Alumni bandcamp(String bandcamp) {
        this.bandcamp = bandcamp;
        return this;
    }

    public void setBandcamp(String bandcamp) {
        this.bandcamp = bandcamp;
    }

    public String getOtherSocialMedia() {
        return otherSocialMedia;
    }

    public Alumni otherSocialMedia(String otherSocialMedia) {
        this.otherSocialMedia = otherSocialMedia;
        return this;
    }

    public void setOtherSocialMedia(String otherSocialMedia) {
        this.otherSocialMedia = otherSocialMedia;
    }

    public String getOtherUrl() {
        return otherUrl;
    }

    public Alumni otherUrl(String otherUrl) {
        this.otherUrl = otherUrl;
        return this;
    }

    public void setOtherUrl(String otherUrl) {
        this.otherUrl = otherUrl;
    }

    public String getOtherSocialMedia2() {
        return otherSocialMedia2;
    }

    public Alumni otherSocialMedia2(String otherSocialMedia2) {
        this.otherSocialMedia2 = otherSocialMedia2;
        return this;
    }

    public void setOtherSocialMedia2(String otherSocialMedia2) {
        this.otherSocialMedia2 = otherSocialMedia2;
    }

    public String getOtherURL2() {
        return otherURL2;
    }

    public Alumni otherURL2(String otherURL2) {
        this.otherURL2 = otherURL2;
        return this;
    }

    public void setOtherURL2(String otherURL2) {
        this.otherURL2 = otherURL2;
    }

    public String getJob() {
        return job;
    }

    public Alumni job(String job) {
        this.job = job;
        return this;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany() {
        return company;
    }

    public Alumni company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyURL() {
        return companyURL;
    }

    public Alumni companyURL(String companyURL) {
        this.companyURL = companyURL;
        return this;
    }

    public void setCompanyURL(String companyURL) {
        this.companyURL = companyURL;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public Alumni workAddress(String workAddress) {
        this.workAddress = workAddress;
        return this;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public Alumni workEmail(String workEmail) {
        this.workEmail = workEmail;
        return this;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getWorkTelephone() {
        return workTelephone;
    }

    public Alumni workTelephone(String workTelephone) {
        this.workTelephone = workTelephone;
        return this;
    }

    public void setWorkTelephone(String workTelephone) {
        this.workTelephone = workTelephone;
    }

    public String getUploadCVURL() {
        return uploadCVURL;
    }

    public Alumni uploadCVURL(String uploadCVURL) {
        this.uploadCVURL = uploadCVURL;
        return this;
    }

    public void setUploadCVURL(String uploadCVURL) {
        this.uploadCVURL = uploadCVURL;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public Alumni maidenName(String maidenName) {
        this.maidenName = maidenName;
        return this;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public User getAlumniuser() {
        return alumniuser;
    }

    public Alumni alumniuser(User user) {
        this.alumniuser = user;
        return this;
    }

    public void setAlumniuser(User user) {
        this.alumniuser = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Alumni alumni = (Alumni) o;
        if (alumni.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, alumni.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Alumni{" +
            "id=" + id +
            ", cornellAlumniIDNumber='" + cornellAlumniIDNumber + "'" +
            ", firstName='" + firstName + "'" +
            ", middleName='" + middleName + "'" +
            ", lastName='" + lastName + "'" +
            ", collegeOrSchool='" + collegeOrSchool + "'" +
            ", major='" + major + "'" +
            ", place='" + place + "'" +
            ", country='" + country + "'" +
            ", livingCity='" + livingCity + "'" +
            ", whereLiveNow='" + whereLiveNow + "'" +
            ", currentProfileLink='" + currentProfileLink + "'" +
            ", life_Bullet_1='" + life_Bullet_1 + "'" +
            ", life_Bullet_2='" + life_Bullet_2 + "'" +
            ", life_Bullet_3='" + life_Bullet_3 + "'" +
            ", life_Bullet_4='" + life_Bullet_4 + "'" +
            ", life_Bullet_5='" + life_Bullet_5 + "'" +
            ", life_Bullet_6='" + life_Bullet_6 + "'" +
            ", life_Bullet_7='" + life_Bullet_7 + "'" +
            ", life_Bullet_8='" + life_Bullet_8 + "'" +
            ", life_Bullet_9='" + life_Bullet_9 + "'" +
            ", life_Bullet_10='" + life_Bullet_10 + "'" +
            ", after_Grad_Bullet_1='" + after_Grad_Bullet_1 + "'" +
            ", after_Grad_Bullet_2='" + after_Grad_Bullet_2 + "'" +
            ", after_Grad_Bullet_3='" + after_Grad_Bullet_3 + "'" +
            ", after_Grad_Bullet_4='" + after_Grad_Bullet_4 + "'" +
            ", after_Grad_Bullet_5='" + after_Grad_Bullet_5 + "'" +
            ", after_Grad_Bullet_6='" + after_Grad_Bullet_6 + "'" +
            ", after_Grad_Bullet_7='" + after_Grad_Bullet_7 + "'" +
            ", after_Grad_Bullet_8='" + after_Grad_Bullet_8 + "'" +
            ", after_Grad_Bullet_9='" + after_Grad_Bullet_9 + "'" +
            ", after_Grad_Bullet_10='" + after_Grad_Bullet_10 + "'" +
            ", primaryEmail='" + primaryEmail + "'" +
            ", alternateEmail='" + alternateEmail + "'" +
            ", homeAddress='" + homeAddress + "'" +
            ", secondaryAddress='" + secondaryAddress + "'" +
            ", telephoneMobile='" + telephoneMobile + "'" +
            ", telephoneLandline='" + telephoneLandline + "'" +
            ", telephoneOther='" + telephoneOther + "'" +
            ", personalWebPage1='" + personalWebPage1 + "'" +
            ", personalWebPage2='" + personalWebPage2 + "'" +
            ", blog1='" + blog1 + "'" +
            ", blog2='" + blog2 + "'" +
            ", facebookLink='" + facebookLink + "'" +
            ", twitter='" + twitter + "'" +
            ", instagram='" + instagram + "'" +
            ", youTube='" + youTube + "'" +
            ", vimeo='" + vimeo + "'" +
            ", bandcamp='" + bandcamp + "'" +
            ", otherSocialMedia='" + otherSocialMedia + "'" +
            ", otherUrl='" + otherUrl + "'" +
            ", otherSocialMedia2='" + otherSocialMedia2 + "'" +
            ", otherURL2='" + otherURL2 + "'" +
            ", job='" + job + "'" +
            ", company='" + company + "'" +
            ", companyURL='" + companyURL + "'" +
            ", workAddress='" + workAddress + "'" +
            ", workEmail='" + workEmail + "'" +
            ", workTelephone='" + workTelephone + "'" +
            ", uploadCVURL='" + uploadCVURL + "'" +
            ", maidenName='" + maidenName + "'" +
            '}';
    }
}
