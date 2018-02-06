package vmd;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import dto.OrderDto;
import service.OrderSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OrderViewVmd {
	@WireVariable
	private OrderSvc orderSvc;
	
	private OrderDto orderDto;

	public OrderSvc getOrderSvc() {
		return orderSvc;
	}

	public void setOrderSvc(OrderSvc orderSvc) {
		this.orderSvc = orderSvc;
	}

	public OrderDto getOrderDto() {
		return orderDto;
	}

	public void setOrderDto(OrderDto orderDto) {
		this.orderDto = orderDto;
	}
	
	@Init
	public void load()
	{
		orderDto=(OrderDto) Sessions.getCurrent().getAttribute("dto");
		
	}
	
	@Command()
	public void back()
	{
		Executions.sendRedirect("order.zul");
		
	}
	
	@Command()
	public void save()
	{
		orderSvc.save(orderDto);
		Messagebox.show("Data berhasil disimpan");
		Executions.sendRedirect("order.zul");
	}
}
