package com.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Tarea {

    //Tareas Programadas
    @Scheduled(fixedRate = 1000)
    public void tarea(){
        System.out.println("Tarea programada");
    }

    //Tarea programada con Cron mas informacion https://riptutorial.com/spring/example/21209/cron-expression
    @Scheduled(cron = "*/5 * * * * *" )
    public void tareaCron(){
        System.out.println("Tarea programada Cron");
    }
}
