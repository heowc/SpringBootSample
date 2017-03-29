package com.example.social.facebook;

import lombok.Data;
import org.springframework.social.facebook.api.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Data
@SuppressWarnings("serial")
public class FacebookUser extends FacebookObject implements Serializable {

	private String id;

	private String about;

	private Location address;

	private AgeRange ageRange = AgeRange.UNKNOWN;

	private String bio;

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

	FacebookUser() {
	}

	public FacebookUser(String id, String name, String firstName, String lastName, String gender, Locale locale) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.locale = locale;
	}
}