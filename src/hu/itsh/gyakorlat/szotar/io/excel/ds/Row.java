package hu.itsh.gyakorlat.szotar.io.excel.ds;

import java.time.LocalDateTime;

public class Row {

	int id;
	String timestamp;
	String prefix;
	String word;
	String suffix;
	String engExplain;
	String engExample;
	String hun0;
	String hun1;
	String hunExplain;
	String hunExample;
	int level;
	char lang;
	String wordClass;
	String form0;
	String form1;
	String form2;
	String form3;

	public Row(int id, String timestamp, String prefix, String word, String suffix, String engExplain,
			String engExample, String hun0, String hun1, String hunExplain, String hunExample, int level, char lang,
			String wordClass, String form0, String form1, String form2, String form3) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.prefix = prefix;
		this.word = word;
		this.suffix = suffix;
		this.engExplain = engExplain;
		this.engExample = engExample;
		this.hun0 = hun0;
		this.hun1 = hun1;
		this.hunExplain = hunExplain;
		this.hunExample = hunExample;
		this.level = level;
		this.lang = lang;
		this.wordClass = wordClass;
		this.form0 = form0;
		this.form1 = form1;
		this.form2 = form2;
		this.form3 = form3;
	}

	
	public Row() {
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getEngExplain() {
		return engExplain;
	}

	public void setEngExplain(String engExplain) {
		this.engExplain = engExplain;
	}

	public String getEngExample() {
		return engExample;
	}

	public void setEngExample(String engExample) {
		this.engExample = engExample;
	}

	public String getHun0() {
		return hun0;
	}

	public void setHun0(String hun0) {
		this.hun0 = hun0;
	}

	public String getHun1() {
		return hun1;
	}

	public void setHun1(String hun1) {
		this.hun1 = hun1;
	}

	public String getHunExplain() {
		return hunExplain;
	}

	public void setHunExplain(String hunExplain) {
		this.hunExplain = hunExplain;
	}

	public String getHunExample() {
		return hunExample;
	}

	public void setHunExample(String hunExample) {
		this.hunExample = hunExample;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public char getLang() {
		return lang;
	}

	public void setLang(char lang) {
		this.lang = lang;
	}

	public String getWordClass() {
		return wordClass;
	}

	public void setWordClass(String wordClass) {
		this.wordClass = wordClass;
	}

	public String getForm0() {
		return form0;
	}

	public void setForm0(String form0) {
		this.form0 = form0;
	}

	public String getForm1() {
		return form1;
	}

	public void setForm1(String form1) {
		this.form1 = form1;
	}

	public String getForm2() {
		return form2;
	}

	public void setForm2(String form2) {
		this.form2 = form2;
	}

	public String getForm3() {
		return form3;
	}

	public void setForm3(String form3) {
		this.form3 = form3;
	}


	@Override
	public String toString() {
		return "Row [id=" + id + ", timestamp=" + timestamp + ", prefix=" + prefix + ", word=" + word + ", suffix="
				+ suffix + ", engExplain=" + engExplain + ", engExample=" + engExample + ", hun0=" + hun0 + ", hun1="
				+ hun1 + ", hunExplain=" + hunExplain + ", hunExample=" + hunExample + ", level=" + level + ", lang="
				+ lang + ", wordClass=" + wordClass + ", form0=" + form0 + ", form1=" + form1 + ", form2=" + form2
				+ ", form3=" + form3 + "]";
	}
	
	
	

}
