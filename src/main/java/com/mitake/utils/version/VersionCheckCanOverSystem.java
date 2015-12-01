package com.mitake.utils.version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mitake.utils.version.VersionCheck.CheckResult;
import com.mitake.utils.version.VersionCheck.InputVersionCompare;


public class VersionCheckCanOverSystem extends VersionCheckAPI {
	private Logger logger = LoggerFactory.getLogger(VersionCheckCanOverSystem.class);

	@Override
	public CheckResult convertVersionCompare(InputVersionCompare inputVersion) {
		CheckResult checkResult = null;
		switch (inputVersion) {
		case isNew:
			checkResult = CheckResult.accept;
			break;

		case isOld:
			checkResult = CheckResult.recommendUpdate;
			break;

		case isEquals:
			checkResult = CheckResult.accept;
			break;
			
		case cantDefine:
			checkResult = CheckResult.forceUpdate;
			break;
		}
		return checkResult;
	}

	@Override
	public String convertCheckRs(CheckResult checkResult) {
		int rscode = 3;
		switch (checkResult) {
		case accept:
			rscode = 1;
			break;

		case recommendUpdate:
			rscode = 2;
			break;

		case forceUpdate:
			rscode = 3;
			break;
			
		case error:
			rscode = 4;
			break;
		}
		return String.valueOf(rscode);
	}
}