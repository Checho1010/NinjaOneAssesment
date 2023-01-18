package com.ninjaone.backendinterviewproject.utils;

/**
 * @author CGomez Enum to specify the different types of operating systems
 */
public enum TypeEnum {

	WINDOWS("Windows", "Microsoft Windows"), MACINTOSH("Mac", "Macintosh"), LINUX("Linux", "Linux Operative System"),
	ANDROID("Android", "Androis OS");

	private String code;
	private String name;

	private TypeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	/**
	 * Validate if the type of operating system of the Device or Service matchs with
	 * at least one of the allowed types
	 * 
	 * @param aType operating system type given to be saved
	 * @return true if the given type matchs with the allowed ones, otherwise
	 *         returns false
	 */
	static public boolean isValidCode(String aType) {
		TypeEnum[] aTypes = TypeEnum.values();
		for (TypeEnum type : aTypes)
			if (type.code.equals(aType))
				return true;
		return false;
	}
}