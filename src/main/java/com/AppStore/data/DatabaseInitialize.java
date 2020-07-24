package com.AppStore.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.AppStore.domain.AppCategory;
import com.AppStore.domain.Application;
import com.AppStore.utils.Utils;

public class DatabaseInitialize {

    private List<Application> getAppList() {
        List<Application> allApps = new ArrayList<>();
        allApps.add(new Application(1, "Crocin", "Crocin Pain Relief provides targeted pain relief.", AppCategory.TABLETS, 100, 125d, "images/crocin.jpg", 2018d));
        allApps.add(new Application(2, "Ibuprofen", "Sinus headache ?", AppCategory.TABLETS, 100, 125d, "images/Ibuprofen.jpg", 2018d));
        allApps.add(new Application(3, "Cymbalta", "Cough 'n' cold now no more", AppCategory.SYRUPS, 100, 125d, "images/cymbalta.jpg", 2018d));
        allApps.add(new Application(4, "Tramadol", "Diabetes", AppCategory.INJECTION, 100, 125d, "images/tramadol.jpg", 2018d));
        allApps.add(new Application(5, "Cipro", "Cold syrup", AppCategory.SYRUPS, 100, 125d, "images/Cipro.jpg", 2018d));
        allApps.add(new Application(6,"Hydrocodone","Hydrocodone is used to relieve severe pain.",AppCategory.SYRUPS,100000,3.6d,"images/Hydrocodone.jpg",2018d));
        allApps.add(new Application(7, "Aspirin", "Aspirin is a salicylate (sa-LIS-il-ate). It works by reducing substances in the body that cause pain, fever, and inflammation.", AppCategory.TABLETS, 100, 5.0d, "images/Aspirin.jpg", 2018d));
        allApps.add(new Application(8, "Azathioprine", "Azathioprine is also used to treat symptoms of rheumatoid arthritis.", AppCategory.TABLETS, 100, 125d, "images/Azathioprine.png", 2018d));
        allApps.add(new Application(9, "Glucagon", "Glucagon for Injection is indicated for the treatment of severe hypoglycemia in pediatric and adult patients with diabetes.", AppCategory.INJECTION, 100, 125d, "images/Glucagon.jpg", 2018d));
        allApps.add(new Application(10, "Xanax", "Xanax is a prescription medicine used to treat anxiety disorders and anxiety caused by depression.", AppCategory.TABLETS, 100, 125d, "images/Xanax.jpg", 2018d));
        allApps.add(new Application(11, "Valtrex", "Valtrex is used to treat infections caused by herpes viruses, including genital herpes, cold sores, and shingles (herpes zoster) in adults.", AppCategory.SYRUPS, 100, 125d, "images/Valtrex.jpg", 2018d));
        allApps.add(new Application(12, "Lexapro", "Lexapro is used to treat anxiety in adults.", AppCategory.TABLETS, 100, 1.2d, "images/Lexapro.jpg", 2018d));
        allApps.add(new Application(13, "Gabapentin", "Gabapentin is used together with other medicines to treat partial seizures in adults and children at least 3 years old.", AppCategory.INJECTION, 100, 125d, "images/Gabapentin.png", 2018d));
        allApps.add(new Application(14, "Ciprofloxacin", "It is used to treat different types of bacterial infections", AppCategory.SYRUPS, 100, 125d, "images/Ciprofloxacin.jpg", 2018d));
        allApps.add(new Application(15, "Ranitidine", "Ranitidine has been used to treat and prevent ulcers in the stomach and intestines. ", AppCategory.TABLETS, 100, 125d, "images/Ranitidine.jpg", 2018d));
        allApps.add(new Application(16, "Tizanidine", "Tizanidine is a short-acting muscle relaxer. ", AppCategory.INJECTION, 100, 125d, "images/Tizanidine.jpg", 2018d));
        allApps.add(new Application(17, "Naproxen", "Naproxen is used to treat pain or inflammation caused by conditions such as arthritis, ankylosing spondylitis, tendinitis.", AppCategory.INJECTION, 100, 125d, "images/Naproxen.jpg", 2018d));
        allApps.add(new Application(18, "Ativan", "Lorazepam is used to treat anxiety disorders and seizure disorders.", AppCategory.TABLETS, 100, 125d, "images/Ativan.jpg", 2018d));
        allApps.add(new Application(19, "Codeine", "Codeine is used to treat mild to moderately severe pain.", AppCategory.INJECTION, 100, 125d, "images/Codeine.jpg", 2018d));
        allApps.add(new Application(20, "Lyrica", "Lyrica is used to treat pain caused by fibromyalgia, or nerve pain in people with diabetes.", AppCategory.INJECTION, 100, 125d, "images/Lyrica.jpg", 2018d));
        allApps.add(new Application(21, "Amlodipine", "Amlodipine is used to treat chest pain (angina) and other conditions caused by coronary artery disease.", AppCategory.TABLETS, 100, 125d, "images/Amlodipine.png", 2018d));
        allApps.add(new Application(22, "Viagra", "Viagra is used to treat erectile dysfunction (impotence) in men.", AppCategory.TABLETS, 100, 125d, "images/Viagra.jpg", 2018d));
        allApps.add(new Application(23, "Adderall", "Adderall is used to treat attention deficit hyperactivity disorder (ADHD) and narcolepsy.", AppCategory.INJECTION, 100, 125d, "images/Adderall.jpg", 2018d));
        allApps.add(new Application(24, "Meropenem", "Meropenem is used to treat severe infections of the skin or stomach.", AppCategory.SYRUPS, 100, 125d, "images/Meropenem.jpg", 2018d));
        allApps.add(new Application(25, "Metolazone", "Metolazone is used to treat fluid retention (edema) in people with congestive heart failure", AppCategory.TABLETS, 100, 125d, "images/Metolazone.jpeg", 2018d));
        allApps.add(new Application(26, "Metoprolol", "Metoprolol is used to treat angina (chest pain) and hypertension (high blood pressure).", AppCategory.SYRUPS, 100, 125d, "images/Metoprolol.jpg", 2018d));
        allApps.add(new Application(27, "Morphine", "Morphine is used to treat moderate to severe pain.", AppCategory.INJECTION, 100, 125d, "images/Morphine.jpg", 2018d));
        allApps.add(new Application(28, "Mupirocin", "Indicated for the topical treatment of impetigo ", AppCategory.SYRUPS, 100, 125d, "images/Mupirocin.png", 2018d));
        allApps.add(new Application(29, "Ritalin", "A central nervous system stimulant.", AppCategory.INJECTION, 100, 125d, "images/Ritalin.jpg", 2018d));
        allApps.add(new Application(30, "Robaxin", "Robaxin (methocarbamol) is a muscle relaxer.", AppCategory.INJECTION, 100, 125d, "images/Robaxin.jpg", 2018d));
        return allApps;
    }

    public void initializeDatabase() {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/awp", "root", Utils.SQL_PASSWORD);) {

            try (PreparedStatement prepStm = conn.prepareStatement("DROP TABLE IF EXISTS apps;")) {
                prepStm.execute();
            }

            try (PreparedStatement prepStm = conn.prepareStatement("DROP TABLE IF EXISTS downloads;")) {
                prepStm.execute();
            }

            try (PreparedStatement prepStm = conn.prepareStatement("DROP TABLE IF EXISTS mysubscriptiondb;")) {
                prepStm.execute();
            }

            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE apps (id int primary key, name varchar(30), description varchar(150), category varchar(30), downloads int,rating double,logo varchar(200),version double);")) {
                prepStm.execute();
            }
            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS downloads (userName varchar(30), contents TEXT);")) {
                prepStm.execute();
            }
            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS customerData (Name varchar(30), Email varchar(30), Telephone varchar(20),Password varchar(25));")) {
                prepStm.execute();
            }
            try (PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE mysubscriptiondb (id int,username varchar(30),status varchar(30));")) {
                prepStm.execute();
            }
            List<Application> itemsList = getAppList();
            for (Application app : itemsList) {
                try (PreparedStatement prepStm = conn.prepareStatement("INSERT INTO apps (id, name, description, category, downloads,rating,logo,version) values (?,?,?,?,?,?,?,?);");) {
                    prepStm.setInt(1, app.getId());
                    prepStm.setString(2, app.getName());
                    prepStm.setString(3, app.getDescription());
                    prepStm.setString(4, app.getCategory().toString());
                    prepStm.setInt(5, app.getNumDownloads());
                    prepStm.setDouble(6, app.getRating());
                    prepStm.setString(7, app.getLogo());
                    prepStm.setDouble(8, app.getVersion());
                    prepStm.execute();
                }
            }

            
        } catch (SQLException e) {
        }
    }
    
    public static void main(String args[])
    {
        DatabaseInitialize db=new DatabaseInitialize();
        db.initializeDatabase();
    }
}