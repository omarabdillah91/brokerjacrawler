/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokerja;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author omaabdillah
 */
public class BrokerjaCrawler {

    static Worker worker = new Worker();
    static int[] salary_category = new int[]{20, 15, 10, 5, 1};
    static String url = "http://www.jobstreet.co.id/id/job-search/job-vacancy.php?key=&location=&specialization=&area=&salary=";
    static ArrayList<Vacancy> vacancies = new ArrayList<Vacancy>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String link = "";
        for (int salary : salary_category) {
            link = url + salary + "%2C000%2C000&ojs=3&src=12";
            int total_vacancy = getTotalVacancy(link);
            int current_vacancy = 0;
            int indeks = 1;
            while(current_vacancy <= total_vacancy) {
                link = "http://www.jobstreet.co.id/id/job-search/job-vacancy.php?area=1&option=1&salary="+
                        salary+"000000&salary-option=0&salary-currency=7&job-source=1%2C64&classified=0&job-posted=0&sort=1&order=0&pg="+
                        indeks+"&src=16&srcr=12&ojs=3";
                Document doc = worker.getDoc(link);
                getAllVacancyonPage(doc);
                current_vacancy+=20;
                indeks++;
                break;
            }
            break;
        }

    }

    private static int getTotalVacancy(String link) {
        int total = 0;
        Document doc = worker.getDoc(link);
        Element div = doc.getElementsByClass("panel-body text-center").get(0);
        Elements span = div.getElementsByTag("span");
        for (Element s : span) {
            if (s.className().equalsIgnoreCase("pull-right pagination-result-count")) {
                String in = s.text();
                String temp = in.substring(in.indexOf("of ") + 3, in.indexOf(" lowongan"));
                total = Integer.parseInt(temp.replaceAll("[^0-9]", ""));
                break;
            }
        }
        return total;
    }

    private static void getAllVacancyonPage(Document doc) {
        Elements list = doc.select("div.panel");
        System.out.println(list.size());
    }

}
