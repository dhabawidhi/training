package vmd;

import java.util.List;

import javassist.runtime.Inner;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import dto.ProductDto;
import service.ProductSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProductVmd {
	@WireVariable
	private ProductSvc productSvc;
	
	private List<ProductDto> productDtos;
	private ProductDto productDto;
	private String search;
	
	
	
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
}
