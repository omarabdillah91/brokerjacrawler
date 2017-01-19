/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokerja;

/**
 *
 * @author omaabdillah
 */
public class Vacancy {

    String job_title;
    String company;
    String location;
    String job_url;
    String image_url;
    String specialization;
    String description;
    int salary_category;
    
    public Vacancy(String j_title, String comp, String loc, String j_url, String i_url, String spec, String desc, int salary) {
        this.job_title = j_title;
        this.company = comp;
        this.location = loc;
        this.job_url = j_url;
        this.image_url = i_url;
        this.specialization = spec;
        this.description = desc;
        this.salary_category = salary;
    }
}
