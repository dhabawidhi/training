package vmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import org.zkoss.zul.Window;

import dto.CustomerDto;
import dto.OrderDetailDto;
import dto.OrderDto;
import dto.ProductDto;
import service.CustomerSvc;
import service.OrderDetailSvc;
import service.OrderSvc;
import service.ProductSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OrderDetailVmd {
	@WireVariable
	private OrderSvc orderSvc;
	
	@WireVariable
	private CustomerSvc customerSvc;
	
	@WireVariable
	private ProductSvc productSvc;
	
	@WireVariable
	private OrderDetailSvc orderDetailSvc;
	
	
	private OrderDto orderDto;
	private List<CustomerDto> customerDtos;
	private List<OrderDetailDto> orderDetailDtos = new ArrayList<OrderDetailDto>();
	private OrderDetailDto orderDetailDto;
	private boolean statusPopUp;
	private List<ProductDto> productDtos;
	private ProductDto productDto;
	private CustomerDto customerDto;
	private int counterCekDto;
	
	

	public int getCounterCekDto() {
		return counterCekDto;
	}

	public void setCounterCekDto(int counterCekDto) {
		this.counterCekDto = counterCekDto;
	}

	public CustomerDto getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}

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
	
	
	
	public CustomerSvc getCustomerSvc() {
		return customerSvc;
	}

	public void setCustomerSvc(CustomerSvc customerSvc) {
		this.customerSvc = customerSvc;
	}

	public ProductSvc getProductSvc() {
		return productSvc;
	}

	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
	}

	public List<CustomerDto> getCustomerDtos() {
		return customerDtos;
	}

	public void setCustomerDtos(List<CustomerDto> customerDtos) {
		this.customerDtos = customerDtos;
	}

	public List<OrderDetailDto> getOrderDetailDtos() {
		return orderDetailDtos;
	}

	public void setOrderDetailDtos(List<OrderDetailDto> orderDetailDtos) {
		this.orderDetailDtos = orderDetailDtos;
	}

	public OrderDetailDto getOrderDetailDto() {
		return orderDetailDto;
	}

	public void setOrderDetailDto(OrderDetailDto orderDetailDto) {
		this.orderDetailDto = orderDetailDto;
	}

	public boolean isStatusPopUp() {
		return statusPopUp;
	}

	public void setStatusPopUp(boolean statusPopUp) {
		this.statusPopUp = statusPopUp;
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
	public void load()
	{
		orderDto = (OrderDto) Sessions.getCurrent().getAttribute("dto");
		customerDtos = customerSvc.findAll();
		productDtos = productSvc.findAll();
		if(orderDto.getOrderId() != null){
			customerDto = customerSvc.findOne(orderDto.getCustId());
			orderDetailDtos = orderDto.getOrderDetailDtos();
			
		}
		Sessions.getCurrent().setAttribute("orderDetailDtos", orderDetailDtos);
		
		orderDetailDtos = (List<OrderDetailDto>) Sessions.getCurrent().getAttribute("orderDetailDtosUpdate");
		counterCekDto=0;
	}
	
	@Command()
	public void save()
	{
		orderDto.setOrderDetailDtos(orderDetailDtos);
		orderDto.setCustId(customerDto.getCustId());
		
		orderSvc.save(orderDto);
		Messagebox.show("Data berhasil disimpan");
		Executions.sendRedirect("order.zul");
	}
	
	@Command()
	public void back()
	{
		Executions.sendRedirect("order.zul");
		
	}
	
	@Command
	@NotifyChange("orderDetailDtos")
	public void deleteProduct()
	{
		if(orderDetailDto!=null)
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
						orderDetailSvc.delete(orderDetailDto);
						orderDetailDtos.remove(orderDetailDto);
						orderDetailDto=null;
						BindUtils.postNotifyChange(null, null, OrderDetailVmd.this, "orderDetailDtos");
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
	@NotifyChange("orderDetailDtos")
	public void addProduct() {
        // TOS should be checked before accepting order
//        if(tosCheckbox.isChecked()) {
//            carService.order(((ListModelList<OrderItem>)orderItemsModel));
            // show result
//            Map<String, Object> arguments = new HashMap<String, Object>();
//            arguments.put("orderItems", orderItemsModel);
//            arguments.put("totalPrice", getTotalPrice());
		Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("orderId", orderDto.getOrderId());
		String template = "/product_view.zul";
        Window window = (Window)Executions.createComponents(template, null, arguments);
        window.doModal();
        
        counterCekDto=1;
        
    }
	
	/*
	@WireVariable
	private OrderDetailSvc orderDetailSvc;
	
	private List<OrderDetailDto> orderDetailDtos;
	private OrderDetailDto orderDetailDto;
	private String search;
	private Set<OrderDetailDto> pickedItemSet= new HashSet<OrderDetailDto>();
	
	
	
	
	public Set<OrderDetailDto> getPickedItemSet() {
		return pickedItemSet;
	}
	public void setPickedItemSet(Set<OrderDetailDto> pickedItemSet) {
		this.pickedItemSet = pickedItemSet;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public OrderDetailSvc getOrderDetailSvc() {
		return orderDetailSvc;
	}
	public void setOrderDetailSvc(OrderDetailSvc orderDetailSvc) {
		this.orderDetailSvc = orderDetailSvc;
	}
	public List<OrderDetailDto> getOrderDetailDtos() {
		return orderDetailDtos;
	}
	public void setOrderDetailDtos(List<OrderDetailDto> orderDetailDtos) {
		this.orderDetailDtos = orderDetailDtos;
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
		orderDetailDtos=orderDetailSvc.findAll();
		
	}
	
	
////	@NotifyChange("orderDetailDtos")
//	@Command
//	public void search()
//	{
////		Messagebox.show(search);
//		List<OrderDetailDto> orderDetailDtoSearch=orderDetailSvc.findAll(search);
//		if (orderDetailDtoSearch.size()>0) {
//			orderDetailDtos=orderDetailDtoSearch;
//			BindUtils.postNotifyChange(null, null, this, "orderDetailDtos");// sama seperti notify change secara program
//		} else {
//			Messagebox.show("Not found");
////			orderDetailDtos=orderDetailSvc.findAll();
////			BindUtils.postNotifyChange(null, null, this, "orderDetailDtos");
//		}
//		
//		
//		
//	}
//	
	
	@Command
	public void delete()
	{
		if(orderDetailDto!=null)
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
						orderDetailSvc.delete(orderDetailDto);
						orderDetailDtos.remove(orderDetailDto);
						orderDetailDto=null;
						BindUtils.postNotifyChange(null, null, OrderDetailVmd.this, "orderDetailDtos");
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
	@NotifyChange("orderDetailDto")
	public void pick(@BindingParam("checked") boolean isPicked, @BindingParam("picked") OrderDetailDto item)
	{
		if(isPicked)
		{
			pickedItemSet.add(item);
			
		}
		else {
			pickedItemSet.remove(item);
			
		}
		orderDetailDto=null;
		if(pickedItemSet.size()>0)
		{
			orderDetailDto=(OrderDetailDto) pickedItemSet.toArray()[0];
			
		}
		
	}
	
	@Command
	public void add()
	{
		orderDetailDto= new OrderDetailDto();
		Sessions.getCurrent().setAttribute("dto", orderDetailDto);
		Executions.sendRedirect("orderDetail_detail_view.zul");
		
	}
	
	@Command()
	public void edit()
	{
		if(orderDetailDto==null)
		{
			Messagebox.show("Silahkan pilih data");
			
		}
		else {
			Sessions.getCurrent().setAttribute("dto", orderDetailDto);
			Executions.sendRedirect("order_detail_view.zul");
		}
		
	}
	*/
}
