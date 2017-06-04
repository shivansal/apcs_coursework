public class Senator implements Comparable<Senator>
{
	private String name;
	private int yearsInOffice;
	private int birthMonth;
	private int birthDay;
	private int birthYear;
	private String party;
	private String state;
	private int termMonth;
	private int termDay;
	private int termYear;

	public Senator(String n, int y, int bm, int bd, int by, String p, String s, int tm, int td, int ty)
	{
		name = n;
		yearsInOffice = y;
		birthDay = bm;
		birthMonth = bd;
		birthYear = by;
		party = p;
		state = s;
		termMonth = tm;
		termDay = td;
		termYear = ty;
	}



	public int compareTo(Senator other)
	{
		Senator sen = (Senator)other;
		if(this.birthYear!=sen.birthYear)
			return this.birthYear - sen.birthYear;
		if(this.birthMonth!=sen.birthMonth)
			return this.birthMonth - sen.birthMonth;
		if(this.birthDay!=sen.birthDay)
			return this.birthDay - sen.birthDay;
		return this.name.compareTo(sen.name);
	}

	public String getName ( )
	{
		return name;
	}

	public String toString()
	{
		return String.format("%-20s%-8d%-4d%-4d%-8d%-20s%-20s%-20d%-20d%-20d",name, birthDay, birthMonth, yearsInOffice, birthYear, party, state, termMonth, termDay, termYear);
		//return String.format();
	}
}
