package com.maan.Home.MasterController;



public class NumberToWordBean {
	private transient String string;

	final private transient String once[] = { "", "One", "Two", "Three",
			"Four", "Five", "Six", "Seven", "Eight", "Nine" };

	final private transient String hundred[] = { "Hundred", "Thousand", "Lakh",
			"Crore" };

	final private transient String twentys[] = { "Ten", "Eleven", "Twelve",
			"Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
			"Eighteen", "Ninteen" };
	
	final private transient String twentysdate[] = { "Tenth", "Eleventh", "Twelveth",
			"Thirteenth", "Fourteenth", "Fifteenth", "Sixteenth", "Seventeenth",
			"Eighteenth", "Ninteenth" };

	final private transient String tens[] = {

	"Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy", "Eighty",
			"Ninty" };
	final private transient String tensnorem[] = {
			"Twenteenth", "Thirteenth", "Fourteenth", "Fifteenth", "Sixteenth", "Seventeenth", "Eighteenth",
					"Ninteenth" };
	final private transient String days[] = { "", "First", "Second", "Third",
			"Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Nineth" };
	public String convertNumToWord(final int pNumber) {

		int reminder;
		string = "";
		int number = pNumber;
		int check;
		check = 1;
		while (number != 0) {
			switch (check) {
			case 1:
				reminder = number % 100;
				pass(reminder);
				if (number > 100 && reminder != 0) {
					display("and ");
				}
				number /= 100;
				break;

			case 2:
				reminder = number % 10;
				if (reminder != 0) {
					display(" " + hundred[0] + " ");
					pass(reminder);
				}
				number /= 10;
				break;

			case 3:
				reminder = number % 100;
				if (reminder != 0) {
					display(" " + hundred[1] + " ");
					pass(reminder);
				}
				number /= 100;
				break;

			case 4:
				reminder = number % 100;
				if (reminder != 0) {
					display(" " + hundred[2] + " ");
					pass(reminder);
				}
				number /= 100;
				break;

			case 5:
				reminder = number % 100;
				if (reminder != 0) {
					display(" " + hundred[3] + " ");
					pass(reminder);
				}
				number /= 100;
				break;

			default:
				break;

			}
			check++;
		}
		return string;
	}
	public void pass(int number)
	{
		if(number<0)
			number=-1*number;
		int reminder, quoteSent ;
		if ( number < 10 ){
			display ( once[number] ) ;
		}
		if ( number > 9 && number < 20 ){
			display ( twentys[number-10] ) ;
		}
		if ( number > 19 )
		{
			reminder = number % 10 ;
			if ( reminder == 0 )
			{
				quoteSent = number / 10 ;
				display ( tens[quoteSent-2] ) ;
			}
			else
			{
				quoteSent = number / 10 ;
				display ( once[reminder] ) ;
				display ( " " ) ;
				display ( tens[quoteSent-2] ) ;
			}
		}
	}

	public void display(final String pStr) {
		String tempString;
		tempString = string;
		string = pStr;
		string += tempString;
	}
	public String convertDateNumToWord(final int pNumber) {

		int reminder;
		string = "";
		int number = pNumber;
		int check;
		check = 1;
		while (number != 0) {
			switch (check) {
			case 1:
				reminder = number % 100;
				passDate(reminder);
				if (number > 100 && reminder != 0) {
					display("and ");
				}
				number /= 100;
				break;

			case 2:
				reminder = number % 10;
				if (reminder != 0) {
					display(" " + hundred[0] + " ");
					passDate(reminder);
				}
				number /= 10;
				break;

			case 3:
				reminder = number % 100;
				if (reminder != 0) {
					display(" " + hundred[1] + " ");
					passDate(reminder);
				}
				number /= 100;
				break;

			case 4:
				reminder = number % 100;
				if (reminder != 0) {
					display(" " + hundred[2] + " ");
					passDate(reminder);
				}
				number /= 100;
				break;

			case 5:
				reminder = number % 100;
				if (reminder != 0) {
					display(" " + hundred[3] + " ");
					passDate(reminder);
				}
				number /= 100;
				break;

			default:
				break;

			}
			check++;
		}
		return string;
	}
	public void passDate(int number)
	{
		int reminder, quoteSent ;
		if ( number < 10 ){
			display ( days[number] ) ;
		}
		if ( number > 9 && number < 20 ){
			display ( twentysdate[number-10] ) ;
		}
		if ( number > 19 )
		{
			reminder = number % 10 ;
			if ( reminder == 0 )
			{
				quoteSent = number / 10 ;
				display ( tensnorem[quoteSent-2] ) ;
			}
			else
			{
				quoteSent = number / 10 ;
				display ( days[reminder] ) ;
				display ( " " ) ;
				display ( tens[quoteSent-2] ) ;
			}
		}
	}

}