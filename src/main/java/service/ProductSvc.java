package service;

import java.util.List;

import dto.ProductDto;

public interface ProductSvc {
	public List<ProductDto> findAll();
	public List<ProductDto> findAll(String search);
	public void save(ProductDto productDto);
	public void delete(ProductDto productDto);
	public ProductDto findOne(String prodId);
}
