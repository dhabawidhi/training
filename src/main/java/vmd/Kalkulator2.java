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
public class Kalkulator2 {
	
	private int number;
	private int number1;
	private int number2;
	private String numbers="";
	private String value;
	private String param;
	private String numberPlaceholder="";
	
	public String getNumberPlaceholder() {
		return numberPlaceholder;
	}

	public void setNumberPlaceholder(String numberPlaceholder) {
		this.numberPlaceholder = numberPlaceholder;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber1() {
		return number1;
	}

	public void setNumber1(int number1) {
		this.number1 = number1;
	}

	public int getNumber2() {
		return number2;
	}

	public void setNumber2(int number2) {
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
		number1=0;
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
		number1=Integer.parseInt(numbers);
		numbers="";
	}
	
	@NotifyChange("numbers")
	@Command()
	public void inputParam(@BindingParam("param2") String param2)
	{
		param=param2;
		number1=Integer.parseInt(numbers);
		numberPlaceholder=numbers;
		numbers="";
		
	}
	
	@NotifyChange("numbers")
	@Command()
	public void backSpace()
	{
		numbers=numbers.substring(0, numbers.length()-1);
		
	}
	
	@NotifyChange("numbers")
	@Command()
	public void root()
	{
		int willSqrt=Integer.parseInt(numbers);
		willSqrt=(int) Math.sqrt(willSqrt);
		numbers=willSqrt+"";
		
	}
	
	
	@NotifyChange("numbers")
	@Command()
	public void clear()
	{
		numbers="";
		
	}
	
	
	@NotifyChange("numbers")
	@Command()
	public void result()
	{
		number2=Integer.parseInt(numbers);
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
	}
	
	@NotifyChange("numbers")
	@Command()
	public String getResult()
	{
		number2=Integer.parseInt(numbers);
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
