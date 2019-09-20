package leetCode.subjectRang1To50;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author BrunE
 * @date 2019-09-18 16:07
 **/
public class subject2 {

    private static final Logger logger = LoggerFactory.getLogger(subject2.class);

    //执行用时    内存消耗
    // 2074 ms	39 MB
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int mid;
        if (null == l1) {
            l1 = new ListNode(0);
        }
        if (null == l2) {
            l2 = new ListNode(0);
        }
        mid = l1.val + l2.val;
        ListNode startLn = new ListNode(mid % 10);

        if (null != l1.next || null != l2.next || mid / 10 > 0) {
            addTwoNumbersHepler(l1.next, l2.next, mid / 10, startLn);
        }

        return startLn;

    }

    public static ListNode addTwoNumbersHepler(ListNode l1, ListNode l2, int preVal, ListNode startLn) {
        int mid;
        if (null == l1) {
            l1 = new ListNode(0);
        }
        if (null == l2) {
            l2 = new ListNode(0);
        }
        mid = l1.val + l2.val + preVal;

        ListNode nextLn = new ListNode(mid % 10);
        startLn.next = nextLn;

        if (null != l1.next || null != l2.next || mid / 10 > 0) {
            addTwoNumbersHepler(l1.next, l2.next, mid / 10, nextLn);
        }

        return startLn;
    }

    public static class ListNode {

        public int val;
        public ListNode next;

        public ListNode() {
        }

        ;

        ListNode(int x) {
            val = x;
        }
    }


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
