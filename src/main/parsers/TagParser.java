package parsers;

import model.DueDate;
import model.Priority;
import model.Status;
import model.Task;
import model.exceptions.EmptyStringException;
import parsers.exceptions.ParsingException;

import java.util.*;

public class TagParser extends Parser {
    public TagParser() {
    }

    @Override
    public void parse(String input, Task task) throws ParsingException {
        String strDescription = getTaskDescription(input);
        this.description = strDescription;
        // set description
        try {
            task.setDescription(strDescription);
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }

        if (!input.contains("##")) {
            throw new ParsingException();
        }

        ArrayList<String> tagsList = extractTags(input);

        tagsList = cleanTags(tagsList);

        tagsList = removeDuplicate(tagsList);


        String myDueDate = null;
        String myStatus = null;
        ArrayList<String> arrPriority = new ArrayList<>();

        ArrayList<String> arrRealTags = new ArrayList<>();

        setTask(task, tagsList, myDueDate, myStatus, arrPriority, arrRealTags);

    }

    private void setTask(Task task, ArrayList<String> tagsList, String myDueDate, String myStatus,
                         ArrayList<String> arrPriority, ArrayList<String> arrRealTags) {
        for (String var :
                tagsList) {
            if (isDueDate(var) && myDueDate == null) {
                myDueDate = var;
            } else if (isStatus(var) && myStatus == null) {
                myStatus = var;
            } else if (isPriority(var)) {
                arrPriority.add(var);
            } else {
                arrRealTags.add(var);
            }

        }

        setPriority(task, arrPriority);
        setDueDate(task, myDueDate);
        setStatus(task, myStatus);
        setTags(task, arrRealTags);
    }

    public ArrayList<String> cleanTags(ArrayList<String> tagsList) {
        ArrayList<String> newList = new ArrayList();
        for (String ele : tagsList) {
            if (ele == null || ele.trim().isEmpty()) {
                //do nothing
            } else {
                newList.add(ele.trim());
            }
        }
        return newList;
    }

    public ArrayList<String> removeDuplicate(ArrayList<String> tagsList) {
        ArrayList<String> newList = new ArrayList();

        TreeSet<String> seen = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        tagsList.removeIf(s -> !seen.add(s));
        return tagsList;

    }

    public String getTaskDescription(String input) {
        if (input.contains("##")) {
            return input.split("##")[0];
        } else {
            // there is no ## sign
            return input;
        }

    }

    // separate input to description and tags
    // return description
    public ArrayList<String> extractTags(String input) {
        ArrayList<String> tagsList;
        if (input.split("##").length >= 2) {
            String[] temp = input.split("##")[1].split(";");
            return tagsList = new ArrayList<String>(Arrays.asList(temp));
        }

        return tagsList = new ArrayList<String>();

    }

    public boolean isPriority(String str) {
        return (str.toLowerCase().equals("important")
                || str.toLowerCase().equals("urgent"));
    }

    public boolean isDueDate(String str) {

        return (str.toLowerCase().equals("today")
                || str.toLowerCase().equals("tomorrow"));
    }

    public boolean isStatus(String str) {
        return (str.toLowerCase().equals("to do")
                || str.toLowerCase().equals("up next")
                || str.toLowerCase().equals("in progress")
                || str.toLowerCase().equals("done"));
    }


    private void setTags(Task task, ArrayList<String> arrTags) {
        if (arrTags.size() == 0) {
            //task.addTag(null);
            // no tag do nothing
        } else {
            for (String t :
                    arrTags) {
                try {
                    task.addTag(t);
                } catch (EmptyStringException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setStatus(Task task, String str) {
        if (str == null || str.isEmpty()) {
            //default
            task.setStatus(Status.TODO);
        } else {
            Status st = matchStatus(str);
            task.setStatus(st);
        }
    }

    private void setDueDate(Task task, String str) {
        if (str == null || str.isEmpty()) {
            task.setDueDate(null);
        } else {
            if (str.toLowerCase().equals("today")) {
                // today
                task.setDueDate(new DueDate(getTodayDate()));
            } else {
                //tomorrow
                task.setDueDate(new DueDate(getTomorrowDate()));
            }

        }
    }

    private void setPriority(Task task, ArrayList<String> arrPriority) {
        Priority p = new Priority();
        if (arrPriority.size() == 0) {
            // no priority setup, default p
            task.setPriority(p);
        } else {

            //set new Priority
            for (int i = 0; i < arrPriority.size(); i++) {
                if (arrPriority.get(i).toLowerCase().equals("important")) {
                    p.setImportant(true);
                } else if (arrPriority.get(i).toLowerCase().equals("urgent")) {
                    p.setUrgent(true);
                }
            }

            task.setPriority(p);
        }
    }

    private Status matchStatus(String s) {
        if (s.toLowerCase().equals("to do")) {
            return Status.TODO;
        } else if (s.toLowerCase().equals("up next")) {
            return Status.UP_NEXT;
        } else if (s.toLowerCase().equals("in progress")) {
            return Status.IN_PROGRESS;
        } else if (s.toLowerCase().equals("done")) {
            return Status.DONE;
        }
        return Status.TODO;
    }

    private Date getTodayDate() {
        // today 23:59:59
        Calendar cal = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();

    }

    private Date getTomorrowDate() {
        // today 23:59:59
        Calendar cal = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));

        cal.add(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }
}
