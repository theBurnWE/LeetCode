import com.shcepp.shdippsvr.business.util.IDUtil;

public class TheadTest {

    public static void main(String[] args) {
//        RunnableDemo R1 = new RunnableDemo();
//        R1.run();
        try {
            while(true){
                Thread th = null;
                for (int i = 0; i < 20; i++) {
//                    System.out.println(i);
                    th = new Thread(new RunnableDemo());
                    th.start();
                }
                th.sleep(1000);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

//        do{
//            IDUtil iDUtil = new IDUtil();
//            String s = iDUtil.getEasiPySerialNo("MER0000X");
//
//            System.out.println("Creating " + s + "  "+s.length());
//        }while (true);


    }

    static class RunnableDemo implements Runnable {

        int j;
        IDUtil iDUtil = new IDUtil();

        RunnableDemo() {
            this.j = j;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {


                System.out.println("Creating " + iDUtil.getEasiPySerialNo());
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }
}
