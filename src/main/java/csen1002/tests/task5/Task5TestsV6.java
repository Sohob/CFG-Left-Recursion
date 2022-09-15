package csen1002.tests.task5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import csen1002.main.task5.CFG;

class Task5TestsV6 {

	@Test
	@Timeout(5)
	public void testReg1() {
		CFG cfg = new CFG("S,Ps;P,PpP,OPY,PYp,Y;O,OpP,Pp,Po,PO;Y,Yo,Oy,y");
		assertEquals("S,Ps;P,OPYP',YP';P',pPP',YpP',e;O,YP'pO',YP'oO',YP'OO';O',pPO',PYP'pO',PYP'oO',PYP'OO',e;Y,yY';Y',oY',P'pO'yY',P'oO'yY',P'OO'yY',e",cfg.lre());
}
	
	@Test
	@Timeout(5)
	public void testReg2() {
		CFG cfg = new CFG("S,Ds;D,Nn,Dd,Oo,Do;O,DOn,No,On,n;N,Nd,On,d");
		assertEquals("S,Ds;D,NnD',OoD';D',dD',oD',e;O,NnD'OnO',NoO',nO';O',oD'OnO',nO',e;N,nO'nN',dN';N',dN',nD'OnO'nN',oO'nN',e", cfg.lre());
	}
	
	@Test
	@Timeout(5)
	public void testReg3() {
		CFG cfg = new CFG("S,vb;V,vb,VB,vbn;B,Bv,n;N,Nn,Bbn,n");
		assertEquals("S,vb;V,vbV',vbnV';V',BV',e;B,nB';B',vB',e;N,nB'bnN',nN';N',nN',e", cfg.lre());
	}
	
	@Test
	@Timeout(5)
	public void testReg4() {
		CFG cfg = new CFG("S,nV;N,Nn,Bbn,n;V,vb,VB,vbn;B,Bv,n");
		assertEquals("S,nV;N,BbnN',nN';N',nN',e;V,vbV',vbnV';V',BV',e;B,nB';B',vB',e",cfg.lre());
	}
	
	@Test
	@Timeout(5)
	public void testReg5() {
		CFG cfg = new CFG("S,Io;I,ipo,opi,Op,Ip;O,OI,Io,IO;P,OPIi,opi,Iop");
		assertEquals("S,Io;I,ipoI',opiI',OpI';I',pI',e;O,ipoI'oO',opiI'oO',ipoI'OO',opiI'OO';O',IO',pI'oO',pI'OO',e;P,ipoI'oO'PIi,opiI'oO'PIi,ipoI'OO'PIi,opiI'OO'PIi,opi,ipoI'op,opiI'op,ipoI'oO'pI'op,opiI'oO'pI'op,ipoI'OO'pI'op,opiI'OO'pI'op", cfg.lre());
	}
	
}