package com.mitake.utils.version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mitake.utils.version.VersionCheck.CheckResult;
import com.mitake.utils.version.VersionCheck.InputVersionCompare;

public abstract class VersionCheckAPI {
	private Logger logger = LoggerFactory.getLogger(VersionCheckAPI.class);
	
	/**
	 * 就版本號碼來比對
	 * @param inputVersionNumber 1.1.1
	 * @param systemVersionNumber 1.1.1
	 * @return Enum
	 * @throws Exception
	 */
	protected InputVersionCompare compareVersionNumber(String inputVersionNumber,String systemVersionNumber){
		InputVersionCompare compareRs = null;
		try {
			String[] iva = inputVersionNumber.split("\\.");
			String[] sva = systemVersionNumber.split("\\.");
			for(int i = 0 ; i < sva.length ; i++){
				if(iva[i] == null){
					throw new RuntimeException("輸入版本號有問題 " + iva + " 第" + (i+1) + "碼為空值");
				}
				int svai = Integer.valueOf(sva[i]);
				int ivai = Integer.valueOf(iva[i]);
				if(svai > ivai){
					compareRs = InputVersionCompare.isOld;
					break;
				}else if(ivai > svai){
					compareRs = InputVersionCompare.isNew;
					break;
				}
			}
			if(compareRs == null){
				compareRs = InputVersionCompare.isEquals;
			}
		} catch (NumberFormatException e) {
			logger.error("input version ("+inputVersionNumber+") compare error ", e);
			compareRs = InputVersionCompare.cantDefine;
		}
		return compareRs;
	}
	
	
	/**
	 * 將版本新舊改成檢查結果
	 * @param inputVersion
	 * @return
	 */
	public abstract CheckResult convertVersionCompare(InputVersionCompare inputVersion);
	
	/**
	 * 將檢查結果換成代碼
	 * @param checkResult
	 * @return
	 */
	public abstract String convertCheckRs(CheckResult checkResult);

}
