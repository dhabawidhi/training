package vmd;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import dto.OrderDetailDto;
import service.OrderDetailSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OrderDetailViewVmd {
	/*
	@WireVariable
	private OrderDetailSvc orderDetailSvc;
	
	private OrderDetailDto orderDetailDto;

	public OrderDetailSvc getOrderDetailSvc() {
		return orderDetailSvc;
	}

	public void setOrderDetailSvc(OrderDetailSvc orderDetailSvc) {
		this.orderDetailSvc = orderDetailSvc;
	}

	public OrderDetailDto getOrderDetailDto() {
		return orderDetailDto;
	}

	public void setOrderDetailDto(OrderDetailDto orderDetailDto) {
		this.orderDetailDto = orderDetailDto;
	}
	
	@Init
	public void load()
	{
		orderDetailDto=(OrderDetailDto) Sessions.getCurrent().getAttribute("dto");
		
	}
	
	@Command()
	public void back()
	{
		Executions.sendRedirect("order_detail.zul");
		
	}
	
	@Command()
	public void save()
	{
		orderDetailSvc.save(orderDetailDto);
		Messagebox.show("Data berhasil disimpan");
		Executions.sendRedirect("order_detail.zul");
	}
	*/
}
