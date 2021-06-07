package com.jsonUsage;

import java.nio.file.Paths;

public class ReadInputArgs {
    public static String PATH_TO_JSON = "C:\\Users\\Lenovo\\IdeaProjects\\xp\\src\\main\\resources\\simplePath.json";

    public void saveInputArgs(String[] args){
        PATH_TO_JSON = args[0];
    }

    public void setDefaultPath() {
        String projectDirectory = System.getProperty("user.dir");
        String os = System.getProperty("os.name");
        String subOS = os.substring(0,7);

        if(subOS.equals("Windows")){
            String jsonAbsolutePath = Paths.get("src/main/resources/simplePath.json").toAbsolutePath().toString();
//            jsonAbsolutePath = jsonAbsolutePath.replace("\\", "\\\\");
//            PATH_TO_JSON = jsonAbsolutePath;
            PATH_TO_JSON = "C:\\Users\\Lenovo\\IdeaProjects\\xp\\src\\main\\resources\\simplePath.json";
        }else{
            PATH_TO_JSON = projectDirectory + "/src/main/resources/simplePath.json";
        }
    }

}
