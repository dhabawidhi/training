package vmd;

import java.util.List;

import org.zkoss.bind.annotation.Init;
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
}
