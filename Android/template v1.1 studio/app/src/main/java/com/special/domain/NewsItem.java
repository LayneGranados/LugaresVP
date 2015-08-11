package com.special.domain;

public class NewsItem {

private String headline;
    
    private String string1;
    private String string2;
    private String string3;
    private String string4;
    
    
 
    public NewsItem() {
	}

	public NewsItem(String headline, String string1, String string2,
			String string3, String string4) {
		this.headline = headline;
		this.string1 = string1;
		this.string2 = string2;
		this.string3 = string3;
		this.string4 = string4;
	}

	public String getHeadline() {
        return headline;
    }
 
    public void setHeadline(String headline) {
        this.headline = headline;
    }

	public String getstring1() {
		return this.string1;
	}

	public void setstring1(String string1) {
		this.string1 = string1;
	}

	public String getstring2() {
		return this.string2;
	}

	public void setstring2(String string2) {
		this.string2 = string2;
	}

	public String getstring3() {
		return this.string3;
	}

	public void setstring3(String string3) {
		this.string3 = string3;
	}

	public String getstring4() {
		return string4;
	}

	public void setstring4(String string4) {
		this.string4 = string4;
	}

	@Override
	public String toString() {
		return "NewsItem [headline=" + this.headline + ", string1=" + this.string1
				+ ", string2=" + this.string2 + ", string3=" + this.string3
				+ ", string4=" + this.string4 + "]";
	}
	
}
