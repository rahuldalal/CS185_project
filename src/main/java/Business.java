public class Business {
	private String locationAccountNum;
	private String businessName;
	//private String DBAName;
	//private String streetAddress;
	private String city;
	private String zipCode;
	//private String locationDescription;
	//private String mailingAddress;
	//private String mailingCity;
	//private int mailingZipCode;
	private int NAICS;
	private String businessLine;  //primary NAICS description
	//private int councilDistrict;
	//private String locationStartDate;
	//private String locationEndDate;
	//private String location;

	public Business(){
		super();
	}
	/*public Business(String locationAccountNum, String businessName, String DBAName, String streetAddress, String city,
			int zipCode, String locationDescription, String mailingAddress, String mailingCity, int mailingZipCode,
			int NAICS, String primaryNAICSDescription, int councilDistrict, String locationStartDate,
			String locationEndDate, String location) {
		this.locationAccountNum = locationAccountNum;
		this.businessName = businessName;
		this.DBAName = DBAName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.zipCode = zipCode;
		this.locationDescription = locationDescription;
		this.mailingAddress = mailingAddress;
		this.mailingCity = mailingCity;
		this.mailingZipCode = mailingZipCode;
		this.NAICS = NAICS;
		this.businessLine = primaryNAICSDescription;
		this.councilDistrict = councilDistrict;
		this.locationStartDate = locationStartDate;
		this.locationEndDate = locationEndDate;
		this.location = location;
	}*/

	public String getLocationAccountNum() {
		return locationAccountNum;
	}

	public void setLocationAccountNum(String locationAccountNum) {
		this.locationAccountNum = locationAccountNum;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	/*public String getDBAName() {
		return DBAName;
	}

	public void setDBAName(String dBAName) {
		DBAName = dBAName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}*/

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/*public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getMailingCity() {
		return mailingCity;
	}

	public void setMailingCity(String mailingCity) {
		this.mailingCity = mailingCity;
	}

	public int getMailingZipCode() {
		return mailingZipCode;
	}

	public void setMailingZipCode(int mailingZipCode) {
		this.mailingZipCode = mailingZipCode;
	}*/

	public int getNAICS() {
		return NAICS;
	}

	public void setNAICS(int nAICS) {
		NAICS = nAICS;
	}

	public String getPrimaryNAICSDescription() {
		return businessLine;
	}

	public void setPrimaryNAICSDescription(String primaryNAICSDescription) {
		this.businessLine = primaryNAICSDescription;
	}

	/*public int getCouncilDistrict() {
		return councilDistrict;
	}

	public void setCouncilDistrict(int councilDistrict) {
		this.councilDistrict = councilDistrict;
	}

	public String getLocationStartDate() {
		return locationStartDate;
	}

	public void setLocationStartDate(String locationStartDate) {
		this.locationStartDate = locationStartDate;
	}

	public String getLocationEndDate() {
		return locationEndDate;
	}

	public void setLocationEndDate(String locationEndDate) {
		this.locationEndDate = locationEndDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}*/
	
	public static Business parseBusiness(String str) {
		String[] fields = str.split(",");
		
		String locationAccountNum = fields[0];
		String businessName = fields[1];
		String city = fields[4];
		String zipCode = fields[5];
		int NAICS=0;
		try{
			NAICS = Integer.parseInt(fields[10]);
		}
		catch(NumberFormatException e)
		{
			//Invalid NAICS already set to zero
		}
		
		String businessLine = fields[11];
		return new Business(locationAccountNum, businessName, city, zipCode, NAICS,businessLine);
	}

	public Business(String locationAccountNum, String businessName, String city, String zipCode, int nAICS,
			String businessLine) {
		super();
		this.locationAccountNum = locationAccountNum;
		this.businessName = businessName;
		this.city = city;
		this.zipCode = zipCode;
		NAICS = nAICS;
		this.businessLine = businessLine;
	}

	@Override
	public String toString() {
		return "Business [locationAccountNum=" + locationAccountNum + ", businessName=" + businessName + ", city="
				+ city + ", zipCode=" + zipCode + ", NAICS=" + NAICS + ", businessLine=" + businessLine + "]";
	}
	
	
}