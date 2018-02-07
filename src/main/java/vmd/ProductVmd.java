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

import dto.ProductDto;
import service.ProductSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProductVmd {
	@WireVariable
	private ProductSvc productSvc;
	
	private List<ProductDto> productDtos;
	private ProductDto productDto;
	private String search;
	private Set<ProductDto> pickedItemSet= new HashSet<ProductDto>();
	
	
	
	
	public Set<ProductDto> getPickedItemSet() {
		return pickedItemSet;
	}
	public void setPickedItemSet(Set<ProductDto> pickedItemSet) {
		this.pickedItemSet = pickedItemSet;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
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
	public void load()
	{
		
		productDtos=productSvc.findAll();
		
	}
	
	
//	@NotifyChange("productDtos")
	@Command
	public void search()
	{
//		Messagebox.show(search);
		List<ProductDto> productDtoSearch=productSvc.findAll(search);
		if (productDtoSearch.size()>0) {
			productDtos=productDtoSearch;
			BindUtils.postNotifyChange(null, null, this, "productDtos");// sama seperti notify change secara program
		} else {
			Messagebox.show("Not found");
//			productDtos=productSvc.findAll();
//			BindUtils.postNotifyChange(null, null, this, "productDtos");
		}
		
		
		
	}
	
	@Command
	public void delete()
	{
		if(productDto!=null)
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
						productSvc.delete(productDto);
						productDtos.remove(productDto);
						productDto=null;
						BindUtils.postNotifyChange(null, null, ProductVmd.this, "productDtos");
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
	@NotifyChange("productDto")
	public void pick(@BindingParam("checked") boolean isPicked, @BindingParam("picked") ProductDto item)
	{
		if(isPicked)
		{
			pickedItemSet.add(item);
			
		}
		else {
			pickedItemSet.remove(item);
			
		}
		productDto=null;
		if(pickedItemSet.size()>0)
		{
			productDto=(ProductDto) pickedItemSet.toArray()[0];
			
		}
		
	}
	
	@Command
	public void add()
	{
		productDto= new ProductDto();
		Sessions.getCurrent().setAttribute("dto", productDto);
		Executions.sendRedirect("product_detail.zul");
		
	}
	
	@Command()
	public void edit()
	{
		if(productDto==null)
		{
			Messagebox.show("Silahkan pilih data");
			
		}
		else {
			Sessions.getCurrent().setAttribute("dto", productDto);
			Executions.sendRedirect("product_detail.zul");
		}
		
	}
}
