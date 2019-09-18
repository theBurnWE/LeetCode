package com.shcepp.shdippsvr.business.exception;

/**
 * @version $Id: BeanPropertyInvalidException.java 3629 2014-01-02 01:01:21Z yksun $
 * @author itea
 *
 */
public class BeanPropertyInvalidException extends Exception {

	private static final long serialVersionUID = -6119851866001850759L;
	
	private String focusId;
	
	private String tabsId;

	private int tabsIndex;
	
	private String addIndex;
	
	/**
	 * @return the addIndex
	 */
	public String getAddIndex() {
		return addIndex;
	}

	/**
	 * @param addIndex the addIndex to set
	 */
	public void setAddIndex(String addIndex) {
		this.addIndex = addIndex;
	}

	/**
	 * @param tipMessage
	 * @param focusId
	 * @param tabsId
	 * @param tabsIndex
	 */
	public BeanPropertyInvalidException(String message, String focusId,
                                        String tabsId, int tabsIndex) {
		super(message);
		this.focusId = focusId;
		this.tabsId = tabsId;
		this.tabsIndex = tabsIndex;
	}

	public BeanPropertyInvalidException(String message, String focusId,
                                        String tabsId, int tabsIndex, String addIndex) {
		this(message, focusId, tabsId, tabsIndex);
		this.addIndex=addIndex;
	}
	/**
	 * @return the focusId
	 */
	public String getFocusId() {
		return focusId;
	}

	/**
	 * @param focusId the focusId to set
	 */
	public void setFocusId(String focusId) {
		this.focusId = focusId;
	}

	/**
	 * @return the tabsId
	 */
	public String getTabsId() {
		return tabsId;
	}

	/**
	 * @param tabsId the tabsId to set
	 */
	public void setTabsId(String tabsId) {
		this.tabsId = tabsId;
	}

	/**
	 * @return the tabsIndex
	 */
	public int getTabsIndex() {
		return tabsIndex;
	}

	/**
	 * @param tabsIndex the tabsIndex to set
	 */
	public void setTabsIndex(int tabsIndex) {
		this.tabsIndex = tabsIndex;
	}
	
	public BeanPropertyInvalidException() {
		super();
	}

	public BeanPropertyInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public BeanPropertyInvalidException(String message) {
		super(message);
	}

	public BeanPropertyInvalidException(Throwable cause) {
		super(cause);
	}

}
