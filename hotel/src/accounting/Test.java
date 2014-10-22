/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounting;

import java.util.Date;

/**
 *
 * @author Lasantha Ekanayake <lasantha.12@cse.mrt.ac.lk>
 */
public class Test {

    public static void main(String[] args) {
        Transaction test = new Transaction(new Date(2014, 10, 22), 4500.00, "TestTransaction", true);

        System.out.println(test.Save());
    }
}
