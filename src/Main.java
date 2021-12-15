
import java.util.*; 

public class Main 
{

	public static void main(String[] args) throws InterruptedException
	{		
		Bank simulation= new Bank();
		setting_parameters(simulation); // to get the number of tellers and customers
		
		
        Thread t1 = new Thread(new Runnable() {    // waiting and producing new customers until the max
            public void run()
            {
                try {
                    simulation.produce_customer();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Vector<Thread> thread_vec= new Vector<Thread>();
        for (int i=0;i<simulation.get_num_of_tellers();i++)
        {
        	Thread t2=new Thread(new Runnable() { // serving existing customers
        	    public void run()
                {
                    try {
                        simulation.consume();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        	},Integer.toString(i));
        	thread_vec.add(t2);
        }
     // Start threads
        t1.start();
        for (int i=0;i<simulation.get_num_of_tellers();i++)
        {
        	thread_vec.get(i).start();
        } 
        // wait for threads to finish then join
        t1.join();
        for (int i=0;i<simulation.get_num_of_tellers();i++)
        {
        	thread_vec.get(i).join();
        } 
	}
	
	
	
	
	public static void setting_parameters(Bank temp)
	{
		int n,m;
		Scanner sc= new Scanner(System.in);    //System.in is a standard input stream  
		System.out.print("Enter Number of tellers:");
		n= sc.nextInt();  
		System.out.print("Enter Max Number of customers;");
		m= sc.nextInt();
		temp.set_n_m(n, m);
	}
	

}

