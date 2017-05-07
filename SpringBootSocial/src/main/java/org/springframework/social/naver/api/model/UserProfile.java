package java.org.springframework.social.naver.api.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "data")
public class UserProfile extends Model implements Serializable {
	private static final long serialVersionUID = 4399788025738419513L;

	private UserProfileResult result;
	private UserProfileResponse response;

	public UserProfileResult getResult() {
		return result;
	}

	public void setResult(UserProfileResult result) {
		this.result = result;
	}

	public UserProfileResponse getResponse() {
		return response;
	}

	public void setResponse(UserProfileResponse response) {
		this.response = response;
	}
}
