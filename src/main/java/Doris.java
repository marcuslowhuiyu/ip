import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Doris {
    ArrayList<Task> list = new ArrayList<Task>();

    public void start (){
        String logo = "                                                      \n" +
                "                                                      \n" +
                "    ,---,                                             \n" +
                "  .'  .' `\\                       ,--,                \n" +
                ",---.'     \\    ,---.    __  ,-.,--.'|                \n" +
                "|   |  .`\\  |  '   ,'\\ ,' ,'/ /||  |,      .--.--.    \n" +
                ":   : |  '  | /   /   |'  | |' |`--'_     /  /    '   \n" +
                "|   ' '  ;  :.   ; ,. :|  |   ,',' ,'|   |  :  /`./   \n" +
                "'   | ;  .  |'   | |: :'  :  /  '  | |   |  :  ;_     \n" +
                "|   | :  |  ''   | .; :|  | '   |  | :    \\  \\    `.  \n" +
                "'   : | /  ; |   :    |;  : |   '  : |__   `----.   \\ \n" +
                "|   | '` ,/   \\   \\  / |  , ;   |  | '.'| /  /`--'  / \n" +
                ";   :  .'      `----'   ---'    ;  :    ;'--'.     /  \n" +
                "|   ,.'                         |  ,   /   `--'---'   \n" +
                "'---'                            ---`-'               \n" +
                "                                                      ";
        Scanner sc = new Scanner(System.in);
        System.out.println(logo);
        System.out.println("Eh what you want?");

        while (true) {
            String command = sc.nextLine();
            try {
                if (command.equals("bye")) {
                    System.out.println("Bye you annoying sia don't want talk to you anymore");
                    return;
                } else if (command.equals("list")) {
                    System.out.println("Eh faster go:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                } else if (command.startsWith("mark")) {
                    int eventNum = Integer.parseInt(command.substring(command.length() - 1));
                    list.get(eventNum - 1).mark();
                    save();
                    System.out.println("Huh you sure you do already or not?");
                    System.out.println("Okay la I trust you I trust you");
                    System.out.println("What else you want?");
                } else if (command.startsWith("unmark")) {
                    int eventNum = Integer.parseInt(command.substring(command.length() - 1));
                    list.get(eventNum - 1).unmark();
                    save();
                    System.out.println("Eh don't laze leh go do go do");
                    System.out.println("What else you want?");
                } else if (command.startsWith("todo")) {
                    if (command.length() <= 5) {
                        throw new DorisException("Oi don't anyhow type must enter a task to do leh");
                    }
                    Todo todo = new Todo(command.substring(5));
                    list.add(todo);
                    save();
                    System.out.println("Eh must remember to do this ah:");
                    System.out.println(todo);
                    System.out.println("You have " + list.size() + " tasks leh better hurry up");
                } else if (command.startsWith("deadline")) {
                    if (command.length() <= 9) {
                        throw new DorisException("Oi don't anyhow type must enter a task to do leh");
                    }
                    String[] commands = command.substring(9).split(" /by ");
                    Deadline deadline = new Deadline(commands[0], commands[1]);
                    list.add(deadline);
                    save();
                    System.out.println("Eh this one due soon stop wasting time go do now:");
                    System.out.println(deadline);
                    System.out.println("You have " + list.size() + " tasks leh better hurry up");
                } else if (command.startsWith("event")) {
                    if (command.length() <= 6) {
                        throw new DorisException("Oi don't anyhow type must enter a task to do leh");
                    }
                    String[] commands = command.substring(6).split(" /at ");
                    Event event = new Event(commands[0], commands[1]);
                    list.add(event);
                    save();
                    System.out.println("Oi remember to attend this ah:");
                    System.out.println(event);
                    System.out.println("You have " + list.size() + " tasks leh better hurry up");
                } else if (command.startsWith("delete")) {
                    int deleteNum = Integer.parseInt(command.substring(command.length() - 1)) - 1;
                    System.out.println("Eh you don't want do this just say la:");
                    System.out.println(list.get(deleteNum));
                    list.remove(deleteNum);
                    save();
                    System.out.println("You have " + list.size() + " tasks leh better hurry up");
                } else {
                    throw new DorisException("Eh what are you talking can speak properly or not");
                }
            } catch (DorisException e) {
                System.out.println(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void save() throws IOException {
        String savePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "doris";
        File saveLocation = new File(savePath);
        if (!saveLocation.exists()) {
            saveLocation.mkdir();
            File taskFile = new File(savePath, "doris.txt");
            PrintWriter taskFileWriter = new PrintWriter(new FileWriter(taskFile));
            for (Task task : list) {
                taskFileWriter.write(task.toString() + "\n");
            }
            taskFileWriter.close();
        } else {
            File taskFile = new File(savePath, "doris.txt");
            PrintWriter taskFileWriter = new PrintWriter(new FileWriter(taskFile));
            for (Task task : list) {
                taskFileWriter.write(task.toStringText() + "\n");
            }
            taskFileWriter.close();
        }
    }

    public static void main(String[] args) {
        new Doris().start();
    }
}
