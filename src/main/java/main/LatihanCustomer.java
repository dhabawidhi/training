package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;

import dao.CustomerDao;
import dao.ProductDao;
import entity.Customer;
import entity.CustomerProduct;
import entity.Product;
import entity.ProductPK;

public class LatihanCustomer {
	public static void main(String[] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		System.out.println("Text");
		
		CustomerDao customerDao = ctx.getBean(CustomerDao.class);
	 	ProductDao productDao=ctx.getBean(ProductDao.class);
		
        Customer c = new Customer();
        
        //start
        String ulang = "y";
        Scanner input = new Scanner(System.in);
        
        while (ulang.equalsIgnoreCase("y")) {
            System.out.println("===Pendaftaran Customer===");
            System.out.println("1.Daftar Customer ");
            System.out.println("2.Input Customer  ");
            System.out.println("3.Ubah(Update) Customer");
            System.out.println("4.Hapus(Delete) Customer");
            System.out.println("5.Keluar(Exit)");
            System.out.println("6.Beli product");
            System.out.println("");
            System.out.print("Masukkan Pilihan ");
            int pil = input.nextInt();
            //DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
            if(pil==1)
            {
        	 	Sort sort =new Sort(Sort.Direction.ASC,"custId");
                    List<Customer> list=customerDao.findAll(sort)  ;
                    for(Customer m: list)
                    {
                        System.out.println("ID        " + m.getCustId());
                        System.out.println("Nama      " + m.getCustName());
                        System.out.println("Alamat    " + m.getCustAddress());
                        /*
//                        Date myDate = new Date();
//                        System.out.println();
                        try
                        {
                            System.out.println("tanggalLahir    " + new SimpleDateFormat("yyyy-MM-dd").format(m.getTglLahir()));
                        }
                        catch (Exception e)
                        {
                            System.out.println("tanggalLahir    " );
                        }
                        */
                        System.out.println("");
                    }
            }
            
            else if(pil==2)
            {
            		System.out.println("======Input Customer========");
            		Scanner sc=new Scanner(System.in);
            		
                    System.out.print("Masukkan Nama   :");
                    String name = sc.nextLine();
                    System.out.print("Masukkan Alamat :");
                    String address = sc.nextLine();
                    
                    c.setCustName(name);
                    c.setCustAddress(address);
                    customerDao.save(c);
                    
            }
            
            else if(pil==3)
            {
                    System.out.println("======Ubah Data Customer========");
                    System.out.println("Masukkan Id Customer      :  ");
                    int id2 = input.nextInt();
                    c= customerDao.findOne(id2);
                    
                    Scanner sc=new Scanner(System.in);
                    System.out.println("Nama Customer Sebelumnya : " + c.getCustName());
                    System.out.println("Alamat Customer Sebelumnya : " + c.getCustAddress());
                    
                    System.out.print("Masukkan Nama Baru Customer      :  ");
                    String nama2 = sc.nextLine();
                    System.out.print("Masukkan Alamat Baru  Customer     :  ");
                    String alamat2 = sc.nextLine();
                    
                    c.setCustId(id2);
                    c.setCustName(nama2);
                    c.setCustAddress(alamat2);
                    
                    customerDao.save(c);
                    
            }
            
            else if(pil==4)
            {

                    System.out.println("======Hapus Data Customer========");

                    System.out.println("Masukkan Id Customer      :  ");
                    int id3 = input.nextInt();
                    customerDao.delete(id3);
                    
                    
            }
            
            else if(pil==5) 
            {
                    System.exit(0);
                    
                    
            }
            else if(pil==6)
            {
            	Scanner sc=new Scanner(System.in);
                System.out.println("======Daftar Beli Customer========");

                System.out.println("Masukkan Id Customer      :  ");
                int id3 = input.nextInt();
                List<String> daftarIdProduk = new ArrayList<String>();
                String idProd;
                System.out.println("Masukkan 't' untuk selesai beli");
                do{
					System.out.println("Masukkan id barang:");
					idProd=sc.nextLine();
					daftarIdProduk.add(idProd);
				}while (!idProd.equals("t"));
                CustomerProduct cp=new CustomerProduct();
                cp.setIdCust(id3);
                cp.setDaftarProduk(daftarIdProduk);
                
                Customer c1= customerDao.findOne(id3);
                System.out.println(c1.getCustName());
                System.out.println("Daftar Produk");
                int count=1;
                int sum=0;
                System.out.println("Produk Name \t Harga");
                for(String x:cp.getDaftarProduk())
                {
                	if(x.equals("t")) break;
                	
                	try {
                		ProductPK prodPK=new ProductPK();
                    	prodPK.setProdId(x);
                    	Product px=productDao.findOne(prodPK);
                    	System.out.print(count+" ");
                    	System.out.println(px.getProdName()+"\t\t"+px.getProdPrice());
                    	sum=sum+px.getProdPrice();
                    	
                    	count++;
					} catch (Exception e) {
						// TODO: handle exception
					}
                	
                }
                System.out.println("Total Price= "+sum);
            }
           
            else
                System.out.println("Menu Tidak Ada");
            
            System.out.println("======================");
            System.out.println("Apakah anda ingin kembali ke menu ? (Y=Kembali)");
            ulang = input.next();
            
        }
    }
}
