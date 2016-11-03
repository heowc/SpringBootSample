package com.example.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.social.facebook.api.AgeRange;
import org.springframework.social.facebook.api.CoverPhoto;
import org.springframework.social.facebook.api.Currency;
import org.springframework.social.facebook.api.Device;
import org.springframework.social.facebook.api.EducationExperience;
import org.springframework.social.facebook.api.Experience;
import org.springframework.social.facebook.api.Location;
import org.springframework.social.facebook.api.PaymentPricePoints;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.SecuritySettings;
import org.springframework.social.facebook.api.VideoUploadLimits;
import org.springframework.social.facebook.api.WorkEntry;

@SuppressWarnings("serial")
public class User implements Serializable {

	private String id;

	private String about;
	
	private Location address;
	
	private AgeRange ageRange = AgeRange.UNKNOWN;

	private String birthday;
	
	private CoverPhoto cover;
	
	private Currency currency;
	
	private List<Device> devices;
	
	private List<EducationExperience> education;
	
	private String email;
	
	private List<Reference> favoriteAthletes;

	private List<Reference> favoriteTeams;
	
	private String firstName;
	
	private String gender;

	private Reference hometown;
	
	private List<Reference> inspirationalPeople;
	
	private boolean installed;
	
	private String installType;
	
	private List<String> interestedIn;

	private boolean isIdentityVerified;
	
	private List<Reference> languages;
	
	private String lastName;

	private String link;

	private Locale locale;

	private Reference location;
	
	private String middleName;
	
	private List<String> meetingFor;

	private String name;
	
	private String nameFormat;
	
	private PaymentPricePoints paymentPricePoints;

	private String political;

	private String quotes;

	private String relationshipStatus;

	private String religion;

	private SecuritySettings securitySettings;
	
	private Reference significantOther;
	
	private List<Experience> sports;
	
	private int testGroup;
	
	private String thirdPartyId;

	private Float timezone;
	
	private Date updatedTime;

	private Boolean verified;

	private boolean viewerCanSendGift;
	
	private String website;

	private List<WorkEntry> work;
	
	private VideoUploadLimits videoUploadLimits;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Location getAddress() {
		return address;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

	public AgeRange getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(AgeRange ageRange) {
		this.ageRange = ageRange;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public CoverPhoto getCover() {
		return cover;
	}

	public void setCover(CoverPhoto cover) {
		this.cover = cover;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public List<EducationExperience> getEducation() {
		return education;
	}

	public void setEducation(List<EducationExperience> education) {
		this.education = education;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Reference> getFavoriteAthletes() {
		return favoriteAthletes;
	}

	public void setFavoriteAthletes(List<Reference> favoriteAthletes) {
		this.favoriteAthletes = favoriteAthletes;
	}

	public List<Reference> getFavoriteTeams() {
		return favoriteTeams;
	}

	public void setFavoriteTeams(List<Reference> favoriteTeams) {
		this.favoriteTeams = favoriteTeams;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Reference getHometown() {
		return hometown;
	}

	public void setHometown(Reference hometown) {
		this.hometown = hometown;
	}

	public List<Reference> getInspirationalPeople() {
		return inspirationalPeople;
	}

	public void setInspirationalPeople(List<Reference> inspirationalPeople) {
		this.inspirationalPeople = inspirationalPeople;
	}

	public boolean isInstalled() {
		return installed;
	}

	public void setInstalled(boolean installed) {
		this.installed = installed;
	}

	public String getInstallType() {
		return installType;
	}

	public void setInstallType(String installType) {
		this.installType = installType;
	}

	public List<String> getInterestedIn() {
		return interestedIn;
	}

	public void setInterestedIn(List<String> interestedIn) {
		this.interestedIn = interestedIn;
	}

	public boolean isIdentityVerified() {
		return isIdentityVerified;
	}

	public void setIdentityVerified(boolean isIdentityVerified) {
		this.isIdentityVerified = isIdentityVerified;
	}

	public List<Reference> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Reference> languages) {
		this.languages = languages;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Reference getLocation() {
		return location;
	}

	public void setLocation(Reference location) {
		this.location = location;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public List<String> getMeetingFor() {
		return meetingFor;
	}

	public void setMeetingFor(List<String> meetingFor) {
		this.meetingFor = meetingFor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameFormat() {
		return nameFormat;
	}

	public void setNameFormat(String nameFormat) {
		this.nameFormat = nameFormat;
	}

	public PaymentPricePoints getPaymentPricePoints() {
		return paymentPricePoints;
	}

	public void setPaymentPricePoints(PaymentPricePoints paymentPricePoints) {
		this.paymentPricePoints = paymentPricePoints;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getQuotes() {
		return quotes;
	}

	public void setQuotes(String quotes) {
		this.quotes = quotes;
	}

	public String getRelationshipStatus() {
		return relationshipStatus;
	}

	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public SecuritySettings getSecuritySettings() {
		return securitySettings;
	}

	public void setSecuritySettings(SecuritySettings securitySettings) {
		this.securitySettings = securitySettings;
	}

	public Reference getSignificantOther() {
		return significantOther;
	}

	public void setSignificantOther(Reference significantOther) {
		this.significantOther = significantOther;
	}

	public List<Experience> getSports() {
		return sports;
	}

	public void setSports(List<Experience> sports) {
		this.sports = sports;
	}

	public int getTestGroup() {
		return testGroup;
	}

	public void setTestGroup(int testGroup) {
		this.testGroup = testGroup;
	}

	public String getThirdPartyId() {
		return thirdPartyId;
	}

	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}

	public Float getTimezone() {
		return timezone;
	}

	public void setTimezone(Float timezone) {
		this.timezone = timezone;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public boolean isViewerCanSendGift() {
		return viewerCanSendGift;
	}

	public void setViewerCanSendGift(boolean viewerCanSendGift) {
		this.viewerCanSendGift = viewerCanSendGift;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<WorkEntry> getWork() {
		return work;
	}

	public void setWork(List<WorkEntry> work) {
		this.work = work;
	}

	public VideoUploadLimits getVideoUploadLimits() {
		return videoUploadLimits;
	}

	public void setVideoUploadLimits(VideoUploadLimits videoUploadLimits) {
		this.videoUploadLimits = videoUploadLimits;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", about=" + about + ", address=" + address
				+ ", ageRange=" + ageRange + ", birthday=" + birthday
				+ ", cover=" + cover + ", currency=" + currency + ", devices="
				+ devices + ", education=" + education + ", email=" + email
				+ ", favoriteAthletes=" + favoriteAthletes + ", favoriteTeams="
				+ favoriteTeams + ", firstName=" + firstName + ", gender="
				+ gender + ", hometown=" + hometown + ", inspirationalPeople="
				+ inspirationalPeople + ", installed=" + installed
				+ ", installType=" + installType + ", interestedIn="
				+ interestedIn + ", isIdentityVerified=" + isIdentityVerified
				+ ", languages=" + languages + ", lastName=" + lastName
				+ ", link=" + link + ", locale=" + locale + ", location="
				+ location + ", middleName=" + middleName + ", meetingFor="
				+ meetingFor + ", name=" + name + ", nameFormat=" + nameFormat
				+ ", paymentPricePoints=" + paymentPricePoints + ", political="
				+ political + ", quotes=" + quotes + ", relationshipStatus="
				+ relationshipStatus + ", religion=" + religion
				+ ", securitySettings=" + securitySettings
				+ ", significantOther=" + significantOther + ", sports="
				+ sports + ", testGroup=" + testGroup + ", thirdPartyId="
				+ thirdPartyId + ", timezone=" + timezone + ", updatedTime="
				+ updatedTime + ", verified=" + verified
				+ ", viewerCanSendGift=" + viewerCanSendGift + ", website="
				+ website + ", work=" + work + ", videoUploadLimits="
				+ videoUploadLimits + "]";
	}
	
	
}
