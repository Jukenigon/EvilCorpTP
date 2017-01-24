package workers;


import utils.Utils;

import java.util.Date;

public class Worker {
    private String task;
    private String action;
    private String name;
    private String begin;
    private String end;
    private String date;

    public Worker(String task, String name, String action, String begin, String end, String date){
        this.task = task;
        this.action = action;
        this.name = name;
        this.begin = begin;
        this.end = end;

        if(date == "-1"){
            this.date = new Date().toString();
        }

        Log();
    }

    private void Log() {
        Utils utils = new Utils();
        String log = task + ", " + action + ", " + name + ", " + begin + ", " + end + ", " + date;
        utils.writeLog(log);
        System.out.println("A new worker was added: ");
        System.out.println(log);
    }
}
