package vmd;

import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import dto.ProductDto;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class KalkulatorBug {
	
	private Double number;
	private Double number1;
	private Double number2;
	private String numbers="";
	private String value;
	private String param;
	private String numberPlaceHolder="0";
	private int counter=0;
	
	public String getnumberPlaceHolder() {
		return numberPlaceHolder;
	}

	public void setnumberPlaceHolder(String numberPlaceHolder) {
		this.numberPlaceHolder = numberPlaceHolder;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public Double getNumber1() {
		return number1;
	}

	public void setNumber1(Double number1) {
		this.number1 = number1;
	}

	public Double getNumber2() {
		return number2;
	}

	public void setNumber2(Double number2) {
		this.number2 = number2;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Init
	public void load()
	{
		number1=(double) 0;
	}
	
	@NotifyChange("numbers")
	@Command()
	public void inputNumber(@BindingParam("input") String input)
	{
		numbers=numbers+input;
		
	}
	
	@NotifyChange("numbers")
	@Command()
	public void plus()
	{
		number1=Double.parseDouble(numbers);
		numbers="";
	}
	
	@NotifyChange({"numbers","numberPlaceHolder"})
	@Command()
	public void inputParam(@BindingParam("param2") String param2)
	{
		/*
		param=param2;
		number1=Double.parseDouble(numbers);
		numberPlaceHolder=numbers;
		numbers="";
		*/
		
		number1=Double.parseDouble(numbers);
		param=param2;
		if(counter==0)
		{
			
			number1=Double.parseDouble(numbers);
			numberPlaceHolder=numbers;
			numbers="";
		}
		else 
		{
			if(param.equals("+"))
			{
				number2=Double.parseDouble(numbers);
				numberPlaceHolder=number1+number2+"";
				number1=Double.parseDouble(numberPlaceHolder);
				numbers="";
			}
			else if(param.equals("-"))
			{
				number2=Double.parseDouble(numbers);
				numberPlaceHolder=number1+number2+"";
				number1=Double.parseDouble(numberPlaceHolder);
				numbers="";
			}
			else if(param.equals("*"))
			{
				number2=Double.parseDouble(numbers);
				numberPlaceHolder=number1*number2+"";
				number1=Double.parseDouble(numberPlaceHolder);
				numbers="";
			}
			else if(param.equals("/"))
			{
				number2=Double.parseDouble(numbers);
				numberPlaceHolder=number1/number2+"";
				number1=Double.parseDouble(numberPlaceHolder);
				numbers="";
			}
		}
		
		counter++;
		
	}
	
	@NotifyChange("numbers")
	@Command()
	public void inputPoint()
	{
		
		if(!numbers.contains("."))
		{
			numbers=numbers+".";
			
		}
		
	}
	
	@NotifyChange({"numbers","numberPlaceHolder"})
	@Command()
	public void backSpace()
	{
		numbers=numbers.substring(0, numbers.length()-1);
		if(numbers.length()==0)
		{
			numberPlaceHolder="0";
			
		}
	}
	
	@NotifyChange("numbers")
	@Command()
	public void satuPerX()
	{
		Double willPerX;
		try {
			willPerX=(double) Integer.parseInt(numbers);
		} catch (Exception e) {
			// TODO: handle exception
			willPerX=Double.parseDouble(numbers);
		}
		numbers=1/willPerX+"";
	}
	
	@NotifyChange("numbers")
	@Command()
	public void root()
	{
		int willSqrt;
		try {
			willSqrt=Integer.parseInt(numbers);
		} catch (Exception e) {
			// TODO: handle exception
			willSqrt=(int) Double.parseDouble(numbers);
		}
		
		willSqrt=(int) Math.sqrt(willSqrt);
		numbers=willSqrt+"";
		
	}
	
	@NotifyChange("numbers")
	@Command()
	public void plusMinus()
	{
		if(numbers.substring(0,1).equals("-"))
		{
			numbers=numbers.substring(1, numbers.length());
			
		}
		else {
			numbers="-"+numbers;
		}
	}
	
	@NotifyChange({"numbers","numberPlaceHolder"})
	@Command()
	public void clear()
	{
		numberPlaceHolder="0";
		numbers="";
		
	}
	
	
	@NotifyChange({"numberPlaceHolder","numbers"})
	@Command()
	public void result()
	{
		/*
		number2=Double.parseDouble(numbers);
		if(param.equals("+"))
		{
			numbers=number1+number2+"";
//			number1=Double.parseDouble(numberPlaceHolder);
//			numbers="";
			numberPlaceHolder=number1+number2+"";
		}
		else if(param.equals("-"))
		{
			numbers=number1-number2+"";	
		}
		else if(param.equals("*"))
		{
			numbers=number1*number2+"";	
		}
		else if(param.equals("/"))
		{
			numbers=number1/number2+"";	
		}
		else if(param.equals("%"))
		{
			numbers=number1%number2+"";	
		}
		*/
		/*
		if(param.equals("+"))
		{
			number2=Double.parseDouble(numbers);
			numberPlaceHolder=number1+number2+"";
			number1=Double.parseDouble(numberPlaceHolder);
			numbers="";
		}
		else if(param.equals("-"))
		{
			number2=Double.parseDouble(numbers);
			numberPlaceHolder=number1+number2+"";
			number1=Double.parseDouble(numberPlaceHolder);
			numbers="";
		}
		else if(param.equals("*"))
		{
			number2=Double.parseDouble(numbers);
			numberPlaceHolder=number1*number2+"";
			number1=Double.parseDouble(numberPlaceHolder);
			numbers="";
		}
		else if(param.equals("/"))
		{
			number2=Double.parseDouble(numbers);
			numberPlaceHolder=number1/number2+"";
			number1=Double.parseDouble(numberPlaceHolder);
			numbers="";
		}
		*/
	}
	
	@NotifyChange("numbers")
	@Command()
	public String getResult()
	{
		number2=Double.parseDouble(numbers);
		if(param.equals("+"))
		{
			numbers=number1+number2+"";	
		}
		else if(param.equals("-"))
		{
			numbers=number1-number2+"";	
		}
		else if(param.equals("*"))
		{
			numbers=number1*number2+"";	
		}
		else if(param.equals("/"))
		{
			numbers=number1/number2+"";	
		}
		else if(param.equals("%"))
		{
			numbers=number1%number2+"";	
		}
		return numbers;
	}
}
