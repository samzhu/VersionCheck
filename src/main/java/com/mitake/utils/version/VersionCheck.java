package com.mitake.utils.version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VersionCheck {
	private Logger logger = LoggerFactory.getLogger(VersionCheck.class);
	private String methodclassname;

	/**
	 * 通過、建議更新、強制更新、版本錯誤
	 * @author samzhu
	 *
	 */
	protected enum CheckResult {
		accept, recommendUpdate, forceUpdate, error
	}

	/**
	 * APP版本 較新、較舊、同版本
	 * @author samzhu
	 *
	 */
	protected enum InputVersionCompare {
		isNew, isOld, isEquals, cantDefine
	}
	
	public VersionCheck(String methodclassname){
		this.methodclassname = methodclassname;
	}

	/**
	 * 檢查方法
	 * @param inputVersion
	 * @param systemVersion
	 * @return String ReturnCode
	 */
	public String verify(String inputVersionStr,String systemVersionStr){
		VersionCheckAPI versioncheck = null;
		InputVersionCompare inputVersion = null;
		CheckResult checkResult = null;
		String rscode = null;
		try {
			versioncheck = (VersionCheckAPI) Class.forName("com.mitake.utils.version."+methodclassname).newInstance();
			inputVersion = versioncheck.compareVersionNumber(inputVersionStr, systemVersionStr);
			checkResult = versioncheck.convertVersionCompare(inputVersion);
			rscode = versioncheck.convertCheckRs(checkResult);
		} catch (Exception e) {
			logger.error("", e);
		}
		return rscode;
	}
}