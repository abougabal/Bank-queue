
public class Customer implements Comparable<Customer>
{
 int number;
 int vip=0;


public Customer (int n, int vip)
{
	this.number=n;
	this.vip=vip;
}

public int getnum()
{
	return this.number;
}
@Override
public int compareTo(Customer o) { // to sort the array according to the vip field, to serve them first
    if (o.vip > vip){
        return 1;
    }
    if (o.vip < vip){
        return -1;
    }
    return 0;
}

}
