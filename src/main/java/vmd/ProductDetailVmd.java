package vmd;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import dto.OrderDetailDto;
import dto.ProductDto;
import service.ProductSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProductDetailVmd {
	@WireVariable
	private ProductSvc productSvc;
	
	private ProductDto productDto;

	public ProductSvc getProductSvc() {
		return productSvc;
	}

	public void setProductSvc(ProductSvc productSvc) {
		this.productSvc = productSvc;
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
		productDto=(ProductDto) Sessions.getCurrent().getAttribute("dto");
		
		OrderDetailDto orderDetailDto2=new OrderDetailDto();
		orderDetailDto2=(OrderDetailDto) Sessions.getCurrent().getAttribute("dtoAdd");
	}
	
	@Command()
	public void back()
	{
		Executions.sendRedirect("product.zul");
		
	}
	
	@Command()
	public void save()
	{
		System.out.println(productDto.getExpDate());
		System.out.println(productDto.getProdPrice());
		System.out.println(productDto.getProdName());
		System.out.println(productDto.getProdId());
		productSvc.save(productDto);
		Messagebox.show("Data berhasil disimpan");
		Executions.sendRedirect("product.zul");
	}
}
