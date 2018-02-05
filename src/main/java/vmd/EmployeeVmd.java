package vmd;

import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.Button;
import org.zkoss.zul.Messagebox.ClickEvent;

import dto.EmployeeDto;
import dto.ProductDto;
import service.EmployeeSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class EmployeeVmd {
	@WireVariable
	private EmployeeSvc employeeSvc;
	
	private List<EmployeeDto> employeeDtos;
	private EmployeeDto employeeDto;
	
	
	
	public EmployeeSvc getEmployeeSvc() {
		return employeeSvc;
	}

	public void setEmployeeSvc(EmployeeSvc employeeSvc) {
		this.employeeSvc = employeeSvc;
	}

	public List<EmployeeDto> getEmployeeDtos() {
		return employeeDtos;
	}

	public void setEmployeeDtos(List<EmployeeDto> employeeDtos) {
		this.employeeDtos = employeeDtos;
	}

	public EmployeeDto getEmployeeDto() {
		return employeeDto;
	}

	public void setEmployeeDto(EmployeeDto employeeDto) {
		this.employeeDto = employeeDto;
	}

	@Init
	public void load()
	{
		employeeDtos=employeeSvc.findAll();
		
	}
	
	@Command
	public void add()
	{
		employeeDto= new EmployeeDto();
		Sessions.getCurrent().setAttribute("dto", employeeDto);
		Executions.sendRedirect("employee_detail.zul");
		
	}
	
	@Command()
	public void edit()
	{
		if(employeeDto==null)
		{
			Messagebox.show("Silahkan pilih data");
			
		}
		else {
			Sessions.getCurrent().setAttribute("dto", employeeDto);
			Executions.sendRedirect("employee_detail.zul");
		}
		
	}
	
	@Command
	public void delete()
	{
		if(employeeDto!=null)
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
						employeeSvc.delete(employeeDto);
						employeeDtos.remove(employeeDto);
						employeeDto=null;
						BindUtils.postNotifyChange(null, null, EmployeeVmd.this, "employeeDtos");
						Messagebox.show("Data berhasil di hapus");
						
					}
					
					
				}
				
			});
			
		}
		else {
			Messagebox.show("Silahkan pilih data");
		}
		
	}
}
