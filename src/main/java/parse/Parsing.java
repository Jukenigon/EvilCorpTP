package parse;

import com.google.gson.*;
import data.DataString;
import org.apache.commons.io.FileUtils;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import workers.Worker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parsing {
    private CmdLineParser parser;
    @Option(name="--file",usage="take a file as data.")
    private boolean file;

    @Argument
    private List<String> arguments = new ArrayList<String>();
    public void doMain(String[] args) throws IOException {
        parser = new CmdLineParser(this);
        parser.setUsageWidth(80);

        try {
            parser.parseArgument(args);
            if( arguments.isEmpty() )
                throw new CmdLineException(parser,"No argument is given");

        } catch( CmdLineException e ) {
            System.err.println(e.getMessage());
            System.err.println(DataString.USAGE_ACTIVITY);
            parser.printUsage(System.err);
            return;
        }
        log();
    }

    private void log() {
        if(!file && !(arguments.get(0).equals("activity") || arguments.get(0).equals("robot"))){
            System.out.println("Nothing to do.");
            parser.printUsage(System.err);
            return;
        }
        System.out.println("Working...");
        if(file && operation())
            System.out.println(DataString.FILE_ADDED);

        if(arguments.get(0).equals("activity") && operation())
            System.out.println(DataString.ACTIVITY_ADDED);

        if(arguments.get(0).equals("robot") && operation())
            System.out.println(DataString.ROBOT_ADDED);

        System.out.println("Finish");
    }

    private boolean operation(){
        if(file){
            if(arguments.size() != 1){
                System.err.println(DataString.USAGE_FILE);
                return false;
            }else{
                String str = null;
                try {
                    str = FileUtils.readFileToString(new File(arguments.get(1)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(str);
                JsonElement jelement = new JsonParser().parse(str);
                JsonObject jobject = jelement.getAsJsonObject();
                String result = jobject.get("translatedText").toString();
                System.out.println(result);
            }
        }

        if(arguments.get(0).equals("activity") || arguments.get(0).equals("robot")){
            if(arguments.size() == 6){
                Worker worker2 = new Worker(arguments.get(1), arguments.get(2), arguments.get(3), arguments.get(4), arguments.get(5), "-1");
            }else if(arguments.size() == 7){
                Worker worker2 = new Worker(arguments.get(1), arguments.get(2), arguments.get(3), arguments.get(4), arguments.get(5), arguments.get(6));
            }else{
                System.err.println(DataString.USAGE_ACTIVITY);
            }
        }

        return false;
    }
}
