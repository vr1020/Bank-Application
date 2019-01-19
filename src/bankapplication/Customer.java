/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapplication;

/**
 *
 * @author vramakri
 */
public class Customer extends Person
{
    private double balance;
    
    public Customer (String user, String pass)
    {
        this.Role = "customer";
        this.username = user;
        this.password = pass;
        this.balance = 100;
    }
    
    @Override
    public Customer clone() throws CloneNotSupportedException
    {
        if (!this.username.equals("")&& !this.password.equals("") && "customer".equals(this.Role))
        {
            Customer c = new Customer(this.username,this.password);
            c.balance = this.balance;
            c.Role = "customer";
            return c;
        }
        else
        {
            throw new CloneNotSupportedException();
        }
    }
}
