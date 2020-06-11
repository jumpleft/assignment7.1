package merit.assignment7.models;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import merit.assignment7.models.CDAccount;




@Entity
@Table(name = "cd_offer")
public class CDOffering{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_id")
	private long cdId;
	

	private int term;
	private double interestRate;
	private static CDOffering cdOffering;
	
	@Column(name = "id")
	private long id;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CDAccount> cdAccount;
	
	
	public long getCdId() {
		return cdId;
	}
	public void setCdId(long cdId) {
		this.cdId = cdId;
	}

	public CDOffering() {}
	public CDOffering(int term, double interestRate){

		this.term = term;
		this.interestRate = interestRate;
	}
	public int getTerm(){
		return term;
	}
	public double getInterestRate(){		
		return interestRate;
	}
	
	public List<CDAccount> getCdAccount() {
		return cdAccount;
	}
	public void setCdAccount(List<CDAccount> cdAccount) {
		this.cdAccount = cdAccount;
	}
	public static CDOffering readFromString(String cdOfferingDataString) {
		//Should throw a java.lang.NumberFormatException if String cannot be correctly parsed		
			String[] array = cdOfferingDataString.split(",");			
			int term = Integer.parseInt(array[0]);
			double interest = Double.parseDouble(array[1]);			
			cdOffering = new CDOffering(term, interest);
			return cdOffering;
	}
	public String writeToString() {
		try {
			FileWriter writer = new FileWriter ("CDOffering.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(toString()); 
			bufferedWriter.close();			
		}		
		catch(IOException e) {
			e.printStackTrace();			
		}
		return toString();
	}
	@Override
	public String toString() {
		return term + "," + interestRate + "\n";
	}

}
