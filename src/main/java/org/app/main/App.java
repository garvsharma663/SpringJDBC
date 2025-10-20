package org.app.main;

import org.app.config.AppConfig;
import org.app.dao.MusicDAO;
import org.app.model.Music;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "org.app")
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MusicDAO dao = context.getBean(MusicDAO.class);
        Music m1= new Music();
        m1.setName("Faded");
        m1.setType("wav");
        Music m2= new Music();
        m2.setName("Hey");
        m2.setType("mp3");
        dao.addMusic(m1);
        dao.addMusic(m2);

        List<Music> allmusic = dao.findAll();
        System.out.println(allmusic);
    }
}
