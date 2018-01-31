package main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;

import dao.CustomerDao;
import dao.ProductDao;
import entity.Customer;
import entity.Product;
import entity.ProductPK;

public class LatihanProduct {
	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		System.out.println("Text");
		
		ProductDao productDao = ctx.getBean(ProductDao.class);
	 	
        Product c = new Product();
        
        //start
        String ulang = "y";
        Scanner input = new Scanner(System.in);
        
        while (ulang.equalsIgnoreCase("y")) {
            System.out.println("===Pendaftaran Product===");
            System.out.println("1.Daftar Product ");
            System.out.println("2.Input Product  ");
            System.out.println("3.Ubah(Update) Product");
            System.out.println("4.Hapus(Delete) Product");
            System.out.println("5.Keluar(Exit)");
            System.out.println("");
            System.out.print("Masukkan Pilihan ");
            int pil = input.nextInt();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            if(pil==1)
            {
        	 	Sort sort =new Sort(Sort.Direction.ASC,"prodId");
                List<Product> list=productDao.findAll(sort)  ;
                for(Product m: list)
                {
                    System.out.println("ID        " + m.getProdId());
                    System.out.println("Nama      " + m.getProdName());
                    System.out.println("Price    " + m.getProdPrice());
                    
//                        Date myDate = new Date();
//                        System.out.println();
                    try
                    {
                        System.out.println("tanggalLahir    " + new SimpleDateFormat("dd-MM-yyyy").format(m.getExpDate()));
                    }
                    catch (Exception e)
                    {
                        System.out.println("tanggalLahir    " );
                    }
                    
                    System.out.println("");
                }
            }
            
            else if(pil==2)
            {
            		System.out.println("======Input Product========");
            		Scanner sc=new Scanner(System.in);
            		
            		System.out.print("Masukkan ID   :");
                    String id = sc.nextLine();
                    System.out.print("Masukkan Nama   :");
                    String name = sc.nextLine();
                    System.out.print("Masukkan Tanggal Kadaluarsa (dd-MM-yyyy) :");
                    String expDate=sc.nextLine();
                    Date expDate2 = df.parse(expDate);
                    System.out.print("Masukkan Harga :");
                    int price = sc.nextInt();
                    
                    
                    c.setProdId(id);
                    c.setProdName(name);
                    c.setExpDate(expDate2);
                    c.setProdPrice(price);
                    productDao.save(c);
            }
            
            else if(pil==3)
            {
            	Scanner sc=new Scanner(System.in);
                System.out.println("======Ubah Data Product========");
                System.out.println("Masukkan Id Product      :  ");
                String id2 = sc.nextLine();
                ProductPK pk=new ProductPK();
                pk.setProdId(id2);
                c= productDao.findOne(pk);
                
                
                System.out.println("ID Sebelumnya : " + c.getProdId());
                System.out.println("Nama Product Sebelumnya : " + c.getProdName());
                System.out.println("Expired Date Product Sebelumnya : " + df.format(c.getExpDate()) );
                System.out.println("Harga Product Sebelumnya : " + c.getProdPrice());
                
                System.out.print("Masukkan ID Baru  :");
                String id = sc.nextLine();
                System.out.print("Masukkan Nama Bary  :");
                String name = sc.nextLine();
                System.out.print("Masukkan Tanggal Kadaluarsa (dd-MM-yyyy) Baru :");
                String expDate=sc.nextLine();
                Date expDate2 = df.parse(expDate);
                System.out.print("Masukkan Harga Baru :");
                int price = sc.nextInt();
                
                if(id!=id2)
                {
                	productDao.delete(pk);
                	c.setProdId(id);
                    c.setProdName(name);
                    c.setExpDate(expDate2);
                    c.setProdPrice(price);
                    productDao.save(c);
                }
                else 
                {
                	c.setProdId(id);
                    c.setProdName(name);
                    c.setExpDate(expDate2);
                    c.setProdPrice(price);
                    productDao.save(c);	
				}
                
            }
            
            else if(pil==4)
            {
            	Scanner sc=new Scanner(System.in);
                System.out.println("======Hapus Data Product========");

                System.out.println("Masukkan Id Product      :  ");
                String id2 = sc.nextLine();
                ProductPK pk=new ProductPK();
                pk.setProdId(id2);
                productDao.delete(pk);
                    
            }
            
            else if(pil==5) 
            {
                    System.exit(0);
                    
                    
            }
           
            else
                System.out.println("Menu Tidak Ada");
            
            System.out.println("======================");
            System.out.println("Apakah anda ingin kembali ke menu ? (Y=Kembali)");
            ulang = input.next();
            
        }
	}
}
