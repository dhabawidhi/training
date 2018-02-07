package vmd;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.omg.CORBA.ARG_IN;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.Button;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Window;

import dto.OrderDetailDto;
import dto.OrderDto;
import dto.ProductDto;
import service.OrderDetailSvc;
import service.ProductSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProductViewVmd {
	@WireVariable
	private ProductSvc productSvc;
	
	@WireVariable
	private OrderDetailSvc orderDetailSvc;
	
	
	private List<ProductDto> productDtos;
	private ProductDto productDto;
	private int quantity=0;
	private int subTotal;
	private String orderId;
	private List<OrderDetailDto> orderDetailDtos;
	

	
	
	public List<OrderDetailDto> getOrderDetailDtos() {
		return orderDetailDtos;
	}



	public void setOrderDetailDtos(List<OrderDetailDto> orderDetailDtos) {
		this.orderDetailDtos = orderDetailDtos;
	}



	public String getOrderId() {
		return orderId;
	}



	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public int getSubTotal() {
		return subTotal;
	}



	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}



	public ProductSvc getProductSvc() {
		return productSvc;
	}



	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}



	public List<ProductDto> getProductDtos() {
		return productDtos;
	}



	public void setProductDtos(List<ProductDto> productDtos) {
		this.productDtos = productDtos;
	}



	public ProductDto getProductDto() {
		return productDto;
	}



	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}



	@Init
	@NotifyChange({"quantity","subTotal"})
	public void load(@ExecutionArgParam("orderId") String orderId)
	{
		this.orderId=orderId;
		productDtos=productSvc.findAll();
		
		orderDetailDtos = (List<OrderDetailDto>) Sessions.getCurrent().getAttribute("orderDetailDtos");
		
//		for(ProductDto productDto:productDtos)
//		{
//			subTotal=quantity*productDto.getProdPrice();
//			
//		}
//		try {
//			subTotal=quantity*productDto.getProdPrice();
//		} catch (Exception e) {
//			quantity=0;
//			subTotal=0;
//		}
	}
	
	@Wire
	private Window window;
	
	@Command
    public void back() {
        window.onClose();
    }
	
	@Command
	@NotifyChange("subTotal")
    public void calculate() {
		subTotal=quantity*productDto.getProdPrice();

    }
	
	@Command
	public void saveProduct() {
		System.out.println(orderId);
		OrderDetailDto orderDetailDto=new OrderDetailDto();
		orderDetailDto.setOrderId(orderId);
		orderDetailDto.setProdId(productDto.getProdId());
		orderDetailDto.setProdPrice(productDto.getProdPrice());
		orderDetailDto.setProdQty(quantity);
		orderDetailDto.setSubTotal(subTotal);
		
		orderDetailDtos.add(orderDetailDto);
		orderDetailSvc.save(orderDetailDto);
		Sessions.getCurrent().setAttribute("orderDetailDtosUpdate", orderDetailDtos);
		Executions.sendRedirect("order_detail.zul");
	}	
}
