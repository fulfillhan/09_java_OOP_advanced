package step9_01.atm_v1;

public class Account {
	
	String number;
	int money;
	
	void printOwnAccount() {
		System.out.println(this.number +  " : " + this.money);
	}
	
}
