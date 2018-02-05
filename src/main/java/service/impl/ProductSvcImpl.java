package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ProductDao;
import dto.ProductDto;
import entity.ProductPK;
import entity.Product;
import service.ProductSvc;

@Service("productSvc") // copy nama interface dan buat jadi huruf kecil nama servicenya
@Transactional
public class ProductSvcImpl implements ProductSvc {

	@Autowired 
	private ProductDao productDao;
	
	@Override
	public List<ProductDto> findAll() {
		List<ProductDto> productDtos= new ArrayList<ProductDto>();
		Sort sort =new Sort(Sort.Direction.ASC,"prodId");
		List<Product> products=productDao.findAll(sort);
		for(Product product:products)
		{
			ProductDto productDto=new ProductDto();
			productDto.setExpDate(product.getExpDate());
			productDto.setProdId(product.getProdId());
			productDto.setProdName(product.getProdName());
			productDto.setProdPrice(product.getProdPrice());
			productDtos.add(productDto);
			
		}
		return productDtos;
		
	}

	@Override
	public List<ProductDto> findAll(String search) {
		List<ProductDto> productDtos= new ArrayList<ProductDto>();
		List<Product> products=productDao.findAll("%"+search+"%");
		for(Product product:products)
		{
			ProductDto productDto=new ProductDto();
			productDto.setExpDate(product.getExpDate());
			productDto.setProdId(product.getProdId());
			productDto.setProdName(product.getProdName());
			productDto.setProdPrice(product.getProdPrice());
			productDtos.add(productDto);
			
		}
		return productDtos;
		
	}
	
	@Override
	public void save(ProductDto productDto) {
		// TODO Auto-generated method stub
		Product product=new Product();
		product.setExpDate(productDto.getExpDate());
		product.setProdId(productDto.getProdId());
		product.setProdName(productDto.getProdName());
		product.setProdPrice(productDto.getProdPrice());
		productDao.save(product);
	
	}

	@Override
	public void delete(ProductDto productDto) {
		// TODO Auto-generated method stub
		ProductPK productPK=new ProductPK();
		productPK.setProdId(productDto.getProdId());
		productDao.delete(productPK);
		
	}

	@Override
	public ProductDto findOne(String prodId) {
		ProductDto productDto=new ProductDto();
		ProductPK productPK=new ProductPK();
		productPK.setProdId(prodId);
		Product product=productDao.findOne(productPK);
		if(product!=null)
		{
			productDto.setExpDate(product.getExpDate());
			productDto.setProdId(product.getProdId());
			productDto.setProdName(product.getProdName());
			productDto.setProdPrice(product.getProdPrice());
		}
		return productDto;
	
	}

}
