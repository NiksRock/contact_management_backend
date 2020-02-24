package com.contactmanagement.util;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactManagementConstants {

	public static String getDefaultDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	public static String getDefaultDateFormat = "yyyy-MM-dd";
	public static String getMonthDateFormat = "MM/dd";

	public static String dataFetchMessage = "data Fetched Successfully";
	public static String saveMessage = "Successfully Saved";
	public static String updateMessage = "Updated Successfully";
	public static String ErrorInSaveMessage = "Error in Saving Record";
	public static String ErrorInUpdateMessage = "Error in Updating Record";
	public static String deleteSuccessMessage = "Deleted Successfully.";
	public static String deleteFailMessage = "Can not deleted record.";
	public static String ErrorInSearchMessage = "Error on Serching  Record";

	public static long defaultPageNumber = 1;
	public static long defaultRecordPerPage = 10;

	public static Object getFailedText = "Failed";

	public static String getErrorStatus = "Error";
	public static String getSuccessStatus = "Success";
	public static String getWarningStatus = "Warning";
	public static String getInfoStatus = "INFO";
	
	public static String getSuccessMsgForPasswordChange = "Successfully changed the password";
	public static String getSuccessMsgForSave = "Successfully Saved";
	public static String getSuccessMsgForUpdate = "Successfully Updated";
	public static String getSuccessMsgForDelete = "Successfully Deleted";
	public static String getErrorMsgForPasswordChange = "Error in changing the password";
	public static String getNoDataFoundMsg = "No Data Found";
	private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	// Validetor
	public static String getInvalidEmailError = "Invalid Email Input";
	public static String getInvalidPhoneNumberError = "Invalid Phone Number Input";
	public static String getUserNamePrecentError = "User Name Already Precent ";
	public static String getInvalidPasswordCriteriaError = "Password Policy Mismatch";
	public static String getMinimumTextUserNameError = "Minimum 4 Cherector User Name Require";

	public static int minimumTextRequire = 4;
	public static int minimumPasswordLength = 6;
	public static int maximumPasswordLength = 10;


	public static HashMap<String, String> getEntityPrimaryKey = new HashMap<String, String>() {
		{
			put("User", "getUserid");
		}
	};

	public static boolean emailValidator(String email) {
		if (email == null) {
			return false;
		}

		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}

	public static boolean phoneNumberValidator(String phoneNo) {
		try {
			return phoneNo.matches("\\d{10}");
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean checkPasswordCriteria(String password) {
		return password.length() >= ContactManagementConstants.minimumPasswordLength
				&& password.length() <= ContactManagementConstants.maximumPasswordLength ? true : false;
	}
}
