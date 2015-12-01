package com.mitake.sam.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mitake.utils.version.VersionCheck;

public class TGJunitTest {
	private VersionCheck versioncheck;


	@Before
	public void setUpBeforeClass() throws Exception {
		this.versioncheck = new VersionCheck("TGCheckCanOverSystem");
	}

	@Test
	public void isOld() {
		String inputVersionStr = "1.1.1";
		String systemVersionStr = "1.1.2";
		assertEquals(this.versioncheck.verify(inputVersionStr, systemVersionStr), "2");
	}

	@Test
	public void isNew() {
		String inputVersionStr = "1.1.3";
		String systemVersionStr = "1.1.2";
		assertEquals(this.versioncheck.verify(inputVersionStr, systemVersionStr), "0");
	}
	
	@Test
	public void isSame() {
		String inputVersionStr = "1.1.2";
		String systemVersionStr = "1.1.2";
		assertEquals(this.versioncheck.verify(inputVersionStr, systemVersionStr), "0");
	}
	
	@Test
	public void isUnknow() {
		String inputVersionStr = "G1.1.2";
		String systemVersionStr = "1.1.2";
		assertEquals(this.versioncheck.verify(inputVersionStr, systemVersionStr), "3");
	}

}
