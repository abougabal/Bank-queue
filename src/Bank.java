import java.util.*;
public class Bank /*implements Runnable  */
	{   
	    LinkedList<Customer> list = new LinkedList<>();
	    int n; // number of tellers
	    int m; // numbers of customers
	    int capacity = 0;
	    public void set_n_m(int n, int m)
	    {
	    	this.n=n;
	    	this.m=m;
	    	
	    }
	    public int get_num_of_tellers()
	    {
	    	return n;
	    }
	    public void produce_customer() throws InterruptedException
	    {
	        while (capacity!= m) {
	            synchronized (this)	
	            {
	                // producer thread waits while list
	                // is full

	                System.out.println("new customer arrived:"
	                                   + capacity);

	                // to insert the jobs in the list
	                Scanner sc= new Scanner(System.in);
	                System.out.print("Enter 1 if you are a VIP 0 otherwise\n");
	                int temp_flag= sc.nextInt(); 
	                while( temp_flag!=0 && temp_flag!=1)
	                {
		             System.out.print("error,Enter 1 if you are a VIP 0 otherwise\n");
		             temp_flag= sc.nextInt(); 
	                }
	                Customer Temp_customer= new Customer(capacity,temp_flag);
	                capacity++;
	                list.add(Temp_customer); // to let the vip get served first then the regular clcients 
	                Collections.sort(list);
	                Random rand = new Random();
	                int rand_int1 = rand.nextInt(10)+1; // random number until the next customer arrice
	                // notifies the consumer thread that
	                // now it can start consuming
	                notify();
	                // random number of waiting until the next customer
	                Thread.sleep(rand_int1*1000);
	            }
	            }
	    }

	    // Function called by consumer thread
	   public void consume() throws InterruptedException
	    {
	        while (true) {


	                // retrieve next customer
	                Customer temp;
	                synchronized(this){ // crticial section that need to be protected
		                // consumer thread waits while list
		                // is empty
		                while (list.size() == 0)
		                    wait();
	                 temp=list.removeFirst();
	                }
	                System.out.println("Clinet Number:" +temp.getnum() +" is being servied by teller number: " + Thread.currentThread().getName()+"\n");
	                      

	                // and sleep
	                Random rand = new Random();
	                int rand_int1 = rand.nextInt(10)+1;
	                Thread.sleep(rand_int1*1000);
	                System.out.println("Clinet Number:" +temp.getnum()+" has finished\n");
	                if(capacity== m && list.isEmpty())
	                {
	                return;	
	                }
	            
	        }
	    }
	   /*
		@Override
		public void run() {
			// TODO Auto-generated method stub
			 while (true) {
		            synchronized (this)
		            {
		                // consumer thread waits while list
		                // is empty
		                while (list.size() == 0)
							try {
								wait();
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		                // retrieve next customer
		                Customer temp=list.removeFirst();
		                System.out.println("Clinet Number:" +temp.getnum() +" is being servied by teller number: " + Thread.currentThread().getName()+"\n");
		                      

		                // Wake up producer thread
		                notify();

		                // and sleep
		                Random rand = new Random();
		                int rand_int1 = rand.nextInt(10)+1;
		                try {
							Thread.sleep(rand_int1*1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                System.out.println("Clinet Number:" +temp.getnum()+" has finished\n");
		                
		            }
		        }
		}*/
}