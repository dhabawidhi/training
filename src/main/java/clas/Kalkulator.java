package clas;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;
/**
 *
 * @author aerdy
 */
public class Kalkulator extends GenericForwardComposer {
 private float temporaryValue;
 private Textbox textInput;
 private int currentOperator;
 

 private static final long serialVersionUID = -4832335933787054924L;
 
 @Override
 public void doAfterCompose(Component comp) throws Exception {
  super.doAfterCompose(comp);
 }
 
 public void onClick$btnBackSpace(Event event){
  textInput.setText(textInput.getText().substring(0, textInput.getText().length() - 1));
 }
 
 public void onClick$btnCE(Event event){
  textInput.setText("");
 }
 
 public void onClick$btnC(Event event){
  temporaryValue = 0;
  textInput.setText("");
 }
 
 public void onClick$btnPlus(Event event){
  currentOperator = 1;
  temporaryValue = Float.parseFloat(textInput.getText()) + temporaryValue ;
  textInput.setText("");
 }
 
 public void onClick$btnMinus(Event event){
  currentOperator = 2;
  temporaryValue =   Float.parseFloat(textInput.getText())-temporaryValue ;
  textInput.setText("");
 }
 
 public void onClick$btnBagi(Event event){
  currentOperator = 3;
  temporaryValue = temporaryValue / Float.parseFloat(textInput.getText());
  textInput.setText("");
 }
 
 public void onClick$btnKali(Event event){
  currentOperator = 4;
  temporaryValue = temporaryValue * Float.parseFloat(textInput.getText());
  textInput.setText("");
 }
 public void onClick$sin(Event event){
    
    float a = Float.parseFloat(textInput.getText());
     temporaryValue =(float) Math.sin(a);
     textInput.setText(""+temporaryValue);
      
 }
  public void onClick$cos(Event event){
    
    float a = Float.parseFloat(textInput.getText());
     temporaryValue =(float) Math.cos(a);
     textInput.setText(""+temporaryValue);
      
 }
    public void onClick$tan(Event event){
    
    float a = Float.parseFloat(textInput.getText());
     temporaryValue =(float) Math.tan(a);
     textInput.setText(""+temporaryValue);
      
 }
   public void onClick$xy(Event event){
    currentOperator = 5;
    float a = Float.parseFloat(textInput.getText());
     temporaryValue =(float) Math.pow(temporaryValue, a);
     textInput.setText("");
      
 }
 
 public void onClick$btn0(Event event){
  textInput.setText(textInput.getText() + "0");
 }
 
 public void onClick$btn1(Event event){
  textInput.setText(textInput.getText() + "1");
 }
 
 public void onClick$btn2(Event event){
  textInput.setText(textInput.getText() + "2");
 }
 
 public void onClick$btn3(Event event){
  textInput.setText(textInput.getText() + "3");
 }
 
 public void onClick$btn4(Event event){
  textInput.setText(textInput.getText() + "4");
 }
 
 public void onClick$btn5(Event event){
  textInput.setText(textInput.getText() + "5");
 }
 
 public void onClick$btn6(Event event){
  textInput.setText(textInput.getText() + "6");
 }
 
 public void onClick$btn7(Event event){
  textInput.setText(textInput.getText() + "7");
 }
 
 public void onClick$btn8(Event event){
  textInput.setText(textInput.getText() + "8");
 }
 
 public void onClick$btn9(Event event){
  textInput.setText(textInput.getText() + "9");
 }
 
 public void onClick$submit(Event event){
  switch (currentOperator) {
  case 1:{
   onClick$btnPlus(null);
   break;
    }
  case 2:{
   onClick$btnMinus(null);
   break;
    }
  case 3:{
   onClick$btnBagi(null);
   break;
    }
  case 4:{
   onClick$btnKali(null);
   break;
    }
      case 5:{
   onClick$xy(null);
   break;
    }
 
  default:
   alert("Operator tidak dikenal");
   break;
  }
  textInput.setText(String.valueOf(temporaryValue));
 }
}