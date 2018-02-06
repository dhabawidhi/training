package vmd;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.Button;
import org.zkoss.zul.Messagebox.ClickEvent;

import dto.OrderDto;
import service.OrderSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OrderVmd {
	@WireVariable
	private OrderSvc orderSvc;
	
	private List<OrderDto> orderDtos;
	private OrderDto orderDto;
	private String search;
	private Set<OrderDto> pickedItemSet= new HashSet<OrderDto>();
	
	
	
	
	public Set<OrderDto> getPickedItemSet() {
		return pickedItemSet;
	}
	public void setPickedItemSet(Set<OrderDto> pickedItemSet) {
		this.pickedItemSet = pickedItemSet;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public OrderSvc getOrderSvc() {
		return orderSvc;
	}
	public void setOrderSvc(OrderSvc orderSvc) {
		this.orderSvc = orderSvc;
	}
	public List<OrderDto> getOrderDtos() {
		return orderDtos;
	}
	public void setOrderDtos(List<OrderDto> orderDtos) {
		this.orderDtos = orderDtos;
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
		orderDtos=orderSvc.findAllOrder();
		
	}
	
	/*
//	@NotifyChange("orderDtos")
	@Command
	public void search()
	{
//		Messagebox.show(search);
		List<OrderDto> orderDtoSearch=orderSvc.findAll(search);
		if (orderDtoSearch.size()>0) {
			orderDtos=orderDtoSearch;
			BindUtils.postNotifyChange(null, null, this, "orderDtos");// sama seperti notify change secara program
		} else {
			Messagebox.show("Not found");
//			orderDtos=orderSvc.findAll();
//			BindUtils.postNotifyChange(null, null, this, "orderDtos");
		}
		
		
		
	}
	*/
	
	@Command
	public void delete()
	{
		if(orderDto!=null)
		{
			Messagebox.show("Apakah yakin mau di hapus?"," Perhatian",
			new Button[]{Button.YES,Button.NO}, 
			Messagebox.QUESTION,
			Button.NO,
			new EventListener<Messagebox.ClickEvent>()
			{
				public void onEvent(ClickEvent event)
				{
					if(Messagebox.ON_YES.equals(event.getName()))
					{
						orderSvc.delete(orderDto);
						orderDtos.remove(orderDto);
						orderDto=null;
						BindUtils.postNotifyChange(null, null, OrderVmd.this, "orderDtos");
						Messagebox.show("Data berhasil di hapus");
						
					}
					
					
				}
				
			});
			
		}
		else {
			Messagebox.show("Silahkan pilih data");
		}
		
	}
	
	
	
	@Command
	@NotifyChange("orderDto")
	public void pick(@BindingParam("checked") boolean isPicked, @BindingParam("picked") OrderDto item)
	{
		if(isPicked)
		{
			pickedItemSet.add(item);
			
		}
		else {
			pickedItemSet.remove(item);
			
		}
		orderDto=null;
		if(pickedItemSet.size()>0)
		{
			orderDto=(OrderDto) pickedItemSet.toArray()[0];
			
		}
		
	}
	
	@Command
	public void add()
	{
		orderDto= new OrderDto();
		Sessions.getCurrent().setAttribute("dto", orderDto);
		Executions.sendRedirect("order_detail.zul");
		
	}
	
	@Command()
	public void edit()
	{
		if(orderDto==null)
		{
			Messagebox.show("Silahkan pilih data");
			
		}
		else {
			Sessions.getCurrent().setAttribute("dto", orderDto);
			Executions.sendRedirect("order_detail.zul");
		}
		
	}
}
