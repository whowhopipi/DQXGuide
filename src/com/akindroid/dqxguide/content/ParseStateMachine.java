package com.akindroid.dqxguide.content;

public interface ParseStateMachine {
	public void setStartTag(String sTag);
	public Object setEndTag(String eTag);
	public void setText(String text);
}
