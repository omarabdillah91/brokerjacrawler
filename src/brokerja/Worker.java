/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokerja;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author omaabdillah
 */
public class Worker {

    static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";

    public static Document getDoc(String root) {

        HttpClient client = new DefaultHttpClient();
        Document doc = null;
        try {
            while (doc == null) {
                HttpGet request = new HttpGet(root);

                request.setHeader("User-Agent", USER_AGENT);
                request.setHeader("Accept",
                        "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                request.setHeader("Accept-Language", "en-US,en;q=0.5");
                HttpResponse response = client.execute(request);
//
//                System.out.println("Response Code : "
//                        + response.getStatusLine().getStatusCode());

                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                doc = Jsoup.parse(result.toString());
            }
        } catch (SocketTimeoutException e) {
            //ERROR TYPE 1
            System.out.println("Socket Timeout Error");
            System.out.println(e);
            System.exit(0);
        } catch (SocketException e) {
            //ERROR TYPE 1
            System.out.println("Internet Connection Error");
            System.out.println(e);
            System.exit(0);
        } catch (Exception e) {
            //ERROR TYPE 2
            System.out.println("JSoup Error");
            System.out.println(e);
            System.exit(0);
        }
        return doc;
    }
}
