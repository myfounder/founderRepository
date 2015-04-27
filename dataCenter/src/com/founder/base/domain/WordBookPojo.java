/**
 * @file：WordBookPojo.java
 * @version: 1.0
 * @author: YeJianPing
 * @date：2015-2-12
 * @copyright: ©2015 Founder company limited copyright 
 */
package com.founder.base.domain;

import java.io.Serializable;

/**
 * @description: 数据字典pojo ,数据库表：W_WORDBOOK
 * @author: YeJianPing
 * @date：2015-2-12
 */
public class WordBookPojo implements Serializable {

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = -178521796711367123L;

	private Integer wb_id;
	private String wb_type;
	private String wb_code;
	private String wb_name;
	private String wb_note;
	private Integer wb_order;
	private String wb_state;

	/**
	 * @return wb_id : return the property wb_id.
	 */
	public Integer getWb_id() {
		return wb_id;
	}

	/**
	 * @param wbId
	 *            : set the property wb_id.
	 */
	public void setWb_id(Integer wbId) {
		wb_id = wbId;
	}

	/**
	 * @return wb_type : return the property wb_type.
	 */
	public String getWb_type() {
		return wb_type;
	}

	/**
	 * @param wbType
	 *            : set the property wb_type.
	 */
	public void setWb_type(String wbType) {
		wb_type = wbType;
	}

	/**
	 * @return wb_code : return the property wb_code.
	 */
	public String getWb_code() {
		return wb_code;
	}

	/**
	 * @param wbCode
	 *            : set the property wb_code.
	 */
	public void setWb_code(String wbCode) {
		wb_code = wbCode;
	}

	/**
	 * @return wb_name : return the property wb_name.
	 */
	public String getWb_name() {
		return wb_name;
	}

	/**
	 * @param wbName
	 *            : set the property wb_name.
	 */
	public void setWb_name(String wbName) {
		wb_name = wbName;
	}

	/**
	 * @return wb_note : return the property wb_note.
	 */
	public String getWb_note() {
		return wb_note;
	}

	/**
	 * @param wbNote
	 *            : set the property wb_note.
	 */
	public void setWb_note(String wbNote) {
		wb_note = wbNote;
	}

	/**
	 * @return wb_order : return the property wb_order.
	 */
	public Integer getWb_order() {
		return wb_order;
	}

	/**
	 * @param wbOrder
	 *            : set the property wb_order.
	 */
	public void setWb_order(Integer wbOrder) {
		wb_order = wbOrder;
	}

	/**
	 * @return wb_state : return the property wb_state.
	 */
	public String getWb_state() {
		return wb_state;
	}

	/**
	 * @param wbState
	 *            : set the property wb_state.
	 */
	public void setWb_state(String wbState) {
		wb_state = wbState;
	}

}
