package edu.truman.cs370.team6.addrSys;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address
{
	private String street;
	private String city;
	private String state;
	private String zipFive;
	private String zipFour;
	private String[] parsedStreet;
	private boolean hasAllFields;
	
	private static final int MAX_USPS_CHARACTER_LENGTH = 40;
	private static final int MAX_USPS_NUMBER_WORDS = 8;
	private static final List<String> STATE_ABBREVIATION_LIST = Arrays.asList(
			"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI", 
			"IA", "ID", "IL", "IN", "KS", "KY", "LA", "NE", "ND", "MA", "MI",
			"MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC",
			"ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT",
			"VT", "VA", "WA", "WV", "WI", "WY" 
	);
	
	private static final Map<String, String> STATE_MAP;
	static 
	{
		STATE_MAP = new HashMap<String, String>();
		STATE_MAP.put("ALABAMA", "AL");
		STATE_MAP.put("ALASKA", "AK");
		STATE_MAP.put("ARIZONA", "AZ");
		STATE_MAP.put("ARKANSAS", "AR");
		STATE_MAP.put("CALIFORNIA", "CA");
		STATE_MAP.put("COLORADO", "CO");
		STATE_MAP.put("CONNECTICUT", "CT");
		STATE_MAP.put("DELAWARE", "DE");
		STATE_MAP.put("FLORIDA", "FL");
		STATE_MAP.put("GEORGIA", "GA");
		STATE_MAP.put("HAWAII", "HI");
		STATE_MAP.put("IDAHO", "ID");
		STATE_MAP.put("ILLINOIS", "IL");
		STATE_MAP.put("INDIANA", "IN");
		STATE_MAP.put("IOWA", "IA");
		STATE_MAP.put("KANSAS", "KS");
		STATE_MAP.put("KENTUCKY", "KY");
		STATE_MAP.put("LOUISIANA", "LA");
		STATE_MAP.put("MAINE", "ME");
		STATE_MAP.put("MARYLAND", "MD");
		STATE_MAP.put("MASSACHUSETTS", "MA");
		STATE_MAP.put("MICHIGAN", "MI");
		STATE_MAP.put("MINNESOTA", "MN");
		STATE_MAP.put("MISSISSIPPI", "MS");
		STATE_MAP.put("MISSOURI", "MO");
		STATE_MAP.put("MONTANA", "MT");
		STATE_MAP.put("NEBRASKA", "NE");
		STATE_MAP.put("NEVADA", "NV");
		STATE_MAP.put("NEW HAMPSHIRE", "NH");
		STATE_MAP.put("NEW JERSEY", "NJ");
		STATE_MAP.put("NEW MEXICO", "NM");
		STATE_MAP.put("NEW YORK", "NY");
		STATE_MAP.put("NORTH CAROLINA", "NC");
		STATE_MAP.put("NORTH DAKOTA", "ND");
		STATE_MAP.put("OHIO", "OH");
		STATE_MAP.put("OKLAHOMA", "OK");
		STATE_MAP.put("OREGON", "OR");
		STATE_MAP.put("PENNSYLVANIA", "PA");
		STATE_MAP.put("RHODE ISLAND", "RI");
		STATE_MAP.put("SOUTH CAROLINA", "SC");
		STATE_MAP.put("SOUTH DAKOTA", "SD");
		STATE_MAP.put("TENNESSEE", "TN");
		STATE_MAP.put("TEXAS", "TX");
		STATE_MAP.put("UTAH", "UT");
		STATE_MAP.put("VERMONT", "VT");
		STATE_MAP.put("VIRGINIA", "VA");
		STATE_MAP.put("WASHINGTON", "WA");
		STATE_MAP.put("WEST VIRGINIA", "WV");
		STATE_MAP.put("WISCONSIN", "WS");
		STATE_MAP.put("WYOMING", "WY");
		STATE_MAP.put("DISTRICT OF COLUMBIA", "DC");
	}
	
	private final String[][] SECONDARY_UNIT_TABLE = 
	{
		{"APARTMENT", "APT", "needRange"},
		{"BASEMENT", "BSMT", "needNoRange"},
		{"BUILDING", "BLDG", "needRange"},
		{"DEPARTMENT", "DEPT", "needRange"},
		{"FLOOR", "FL", "needRange"},
		{"FRONT", "FRNT", "needNoRange"},
		{"HANGER", "HNGR", "needRange"},
		{"LOBBY", "LBBY", "needNoRange"},
		{"LOT", "LOT", "needRange"},
		{"LOWER", "LOWR", "needNoRange"},
		{"OFFICE", "OFC", "needNoRange"},
		{"PENTHOUSE", "PH", "needNoRange"},
		{"PIER", "PIER", "needRange"},
		{"REAR", "REAR", "needNoRange"},
		{"ROOM", "RM", "needRange"},
		{"SIDE", "SIDE", "needNoRange"},
		{"SLIP", "SLIP", "needRange"},
		{"SPACE", "SPC", "needRange"},
		{"STOP", "STOP", "needRange"},
		{"SUITE", "STE", "needRange"},
		{"TRAILER", "TRLR", "needRange"},
		{"UNIT", "UNIT", "needRange"},
		{"UPPER", "UPPR", "needNoRange"},	
	};
	
	private final String[][] SUFFIX_TABLE = 
	{
		{"ALLEY", "ALY"},
		{"ANNEX", "ANX"},
		{"ARCADE", "ARC"},
		{"AVENUE", "AVE"},
		{"BAYOO", "BYU"},
		{"BEACH", "BCH"},
		{"BEND", "BND"},
		{"BLUFF", "BLF"},
		{"BLUFFS", "BLFS"},
		{"BOTTOM", "BTM"},
		{"BOULEVARD", "BLVD"},
		{"BRANCH", "BR"},
		{"BRIDGE", "BRG"},
		{"BROOK", "BRK"},
		{"BROOKS", "BRKS"},
		{"BURG", "BG"},
		{"BURGS", "BGS"},
		{"BYPASS", "BYP"},
		{"CAMP", "CP"},
		{"CANYON", "CYN"},
		{"CAPE", "CPE"},
		{"CAUSEWAY", "CSWY"},
		{"CENTER", "CTR"},
		{"CENTERS", "CTRS"},
		{"CIRCLE", "CIR"},
		{"CIRCLES", "CIRS"},
		{"CLIFF", "CLF"},
		{"CLIFFS", "CLFS"},
		{"CLUB", "CLB"},
		{"COMMON", "CMN"},
		{"CORNER", "COR"},
		{"CORNERS", "CORS"},
		{"COURSE", "CRSE"},
		{"COURT", "CT"},
		{"COURTS", "CTS"},
		{"COVE", "CV"},
		{"COVES", "CVS"},
		{"CREEK", "CRK"},
		{"CRESCENT", "CRES"},
		{"CREST", "CRST"},
		{"CROSSING", "XING"},
		{"CROSSROAD", "XRD"},
		{"CURVE", "CURV"},
		{"DALE", "DL"},
		{"DAM", "DM"},
		{"DIVIDE", "DV"},
		{"DRIVE", "DR"},
		{"DRIVES", "DRS"},
		{"ESTATE", "EST"},
		{"ESTATES", "ESTS"},
		{"EXPRESSWAY", "EXPY"},
		{"EXTENSION", "EXT"},
		{"EXTENSIONS", "EXTS"},
		{"FALL", "FALL"},
		{"FALLS", "FLS"},
		{"FERRY", "FRY"},
		{"FIELD", "FLD"},
		{"FIELDS", "FLDS"},
		{"FLAT", "FLT"},
		{"FLATS", "FLTS"},
		{"FORD", "FRD"},
		{"FORDS", "FRDS"},
		{"FOREST", "FRST"},
		{"FORGE", "FRG"},
		{"FORGES", "FRGS"},
		{"FORK", "FRK"},
		{"FORKS", "FRKS"},
		{"FORT", "FT"},
		{"FREEWAY", "FWY"},
		{"GARDEN", "GDN"},
		{"GARDENS", "GDNS"},
		{"GATEWAY", "GTWY"},
		{"GLEN", "GLN"},
		{"GLENS", "GLNS"},
		{"GREEN", "GRN"},
		{"GREENS", "GRNS"},
		{"GROVE", "GRV"},
		{"GROVES", "GRVS"},
		{"HARBOR", "HBR"},
		{"HARBORS", "HBRS"},
		{"HAVEN", "HVN"},
		{"HEIGHTS", "HTS"},
		{"HIGHWAY", "HWY"},
		{"HILL", "HL"},
		{"HILLS", "HLS"},
		{"HOLLOW", "HOLW"},
		{"INLET", "INLT"},
		{"INTERSTATE", "I"},
		{"ISLAND", "IS"},
		{"ISLANDS", "ISS"},
		{"ISLE", "ISLE"},
		{"JUNCTION", "JCT"},
		{"JUNCTIONS", "JCTS"},
		{"KEY", "KY"},
		{"KEYS", "KYS"},
		{"KNOLL", "KNL"},
		{"KNOLLS", "KNLS"},
		{"LAKE", "LK"},
		{"LAKES", "LKS"},
		{"LAND", "LAND"},
		{"LANDING", "LNDG"},
		{"LANE", "LN"},
		{"LIGHT", "LGT"},
		{"LIGHTS", "LGTS"},
		{"LOAF", "LF"},
		{"LOCK", "LCK"},
		{"LOCKS", "LCKS"},
		{"LODGE", "LDG"},
		{"LOOP", "LOOP"},
		{"MALL", "MALL"},
		{"MANOR", "MNR"},
		{"MANORS", "MNRS"},
		{"MEADOW", "MDW"},
		{"MEADOWS", "MDWS"},
		{"MEWS", "MEWS"},
		{"MILL", "ML"},
		{"MILLS", "MLS"},
		{"MISSION", "MSN"},
		{"MOORHEAD", "MHD"},
		{"MOTORWAY", "MTWY"},
		{"MOUNT", "MT"},
		{"MOUNTAIN", "MTN"},
		{"MOUNTAINS", "MTNS"},
		{"NECK", "NCK"},
		{"ORCHARD", "ORCH"},
		{"OVAL", "OVAL"},
		{"OVERPASS", "OPAS"},
		{"PARK", "PARK"},
		{"PARKS", "PARK"},
		{"PARKWAY", "PKWY"},
		{"PARKWAYS", "PKWY"},
		{"PASS", "PASS"},
		{"PASSAGE", "PSGE"},
		{"PATH", "PATH"},
		{"PIKE", "PIKE"},
		{"PINE", "PNE"},
		{"PINES", "PNES"},
		{"PLACE", "PL"},
		{"PLAIN", "PLN"},
		{"PLAINS", "PLNS"},
		{"PLAZA", "PLZ"},
		{"POINT", "PT"},
		{"POINTS", "PTS"},
		{"PORT", "PRT"},
		{"PORTS", "PRTS"},
		{"PRAIRIE", "PR"},
		{"RADIAL", "RADL"},
		{"RAMP", "RAMP"},
		{"RANCH", "RNCH"},
		{"RAPID", "RPD"},
		{"RAPIDS", "RPDS"},
		{"REST", "RST"},
		{"RIDGE", "RDG"},
		{"RIDGES", "RDGS"},
		{"RIVER", "RIV"},
		{"ROAD", "RD"},
		{"ROADS", "RDS"},
		{"ROUTE", "RTE"},
		{"ROW", "ROW"},
		{"RUE", "RUE"},
		{"RUN", "RUN"},
		{"SHOAL", "SHL"},
		{"SHOALS", "SHLS"},
		{"SHORE", "SHR"},
		{"SHORES", "SHRS"},
		{"SKYWAY", "SKWY"},
		{"SPRING", "SPG"},
		{"SPRINGS", "SPGS"},
		{"SPUR", "SPUR"},
		{"SPURS", "SPUR"},
		{"SQUARE", "SQ"},
		{"SQUARES", "SQS"},
		{"STATION", "STA"},
		{"STREAM", "STRM"},
		{"STREET", "ST"},
		{"STREETS", "STS"},
		{"SUMMIT", "SMT"},
		{"TERRACE", "TER"},
		{"THROUGHWAY", "TRWY"},
		{"TRACE", "TRCE"},
		{"TRACK", "TRAK"},
		{"TRAIL", "TRL"},
		{"TUNNEL", "TUNL"},
		{"TURNPIKE", "TPKE"},
		{"UNDERPASS", "UPAS"},
		{"UNION", "UN"},
		{"UNIONS", "UNS"},
		{"VALLEY", "VLY"},
		{"VALLEYS", "VLYS"},
		{"VIADUCT", "VIA"},
		{"VIEW", "VW"},
		{"VIEWS", "VWS"},
		{"VILLAGE", "VLG"},
		{"VILLAGES", "VLGS"},
		{"VILLE", "VL"},
		{"VISTA", "VIS"},
		{"WALK", "WALK"},
		{"WALKS", "WALK"},
		{"WALL", "WALL"},
		{"WAY", "WAY"},
		{"WAYS", "WAYS"},
		{"WELL", "WL"},
		{"WELLS", "WLS"}
	};
	
	private static final Map<String, String> DIRECTIONAL_MAP;
	static 
	{
		DIRECTIONAL_MAP = new HashMap<String, String>();
		DIRECTIONAL_MAP.put("SOUTH", "S");
		DIRECTIONAL_MAP.put("NORTH", "N");
		DIRECTIONAL_MAP.put("EAST", "E");
		DIRECTIONAL_MAP.put("WEST", "W");
		DIRECTIONAL_MAP.put("SOUTHWEST", "SW");
		DIRECTIONAL_MAP.put("SOUTHEAST", "SE");
		DIRECTIONAL_MAP.put("NORTHWEST", "NW");
		DIRECTIONAL_MAP.put("NORTHEAST", "NE");

	}
	
	public Address(String fullAddress)	
	{
		street = "";
		city = "";
		state = "";
		zipFive = "";
		zipFour = "";
		parseAddress(fullAddress);
	}
	
	private void parseAddress(String fullAddress)
	{	
		try 
		{
			Scanner addrParser = new Scanner(fullAddress).useDelimiter("\t");
			street = setNullAsEmptyString(addrParser.next().trim());
			city = setNullAsEmptyString(addrParser.next().trim());
			state = setNullAsEmptyString(addrParser.next().trim());
			zipFive = setNullAsEmptyString(addrParser.next().trim());
			zipFour = setNullAsEmptyString(addrParser.next().trim());
			addrParser.close();
			setHasAllFields(true);
		} catch (NoSuchElementException e) {
			setHasAllFields(false);
		}
	}
	
	private String setNullAsEmptyString(String s)
	{
		if (s == null)
		{
			return "";
		}
		else
		{
			return s;
		}
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public String getZipFive()
	{
		return zipFive;
	}
	
	public String getZipFour()
	{
		return zipFour;
	}
	
	public boolean hasAllFields() 
	{
		return hasAllFields;
	}

	public void setHasAllFields(boolean isComplete) 
	{
		this.hasAllFields = isComplete;
	}
	
	public boolean normalize() 
	{
		boolean normalizationSuccessful;
		
		preprocessing();
		normalizationSuccessful = ableToBeNormalized();
		
		if (normalizationSuccessful == false)
		{
			return false;
		}
		
		fixSuffixes();
		fixSecondaryDesignators();
		fixPredirectionalsPostdirectionals();
		
		postprocessing();
		
		normalizationSuccessful = checkIfAddressRightLength();
		return normalizationSuccessful;
	}

	private boolean checkIfAddressRightLength()
	{
		if (street.length() > MAX_USPS_CHARACTER_LENGTH){
			return false;
		}

		if (parsedStreet.length > MAX_USPS_NUMBER_WORDS){
			return false;
		}
		
		return true;
	}
	private void postprocessing()
	{
		street = stringJoin(parsedStreet, " ");

	}
	
	private static String stringJoin(String[] stringArray, String separator) 
	{
	    StringBuilder subString = new StringBuilder();
	    for (int i = 0, il = stringArray.length; i < il; i++) 
	    {
	        if (i > 0)
	        {
	         subString.append(separator);
	        }
	        subString.append(stringArray[i]);
	    }
	    return subString.toString();
	}
	
	private boolean ableToBeNormalized() 
	{
		if (checkDoesntStartWithNumber() ||
				containsFractionalAddress() ||
				containsGridStylePeriods() ||
				ZIP5Invalid() ||
				ZIP4Invalid() ||
				stateInvalid() ||
				checkInvalidSecondary()
				)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	private void preprocessing()
	{
		capitalize();
		parsedStreet = street.split(" ");
		removeEndPunctuation();
	}

	private void capitalize()
	{
		street = street.toUpperCase(); 
		city = city.toUpperCase();
		state = state.toUpperCase();
	}

	/**
	 * Removes trailing punctuation from entire address.
	 */
	public void removeEndPunctuation()
	{
		removeStreetPunctuation();
		
		if (city.endsWith(".") || city.endsWith(","))
		{
			if (city.contains("."))
				city = city.substring(0, city.indexOf("."));
			else if (city.contains(","))
				city = city.substring(0, city.indexOf(","));
		}
		if (state.endsWith(".") || state.endsWith(","))
		{
			if (state.contains("."))
				state = state.substring(0, state.indexOf("."));
			else if (state.contains(","))
				state = state.substring(0, state.indexOf(","));
		}
		if (zipFive.endsWith(".") || zipFive.endsWith(","))
		{
			if (zipFive.contains("."))
				zipFive = zipFive.substring(0, zipFive.indexOf("."));
			else if (zipFive.contains(","))
				zipFive = zipFive.substring(0, zipFive.indexOf(","));
		}
		/*if(!zipFour.equals("")){
			if (zipFour.endsWith(".") || zipFour.endsWith(","))
			{
				if (zipFour.contains("."))
					zipFour = zipFour.substring(0, zipFour.indexOf("."));
				else if (zipFour.contains(","))
					zipFour = zipFour.substring(0, zipFour.indexOf(","));
			}
		}*/	
	}
	
	/**
	 * Removes trailing punctuation 
	 * from street portion of the address.
	 */
	private void removeStreetPunctuation()
	{
		String[] streetNoPunc = parsedStreet;
		for (int i = 0; i < parsedStreet.length; i++)
		{
			String stPart = parsedStreet[i];	//streetPart
			if (stPart.endsWith(".") || stPart.endsWith(","))
			{
				if (stPart.contains("."))
				{
					streetNoPunc[i] = stPart.substring(0, stPart.indexOf("."));
				} else if (stPart.contains(",")) 
				{
					streetNoPunc[i] = stPart.substring(0, stPart.indexOf(","));
				}
			}
		}
		parsedStreet = streetNoPunc;
	}

	private boolean checkDoesntStartWithNumber() 
	{
//		PO Boxes will be incorrectly caught in this method. Use commented code 
//		(or something like it) when you implement this method
		boolean hasPOBox = hasPOBox();
		if (hasPOBox) 
		{
			return false;
		} 
		else 
		{
			if (isNumber(parsedStreet[0])) 
			{
				return false;
			} 
			else 
			{
				return true;
			}
		}
	}
	
	private boolean isNumber(String addressField) 
	{
		String zip5Regex = "[0-9]+$";
		Pattern zip5Pattern = Pattern.compile(zip5Regex);
		Matcher matcher = zip5Pattern.matcher(addressField);
		
		if (matcher.matches()) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}
	
	public boolean hasPOBox() 
	{
		try 
		{
			String POBox = parsedStreet[0] + " " + parsedStreet[1];
			String POBoxRegex = "^(PO BOX|P BOX)$";
			Pattern POBoxPattern = Pattern.compile(POBoxRegex);
			Matcher matcher = POBoxPattern.matcher(POBox);
			if (matcher.matches()) 
			{
				if (parsedStreet[0].equals("P.O.")) 
				{
					parsedStreet[0] = "PO";
				}
				return true;
			} 
			else 
			{
				return false;
			}
		} 
		catch(IndexOutOfBoundsException e) 
		{
			return false;
		}
	}
	
	private boolean containsFractionalAddress()
	{
		for (String word : parsedStreet) 
		{
			if (word.contains("/")) 
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean containsGridStylePeriods()
	{
		for (String word : parsedStreet) 
		{
			if (word.contains(".")) 
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean ZIP5Invalid()
	{
		String zip5Regex = "^[0-9]{5}$";
		Pattern zip5Pattern = Pattern.compile(zip5Regex);
		Matcher matcher = zip5Pattern.matcher(getZipFive());
		
		if (matcher.matches()) 
		{
			return false;
		} 
		else 
		{
			return true;
		}
	}
	
	private boolean ZIP4Invalid() 
	{
		String zip4Regex = "^[0-9]{4}$";
		Pattern zip4Pattern = Pattern.compile(zip4Regex);
		Matcher matcher = zip4Pattern.matcher(getZipFour());
		
		if (matcher.matches() || zipFour.equals("")) 
		{
			return false;
		} 
		else 
		{
			return true;
		}
	}
	
	private boolean stateInvalid()
	{
		if (STATE_ABBREVIATION_LIST.contains(getState())) 
		{
			return false;
		}
		
		if (STATE_MAP.containsKey(state)) 
		{
			state = STATE_MAP.get(state);
			return false;
		}
		return true;
	}
	
	private void fixSuffixes()
	{
		//maybe more??
		abbreviateLastSuffix();
	}
	
	private void abbreviateLastSuffix()
	{
		for (int element=parsedStreet.length - 1; element >= 0; element--)
		{
			for (String[] suffixSubtable: SUFFIX_TABLE)
			{
				if (parsedStreet[element].equals(suffixSubtable[0]))
				{
					parsedStreet[element] = suffixSubtable[1];
					return;
				}
			}
		}		
	}
		
	private int checkSuffixLocation()
	{
		for (int element = parsedStreet.length - 1; element >= 0; element--)
		{
			for (String[] suffixSubtable: SUFFIX_TABLE)
			{
				if (parsedStreet[element].equals(suffixSubtable[1]))
				{
					return element;
				}
			}
		}
		return -1;		
	}
	
	private void fixSecondaryDesignators()
	{
		for (int element = 0; element < parsedStreet.length; element++) 
		{
			for (String[] secondarySubtable: SECONDARY_UNIT_TABLE)
			{
				if (parsedStreet[element].equals(secondarySubtable[0]))
				{
					if (checkSuffixLocation() < element)
					{
					parsedStreet[element] = secondarySubtable[1];
					}
				}
			}
		}
	}
	
	private boolean checkInvalidSecondary() 
	{
		for (String word: parsedStreet)
		{
			for (String[] secondarySubtable: SECONDARY_UNIT_TABLE)
			{
				if (secondarySubtable[2].equals("needRange"))
				{
					if (word.equals(secondarySubtable[0]) || word.equals(secondarySubtable[1]))
					{
						if (word.equals(parsedStreet[parsedStreet.length - 1]))
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private void fixPredirectionalsPostdirectionals()
	{
		predirectionals();
		postdirectionals();
	}

	private void predirectionals() 
	{
		String directionalRegex = "[0-9]+";
		Pattern directionalPattern = Pattern.compile(directionalRegex);
		Matcher matcher = directionalPattern.matcher(parsedStreet[0]);
		
		try 
		{
			if (matcher.matches()) 
			{
				int streetEndingIndex; 
				for (int i = parsedStreet.length - 1; i > 0; i--) 
				{
					for (int j = 0; j < SUFFIX_TABLE.length; j++)
					{
						if (SUFFIX_TABLE[j][1].equals(parsedStreet[i])) 
						{
							streetEndingIndex = i;
							if (!DIRECTIONAL_MAP.containsKey(parsedStreet[streetEndingIndex - 1])) 
							{
								if (!DIRECTIONAL_MAP.containsKey(parsedStreet[streetEndingIndex - 2])) 
								{
									normalizePredirectionals(streetEndingIndex - 3);
								} 
								else 
								{
									normalizePredirectionals(streetEndingIndex - 2);
								}
							}
						}
					}
				}
			} 
		} 
		catch (IndexOutOfBoundsException e) {}
	}
	
	private void normalizePredirectionals(int index) 
	{
		for (int i = index; i > 0; i--) 
		{
			if (DIRECTIONAL_MAP.containsKey(parsedStreet[i])) 
			{
				parsedStreet[i] = DIRECTIONAL_MAP.get(parsedStreet[i]);
			}
		}
	}

	private void postdirectionals() 
	{
		int streetEndingIndex;
		try 
		{
			for (int i = parsedStreet.length - 1; i > 0; i--) 
			{
				for (int j = 0; j < SUFFIX_TABLE.length; j++) 
				{
					if (SUFFIX_TABLE[j][1].equals(parsedStreet[i])) 
					{
						streetEndingIndex = i;
						normalizePostdirectionals(streetEndingIndex);
					}
				}
			}	
		} 
		catch (IndexOutOfBoundsException e) {}
	}
	
	private void normalizePostdirectionals(int index) 
	{
		for (int i = index; i < parsedStreet.length; i++) 
		{
			if (DIRECTIONAL_MAP.containsKey(parsedStreet[i])) 
			{
				parsedStreet[i] = DIRECTIONAL_MAP.get(parsedStreet[i]);
			}
		}		
	}

	public String formatFileLine()
	{
		return street + "\t" + city + "\t" + state + "\t" + 
	        zipFive + "\t" + zipFour;
	}
}