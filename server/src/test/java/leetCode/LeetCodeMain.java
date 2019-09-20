package leetCode;

import leetCode.subjectRang1To50.*;

import java.math.BigDecimal;

public class LeetCodeMain {

    public static void main(String[] args) {

        subject2.ListNode l1 = new subject2.ListNode();
        subject2.ListNode l2 = new subject2.ListNode();
        subject2.ListNode l3 = new subject2.ListNode();


        subject2.ListNode n1 = new subject2.ListNode();
        subject2.ListNode n2 = new subject2.ListNode();
        subject2.ListNode n3 = new subject2.ListNode();

        l1.val = 2;
        l1.next=l2;
        l2.val = 4;
        l2.next=l3;
        l3.val = 3;



        n1.val = 5;
        n1.next=n2;
        n2.val = 6;
        n2.next=n3;
        n3.val = 4;
        subject2.addTwoNumbers(l1,n1);


    }
}
