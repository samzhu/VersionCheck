package com.mitake.utils.version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mitake.utils.version.VersionCheck.CheckResult;
import com.mitake.utils.version.VersionCheck.InputVersionCompare;


public class TGCheckCanOverSystem extends VersionCheckAPI {
	private Logger logger = LoggerFactory.getLogger(TGCheckCanOverSystem.class);

	@Override
	public CheckResult convertVersionCompare(InputVersionCompare inputVersion) {
		CheckResult checkResult = null;
		switch (inputVersion) {
		case isNew:
			checkResult = CheckResult.accept;
			break;

		case isOld:
			checkResult = CheckResult.forceUpdate;
			break;

		case isEquals:
			checkResult = CheckResult.accept;
			break;
			
		case cantDefine:
			checkResult = CheckResult.error;
			break;
		}
		return checkResult;
	}

	@Override
	public String convertCheckRs(CheckResult checkResult) {
		int rscode = 3;
		switch (checkResult) {
		case accept:
			rscode = 0;
			break;

		case recommendUpdate:
			rscode = 2;
			break;

		case forceUpdate:
			rscode = 2;
			break;
			
		case error:
			rscode = 3;
			break;
		}
		return String.valueOf(rscode);
	}
}